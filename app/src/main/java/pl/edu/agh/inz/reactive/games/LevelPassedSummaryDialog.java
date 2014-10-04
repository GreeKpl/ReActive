package pl.edu.agh.inz.reactive.games;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import pl.edu.agh.inz.reactive.R;

/**
 * Created by alek on 02.10.14.
 */
public class LevelPassedSummaryDialog extends AbstractLevelSummaryDialog {
    @Override
    protected AlertDialog getBuilder(int percent) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View summaryContentsView = inflater.inflate(R.layout.dialog_level_summary, null);
        LinearLayout descView = (LinearLayout) summaryContentsView.findViewById(R.id.dialog_desc);

        descView.addView(createStar());

        if (percent >= 70) {
            descView.addView(createStar());
        }
        if (percent >= 80) {
            descView.addView(createStar());
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setView(summaryContentsView)
                .setPositiveButton(getString(R.string.level_passed_summary_dialog_positive_button), getNextLevelClickListener())
                .setNegativeButton(getString(R.string.level_passed_summary_dialog_negative_button), getBackToMenuListener());

        AlertDialog dialog = builder.create();
        return dialog;
    }

    private ImageView createStar() {
        ImageView star = new ImageView(getActivity());
        star.setImageResource(R.drawable.star);
        return star;
    }
}
