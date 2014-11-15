package pl.edu.agh.inz.reactive.games.finish.criteria;

import pl.edu.agh.inz.reactive.games.GameLevel;

/**
 * Created by alek on 14.11.14.
 */
public class FinishCriteriaFactory {

    private boolean timeBased;

    public FinishCriteriaFactory(boolean timeBased) {
        this.timeBased = timeBased;
    }

    public FinishCriteria create(GameLevel level) {
        if (timeBased) {
            return new TimeBasedFinishCriteria(level.getSeconds());
        } else {
            return new ScoreBasedFinishCriteria(level);
        }

    }

    public boolean isTimeBased() {
        return timeBased;
    }
}
