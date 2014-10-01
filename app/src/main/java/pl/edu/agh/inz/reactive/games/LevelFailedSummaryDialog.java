package pl.edu.agh.inz.reactive.games;

import android.app.AlertDialog;

/**
 * Created by alek on 02.10.14.
 */
public class LevelFailedSummaryDialog extends AbstractLevelSummaryDialog {
    @Override
    protected AlertDialog.Builder getBuilder(int percent) {
        return new AlertDialog.Builder(getActivity())
                .setMessage("level failed " + percent + "%")
                .setPositiveButton("restart level", getRestartLevelClickListener())
                .setNegativeButton("back to menu", getBackToMenuListener());
    }
}
