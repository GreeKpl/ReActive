package pl.edu.agh.inz.reactive.games.fit;

import pl.edu.agh.inz.reactive.R;
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
        super(factory.isTimeBased() ? GameRegistry.FIT_GAME : GameRegistry.FIT_GAME_TRAINING, factory, context);
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

        private final int seconds;
        private final int rows;
        private final int columns;
        private final int image;
        private final int scoreNeeded;
        private final String desc;

        public Level(Builder builder) {
            this.scoreNeeded = builder.scoreNeeded;
            this.image = builder.image;
            seconds = builder.seconds;
            this.desc = builder.desc;
            this.columns = builder.columns;
            this.rows = builder.rows;
        }

        @Override
        public String getPreparationText() {
            return "Używając dwóch palców ułóż obrazek";
        }

        @Override
        public int getPreparationImgResource() {
            return R.drawable.fit_double_tap;
        }

        @Override
        public String getDesc() {
            return desc;
        }

        @Override
        public int getScoreNeeded() {
            return scoreNeeded;
        }

        @Override
        public int getSeconds() {
            return seconds;
        }

        public int getRows() {
            return rows;
        }

        public int getColumns() {
            return columns;
        }

        public int getImage() {
            return image;
        }

        public static class Builder {
            private final int seconds;
            private final int rows;
            private final int columns;
            private int image;
            private final int scoreNeeded;
            private String desc = "";

            public Builder(int rows, int columns, int scoreNeeded, int seconds) {
                this.rows = rows;
                this.columns = columns;
                this.scoreNeeded = scoreNeeded;
                this.seconds = seconds;
            }

            public Builder image(int image) {
                this.image = image;
                return this;
            }

            public Builder desc(String desc) {
                this.desc = desc;
                return this;
            }

            public Level build() {
                return new Level(Builder.this);
            }
        }
    }
}
