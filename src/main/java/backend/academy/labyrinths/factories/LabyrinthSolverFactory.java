package backend.academy.labyrinths.factories;

import backend.academy.labyrinths.enums.SolverType;
import backend.academy.labyrinths.impl.solvers.DeepFirstSearchSolver;
import backend.academy.labyrinths.interfaces.solvers.LabyrinthSolver;
import java.util.HashMap;
import java.util.Map;

public class LabyrinthSolverFactory {

    private final Map<SolverType, LabyrinthSolver> solvers;

    public LabyrinthSolverFactory() {
        this.solvers = new HashMap<>();

        for (SolverType type : SolverType.values()) {
            if (type.equals(SolverType.DEEP_FIRST_SEARCH)) {
                solvers.put(type, new DeepFirstSearchSolver());
            }
        }
    }

    public LabyrinthSolver get(SolverType type) {
        return this.solvers.get(type);
    }
}