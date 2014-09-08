package pl.edu.agh.inz.reactive.games.rainbow;

import android.view.View;

import pl.edu.agh.inz.reactive.User;
import pl.edu.agh.inz.reactive.games.AbstractGame;

/**
 * Created by alek on 25.08.14.
 */
public class RainbowGame extends AbstractGame {

    public RainbowGame(User user) {
        super("Sea", user, 40); // todo
    }

    public Level getLevelDescription(int level) {
        if (level > getMaxLevel()) {
            throw new IllegalArgumentException("level cannot be higher than " + getMaxLevel());
        }

        Level[] levels = new Level[] {
            new Level(1, 200, 0, 100, 20),
            new Level(1, 100, 0, 100, 20),
            new Level(1,  50, 0, 100, 20),
            new Level(1,  50, 1, 100, 20),
        };
        return levels[level];
    }

    public void onObjectClick(View v) {
        if (v instanceof TargetImageView) {
            setScore(getScore() + 1);
        } else {
            setScore(getScore() - 1);
        }
    }

    public class Level {
        private final int targets;
        private final int targetSize;
        private final int otherObjects;

        private final int otherObjectsSize;
        private final int seconds;

        public Level(int targets, int targetSize, int otherObjects, int otherObjectsSize, int seconds) {

            this.targets = targets;
            this.targetSize = targetSize;
            this.otherObjects = otherObjects;
            this.otherObjectsSize = otherObjectsSize;
            this.seconds = seconds;
        }

        public int getTargets() {
            return targets;
        }

        public int getTargetSize() {
            return targetSize;
        }

        public int getOtherObjects() {
            return otherObjects;
        }

        public int getOtherObjectsSize() {
            return otherObjectsSize;
        }

        public int getSeconds() {
            return seconds;
        }

    }
}
