package pl.edu.agh.inz.reactive.games;

import android.media.MediaPlayer;

import pl.edu.agh.inz.reactive.R;

/**
 * Created by alek on 29.09.14.
 */
public class LevelSummaryDialogFactory {

    public LevelSummaryDialogFactory() {

    }

    public AbstractLevelSummaryDialog create(GameActivity gameActivity, boolean passed, GameLevel nextLevel, int percent) {
        AbstractLevelSummaryDialog summaryDialog;
        if (passed && nextLevel == null) {
            summaryDialog = new GameFinishedSummaryDialog();
        } else if (passed) {
            LevelPassedSummaryDialog levelPassedDialog = new LevelPassedSummaryDialog();
            levelPassedDialog.setNextLevel(nextLevel);
            summaryDialog = levelPassedDialog;

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
