package pl.edu.agh.inz.reactive.games.fit;

import pl.edu.agh.inz.reactive.R;
import pl.edu.agh.inz.reactive.games.AbstractGame;
import pl.edu.agh.inz.reactive.games.GameActivity;
import pl.edu.agh.inz.reactive.games.finish.criteria.DefaultFinishListener;

/**
 * Created by Jacek on 2015-07-19.
 */
public class FitActivity extends GameActivity {

    private FitGame logic;
    private FitGame.Level level;

    @Override
    public void startLevel(int levelId) {
        setContentView(R.layout.activity_fit);

        logic.startLevel(levelId);

        level = logic.getLevelDescription(levelId);

        logic.setFinishListener(new DefaultFinishListener(logic, level, this));

    }

    @Override
    public AbstractGame getLogic() {
        return logic;
    }

    @Override
    public void createGameLogic() {
        logic = new FitGame(this, factory);
    }
}
