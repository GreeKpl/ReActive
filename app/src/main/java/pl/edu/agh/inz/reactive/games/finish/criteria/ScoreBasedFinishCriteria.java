package pl.edu.agh.inz.reactive.games.finish.criteria;

import pl.edu.agh.inz.reactive.games.GameLevel;

public class ScoreBasedFinishCriteria extends FinishCriteria {

    private GameLevel level;

    public ScoreBasedFinishCriteria(GameLevel level) {
        this.level = level;
    }

    @Override
    public void onScoreChange(int score) {
        if (score >= level.getScoreNeeded()) {
            if (finishListener != null) {
                finishListener.onFinish();
            }
        }
    }

    @Override
    public void destroy() {
        // do nothing
    }
}
