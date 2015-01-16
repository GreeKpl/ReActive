package pl.edu.agh.inz.reactive.games.summary.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.SyncStatusObserver;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import pl.edu.agh.inz.reactive.R;
import pl.edu.agh.inz.reactive.games.GameActivity;
import pl.edu.agh.inz.reactive.games.GameLevel;

/**
 * Created by alek on 16.01.15.
 */
public abstract class PreparationDialog extends DialogFragment {

    public static final int PREPARATION_DIALOG_TIME_SEC = 4;

    private ScheduledThreadPoolExecutor timer;
    private GameLevel level;

    public PreparationDialog() {
        timer = new ScheduledThreadPoolExecutor(1);
    }

    public void startNextLevel() {
        timer.schedule(new Runnable() {

            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        timer.shutdownNow();
                        runLevel();
                        PreparationDialog.this.dismiss();
                    }
                });
            }
        }, PREPARATION_DIALOG_TIME_SEC, TimeUnit.SECONDS);
    }

    protected abstract void runLevel();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog dialog = getBuilder();

        dialog.setCanceledOnTouchOutside(false);

        return dialog;
    }

    protected AlertDialog getBuilder() {

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View preparationContentsView = inflater.inflate(R.layout.dialog_preparation, null);
        TextView instructionField = (TextView) preparationContentsView.findViewById(R.id.tvInstructionForLevel);
        LinearLayout images = (LinearLayout) preparationContentsView.findViewById(R.id.imagesForLevel);


        String instruction = getString(R.string.instruction);
        instructionField.setText(instruction);

        if (level != null && level.getPreparationText() != null) {
            instructionField.setText(level.getPreparationText());
        }
        if (level != null && level.getPreparationImgResource() != 0) {
            images.addView(createImage(level.getPreparationImgResource()));
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setView(preparationContentsView);

        return builder.create();
    }

    public PreparationDialog setParams(GameLevel level) {
        this.level = level;
        return this;
    }

    private ImageView createImage(int resource) {
        ImageView image = new ImageView(getActivity());
        image.setImageResource(resource);
        return image;
    }
}