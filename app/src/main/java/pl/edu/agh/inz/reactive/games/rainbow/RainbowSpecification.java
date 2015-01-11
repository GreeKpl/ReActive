package pl.edu.agh.inz.reactive.games.rainbow;

import pl.edu.agh.inz.reactive.games.GameSpecification;

import static pl.edu.agh.inz.reactive.games.rainbow.RainbowGame.Level;
import static pl.edu.agh.inz.reactive.R.drawable.*;

/**
 * Created by alek on 18.09.14.
 */
public class RainbowSpecification implements GameSpecification {

    private static final int SEC_PER_LEVEL = 60;

    private Level[] levels = new Level[] {
            new Level.Builder(5, SEC_PER_LEVEL).bg(rainbow_bg_notepad1).targets(1, 0.33, 20, rainbow_t_letter_a).build(),
            new Level.Builder(5, SEC_PER_LEVEL).bg(rainbow_bg_notepad1).targets(1, 0.33, 15, rainbow_t_letter_b).build(),
            new Level.Builder(5, SEC_PER_LEVEL).bg(rainbow_bg_notepad1).targets(1, 0.33, 10, rainbow_t_letter_a).build(),
            new Level.Builder(6, SEC_PER_LEVEL).bg(rainbow_bg_notepad1).targets(1, 0.33, 10, rainbow_t_pen).build(),
            new Level.Builder(6, SEC_PER_LEVEL).bg(rainbow_bg_notepad1).targets(1, 0.33, 10, rainbow_t_pen).build(),
            new Level.Builder(8, SEC_PER_LEVEL).bg(rainbow_bg_cook1).targets(1, 0.33, 10, rainbow_t_cake).others(1, 0.3, rainbow_t_bread).build(), // 6
            new Level.Builder(8, SEC_PER_LEVEL).bg(rainbow_bg_cook1).targets(1, 0.33, 10, rainbow_t_cake).others(2, 0.3, rainbow_t_bread).build(),
            new Level.Builder(8, SEC_PER_LEVEL).bg(rainbow_bg_4).targets(1, 0.3, 10, rainbow_t_torte).others(3, 0.3, rainbow_t_bread).build(),
            new Level.Builder(10, SEC_PER_LEVEL).bg(rainbow_bg_4).targets(1, 0.27, 10, rainbow_t_cook1).others(4, 0.3, rainbow_t_bread).build(),
            new Level.Builder(10, SEC_PER_LEVEL).bg(rainbow_bg_4).targets(1, 0.25, 10, rainbow_t_cook1).others(5, 0.3, rainbow_t_cake).build(),
            new Level.Builder(10, SEC_PER_LEVEL).bg(rainbow_bg_moto1).targets(2, 0.24, 8, rainbow_t_moto2).others(6, 0.26, rainbow_t_motorcycle).build(), // 11
            new Level.Builder(10, SEC_PER_LEVEL).bg(rainbow_bg_moto1).targets(2, 0.21, 8, rainbow_t_moto2).others(7, 0.22, rainbow_t_motorcycle).build(),
            new Level.Builder(10, SEC_PER_LEVEL).bg(rainbow_bg_2).targets(1, 0.18, 2, rainbow_t_moto1).others(8, 0.2, rainbow_t_moto2).build(),
            new Level.Builder(10, SEC_PER_LEVEL).bg(rainbow_bg_2).targets(1, 0.15, 8, rainbow_t_moto1).others(9, 0.15, rainbow_t_moto2).build(),
            new Level.Builder(10, SEC_PER_LEVEL).bg(rainbow_bg_2).targets(1, 0.12, 8, rainbow_t_motorcycle).others(10, 0.12, rainbow_t_moto2).build(),
            new Level.Builder(16, SEC_PER_LEVEL).bg(rainbow_bg_1).targets(2, 0.12, 8, rainbow_t_hen).others(10, 0.12, rainbow_t_pen).build(), // 16
            new Level.Builder(16, SEC_PER_LEVEL).bg(rainbow_bg_1).targets(3, 0.12, 8, rainbow_t_pony).others(10, 0.12, rainbow_t_moto2).build(),
            new Level.Builder(16, SEC_PER_LEVEL).bg(rainbow_bg_1).targets(4, 0.12, 8, rainbow_t_cat).others(10, 0.12, rainbow_t_cake).build(),
            new Level.Builder(16, SEC_PER_LEVEL).bg(rainbow_bg_1).targets(5, 0.12, 8, rainbow_t_cat).others(10, 0.12, rainbow_t_cake).build(),
            new Level.Builder(16, SEC_PER_LEVEL).bg(rainbow_bg_1).targets(6, 0.12, 8, rainbow_t_cat).others(10, 0.12, rainbow_t_cake).build(),
            new Level.Builder(20, SEC_PER_LEVEL).bg(rainbow_bg_3).targets(2, 0.12, 8, lodka).others(10, 0.12, rainbow_t_ship).build(), // 21
            new Level.Builder(20, SEC_PER_LEVEL).bg(rainbow_bg_3).targets(2, 0.12, 8, rainbow_t_ship).others(10, 0.12, rainbow_t_pirates).build(),
            new Level.Builder(20, SEC_PER_LEVEL).bg(rainbow_bg_3).targets(2, 0.12, 8, rainbow_t_ship).others(10, 0.12, rainbow_t_pirates).build(),
            new Level.Builder(20, SEC_PER_LEVEL).bg(rainbow_bg_3).targets(4, 0.12, 8, rainbow_t_ship).others(10, 0.12, rainbow_t_pirates).build(),
            new Level.Builder(20, SEC_PER_LEVEL).bg(rainbow_bg_3).targets(4, 0.12, 8, rainbow_t_ship).others(10, 0.12, rainbow_t_pirates).build(),
            new Level.Builder(25, SEC_PER_LEVEL).bg(rainbow_bg_bytecode).targets(1, 0.12, 5, rainbow_t_java).others(15, 0.12, rainbow_t_linux).build(), // 26
            new Level.Builder(25, SEC_PER_LEVEL).bg(rainbow_bg_bytecode).targets(2, 0.12, 5, rainbow_t_java).others(15, 0.12, rainbow_t_linux).build(),
            new Level.Builder(25, SEC_PER_LEVEL).bg(rainbow_bg_bytecode).targets(3, 0.12, 5, rainbow_t_java).others(15, 0.12, rainbow_t_linux).build(),
            new Level.Builder(25, SEC_PER_LEVEL).bg(rainbow_bg_bytecode).targets(3, 0.12, 5, rainbow_t_java).others(15, 0.12, rainbow_t_windows).build(),
            new Level.Builder(30, SEC_PER_LEVEL).bg(rainbow_bg_bytecode).targets(3, 0.12, 3, rainbow_t_java).others(15, 0.12, rainbow_t_windows).build(),


    };

    public RainbowGame.Level[] getLevels() {
        return levels;
    }

    @Override
    public int getMaxLevel() {
        return getLevels().length - 1;
    }
}
