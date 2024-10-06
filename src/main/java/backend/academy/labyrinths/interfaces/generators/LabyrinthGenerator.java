package backend.academy.labyrinths.interfaces.generators;

import backend.academy.labyrinths.entites.Labyrinth;

public interface LabyrinthGenerator {
    Labyrinth generate(int width, int height);
}
