package pl.edu.agh.inz.reactive.games.rainbow;

import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import pl.edu.agh.inz.reactive.R;
import pl.edu.agh.inz.reactive.games.AbstractGame;
import pl.edu.agh.inz.reactive.games.GameActivity;
import pl.edu.agh.inz.reactive.games.finish.criteria.DefaultFinishListener;
import pl.edu.agh.inz.reactive.games.rainbow.images.OtherImageView;
import pl.edu.agh.inz.reactive.games.rainbow.images.TargetImageView;

public class RainbowActivity extends GameActivity {

    private RelativeLayout layout;

    private RainbowGame logic;
    private RainbowGame.Level level;

    private List<ImageView> targetObjectsNow = new ArrayList<ImageView>();
    private List<ImageView> otherObjectsNow = new ArrayList<ImageView>();

    private Random rand = new Random();

    private ScheduledThreadPoolExecutor timer;

    @Override
    public void createGameLogic() {
        logic = new RainbowGame(this, factory);
    }

    @Override
    public void startLevel(int levelId) {
        setContentView(R.layout.activity_rainbow);

        logic.startLevel(levelId);

        level = logic.getLevelDescription(levelId);

        layout = (RelativeLayout)findViewById(R.id.rainbowLayout);
        layout.setBackgroundResource(level.getBackgroundImg());

        targetObjectsNow = new ArrayList<ImageView>();
        otherObjectsNow = new ArrayList<ImageView>();

        timer = new ScheduledThreadPoolExecutor(1);

        logic.setFinishListener(new DefaultFinishListener(logic, level, this) {
            @Override
            public void finishAddition() {
                timer.shutdownNow();
            }
        });

        updateGameState();
    }

    public ImageView createTargetObject(int imgResource, double size) {
        final ImageView targetObject = new TargetImageView(this);
        targetObjectsNow.add(targetObject);
        setupObjectParams(targetObject, imgResource, size);
        layout.addView(targetObject);

        try {
            timer.schedule(new Runnable() {
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
            }, level.getSecondsUntilMove(), TimeUnit.SECONDS);
        } catch (Exception e) {
            System.out.println("THATS VERY BAD!!!");
        } // TODO

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

        BitmapFactory.Options originalDim = getOriginalDimensions(imgResource);
        double widthToHeight = 1.0 * originalDim.outWidth / originalDim.outHeight;

        Point screenSize = getScreenSize();
        double width = screenSize.x * size;
        double height = screenSize.y * size;

        Rect rect = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        int statusBarHeight = rect.top;
        int contentViewTop = getWindow().findViewById(getWindow().ID_ANDROID_CONTENT).getTop();
        int titleBarHeight = contentViewTop - statusBarHeight;

        params.width = (int) Math.min(width, height * widthToHeight);
        params.height = (int) Math.min(height, width / widthToHeight);
        params.setMargins(rand.nextInt(screenSize.x - params.width - 1), rand.nextInt(screenSize.y - params.height - 40 - titleBarHeight), 1, 1);

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

    private BitmapFactory.Options getOriginalDimensions(int imgResource) {
        BitmapFactory.Options dimensions = new BitmapFactory.Options();
        dimensions.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), imgResource, dimensions);
        return dimensions;
    }

    private void updateGameState() {
        RainbowGame.Level desc = level;
        for (int i = otherObjectsNow.size(); i < desc.getOtherObjects(); i++) {
            createOtherObject(desc.getOtherImg(), desc.getOtherObjectsSize());
        }
        for (int i = otherObjectsNow.size(); i > desc.getOtherObjects(); i--) {
            removeObject(otherObjectsNow.get(i));
        }
        for (int i = targetObjectsNow.size(); i < desc.getTargets(); i++) {
            System.out.println("dodaje target");
            createTargetObject(desc.getTargetImg(), desc.getTargetSize());
        }
        for (int i = targetObjectsNow.size(); i > desc.getTargets(); i--) {
            System.out.println("usuwam target");
            removeObject(targetObjectsNow.get(i));
        }
    }

    private void removeObject(View image) {
        if (image instanceof TargetImageView) {
            targetObjectsNow.remove(image);
        } else if (image instanceof OtherImageView){
            otherObjectsNow.remove(image);
        }
        layout.removeView(image);
    }

    @Override
    public AbstractGame getLogic() {
        return logic;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.shutdownNow();
        }
    }
}
