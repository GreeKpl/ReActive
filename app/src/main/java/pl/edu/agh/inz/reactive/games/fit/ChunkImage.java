package pl.edu.agh.inz.reactive.games.fit;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class ChunkImage extends ImageView {

    int leftPosition;

    int topPosition;

    int measurementErrorOfPosition;

    int measurementErrorOfRotation;

    private static final int NONE = 0;
    private static final int DRAG = 1;
    private static final int ROT = 2;
    private int mode = NONE;
    // remember some things for zooming
    private PointF start = new PointF();
    private PointF mid = new PointF();
    private float oldDist = 1f;
    private float dx = 0f;
    private float dy = 0f;
    private float d = 0f;
    private float newRot = 0f;
    private float[] lastEvent = null;
    private double startAngle;
    private ChunkMatchingChangeListener chunkMatchingListener;
    private View copy;



    public ChunkImage(FitActivity context, Bitmap bitmap, int topPosition, int leftPosition, int measurementErrorOfPosition, int measurementErrorOfRotation, int chunkId) {
        super(context);
        this.leftPosition = leftPosition;
        this.topPosition = topPosition;
        this.measurementErrorOfPosition = measurementErrorOfPosition;
        this.measurementErrorOfRotation = measurementErrorOfRotation;
        setImageBitmap(bitmap);
        setScaleType(ScaleType.MATRIX);
        setOnTouchListener(new OnTouchListener() {
            private Matrix matrix;
            private Matrix savedMatrix;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                bringToFront();
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        start.set(event.getX(), event.getY());
                        mode = DRAG;
                        lastEvent = null;
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        oldDist = spacing(event);
                        if (oldDist > 1f) {
                            midPoint(mid, event);
                            mode = ROT;
                        }
                        d = getRotation(event);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:
                        mode = NONE;
                        lastEvent = null;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (mode == DRAG) {
                            float dx = event.getX() - start.x;
                            float dy = event.getY() - start.y;
                            ChunkImage.this.setX(ChunkImage.this.getX() + dx);
                            ChunkImage.this.setY(ChunkImage.this.getY() + dy);
                        } else if (mode == ROT) {
                            float newRot = getRotation(event);
                            ChunkImage.this.setRotation(newRot - d);
//                            d = newRot;
                        }
                        break;
                }

                if (isOnPosition()) {
                    setPerfectPosition();
                    chunkMatchingListener.onChanged(true);
                } else {
                    chunkMatchingListener.onChanged(false);
                }

                return true;
            }

        });

    }

    private void midPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }

    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return FloatMath.sqrt(x * x + y * y);
    }

    private float getRotation(MotionEvent event) {
        double delta_x = (event.getX(0) - event.getX(1));
        double delta_y = (event.getY(0) - event.getY(1));
        double radians = Math.atan2(delta_y, delta_x);
        return (float) Math.toDegrees(radians);
    }

    public boolean isOnPosition() {
        return (Math.abs(leftPosition - getX()) < measurementErrorOfPosition) &&
                (Math.abs(topPosition - getY()) < measurementErrorOfPosition) &&
                (Math.abs(getRotation()) < measurementErrorOfRotation);
    }

    public void setPerfectPosition() {
        setX(leftPosition);
        setY(topPosition);
        setRotation(0);
    }

    public void onToggleFinalPosition(ChunkMatchingChangeListener listener) {
        this.chunkMatchingListener = listener;
    }

    public interface ChunkMatchingChangeListener {
        void onChanged(boolean correctPosition);
    }
}
