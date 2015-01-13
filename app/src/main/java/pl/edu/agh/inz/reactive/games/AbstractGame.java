package pl.edu.agh.inz.reactive.games;

import android.content.Context;

import java.util.Date;

import pl.edu.agh.inz.reactive.DatabaseManager;
import pl.edu.agh.inz.reactive.User;
import pl.edu.agh.inz.reactive.games.finish.criteria.FinishCriteria;
import pl.edu.agh.inz.reactive.games.finish.criteria.FinishCriteriaFactory;
import pl.edu.agh.inz.reactive.games.finish.criteria.FinishListener;

/**
 * Created by jacek on 21.08.14.
 */
public abstract class AbstractGame {

    protected final DatabaseManager db;
    private final int gameId;
    private FinishCriteriaFactory factory;

    protected User user;
    private int level;

    private int score;
    private FinishCriteria finishCriteria;

    public AbstractGame(int gameId, FinishCriteriaFactory factory, GameActivity context) {
        this.gameId = gameId;
        this.factory = factory;

        db = new DatabaseManager(context);
        db.open();
        this.user = db.getActiveUser();
    }

    public void startLevel(int levelId) {
        setLevel(levelId);

        if (finishCriteria != null) {
            finishCriteria.destroy();
        }
        finishCriteria = factory.create(getSpecification().getLevels()[levelId]);

        setScore(0);
    }

    public void setFinishListener(FinishListener listener) {
        finishCriteria.setOnFinishListener(listener);
    }

    public GameLevel getLevelDescription(int levelId) {
        if (levelId > getMaxLevel()) {
            return null;
        }

        GameLevel[] levels = getSpecification().getLevels();
        return levels[levelId];
    }

    public Integer[] getLevelsArray() {
        int maxLevel = this.getUserMaxLevel() + 1;
        Integer[] ints = new Integer[maxLevel];
        for (int i = 0; i < maxLevel; i++) {
            ints[i] = i + 1;
        }
        return ints;
    }

    public abstract GameSpecification getSpecification();

    public void setUser(User user) {
        this.user = user;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getUserMaxLevel() {
        return db.getMaxLevel(user.getLogin(), gameId);
    }

    public int getMaxLevel() {
        return getSpecification().getMaxLevel();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {

        if (score > db.getPointsFromLevel(user.getLogin(), gameId, getLevel())) {
            db.saveLevelResult(user.getLogin(), gameId, getLevel(), score);
        }

        db.prepareToSaveDateResult(user.getLogin(), gameId, new Date().getTime(), getLevel(), score);

        this.score = score;
        finishCriteria.onScoreChange(score);
    }

    public void destroy() {
        db.close();
        if (finishCriteria != null) {
            finishCriteria.destroy();
        }
    }
}
