package pl.edu.agh.inz.reactive.games.three;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import android.media.MediaPlayer;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import pl.edu.agh.inz.reactive.R;
import pl.edu.agh.inz.reactive.games.AbstractGame;
import pl.edu.agh.inz.reactive.games.GameActivity;
import pl.edu.agh.inz.reactive.games.LevelSummaryDialogFactory;

public class ThreeActivity extends GameActivity /* implements OnClickListener,*/ {

	Random rand = new Random();

    private ThreeGame logic;

    private PatternImageView patternElement;
    private ThreeGame.Level level;
    private ArrayList<PickableImageView> pickablesList;
    private ScheduledThreadPoolExecutor timer;
    private MediaPlayer mp = MediaPlayer.create(this, R.raw.round);;

    @Override
    public AbstractGame getLogic() {
        return logic;
    }

    @Override
    public void createGameLogic() {
        logic = new ThreeGame(this);
    }

    @Override
    public void startLevel(int levelId) {
        setContentView(R.layout.activity_three);

        logic.setLevel(levelId);
        logic.setScore(0);

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

        timer.schedule(new Runnable() {
            @Override
            public void run() {
                int scorePercent = 100 * logic.getScore() / level.getScoreNeeded();
                new LevelSummaryDialogFactory().create(ThreeActivity.this, scorePercent >= 20,
                        logic.getLevel() == logic.getMaxLevel(), scorePercent)
                        .show(getFragmentManager(), "level finished");
                timer.shutdownNow();
            }
        }, 10, TimeUnit.SECONDS);

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

        int pattern = images.get(rand.nextInt(images.size()));
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
        timer.shutdownNow();
        logic.destroy();
    }

	public void playPositiveSound() {
		mp.start();
	}

}
