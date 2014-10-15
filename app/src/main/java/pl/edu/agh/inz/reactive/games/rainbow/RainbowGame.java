package pl.edu.agh.inz.reactive.games.rainbow;

import android.content.Context;
import android.view.View;

import pl.edu.agh.inz.reactive.games.AbstractGame;
import pl.edu.agh.inz.reactive.games.rainbow.images.TargetImageView;

/**
 * Created by alek on 25.08.14.
 */
public class RainbowGame extends AbstractGame {

    private RainbowSpecification specification = new RainbowSpecification();

    public RainbowGame(Context context) {
        super("Sea", context);

        setMaxLevel(specification.getLevels().length - 1);
    }

    public Level getLevelDescription(int level) {
        if (level > getMaxLevel()) {
            throw new IllegalArgumentException("level cannot be higher than " + getMaxLevel());
        }

        Level[] levels = specification.getLevels();
        return levels[level];
    }

    public void onObjectClick(View v) {
        if (v instanceof TargetImageView) {
            setScore(getScore() + 1);
        } else {
            setScore(getScore() - 1);
        }
        System.out.println("NAJLEPSZY WYNIK: " + db.getPointsFromLevel(user.getLogin(), RAINBOW_GAME, getLevel()));
    }

    public static class Level {
        private final int seconds;

        private final int targets;
        private final double targetSize;
        private final int targetImg;

        private final int otherObjects;
        private final double otherObjectsSize;
        private final int otherImg;
        private final int backgroundImg;
        private final int scoreNeeded;

        public Level(Builder levelBuilder) {
            this.scoreNeeded = levelBuilder.scoreNeeded;
            this.seconds = levelBuilder.seconds;

            this.targets = levelBuilder.targets;
            this.targetSize = levelBuilder.targetSize;
            this.otherObjects = levelBuilder.otherObjects;
            this.otherObjectsSize = levelBuilder.otherObjectsSize;
            this.targetImg = levelBuilder.targetImg;
            this.otherImg = levelBuilder.otherImg;
            this.backgroundImg = levelBuilder.backgroundImg;
        }

        public int getTargets() {
            return targets;
        }

        public double getTargetSize() {
            return targetSize;
        }

        public int getOtherObjects() {
            return otherObjects;
        }

        public double getOtherObjectsSize() {
            return otherObjectsSize;
        }

        public int getSeconds() {
            return seconds;
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

            public Builder(int scoreNeeded, int seconds) {
                this.scoreNeeded = scoreNeeded;
                this.seconds = seconds;
            }

            public Builder targets(int targets, double targetSize, int targetImg) {

                this.targets = targets;
                this.targetSize = targetSize;
                this.targetImg = targetImg;
                return this;
            }

            public Builder others(int otherObjects, double otherObjectsSize, int otherImg) {
                this.otherObjects = otherObjects;
                this.otherObjectsSize = otherObjectsSize;
                this.otherImg = otherImg;
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
