package pl.edu.agh.inz.reactive.games.summary.dialog;

import android.media.MediaPlayer;

import pl.edu.agh.inz.reactive.R;
import pl.edu.agh.inz.reactive.games.GameActivity;
import pl.edu.agh.inz.reactive.games.GameLevel;

/**
 * Created by alek on 29.09.14.
 */
public class LevelSummaryDialogFactory {


    public AbstractLevelSummaryDialog create(GameActivity gameActivity, boolean passed, GameLevel nextLevel, int percent) {
        AbstractLevelSummaryDialog summaryDialog;
        if (passed && nextLevel == null) {
            summaryDialog = new GameFinishedSummaryDialog();
        } else if (passed) {
            LevelPassedSummaryDialog levelPassedDialog = new LevelPassedSummaryDialog();
            levelPassedDialog.setNextLevel(nextLevel);
            summaryDialog = levelPassedDialog;

            summaryDialog.startNextLevel(AbstractLevelSummaryDialog.SUMMARY_DIALOG_TIME_MSEC);
            MediaPlayer mp = MediaPlayer.create(gameActivity, R.raw.round);
            mp.start();
        } else {
            summaryDialog = new LevelFailedSummaryDialog();
            summaryDialog.restartTheSameLevel(AbstractLevelSummaryDialog.SUMMARY_DIALOG_TIME_MSEC);
        }
        summaryDialog.setParams(gameActivity, percent);
        return summaryDialog;
    }
}
