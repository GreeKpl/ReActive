package pl.edu.agh.inz.reactive.games;

import android.app.AlertDialog;

import pl.edu.agh.inz.reactive.R;

/**
 * Created by alek on 02.10.14.
 */
public class LevelFailedSummaryDialog extends AbstractLevelSummaryDialog {
    @Override
    protected AlertDialog getBuilder(int percent) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setMessage(getString(R.string.level_failed_summary_dialog_message) + " " + percent + "%").setTitle("Tym razem niestety się nie udało");

        return builder.create();
    }
}
