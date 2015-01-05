package pl.edu.agh.inz.reactive;

import pl.edu.agh.inz.reactive.games.GameActivity;

/**
 * Created by alek on 07.12.14.
 */
public class GameDescriptor {
    private int id;
    private String name;
    private int img;

    private boolean withTimer = false;
    private Class<? extends GameActivity> gameClass;

    public GameDescriptor(int id, String name, Class<? extends GameActivity> gameClass, boolean withTimer, int img) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.gameClass = gameClass;
        this.withTimer = withTimer;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getImg() {
        return img;
    }

    public Class<? extends GameActivity> getGameClass() {
        return gameClass;
    }

    public boolean isWithTimer() {
        return withTimer;
    }
}
