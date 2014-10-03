package pl.edu.agh.inz.reactive.games;

import android.app.AlertDialog;

import pl.edu.agh.inz.reactive.R;

/**
 * Created by alek on 02.10.14.
 */
public class LevelPassedSummaryDialog extends AbstractLevelSummaryDialog {
    @Override
    protected AlertDialog.Builder getBuilder(int percent) {
        return new AlertDialog.Builder(getActivity())
                .setMessage(getString(R.string.level_passed_summary_dialog_message) + percent + "%")
                .setPositiveButton(getString(R.string.level_passed_summary_dialog_positive_button), getNextLevelClickListener())
                .setNegativeButton(getString(R.string.level_passed_summary_dialog_negative_button), getBackToMenuListener());
    }
}
