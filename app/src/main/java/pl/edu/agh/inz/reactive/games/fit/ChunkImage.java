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
    private float initialRotation = 0f;
    private int sumRotChange = 0;
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
                        initialTouchPos.set(event.getX(), event.getY());
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
                        initialRotation = ChunkImage.this.getRotation();
                        sumRotChange = 0;
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:
                        mode = NONE;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (mode == DRAG) {
                            float dx = event.getX() - initialTouchPos.x;
                            float dy = event.getY() - initialTouchPos.y;

                            float finalDx = (dx + prevDx) / 2;
                            float finalDy = (dy + prevDy) / 2;
                            prevDx = dx;
                            prevDy = dy;

                            ChunkImage.this.setX(initialImagePos.x + finalDx);
                            ChunkImage.this.setY(initialImagePos.y + finalDy);
                        } else if (mode == ROT) {
                            float rotationChange = getRotation(event);
                            if (rotationChange > 10) {
                                rotationChange = 10;
                            } else if (rotationChange < -10) {
                                rotationChange = -10;
                            }
                            sumRotChange += rotationChange;
                            float finalRot = initialRotation + sumRotChange;

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
        double delta_x = (event.getX(0) - event.getX(1));
        double delta_y = (event.getY(0) - event.getY(1));
        double radians = Math.atan2(delta_y, delta_x);
        return (float) Math.toDegrees(radians);
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
