package pl.edu.agh.inz.reactive.games.fit;

import pl.edu.agh.inz.reactive.games.GameSpecification;

import static pl.edu.agh.inz.reactive.games.fit.FitGame.Level;
import static pl.edu.agh.inz.reactive.R.drawable.*;

/**
 * Created by Jacek on 2015-07-19.
 */
public class FitSpecification implements GameSpecification {

    private static final int SEC_PER_LEVEL = 3;

    private Level[] levels = new Level[] {
            //row, columns
            new Level.Builder(2, 2, SEC_PER_LEVEL).desc("nosorożec").image(fit_01).build(),
            new Level.Builder(2, 2, SEC_PER_LEVEL).desc("jaszczurki").image(fit_02).build(),
            new Level.Builder(2, 3, SEC_PER_LEVEL).desc("pies").image(fit_03).build(),
            new Level.Builder(2, 3, SEC_PER_LEVEL).desc("szczeniaki").image(fit_04).build(),
            new Level.Builder(3, 3, SEC_PER_LEVEL).desc("słonie").image(fit_05).build(),
            new Level.Builder(3, 3, SEC_PER_LEVEL).desc("niedźwiedź").image(fit_06).build(),
            new Level.Builder(3, 3, SEC_PER_LEVEL).desc("orły").image(fit_07).build(),
            new Level.Builder(3, 3, SEC_PER_LEVEL).desc("papugi").image(fit_08).build(),
            new Level.Builder(3, 3, SEC_PER_LEVEL).desc("koza").image(fit_09).build(),
            new Level.Builder(3, 3, SEC_PER_LEVEL).desc("lew").image(fit_10).build(),
            new Level.Builder(3, 4, SEC_PER_LEVEL).desc("lampart").image(fit_11).build(),
            new Level.Builder(3, 4, SEC_PER_LEVEL).desc("ryby").image(fit_12).build(),
            new Level.Builder(3, 4, SEC_PER_LEVEL).desc("wodospad").image(fit_13).build(),
            new Level.Builder(3, 4, SEC_PER_LEVEL).desc("jeziora").image(fit_14).build(),
            new Level.Builder(3, 4, SEC_PER_LEVEL).desc("plaża").image(fit_15).build(),
            new Level.Builder(3, 4, SEC_PER_LEVEL).desc("wodospady").image(fit_16).build(),
            new Level.Builder(3, 4, SEC_PER_LEVEL).desc("wybrzeże").image(fit_17).build(),
            new Level.Builder(3, 4, SEC_PER_LEVEL).desc("rzeka").image(fit_18).build(),
            new Level.Builder(3, 4, SEC_PER_LEVEL).desc("wodospady").image(fit_19).build(),
            new Level.Builder(3, 4, SEC_PER_LEVEL).desc("skały").image(fit_20).build(),
            new Level.Builder(4, 4, SEC_PER_LEVEL).desc("droga").image(fit_21).build(),
            new Level.Builder(4, 4, SEC_PER_LEVEL).desc("góry").image(fit_22).build(),
            new Level.Builder(4, 4, SEC_PER_LEVEL).desc("pole rzepaku").image(fit_23).build(),
            new Level.Builder(4, 4, SEC_PER_LEVEL).desc("drzewo").image(fit_24).build(),
            new Level.Builder(4, 4, SEC_PER_LEVEL).desc("ścieżka").image(fit_25).build(),
            new Level.Builder(4, 4, SEC_PER_LEVEL).desc("las").image(fit_26).build(),
            new Level.Builder(4, 4, SEC_PER_LEVEL).desc("zamek").image(fit_27).build(),
            new Level.Builder(4, 4, SEC_PER_LEVEL).desc("miasto").image(fit_28).build(),
            new Level.Builder(4, 4, SEC_PER_LEVEL).desc("zboże").image(fit_29).build(),
            new Level.Builder(4, 4, SEC_PER_LEVEL).desc("pomarańcze").image(fit_30).build(),
            new Level.Builder(4, 4, SEC_PER_LEVEL).desc("brzoskwinie").image(fit_31).build(),
            new Level.Builder(4, 4, SEC_PER_LEVEL).desc("owoce leśne").image(fit_32).build(),
            new Level.Builder(4, 4, SEC_PER_LEVEL).desc("jeżyny").image(fit_33).build(),
            new Level.Builder(4, 4, SEC_PER_LEVEL).desc("maliny").image(fit_34).build(),
            new Level.Builder(4, 4, SEC_PER_LEVEL).desc("truskawki").image(fit_35).build(),
            new Level.Builder(4, 5, SEC_PER_LEVEL).desc("wiśnie").image(fit_36).build(),
            new Level.Builder(4, 5, SEC_PER_LEVEL).desc("pszenica").image(fit_37).build(),
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
