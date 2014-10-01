package pl.edu.agh.inz.reactive.games;

import android.app.AlertDialog;

/**
 * Created by alek on 02.10.14.
 */
public class GameFinishedSummaryDialog extends AbstractLevelSummaryDialog {

    @Override
    protected AlertDialog.Builder getBuilder(int percent) {
        return new AlertDialog.Builder(getActivity())
                .setMessage("BRAWO! WYGRALES TO!!!" + percent + "%")
                .setNegativeButton("done", getBackToMenuListener());
    }
}

