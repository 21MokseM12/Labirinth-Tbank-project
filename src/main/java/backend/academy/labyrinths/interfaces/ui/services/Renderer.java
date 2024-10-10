package backend.academy.labyrinths.interfaces.ui.services;

import backend.academy.labyrinths.entites.Cell;
import backend.academy.labyrinths.entites.Labyrinth;
import java.util.Queue;

public interface Renderer {
    void printLabyrinthDelay(Labyrinth labyrinth, long msDelay) throws InterruptedException;

    void render(Queue<Cell> solveCells);
}
