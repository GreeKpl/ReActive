package pl.edu.agh.inz.reactive.games.rainbow;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import pl.edu.agh.inz.reactive.MainMenuActivity;
import pl.edu.agh.inz.reactive.R;
import pl.edu.agh.inz.reactive.games.AbstractGame;
import pl.edu.agh.inz.reactive.games.AbstractLevelSummaryDialog;
import pl.edu.agh.inz.reactive.games.GameActivity;
import pl.edu.agh.inz.reactive.games.rainbow.images.OtherImageView;
import pl.edu.agh.inz.reactive.games.rainbow.images.TargetImageView;

public class RainbowActivity extends GameActivity {

    private static final int MSEC_PER_SEC = 1000;
    private RelativeLayout layout;

    private RainbowGame logic;
    private RainbowGame.Level level;

    private List<ImageView> targetObjectsNow = new ArrayList<ImageView>();
    private List<ImageView> otherObjectsNow = new ArrayList<ImageView>();

    private Random rand = new Random();
    private int screenHeight = 570;
    private int screenWidth = 1070;

    private Timer timer = new Timer(true);

    @Override
    public void createGameLogic() {
        logic = new RainbowGame(this);
    }

    @Override
    public void startLevel(int levelId) {
        setContentView(R.layout.activity_sea);

        logic.setLevel(levelId);

        level = logic.getLevelDescription(levelId);

        layout = (RelativeLayout)findViewById(R.id.seaLayout);
        layout.setBackgroundResource(level.getBackgroundImg());

        targetObjectsNow = new ArrayList<ImageView>();
        otherObjectsNow = new ArrayList<ImageView>();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                new AbstractLevelSummaryDialog () {

                    @Override
                    public DialogInterface.OnClickListener getNextLevelClickListener() {
                        return new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                RainbowActivity.this.startLevel(logic.getLevel() + 1);
                            }
                        };
                    }

                    @Override
                    public DialogInterface.OnClickListener getRestartLevelClickListener() {
                        return new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                RainbowActivity.this.startLevel(logic.getLevel());
                            }
                        };
                    }

                    @Override
                    public DialogInterface.OnClickListener getBackToMenuListener() {
                        return new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(RainbowActivity.this, MainMenuActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        };
                    }
                }.show(getFragmentManager(), "level finished");
            }
        }, 5000);

        updateGameState();
    }

    public ImageView createTargetObject(int imgResource, double size) {
        final ImageView targetObject = new TargetImageView(this);
        targetObjectsNow.add(targetObject);
        setupObjectParams(targetObject, imgResource, size);
        layout.addView(targetObject);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        removeObject(targetObject);
                        updateGameState();
                    }
                });
            }
        }, MSEC_PER_SEC * level.getSeconds());

        return targetObject;
    }

    public ImageView createOtherObject(int imgResource, double size) {
        ImageView targetObject = new OtherImageView(this);
        otherObjectsNow.add(targetObject);
        setupObjectParams(targetObject, imgResource, size);
        layout.addView(targetObject);
        return targetObject;
    }

    private void setupObjectParams(ImageView targetObject, int imgResource, double size) {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(150, 150);

        params.width = (int) (screenWidth * size);
        params.height = (int) (screenHeight * size);
        params.setMargins(rand.nextInt(screenWidth - params.width), rand.nextInt(screenHeight - params.height), 10, 10);

        targetObject.setLayoutParams(params);
        targetObject.setImageResource(imgResource);
        targetObject.setClickable(true);
        targetObject.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                logic.onObjectClick(v);
                removeObject(v);
                updateGameState();
            }
        });
        System.out.println("Score " + logic.getScore());
    }

    public void updateGameState() {
        RainbowGame.Level desc = level;
        for (int i = targetObjectsNow.size(); i < desc.getTargets(); i++) {
            System.out.println("dodaje target");
            createTargetObject(desc.getTargetImg(), desc.getTargetSize());
        }
        for (int i = targetObjectsNow.size(); i > desc.getTargets(); i--) {
            System.out.println("usuwam target");
            removeObject(targetObjectsNow.get(i));
        }
        for (int i = otherObjectsNow.size(); i < desc.getOtherObjects(); i++) {
            createOtherObject(desc.getOtherImg(), desc.getOtherObjectsSize());
        }
        for (int i = otherObjectsNow.size(); i > desc.getOtherObjects(); i--) {
            removeObject(otherObjectsNow.get(i));
        }
    }

    private void removeObject(View image) {
        if (image instanceof TargetImageView) {
            targetObjectsNow.remove(image);
        } else {
            otherObjectsNow.remove(image);
        }
        layout.removeView(image);
    }

    public ImageView createIncorrectObject(int imgResource, int size) {
        return null;
    }

    @Override
    public AbstractGame getLogic() {
        return logic;
    }


    public void levelNotification(int levelNo, int score) {
		Toast toast = Toast.makeText(this, "Runda "+levelNo+"\nWynik: "+score, Toast.LENGTH_SHORT);
		/*MediaPlayer mp = MediaPlayer.create(this, R.raw.round);
		toast.show();
		mp.start();
		*/
	}
}
