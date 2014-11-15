package pl.edu.agh.inz.reactive.games.rainbow;

import pl.edu.agh.inz.reactive.R;
import pl.edu.agh.inz.reactive.games.GameSpecification;

import static pl.edu.agh.inz.reactive.games.rainbow.RainbowGame.Level;
import static pl.edu.agh.inz.reactive.R.drawable.*;

/**
 * Created by alek on 18.09.14.
 */
public class RainbowSpecification implements GameSpecification {

    private Level[] levels = new Level[] {
            new Level.Builder(4, 20).bg(rainbow_bg_notepad1).targets(1, 0.3, 6, rainbow_t_pen).build(),
            new Level.Builder(3, 15).bg(rainbow_bg_notepad1).targets(1, 0.3, 6, rainbow_t_pen).build(),
            new Level.Builder(3, 10).bg(rainbow_bg_notepad1).targets(1, 0.2, 6, rainbow_t_letter_a).others(1, 0.2, rainbow_t_letter_b).build(),
            new Level.Builder(4, 10).bg(rainbow_bg_notepad1).targets(1, 0.3, 6, rainbow_t_pen).build(),
            new Level.Builder(3, 10).bg(rainbow_bg_notepad1).targets(1, 0.3, 6, rainbow_t_letter_b).build(),
            new Level.Builder(8, 3).bg(rainbow_bg_cook1).targets(1, 0.2, 3, rainbow_t_cook1).build(),
            new Level.Builder(8, 3).bg(rainbow_bg_cook1).targets(1, 0.2, 3, rainbow_t_cook1).build(),
            new Level.Builder(8, 3).bg(rainbow_bg_4).targets(1, 0.2, 3, rainbow_t_cook1).build(),
            new Level.Builder(8, 3).bg(rainbow_bg_4).targets(1, 0.2, 3, rainbow_t_cook1).build(),
            new Level.Builder(8, 3).bg(rainbow_bg_4).targets(1, 0.2, 3, rainbow_t_cook1).build(),
            new Level.Builder(15, 4).bg(rainbow_bg_moto1).targets(2, 0.15, 2, rainbow_t_moto2).others(15, 0.1, rainbow_t_motorcycle).build(),
            new Level.Builder(15, 4).bg(rainbow_bg_moto1).targets(2, 0.15, 2, rainbow_t_moto2).others(15, 0.15, rainbow_t_motorcycle).build(),
            new Level.Builder(10, 4).bg(rainbow_bg_2).targets(1, 0.1, 3, rainbow_t_moto1).build(),
            new Level.Builder(10, 4).bg(rainbow_bg_2).targets(1, 0.1, 3, rainbow_t_moto1).build(),
            new Level.Builder(10, 4).bg(rainbow_bg_2).targets(1, 0.2, 3, rainbow_t_motorcycle).build(),
            new Level.Builder(10, 4).bg(rainbow_bg_1).targets(1, 0.2, 3, rainbow_t_hen).build(),
            new Level.Builder(10, 4).bg(rainbow_bg_1).targets(1, 0.2, 3, rainbow_t_pony).build(),
            new Level.Builder(10, 4).bg(rainbow_bg_1).targets(1, 0.2, 3, rainbow_t_cat).build(),
            new Level.Builder(10, 4).bg(rainbow_bg_1).targets(1, 0.2, 3, rainbow_t_cat).build(),
            new Level.Builder(10, 4).bg(rainbow_bg_1).targets(1, 0.2, 3, rainbow_t_cat).build(),
            new Level.Builder(10, 4).bg(rainbow_bg_3).targets(1, 0.1, 3, lodka).build(),
            new Level.Builder(10, 4).bg(rainbow_bg_3).targets(1, 0.2, 3, rainbow_t_ship).build(),
            new Level.Builder(10, 4).bg(rainbow_bg_3).targets(1, 0.2, 3, rainbow_t_ship).build(),
            new Level.Builder(10, 4).bg(rainbow_bg_3).targets(1, 0.2, 3, rainbow_t_ship).others(3, 0.1, rainbow_t_pirates).build(),
            new Level.Builder(10, 4).bg(rainbow_bg_3).targets(1, 0.2, 3, rainbow_t_ship).others(4, 0.25, rainbow_t_pirates).build(),
            new Level.Builder(10, 4).bg(rainbow_bg_bytecode).targets(1, 0.1, 3, rainbow_t_java).others(3, 0.15, rainbow_t_linux).build(),
            new Level.Builder(10, 4).bg(rainbow_bg_bytecode).targets(2, 0.1, 3, rainbow_t_java).others(5, 0.15, rainbow_t_linux).build(),
            new Level.Builder(10, 4).bg(rainbow_bg_bytecode).targets(1, 0.1, 3, rainbow_t_java).others(10, 0.15, rainbow_t_linux).build(),
            new Level.Builder(10, 4).bg(rainbow_bg_bytecode).targets(1, 0.1, 3, rainbow_t_java).others(10, 0.1, rainbow_t_windows).build(),
            new Level.Builder(10, 4).bg(rainbow_bg_bytecode).targets(1, 0.1, 3, rainbow_t_java).others(20, 0.1, rainbow_t_linux).build(),


    };

    public RainbowGame.Level[] getLevels() {
        return levels;
    }

    @Override
    public int getMaxLevel() {
        return getLevels().length - 1;
    }
}
