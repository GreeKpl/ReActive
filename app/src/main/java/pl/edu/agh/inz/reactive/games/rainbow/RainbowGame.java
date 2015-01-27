package pl.edu.agh.inz.reactive.games.rainbow;

import android.content.Context;
import android.view.View;

import pl.edu.agh.inz.reactive.games.AbstractGame;
import pl.edu.agh.inz.reactive.games.GameActivity;
import pl.edu.agh.inz.reactive.games.GameLevel;
import pl.edu.agh.inz.reactive.games.GameRegistry;
import pl.edu.agh.inz.reactive.games.GameSpecification;
import pl.edu.agh.inz.reactive.games.finish.criteria.FinishCriteriaFactory;
import pl.edu.agh.inz.reactive.games.rainbow.images.TargetImageView;

/**
 * Created by alek on 25.08.14.
 */
public class RainbowGame extends AbstractGame {

    private RainbowSpecification specification = new RainbowSpecification();

    public RainbowGame(GameActivity context, FinishCriteriaFactory factory) {
        super(factory.isTimeBased() ? GameRegistry.RAINBOW_GAME: GameRegistry.RAINBOW_GAME_TRAINING, factory, context);
    }

    public void onObjectClick(View v) {
        if (v instanceof TargetImageView) {
            setScore(getScore() + 1);
        } else {
            setScore(getScore() - 1);
        }
        System.out.println("NAJLEPSZY WYNIK: " + db.getPointsFromLevel(user.getLogin(), GameRegistry.RAINBOW_GAME, getLevel()));
    }

    @Override
    public Level getLevelDescription(int levelId) {
        return (Level) super.getLevelDescription(levelId);
    }

    @Override
    public GameSpecification getSpecification() {
        return specification;
    }

    public static class Level implements GameLevel {
        private final int seconds;

        private final int targets;
        private final double targetSize;
        private final int targetImg;

        private final int otherObjects;
        private final double otherObjectsSize;
        private final int otherImg;
        private final int backgroundImg;
        private final int scoreNeeded;
        private final int secondsUntilMove;
        private final String desc;

        public Level(Builder builder) {
            this.scoreNeeded = builder.scoreNeeded;
            this.seconds = builder.seconds;

            this.targets = builder.targets;
            this.targetSize = builder.targetSize;
            this.secondsUntilMove = builder.secondsUntilMove;
            this.otherObjects = builder.otherObjects;
            this.otherObjectsSize = builder.otherObjectsSize;
            this.targetImg = builder.targetImg;
            this.otherImg = builder.otherImg;
            this.backgroundImg = builder.backgroundImg;
            this.desc = builder.desc;
        }

        public int getTargets() {
            return targets;
        }

        public double getTargetSize() {
            return targetSize;
        }

        public int getSecondsUntilMove() { return secondsUntilMove; }

        public int getOtherObjects() {
            return otherObjects;
        }

        public double getOtherObjectsSize() {
            return otherObjectsSize;
        }

        public int getSeconds() {
            return seconds;
        }

        @Override
        public String getDesc() {
            return desc;
        }

        public int getOtherImg() {
            return otherImg;
        }

        public int getBackgroundImg() {
            return backgroundImg;
        }

        public int getTargetImg() {
            return targetImg;
        }

        public int getScoreNeeded() {
            return scoreNeeded;
        }

        @Override
        public String getPreparationText() {
            return "W następnym poziomie klikaj na: ";
        }

        @Override
        public int getPreparationImgResource() {
            return targetImg;
        }

        public static class Builder {

            private int targets;
            private double targetSize;
            private int targetImg;
            private int otherObjects = 0;
            private double otherObjectsSize = 0;
            private int otherImg = 0;
            private int scoreNeeded;
            private int seconds;
            private int backgroundImg;
            private int secondsUntilMove;
            private String desc = "";

            public Builder(int scoreNeeded, int seconds) {
                this.scoreNeeded = scoreNeeded;
                this.seconds = seconds;
            }

            public Builder targets(int targets, double targetSize, int secondsUntilMove, int targetImg) {
                this.targets = targets;
                this.targetSize = targetSize;
                this.secondsUntilMove = secondsUntilMove;
                this.targetImg = targetImg;
                return this;
            }

            public Builder others(int otherObjects, double otherObjectsSize, int otherImg) {
                this.otherObjects = otherObjects;
                this.otherObjectsSize = otherObjectsSize;
                this.otherImg = otherImg;
                return this;
            }

            public Builder desc(String desc) {
                this.desc = desc;
                return this;
            }

            public Builder bg(int backgroundImg) {
                this.backgroundImg = backgroundImg;
                return this;
            }

            public Level build() {
                return new Level(this);
            }
        }

    }
}
