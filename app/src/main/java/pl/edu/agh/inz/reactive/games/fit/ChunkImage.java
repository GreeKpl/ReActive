package pl.edu.agh.inz.reactive.games.fit;

import android.graphics.Bitmap;
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
    private PointF initialTouchPos = new PointF();
    private float oldDist = 1f;
    private float initialFingersRotation = 0f;
    private float initialImageRotation = 0f;
    private PointF initialImagePos = new PointF();

    private ChunkMatchingChangeListener chunkMatchingListener;
    private float prevDx = 0;
    private float prevDy = 0;


    public ChunkImage(FitActivity context, Bitmap bitmap, int topPosition, int leftPosition, int measurementErrorOfPosition, int measurementErrorOfRotation, int chunkId) {
        super(context);
        this.leftPosition = leftPosition;
        this.topPosition = topPosition;
        this.measurementErrorOfPosition = measurementErrorOfPosition;
        this.measurementErrorOfRotation = measurementErrorOfRotation;
        setImageBitmap(bitmap);
        setScaleType(ScaleType.MATRIX);
        setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                bringToFront();
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        initialTouchPos = getAbsolutePosition(event, 0);
                        initialImagePos.set(ChunkImage.this.getX(), ChunkImage.this.getY());
                        mode = DRAG;
                        prevDx = 0;
                        prevDy = 0;
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        oldDist = spacing(event);
                        if (oldDist > 1f) {
                            mode = ROT;
                        }
                        initialFingersRotation = getRotation(event);
                        initialImageRotation = ChunkImage.this.getRotation();
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:
                        mode = NONE;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (mode == DRAG) {
                            float dx = getAbsolutePosition(event, 0).x - initialTouchPos.x;
                            float dy = getAbsolutePosition(event, 0).y - initialTouchPos.y;

                            ChunkImage.this.setX(initialImagePos.x + dx);
                            ChunkImage.this.setY(initialImagePos.y + dy);

                        } else if (mode == ROT) {
                            float finalRot = initialImageRotation +
                                    (getRotation(event) - initialFingersRotation);
                            ChunkImage.this.setRotation(finalRot);
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

    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return FloatMath.sqrt(x * x + y * y);
    }

    private float getRotation(MotionEvent event) {
        double delta_x = getAbsolutePosition(event, 0).x - getAbsolutePosition(event, 1).x;
        double delta_y = getAbsolutePosition(event, 0).y - getAbsolutePosition(event, 1).y;
        double radians = Math.atan2(delta_y, delta_x);
        return (float) Math.toDegrees(radians);
    }

    private PointF getAbsolutePosition(MotionEvent ev, int index) {
        final int location[] = {0, 0};
        getLocationOnScreen(location);

        float x = ev.getX(index);
        float y = ev.getY(index);

        double angle = Math.toDegrees(Math.atan2(y, x));
        angle += getRotation();

        final float length = PointF.length(x, y);

        x = (float) (length * Math.cos(Math.toRadians(angle))) + location[0];
        y = (float) (length * Math.sin(Math.toRadians(angle))) + location[1];

        return new PointF(x, y);
    }

    public boolean isOnPosition() {
        return (Math.abs(leftPosition - getX()) < measurementErrorOfPosition) &&
                (Math.abs(topPosition - getY()) < measurementErrorOfPosition) &&
                (Math.abs(normalizeDegree(getRotation())) < measurementErrorOfRotation);
    }

    private float normalizeDegree(float degree) {
        while (degree > 180) {
            degree -= 360;
        }
        while (degree < -180) {
            degree += 360;
        }
        return degree;
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
