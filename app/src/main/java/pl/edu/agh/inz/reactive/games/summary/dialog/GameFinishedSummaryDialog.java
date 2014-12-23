package pl.edu.agh.inz.reactive.games.summary.dialog;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import pl.edu.agh.inz.reactive.R;

/**
 * Created by alek on 02.10.14.
 */
public class GameFinishedSummaryDialog extends AbstractLevelSummaryDialog {

    @Override
    protected AlertDialog getBuilder(int percent) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View summaryContentsView = inflater.inflate(R.layout.dialog_level_summary, null);
        LinearLayout images = (LinearLayout) summaryContentsView.findViewById(R.id.imagesForLevel);

        ImageView image = new ImageView(getActivity());
        image.setImageResource(R.drawable.victory);
        images.addView(image);

        TextView instructionField = (TextView) summaryContentsView.findViewById(R.id.tvInstructionForLevel);
        instructionField.setText(getString(R.string.game_finished_summary_dialog_message));

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setView(summaryContentsView).setTitle("")
                .setNegativeButton(getString(R.string.game_finished_summary_dialog_negative_button), getBackToMenuListener());


        return builder.create();
    }
}

