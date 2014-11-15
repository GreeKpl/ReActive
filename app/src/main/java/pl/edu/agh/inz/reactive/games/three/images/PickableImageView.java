package pl.edu.agh.inz.reactive.games.three.images;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by alek on 21.10.14.
 */
public class PickableImageView extends ImageView {
    private int resource;

    public PickableImageView(Context context) {
        super(context);
    }

    public PickableImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PickableImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setImageResource(int resource) {
        this.resource = resource;

        super.setImageResource(resource);
    }

    public int getResource() {
        return resource;
    }
}

// TODO! PickableImageView and pl.edu.agh.inz.reactive.games.three.images.PatternImageView should have abstract superclass