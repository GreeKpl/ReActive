package pl.edu.agh.inz.reactive.games.fit;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by jacek on 17.08.15.
 */
public class ChunkImage extends ImageView{


    int leftDifference;

    int topDifference;

    int rotation;

    public ChunkImage(Context context, Bitmap bitmap, int topDifference, int leftDifference, int rotation) {
        super(context);
        this.leftDifference = leftDifference;
        this.topDifference = topDifference;
        this.rotation = rotation;
        setImageBitmap(bitmap);
        mix();
    }

    private void mix() {
        setRotation(rotation);
    }
}
