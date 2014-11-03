package pl.edu.agh.inz.reactive.games;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import pl.edu.agh.inz.reactive.MainMenuActivity;

/**
 * Created by alek on 29.09.14.
 */
public abstract class AbstractLevelSummaryDialog extends DialogFragment {

    private GameActivity gameActivity;
    private int percent;

    protected Timer timer = new Timer(true);

    public AbstractLevelSummaryDialog() {}

    public void startNextLevel(int delay) {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                gameActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        gameActivity.startLevel(gameActivity.getLogic().getLevel() + 1);
                        AbstractLevelSummaryDialog.this.dismiss();
                    }
                });
            }
        }, delay);
    }

    public void restartTheSameLevel(int delay) {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                gameActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        gameActivity.startLevel(gameActivity.getLogic().getLevel());
                        AbstractLevelSummaryDialog.this.dismiss();
                    }
                });
            }
        }, delay);
    }

    public DialogInterface.OnClickListener getBackToMenuListener() {
        return new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(gameActivity, MainMenuActivity.class);
                startActivity(intent);
                gameActivity.finish();
            }
        };
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        System.out.println("PERCENT " + percent);
        AlertDialog dialog = getBuilder(percent);

        dialog.setCanceledOnTouchOutside(false);

        return dialog;
    }

    protected abstract AlertDialog getBuilder(int percent);

    public DialogFragment setParams(GameActivity activity, int percent) {

        System.out.println("SET PARAMS PERCENT: " + percent);
        gameActivity = activity;
        this.percent = percent;
        return this;
    }
}
