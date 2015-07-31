package pl.edu.agh.inz.reactive.games.fit;

import pl.edu.agh.inz.reactive.games.GameSpecification;

import static pl.edu.agh.inz.reactive.games.fit.FitGame.Level;
import static pl.edu.agh.inz.reactive.R.drawable.*;

/**
 * Created by Jacek on 2015-07-19.
 */
public class FitSpecification implements GameSpecification {

    private static final int SEC_PER_LEVEL = 30;

    private Level[] levels = new Level[] {
            new Level.Builder(2, 2, 1, SEC_PER_LEVEL).desc("morze").image(morze).build(),
            new Level.Builder(2, 2, 1, SEC_PER_LEVEL).desc("tapeta").image(tapeta).build(),
    };

    @Override
    public FitGame.Level[] getLevels() {
        return levels;
    }

    @Override
    public int getMaxLevel() {
        return getLevels().length - 1;
    }
}
