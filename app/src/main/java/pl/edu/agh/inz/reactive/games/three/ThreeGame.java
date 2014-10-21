package pl.edu.agh.inz.reactive.games.three;

import android.content.Context;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import pl.edu.agh.inz.reactive.games.AbstractGame;
import pl.edu.agh.inz.reactive.games.GameLevel;
import pl.edu.agh.inz.reactive.games.rainbow.images.TargetImageView;

/**
 * Created by alek on 21.10.14.
 */
public class ThreeGame extends AbstractGame {

    private ThreeSpecification specification = new ThreeSpecification();

    public ThreeGame(Context context) {
        super(THREE_GAME, context);

        setMaxLevel(specification.getLevels().length - 1);
    }

    public Level getLevelDescription(int level) {
        if (level > getMaxLevel()) {
            throw new IllegalArgumentException("level cannot be higher than " + getMaxLevel());
        }

        Level[] levels = specification.getLevels();
        return levels[level ];
    }

    public void onClick(PickableImageView v, PatternImageView pattern) {
        if (v.getResource() == pattern.getResource()) {
            setScore(getScore() + 1);
            ((ThreeActivity) pattern.getContext()).playPositiveSound();

        } else {
            System.out.println("Nietrafione!!!");
        }

        System.out.println("NAJLEPSZY WYNIK: " + db.getPointsFromLevel(user.getLogin(), THREE_GAME, getLevel()));
    }

    public static class Level implements GameLevel {

        private final int shownAtOnce;
        private final int scoreNeeded;
        private final List<Integer> images;

        public Level(Builder builder) {
            scoreNeeded = builder.scoreNeeded;
            images = builder.images;
            shownAtOnce = builder.shownAtOnce;
        }

        public List<Integer> getImages() {
            return new ArrayList<Integer>(images);
        }

        public int getShownAtOnce() {
            return shownAtOnce;
        }

        public int getScoreNeeded() {
            return scoreNeeded;
        }


        public static class Builder {

            private final int shownAtOnce;
            private final int scoreNeeded;

            private List<Integer> images;

            public Builder(int scoreNeeded, int shownAtOnce) {
                this.scoreNeeded = scoreNeeded;
                this.shownAtOnce = shownAtOnce;
            }

            public Builder setImages(Integer[] images) {
                this.images = Arrays.asList(images);
                return this;
            }

            public Level build() {
                return new Level(Builder.this);
            }
        }
    }
}
