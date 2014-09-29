package pl.edu.agh.inz.reactive.games;

/**
 * Created by alek on 29.09.14.
 */
public class LevelSummaryDialogFactory {

    public LevelSummaryDialogFactory() {

    }

    public LevelSummaryDialog create(GameActivity gameActivity, int percent) {
        LevelSummaryDialog summaryDialog = new LevelSummaryDialog();
        summaryDialog.setParams(gameActivity, percent);
        return summaryDialog;
    }
}
