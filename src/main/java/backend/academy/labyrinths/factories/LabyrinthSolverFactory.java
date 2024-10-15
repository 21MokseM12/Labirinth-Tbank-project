package backend.academy.labyrinths.factories;

import backend.academy.labyrinths.enums.SolverType;
import backend.academy.labyrinths.impl.solvers.DeepFirstSearchSolver;
import backend.academy.labyrinths.impl.solvers.WallFollowerSolver;
import backend.academy.labyrinths.interfaces.solvers.LabyrinthSolver;
import java.util.HashMap;
import java.util.Map;

/**
 * Фабрика алгоритмов поиска пути в лабиринте
 */
public class LabyrinthSolverFactory {

    private final Map<SolverType, LabyrinthSolver> solvers;

    public LabyrinthSolverFactory() {
        this.solvers = new HashMap<>();

        for (SolverType type : SolverType.values()) {
            if (type.equals(SolverType.DEEP_FIRST_SEARCH)) {
                solvers.put(type, new DeepFirstSearchSolver());
            } else if (type.equals(SolverType.WALL_FOLLOWER)) {
                solvers.put(type, new WallFollowerSolver());
            }
        }
    }

    /**
     * Метод, возвращающий конкретную реализацию алгоритма поиска пути в зависимости от переданного типа
     * @param type - тип алгоритма поиска пути лабиринта
     * @return - конкретная реализация алгоритма поиска пути
     */
    public LabyrinthSolver get(SolverType type) {
        return this.solvers.get(type);
    }
}
