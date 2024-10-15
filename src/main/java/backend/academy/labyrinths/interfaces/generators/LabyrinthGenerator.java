package backend.academy.labyrinths.interfaces.generators;

import backend.academy.labyrinths.entites.Labyrinth;

/**
 * Интерфейс алгоритма генерации лабиринта
 */
public interface LabyrinthGenerator {
    Labyrinth generate(int width, int height);
}
