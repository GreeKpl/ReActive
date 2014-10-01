package pl.edu.agh.inz.reactive.games;

/**
 * Created by alek on 29.09.14.
 */
public class LevelSummaryDialogFactory {

    public LevelSummaryDialogFactory() {

    }

    public AbstractLevelSummaryDialog create(GameActivity gameActivity, boolean passed, boolean isLast, int percent) {
        AbstractLevelSummaryDialog summaryDialog;
        if (passed && isLast) {
            summaryDialog = new GameFinishedSummaryDialog();
        } else if (passed) {
            summaryDialog = new LevelPassedSummaryDialog();
        } else {
            summaryDialog = new LevelFailedSummaryDialog();
        }
        summaryDialog.setParams(gameActivity, percent);
        return summaryDialog;
    }
}
