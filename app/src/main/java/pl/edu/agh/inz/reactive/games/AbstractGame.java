package pl.edu.agh.inz.reactive.games;

import android.content.Context;

import java.util.Date;

import pl.edu.agh.inz.reactive.DatabaseManager;
import pl.edu.agh.inz.reactive.User;

/**
 * Created by jacek on 21.08.14.
 */
public abstract class AbstractGame {

    public static final int RAINBOW_GAME = 1;
    public static final int THREE_GAME = 2;

    protected final DatabaseManager db;
    private final int gameId;

    protected User user;
    int level;

    int maxLevel = 40;
    int score;

    public AbstractGame(int gameId, Context context) {
        this.gameId = gameId;

        db = new DatabaseManager(context);
        db.open();
        this.user = db.getActiveUser();
    }

    public Integer[] getLevelsArray() {
        int maxLevel = this.getUserMaxLevel() + 1;
        Integer[] ints = new Integer[maxLevel];
        for (int i = 0; i < maxLevel; i++) {
            ints[i] = i + 1;
        }
        return ints;
    }

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
        return db.getMaxLevel(user.getLogin(), RAINBOW_GAME);
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {

        if (score > db.getPointsFromLevel(user.getLogin(), gameId, getLevel())) {
            db.saveResult(user.getLogin(), gameId, new Date().getTime(), getLevel(), score);
        }

        this.score = score;
    }

    public void destroy() {
        db.close();
    }
}
