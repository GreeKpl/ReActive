package pl.edu.agh.inz.reactive.games;

import pl.edu.agh.inz.reactive.games.GameLevel;

public interface GameSpecification {
    GameLevel[] getLevels();
    int getMaxLevel();
}
