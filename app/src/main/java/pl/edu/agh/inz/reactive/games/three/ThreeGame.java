package pl.edu.agh.inz.reactive.games.three;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pl.edu.agh.inz.reactive.R;
import pl.edu.agh.inz.reactive.games.AbstractGame;
import pl.edu.agh.inz.reactive.games.GameLevel;
import pl.edu.agh.inz.reactive.games.finish.criteria.FinishCriteriaFactory;
import pl.edu.agh.inz.reactive.games.GameSpecification;
import pl.edu.agh.inz.reactive.games.three.images.PatternImageView;
import pl.edu.agh.inz.reactive.games.three.images.PickableImageView;

/**
 * Created by alek on 21.10.14.
 */
public class ThreeGame extends AbstractGame {

    private ThreeSpecification specification = new ThreeSpecification();

    public ThreeGame(Context context, FinishCriteriaFactory factory) {
        super(factory.isTimeBased() ? THREE_GAME_TRAINING : THREE_GAME_TRAINING, factory, context);
    }

    public Level getLevelDescription(int level) {
        return (Level) super.getLevelDescription(level);
    }

    public void onClick(PickableImageView v, PatternImageView pattern) {
        if (v.getResource() == pattern.getResource()) {
            setScore(getScore() + 1);
            ((ThreeActivity) pattern.getContext()).playPositiveSound();

        } else {
            System.out.println("Nietrafione!!!");
        }

        System.out.println("NAJLEPSZY WYNIK: " + db.getPointsFromLevel(user.getLogin(), THREE_GAME_TRAINING, getLevel()));
    }

    @Override
    public GameSpecification getSpecification() {
        return specification;
    }

    public static class Level implements GameLevel {

        private final int seconds;
        private final int shownAtOnce;
        private final int scoreNeeded;
        private final List<Integer> images;

        public Level(Builder builder) {
            scoreNeeded = builder.scoreNeeded;
            images = builder.images;
            shownAtOnce = builder.shownAtOnce;
            seconds = builder.seconds;
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

        @Override
        public int getSeconds() {
            return seconds;
        }

        @Override
        public String getPreparationText() {
            return "Klikaj w obrazek u dołu taki jak na górze";
        }

        @Override
        public int getPreparationImgResource() {
            return R.drawable.three_task;
        }

        public static class Builder {

            private final int shownAtOnce;
            private final int scoreNeeded;
            private final int seconds;

            private List<Integer> images;

            public Builder(int scoreNeeded, int shownAtOnce, int seconds) {
                this.scoreNeeded = scoreNeeded;
                this.shownAtOnce = shownAtOnce;
                this.seconds = seconds;
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
