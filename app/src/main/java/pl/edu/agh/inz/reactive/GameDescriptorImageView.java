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

    private Class<? extends GameActivity> gameClass;

    public GameDescriptorImageView(Context context) {
        super(context);
    }

    public GameDescriptorImageView(Context context, Class<? extends GameActivity> gameClass) {
        super(context);
        this.gameClass = gameClass;
    }

    public void setGameClass(Class<? extends GameActivity> gameClass) {
        this.gameClass = gameClass;
    }

    public Class<? extends GameActivity> getGameClass() {
        return gameClass;
    }
}

