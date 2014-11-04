package pl.edu.agh.inz.reactive.games;

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
public class LevelPassedSummaryDialog extends AbstractLevelSummaryDialog {

    private TextView instructionField;
    private LinearLayout images;
    private GameLevel nextLevel;

    @Override
    protected AlertDialog getBuilder(int percent) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View summaryContentsView = inflater.inflate(R.layout.dialog_level_summary, null);
        LinearLayout descView = (LinearLayout) summaryContentsView.findViewById(R.id.dialog_desc);
        TextView instructionField = (TextView) summaryContentsView.findViewById(R.id.tvInstructionForLevel);
        LinearLayout images = (LinearLayout) summaryContentsView.findViewById(R.id.imagesForLevel);

        if (percent < 70) {
            descView.addView(createStar(true));
            descView.addView(createStar(false));
            descView.addView(createStar(false));
        } else if (percent >= 70) {
            descView.addView(createStar(true));
            descView.addView(createStar(true));
            descView.addView(createStar(false));
        } else if (percent >= 80) {
            descView.addView(createStar(true));
            descView.addView(createStar(false));
            descView.addView(createStar(false));
        }
        String instruction = getString(R.string.instruction);
        instructionField.setText(instruction);

        if (nextLevel != null && nextLevel.getPreparationText() != null) {
            instructionField.setText(nextLevel.getPreparationText());
        }
        if (nextLevel != null && nextLevel.getPreparationImgResource() != 0) {
            images.addView(createImage(nextLevel.getPreparationImgResource()));
        }


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setView(summaryContentsView).setTitle(getString(R.string.level_passed_summary_dialog_message) + " ");

        return builder.create();
    }


    public void setNextLevel(GameLevel nextLevel) {
        this.nextLevel = nextLevel;
    }

    private ImageView createStar(boolean activeStar) {
        ImageView star = new ImageView(getActivity());
        if(activeStar) {
            star.setImageResource(R.drawable.star);
        } else {
            star.setImageResource(R.drawable.empty_star);
        }
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(5, 0, 5, 0);
        star.setLayoutParams(lp);
        return star;
    }

    private ImageView createImage(int resource) {
        ImageView image = new ImageView(getActivity());
        image.setImageResource(resource);
        return image;
    }
}
