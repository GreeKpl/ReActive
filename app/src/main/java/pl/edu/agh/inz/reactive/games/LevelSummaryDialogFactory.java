package pl.edu.agh.inz.reactive.games;

import android.media.MediaPlayer;

import pl.edu.agh.inz.reactive.R;

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
            summaryDialog.startNextLevel(3000);
            MediaPlayer mp = MediaPlayer.create(gameActivity, R.raw.round);
            mp.start();
        } else {
            summaryDialog = new LevelFailedSummaryDialog();
            summaryDialog.restartTheSameLevel(3000);
        }
        summaryDialog.setParams(gameActivity, percent);
        return summaryDialog;
    }
}
