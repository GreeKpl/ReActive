package pl.edu.agh.inz.reactive.games.finish.criteria;

import pl.edu.agh.inz.reactive.games.AbstractGame;
import pl.edu.agh.inz.reactive.games.GameActivity;
import pl.edu.agh.inz.reactive.games.GameLevel;
import pl.edu.agh.inz.reactive.games.summary.dialog.AbstractLevelSummaryDialog;
import pl.edu.agh.inz.reactive.games.summary.dialog.LevelSummaryDialogFactory;

/**
 * Created by alek on 15.11.14.
 */
public class DefaultFinishListener implements FinishListener {

    private AbstractGame logic;
    private GameLevel level;
    private GameActivity activity;

    public DefaultFinishListener(AbstractGame logic, GameLevel level, GameActivity activity) {
        this.logic = logic;
        this.level = level;
        this.activity = activity;
    }

    @Override
    public void onFinish() {
        int scorePercent = 100 * logic.getScore() / level.getScoreNeeded();
        new LevelSummaryDialogFactory().create(activity, scorePercent >= AbstractLevelSummaryDialog.PERCENT_TO_WIN,
                logic.getLevelDescription(logic.getLevel() + 1), scorePercent)
                .show(activity.getFragmentManager(), "level finished");
        finishAddition();
    }

    public void finishAddition() {
        // default implementation is to do nothing
    }
}
