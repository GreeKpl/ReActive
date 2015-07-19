package pl.edu.agh.inz.reactive.games.fit;

import pl.edu.agh.inz.reactive.games.AbstractGame;
import pl.edu.agh.inz.reactive.games.GameActivity;
import pl.edu.agh.inz.reactive.games.GameLevel;
import pl.edu.agh.inz.reactive.games.GameRegistry;
import pl.edu.agh.inz.reactive.games.GameSpecification;
import pl.edu.agh.inz.reactive.games.finish.criteria.FinishCriteriaFactory;

/**
 * Created by Jacek on 2015-07-19.
 */
public class FitGame extends AbstractGame {

    private FitSpecification specyfication = new FitSpecification();

    public FitGame(GameActivity context, FinishCriteriaFactory factory) {
        super(GameRegistry.FIT_GAME, factory, context);
    }

    @Override
    public Level getLevelDescription(int level) {
        return (Level) super.getLevelDescription(level);
    }

    @Override
    public GameSpecification getSpecification() {
        return specyfication;
    }

    public static class Level implements GameLevel {

        @Override
        public String getPreparationText() {
            return "";
        }

        @Override
        public int getPreparationImgResource() {
            return 0;
        }

        @Override
        public String getDesc() {
            return null;
        }

        @Override
        public int getScoreNeeded() {
            return 1;
        }

        @Override
        public int getSeconds() {
            return 0;
        }
    }
}
