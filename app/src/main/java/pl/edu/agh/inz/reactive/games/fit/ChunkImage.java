package pl.edu.agh.inz.reactive.games.fit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by jacek on 17.08.15.
 */
public class ChunkImage extends ImageView{

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

    public ChunkImage(Context context, Bitmap bitmap, int topPosition, int leftPosition, int measurementErrorOfPosition, int measurementErrorOfRotation) {
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
                        start.set(event.getX(), event.getY());
                        dx = event.getX(0) - ChunkImage.this.getX();
                        dy = event.getY(0) - ChunkImage.this.getY();
                        mode = DRAG;
                        lastEvent = null;
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        oldDist = spacing(event);
                        if (oldDist > 1f) {
                            midPoint(mid, event);
                            mode = ROT;
                        }
                        d = rotation(event);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:
                        mode = NONE;
                        lastEvent = null;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (mode == DRAG) {
                            ChunkImage.this.setX(event.getX(0) - dx);
                            ChunkImage.this.setY(event.getY(0) - dy);
                        } else if (mode == ROT) {
                            ChunkImage.this.setRotation(rotation(event) - d);
                        }
                        break;
                }
                if (isOnPosition()) {
                    setPerfectPosition();
                    //addScore
                } else {
                    //cutScore
                }

                return true;
            }

            /*public boolean onTouch(View v, MotionEvent event) {
                //super.onTouch(v, event);

                // POCZATEK NOWEGO KODU
                float x;
                float y;
                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        bringToFront();
                        x = event.getX();
                        y = event.getY();
                        dx = x - ChunkImage.this.getX();
                        dy = y - ChunkImage.this.getY();
                        System.out.println("down dx: "+dx+" dy: "+dy);
                    break;
                    case MotionEvent.ACTION_MOVE:
                        System.out.println("move dx: "+dx+" dy: "+dy);

                        ChunkImage.this.setX(event.getX() - dx);
                        ChunkImage.this.setY(event.getY() - dy);
                    break;
                    case MotionEvent.ACTION_UP:
                        System.out.println("up");
                        //your stuff
                    break;
                    default:
                        super.onTouch(v, event);
                }
                // KONIEC NOWEGO KODU


                if (isOnPosition()) {
                    setPerfectPosition();
                }
                return true;
            }*/
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

    private float rotation(MotionEvent event) {
        double delta_x = (event.getX(0) - event.getX(1));
        double delta_y = (event.getY(0) - event.getY(1));
        double radians = Math.atan2(delta_y, delta_x);
        return (float) Math.toDegrees(radians);
    }

    public boolean isOnPosition() {
        if ((Math.abs(leftPosition-getX()) < measurementErrorOfPosition) &&
                (Math.abs(topPosition-getY()) < measurementErrorOfPosition) &&
                (Math.abs(getRotation()) < measurementErrorOfRotation)) {
            return true;
        }
        return false;
    }

    public void setPerfectPosition() {
        setX(leftPosition);
        setY(topPosition);
        setRotation(0);
    }

}
