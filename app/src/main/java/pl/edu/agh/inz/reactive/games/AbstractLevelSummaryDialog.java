package pl.edu.agh.inz.reactive.games;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import pl.edu.agh.inz.reactive.MainMenuActivity;

/**
 * Created by alek on 29.09.14.
 */
public abstract class AbstractLevelSummaryDialog extends DialogFragment {

    private GameActivity gameActivity;
    private int percent;

    public AbstractLevelSummaryDialog() {}

    public DialogInterface.OnClickListener getNextLevelClickListener() {
        return new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                gameActivity.startLevel(gameActivity.getLogic().getLevel() + 1);
            }
        };
    }

    public DialogInterface.OnClickListener getRestartLevelClickListener() {
        return new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                gameActivity.startLevel(gameActivity.getLogic().getLevel());
            }
        };
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