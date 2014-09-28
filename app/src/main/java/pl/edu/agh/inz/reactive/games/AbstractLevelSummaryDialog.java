package pl.edu.agh.inz.reactive.games;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by alek on 29.09.14.
 */
public abstract class AbstractLevelSummaryDialog extends DialogFragment {

    private int percent;

    public AbstractLevelSummaryDialog() {}

    public abstract DialogInterface.OnClickListener getNextLevelClickListener();

    public abstract DialogInterface.OnClickListener getRestartLevelClickListener();

    public abstract DialogInterface.OnClickListener getBackToMenuListener();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("level passed")
                .setPositiveButton("next level", getNextLevelClickListener())
                .setNegativeButton("restart", getBackToMenuListener());
        return builder.create();
    }
}
