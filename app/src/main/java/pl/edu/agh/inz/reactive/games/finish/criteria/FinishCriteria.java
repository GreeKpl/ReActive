package pl.edu.agh.inz.reactive.games.finish.criteria;

/**
 * Created by alek on 14.11.14.
 */
public abstract class FinishCriteria implements ScoreListener {
    protected FinishListener finishListener;

    public void setOnFinishListener(FinishListener listener) {
        finishListener = listener;
    }

    public abstract void destroy();
}
