package pl.edu.agh.inz.reactive.games;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.edu.agh.inz.reactive.GameDescriptor;
import pl.edu.agh.inz.reactive.R;
import pl.edu.agh.inz.reactive.games.rainbow.RainbowActivity;
import pl.edu.agh.inz.reactive.games.three.ThreeActivity;

/**
 * Singleton with all available games
 */
public class GameRegistry {

    private static GameRegistry instance;

    private final List<GameDescriptor> games;

    // ADD NEW GAME IDS TO THE LIST
    public static final int RAINBOW_GAME = 1;
    public static final int RAINBOW_GAME_TRAINING = 2;
    public static final int THREE_GAME = 3;
    public static final int THREE_GAME_TRAINING = 4;

    // GAME IDS LIST END

    private GameRegistry() {
        ArrayList<GameDescriptor> games = new ArrayList<GameDescriptor>();

        // HERE ADD NEW GAMES TO THE LIST
        games.add(new GameDescriptor(RAINBOW_GAME, "Rainbow", RainbowActivity.class, true, R.drawable.promo_rainbow_time));
        games.add(new GameDescriptor(RAINBOW_GAME_TRAINING, "Rainbow Training", RainbowActivity.class, false, R.drawable.promo_rainbow));
        games.add(new GameDescriptor(THREE_GAME, "Three", ThreeActivity.class, true, R.drawable.promo_three_time));
        games.add(new GameDescriptor(THREE_GAME_TRAINING, "Three Training", ThreeActivity.class, false, R.drawable.promo_three));


        // GAMES LIST END

        this.games = Collections.unmodifiableList(games);
    }

    public static GameRegistry getInstance() {
        if (instance == null) {
            instance = new GameRegistry();
        }
        return instance;
    }

    public List<GameDescriptor> getGames() {
        return games;
    }

}
