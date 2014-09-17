package pl.edu.agh.inz.reactive.games.rainbow;

import pl.edu.agh.inz.reactive.R;

import static pl.edu.agh.inz.reactive.games.rainbow.RainbowGame.Level;
/**
 * Created by alek on 18.09.14.
 */
public class RainbowSpecification {

    private Level[] levels;

    public RainbowSpecification() {
        levels = new Level[] {
            new Level.Builder(6, 20).bg(R.drawable.morze).targets(1, 0.3, R.drawable.lodka).build(),
            new Level.Builder(8, 20).bg(R.drawable.morze).targets(1, 0.2, R.drawable.lodka).build(),
            new Level.Builder(10, 20).bg(R.drawable.morze).targets(1, 0.1, R.drawable.lodka).build(),
            new Level.Builder(15, 20).bg(R.drawable.morze).targets(1, 0.05, R.drawable.lodka).others(1, 0.2, R.drawable.x).build()
        };
    }

    public RainbowGame.Level[] getLevels() {
        return levels;
    }
}
