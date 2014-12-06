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
            new Level.Builder(5, 3, 30).setImages(new Integer[] { three_apple, three_pear, three_plum }).build(), // 1
            new Level.Builder(10, 3, 50).setImages(new Integer[] { three_orange, three_apple, three_peach }).build(),
            new Level.Builder(10, 3, 50).setImages(new Integer[] { three_lemon, three_lime, three_pear }).build(),
            new Level.Builder(10, 3, 50).setImages(new Integer[] { three_mushroom_1, three_mushroom_2, three_toadstool }).build(),
            new Level.Builder(10, 3, 50).setImages(new Integer[] { three_kw01, three_kw02, three_kw03 }).build(),
            new Level.Builder(10, 3, 50).setImages(new Integer[] { three_kw01, three_kw12, three_kw13 }).build(), // 6
            new Level.Builder(10, 3, 50).setImages(new Integer[] { three_kw21, three_kw22, three_kw23 }).build(),
            new Level.Builder(10, 3, 50).setImages(new Integer[] { three_car_a_green, three_car_a_red,
                    three_car_a_purple, three_car_a_yellow,three_car_c_red, three_car_b_blue, three_car_b_orange }).build(),
            new Level.Builder(10, 3, 60).setImages(new Integer[] { three_car_a_red, three_car_b_orange, three_car_c_red }).build(),
            new Level.Builder(10, 4, 60).setImages(new Integer[] { three_clk11, three_clk12, three_clk01, three_clk02, three_clk03 }).build(),
            new Level.Builder(10, 4, 60).setImages(new Integer[] { three_clk01, three_clk02, three_clk03, three_clk11, three_clk12}).build(), // 11
            new Level.Builder(10, 4, 60).setImages(new Integer[] { three_kw21, three_kw22, three_kw23, three_kw31, three_kw32  }).withRotatedPattern().build(),
            new Level.Builder(10, 4, 60).setImages(new Integer[] { three_kw51, three_kw52, three_kw61, three_kw63  }).withRotatedPattern().build(),
            new Level.Builder(10, 4, 60).setImages(new Integer[] { three_sign_b21, three_sign_b22, three_sign_b23, three_sign_bcos }).build(),
            new Level.Builder(10, 4, 60).setImages(new Integer[] { three_sign_a12a, three_sign_a12b,
                    three_sign_a12c, three_sign_a12a_rev, three_sign_a12a_rev2 }).build(),
            new Level.Builder(10, 5, 60).setImages(new Integer[] { three_fig_1, three_fig_2, three_fig_3, three_fig_4, three_fig_5 }).build(), // 16
            new Level.Builder(10, 5, 60).setImages(new Integer[] { three_fig_1, three_fig_2, three_fig_3, three_fig_4, three_fig_5 }).build(),
            new Level.Builder(10, 5, 60).setImages(new Integer[] { three_butterfly1, three_butterfly2,
                    three_butterfly3, three_butterfly4, three_butterfly5,three_butterfly6 }).build(),
            new Level.Builder(10, 5, 60).setImages(new Integer[] { three_pic11, three_pic12, three_pic13, three_pic14, three_pic15  }).build(),
            new Level.Builder(10, 5, 60).setImages(new Integer[] { three_pic11, three_pic12, three_pic13, three_pic14, three_pic15  }).build()
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
