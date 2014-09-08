package pl.edu.agh.inz.reactive.games.rainbow;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pl.edu.agh.inz.reactive.R;
import pl.edu.agh.inz.reactive.User;
import pl.edu.agh.inz.reactive.games.AbstractGame;
import pl.edu.agh.inz.reactive.games.GameActivity;

public class RainbowActivity extends GameActivity {

	RelativeLayout layout;

    private RainbowGame logic;

    List<ImageView> targetObjectsNow = new ArrayList<ImageView>();
    List<ImageView> otherObjectsNow = new ArrayList<ImageView>();

    private Random rand = new Random();
	int wys = 570;
	int szer = 1070;

	int level = 1;

    @Override
    public void createGameLogic() {
        logic = new RainbowGame(new User());
    }

    @Override
    public void startLevel(int levelId) {
        setContentView(R.layout.activity_sea);

        layout = (RelativeLayout)findViewById(R.id.seaLayout);

        logic.setLevel(levelId);

        updateGameState();
    }

    public ImageView createTargetObject(int imgResource, double size) {
        ImageView targetObject = new TargetImageView(this);
        targetObjectsNow.add(targetObject);
        setupObjectParams(targetObject, imgResource, size);
        layout.addView(targetObject);
        return targetObject;
    }

    private void setupObjectParams(ImageView targetObject, int imgResource, double size) {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(150, 150);

        params.setMargins(rand.nextInt(szer), rand.nextInt(wys), 10, 10);
        params.height = (int)(params.height * (size / 100));
        params.width = (int)(params.width * (size / 100));
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

    public ImageView createOtherObject(int imgResource, int size) {
        ImageView targetObject = new OtherImageView(this);
        otherObjectsNow.add(targetObject);
        setupObjectParams(targetObject, imgResource, size);
        layout.addView(targetObject);
        return targetObject;
    }

    public void updateGameState() {
        RainbowGame.Level desc = logic.getLevelDescription(logic.getLevel());
        for (int i = targetObjectsNow.size(); i < desc.getTargets(); i++) {
            System.out.println("dodaje target");
            createTargetObject(R.drawable.lodka, desc.getTargetSize());
        }
        for (int i = targetObjectsNow.size(); i > desc.getTargets(); i--) {
            System.out.println("usuwam target");
            removeObject(targetObjectsNow.get(i));
        }
        for (int i = otherObjectsNow.size(); i < desc.getOtherObjects(); i++) {
            createOtherObject(R.drawable.x, desc.getOtherObjectsSize());
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
