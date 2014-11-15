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

    public static final int RAINBOW_GAME = 1;
    public static final int RAINBOW_GAME_TRAINING = 2;
    public static final int THREE_GAME = 3;
    public static final int THREE_GAME_TRAINING = 4;


    protected final DatabaseManager db;
    private final int gameId;
    private FinishCriteriaFactory factory;

    protected User user;
    int level;

    int score;
    private FinishCriteria finishCriteria;

    public AbstractGame(int gameId, FinishCriteriaFactory factory, Context context) {
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

    public GameLevel getLevelDescription(int level) {
        if (level > getMaxLevel()) {
            return null;
        }

        GameLevel[] levels = getSpecification().getLevels();
        return levels[level];
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

    private User getUser() {
        return user;
    }

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
            db.saveResult(user.getLogin(), gameId, new Date().getTime(), getLevel(), score);
        }

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
