package pl.edu.agh.inz.reactive.games.fit;

import pl.edu.agh.inz.reactive.games.GameLevel;
import pl.edu.agh.inz.reactive.games.GameSpecification;

/**
 * Created by Jacek on 2015-07-19.
 */
public class FitSpecification implements GameSpecification {

    @Override
    public GameLevel[] getLevels() {
        return new GameLevel[] {
                new FitGame.Level()
        };
    }

    @Override
    public int getMaxLevel() {
        return 0;
    }
}
