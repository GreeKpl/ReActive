package pl.edu.agh.inz.reactive.games.three;

import android.graphics.Point;
import android.media.MediaPlayer;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import pl.edu.agh.inz.reactive.R;
import pl.edu.agh.inz.reactive.games.AbstractGame;
import pl.edu.agh.inz.reactive.games.GameActivity;
import pl.edu.agh.inz.reactive.games.finish.criteria.DefaultFinishListener;
import pl.edu.agh.inz.reactive.games.three.images.PatternImageView;
import pl.edu.agh.inz.reactive.games.three.images.PickableImageView;

public class ThreeActivity extends GameActivity {

    private ThreeGame logic;

    private PatternImageView patternElement;
    private ThreeGame.Level level;
    private ArrayList<PickableImageView> pickablesList;
    private ScheduledThreadPoolExecutor timer;
    private MediaPlayer mp;

    private Point screenSize;

    @Override
    public AbstractGame getLogic() {
        return logic;
    }

    @Override
    public void createGameLogic() {
        logic = new ThreeGame(this, factory);
    }

    @Override
    public void startLevel(int levelId) {

        mp = MediaPlayer.create(this, R.raw.round);
        setContentView(R.layout.activity_three);

        logic.startLevel(levelId);

        level = logic.getLevelDescription(levelId);

        screenSize = getScreenSize();

        patternElement = (PatternImageView) this.findViewById(R.id.patternImageView);

        if (level.isRotatePattern()) {
            rotateImage(patternElement);
        }

        int imgSize = screenSize.y / 2 - 50; // it should be possible to have 5 images next to each other

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(imgSize, imgSize);
        layoutParams.gravity = 16;  //16 is center
        patternElement.setLayoutParams(layoutParams);


        LinearLayout pickableElements = (LinearLayout) this.findViewById(R.id.pickableElements);

        pickablesList = new ArrayList<PickableImageView>();
        for (int i = 0; i < level.getShownAtOnce(); i++) { // create pickable objects
            PickableImageView pickable = createPickableImageView();
            int paddingmargin = this.getResources().getInteger(R.integer.tree_activity_padding_margin);
            pickable.setPadding(paddingmargin, 0, paddingmargin, 0);
            pickablesList.add(pickable);
            pickableElements.addView(pickable);
            pickableElements.setGravity(16);    //16 is center
        }

        timer = new ScheduledThreadPoolExecutor(1);

        logic.setFinishListener(new DefaultFinishListener(logic, level, this));

        updateGameState();
    }

    private void rotateImage(ImageView image) {
        float rotate = Math.signum(new Random().nextFloat() * 2 - 1) * 30;
        final RotateAnimation rotateAnim = new RotateAnimation(0.0f, rotate,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);

        rotateAnim.setDuration(0);
        rotateAnim.setFillAfter(true);
        image.startAnimation(rotateAnim);
    }

    private PickableImageView createPickableImageView() {
        PickableImageView pickable = new PickableImageView(this);

        int imgSize = Math.min(screenSize.y / 2 - 50, ((screenSize.x - 50) / level.getShownAtOnce())); // it should be possible to have 5 images next to each other
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(imgSize, imgSize);
        pickable.setLayoutParams(layoutParams);


        pickable.setScaleType(ImageView.ScaleType.FIT_CENTER);

        pickable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logic.onClick((PickableImageView)v, patternElement);
                updateGameState();
            }
        });
        return pickable;
    }

    private void updateGameState() {
        List<Integer> images = level.getImages();

        Collections.shuffle(images);

        patternElement.setImageResource(images.get(0));
        Collections.shuffle(pickablesList);
        for (int i = 0; i < pickablesList.size(); i++) {
            pickablesList.get(i).setImageResource(images.get(i));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.shutdownNow();
        }
    }

	public void playPositiveSound() {
		mp.start();
	}

}
