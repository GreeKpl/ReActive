package pl.edu.agh.inz.reactive.games;

import android.app.AlertDialog;

import pl.edu.agh.inz.reactive.R;

/**
 * Created by alek on 02.10.14.
 */
public class GameFinishedSummaryDialog extends AbstractLevelSummaryDialog {

    @Override
    protected AlertDialog getBuilder(int percent) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setMessage(getString(R.string.game_finished_summary_dialog_message) + percent + "%")
                .setNegativeButton(getString(R.string.game_finished_summary_dialog_negative_button), getBackToMenuListener());

        return builder.create();
    }
}

