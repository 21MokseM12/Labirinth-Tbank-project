package backend.academy.labyrinths.interfaces.solvers;

import backend.academy.labyrinths.entites.Cell;
import backend.academy.labyrinths.entites.Labyrinth;
import java.util.Optional;
import java.util.Queue;

/**
 * Интерфейс алгоритма поиска пути в лабиринте
 */
public interface LabyrinthSolver {
    Optional<Queue<Cell>> solve(Labyrinth labyrinth);
}
