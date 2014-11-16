package pl.edu.agh.inz.reactive.games.three;

import pl.edu.agh.inz.reactive.games.GameSpecification;

import static pl.edu.agh.inz.reactive.games.three.ThreeGame.Level;
import static pl.edu.agh.inz.reactive.R.drawable.*;

/**
 * Created by alek on 21.10.14.
 */
public class ThreeSpecification implements GameSpecification {

    private Level[] levels;

    public ThreeSpecification() {
        levels = new Level[] {
            new Level.Builder(5, 3, 3).setImages(new Integer[] { three_apple, three_pear, three_plum }).build(),
            new Level.Builder(10, 3, 5).setImages(new Integer[] { three_orange, three_apple, three_peach }).build(),
            new Level.Builder(10, 3, 8).setImages(new Integer[] { three_lemon, three_lime, three_pear }).build(),
            new Level.Builder(10, 3, 8).setImages(new Integer[] { three_mushroom_1, three_mushroom_2, three_toadstool }).build()
        };
    }

    public Level[] getLevels() {
            return levels;
        }

    @Override
    public int getMaxLevel() {
        return getLevels().length - 1;
    }
}
