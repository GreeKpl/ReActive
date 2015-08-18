package pl.edu.agh.inz.reactive.games.fit;

import android.content.Context;
import android.graphics.Bitmap;
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
        setOnTouchListener(new MultiTouchListener());
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
