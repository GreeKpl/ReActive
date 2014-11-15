package pl.edu.agh.inz.reactive.games;

/**
 * Created by alek on 21.10.14.
 */
public interface GameLevel {

    String getPreparationText(); // for next level dialog
    int getPreparationImgResource(); // for next level dialog

    int getScoreNeeded();
    int getSeconds();
}
