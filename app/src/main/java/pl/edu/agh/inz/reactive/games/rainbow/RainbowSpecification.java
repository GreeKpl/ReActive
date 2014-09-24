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
            new Level.Builder(6, 20).bg(R.drawable.rainbow_bg_notepad1).targets(1, 0.3, R.drawable.lodka).build(),
            new Level.Builder(8, 8).bg(R.drawable.rainbow_bg_cook1).targets(1, 0.2, R.drawable.rainbow_t_cook1).build(),
            new Level.Builder(10, 20).bg(R.drawable.morze).targets(1, 0.1, R.drawable.lodka).build(),
            new Level.Builder(15, 4).bg(R.drawable.rainbow_bg_moto1).targets(2, 0.15, R.drawable.rainbow_t_moto2).others(15, 0.1, R.drawable.lodka).build()
        };
    }

    public RainbowGame.Level[] getLevels() {
        return levels;
    }
}
