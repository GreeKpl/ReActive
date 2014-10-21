package pl.edu.agh.inz.reactive.games.three;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by alek on 21.10.14.
 */
public class PatternImageView extends ImageView {
    private int resource;

    public PatternImageView(Context context) {
        super(context);
    }

    public PatternImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PatternImageView(Context context, AttributeSet attrs, int defStyle) {
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
