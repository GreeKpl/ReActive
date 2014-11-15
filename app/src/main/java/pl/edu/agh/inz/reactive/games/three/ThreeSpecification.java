package pl.edu.agh.inz.reactive.games.three;

import pl.edu.agh.inz.reactive.R;
import pl.edu.agh.inz.reactive.games.GameSpecification;

import static pl.edu.agh.inz.reactive.games.three.ThreeGame.Level;

/**
 * Created by alek on 21.10.14.
 */
public class ThreeSpecification implements GameSpecification {

    private Level[] levels;

    public ThreeSpecification() {
        levels = new Level[] {
            new Level.Builder(5, 1, 3).setImages(new Integer[] { R.drawable.three_coin, R.drawable.three_pc }).build(),
            new Level.Builder(10, 2, 5).setImages(new Integer[] { R.drawable.three_coin, R.drawable.three_pc, R.drawable.three_sq, R.drawable.three_waga }).build(),
            new Level.Builder(10, 3, 8).setImages(new Integer[] { R.drawable.three_coin, R.drawable.three_pc, R.drawable.three_sq }).build(),
            new Level.Builder(20, 4, 7).setImages(new Integer[] { R.drawable.three_coin, R.drawable.three_pc, R.drawable.three_sq, R.drawable.three_waga }).build()
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
