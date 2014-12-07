package pl.edu.agh.inz.reactive;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import pl.edu.agh.inz.reactive.games.AbstractGame;
import pl.edu.agh.inz.reactive.games.GameActivity;

/**
 * Created by alek on 24.09.14.
 */
public class GameDescriptorImageView extends ImageView {

    private GameDescriptor descriptor;
    public GameDescriptorImageView(Context context) {
        super(context);
    }

    public GameDescriptorImageView(Context context, GameDescriptor descriptor) {
        super(context);
        this.descriptor = descriptor;
        setImageResource(descriptor.getImg());
    }

    public GameDescriptor getGameDescriptor() {
        return descriptor;
    }
}

