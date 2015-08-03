package pl.edu.agh.inz.reactive.games.fit;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import pl.edu.agh.inz.reactive.R;
import pl.edu.agh.inz.reactive.games.AbstractGame;
import pl.edu.agh.inz.reactive.games.GameActivity;
import pl.edu.agh.inz.reactive.games.finish.criteria.DefaultFinishListener;

import static pl.edu.agh.inz.reactive.R.drawable.*;

/**
 * Created by Jacek on 2015-07-19.
 */
public class FitActivity extends GameActivity {

    private FitGame logic;
    private FitGame.Level level;
    private RelativeLayout layout;
    private ImageView mainImageView;
    private ScheduledThreadPoolExecutor timer;

    @Override
    public void startLevel(int levelId) {
        setContentView(R.layout.activity_fit);

        logic.startLevel(levelId);

        level = logic.getLevelDescription(levelId);

        layout = (RelativeLayout)findViewById(R.id.fitLayout);
        layout.setBackgroundResource(level.getImage());

        timer = new ScheduledThreadPoolExecutor(1);

        logic.setFinishListener(new DefaultFinishListener(logic, level, this));

//        splitImage(morze, 3, 4);
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

    @Override
    public void createGameLogic() {
        logic = new FitGame(this, factory);
    }

    public void splitImage(int imgResource, int rows, int cols) {
        ImageView image = new ImageView(this);
        image.setImageResource(imgResource);

        int chunkWidth = image.getWidth() / cols;
        int chunkHeight = image.getHeight() / rows;

        float scaleWidth = ((float) chunkWidth) / image.getWidth();
        float scaleHeight = ((float) chunkHeight) / image.getHeight();

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);

        Bitmap resizedBitmap = Bitmap.createBitmap(image.getDrawingCache(), 0, 0, 50, 50, matrix, true);

        BitmapDrawable bmd = new BitmapDrawable(resizedBitmap);

        ImageView imageView = new ImageView(this);

        imageView.setImageDrawable(bmd);

        layout.addView(imageView);
    }

}
