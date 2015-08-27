package pl.edu.agh.inz.reactive.games.fit;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.DragEvent;
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

    public ChunkImage(Context context, Bitmap bitmap, int topPosition, int leftPosition, int measurementErrorOfPosition, int measurementErrorOfRotation) {
        super(context);
        this.leftPosition = leftPosition;
        this.topPosition = topPosition;
        this.measurementErrorOfPosition = measurementErrorOfPosition;
        this.measurementErrorOfRotation = measurementErrorOfRotation;
        setImageBitmap(bitmap);
        setScaleType(ScaleType.MATRIX);
        setOnTouchListener(new MultiTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //super.onTouch(v, event);

                // POCZATEK NOWEGO KODU
                float x;
                float y;
                float dx = 0;
                float dy = 0;

                bringToFront();

                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        x = event.getX();
                        y = event.getY();
                        dx = x - ChunkImage.this.getX();
                        dy = y - ChunkImage.this.getY();
                    break;
                    case MotionEvent.ACTION_MOVE:
                        ChunkImage.this.setX(event.getX() - dx);
                        ChunkImage.this.setY(event.getY() - dy);
                    break;
                    case MotionEvent.ACTION_UP:
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
            }
        });

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
