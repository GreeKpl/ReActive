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
    protected final DatabaseManager db;
    String name;

    protected User user;
    int level;

    int userMaxLevel = 4;
    int maxLevel = 40;
    int score;

    public AbstractGame(String name, Context context) {
        this.name = name;

        db = new DatabaseManager(context);
        db.open();
        this.user = db.getActiveUser();

    }

    public Integer[] getLevelsArray() {
        int maxLevel = this.getUserMaxLevel();
        Integer[] ints = new Integer[maxLevel];
        for (int i = 0; i < maxLevel; i++) {
            ints[i] = i + 1;
        }
        return ints;
    }

    private int readScore() {
        //TODO :)
        return 0;
    }

    public boolean saveScore(int score, User user) {
        //TODO !! :)
        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return userMaxLevel;
    }

    public void setUserMaxLevel(int userMaxLevel) {
        this.userMaxLevel = userMaxLevel;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public void readUserMaxLevel(User user) {
        //TODO !!!!!!!!!!!!!!!!!
        int readLevel = 1; //wcale nie 1 :P
        setUserMaxLevel(readLevel);
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {

        if (score > db.getPointsFromLevel(user.getLogin(), RAINBOW_GAME, getLevel())) {
            db.saveResult(user.getLogin(), RAINBOW_GAME, new Date().getTime(), getLevel(), score);
        }

        this.score = score;
    }

    public void endGame() {

    }

}
