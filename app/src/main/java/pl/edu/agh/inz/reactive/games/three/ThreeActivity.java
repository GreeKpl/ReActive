package pl.edu.agh.inz.reactive.games.three;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import android.media.MediaPlayer;
import android.view.View;
import android.widget.LinearLayout;

import pl.edu.agh.inz.reactive.R;
import pl.edu.agh.inz.reactive.games.AbstractGame;
import pl.edu.agh.inz.reactive.games.GameActivity;
import pl.edu.agh.inz.reactive.games.finish.criteria.DefaultFinishListener;
import pl.edu.agh.inz.reactive.games.summary.dialog.LevelSummaryDialogFactory;
import pl.edu.agh.inz.reactive.games.finish.criteria.FinishListener;
import pl.edu.agh.inz.reactive.games.three.images.PatternImageView;
import pl.edu.agh.inz.reactive.games.three.images.PickableImageView;

public class ThreeActivity extends GameActivity /* implements OnClickListener,*/ {

	Random rand = new Random();

    private ThreeGame logic;

    private PatternImageView patternElement;
    private ThreeGame.Level level;
    private ArrayList<PickableImageView> pickablesList;
    private ScheduledThreadPoolExecutor timer;
    private MediaPlayer mp;

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

        patternElement = (PatternImageView) this.findViewById(R.id.patternImageView);

        LinearLayout pickableElements = (LinearLayout) this.findViewById(R.id.pickableElements);

        pickablesList = new ArrayList<PickableImageView>();
        for (int i = 0; i < level.getShownAtOnce(); i++) { // create pickable objects
            PickableImageView pickable = createPickableImageView();
            pickablesList.add(pickable);
            pickableElements.addView(pickable);
        }

        timer = new ScheduledThreadPoolExecutor(1);

        logic.setFinishListener(new DefaultFinishListener(logic, level, this));

        updateGameState();
    }

    private PickableImageView createPickableImageView() {
        PickableImageView pickable = new PickableImageView(this);
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
