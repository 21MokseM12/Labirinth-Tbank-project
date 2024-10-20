package backend.academy.labyrinths.impl.solvers;

import backend.academy.labyrinths.entites.Cell;
import backend.academy.labyrinths.entites.Labyrinth;
import backend.academy.labyrinths.impl.def.values.DefaultLabyrinthGridValuesTest;
import backend.academy.labyrinths.interfaces.solvers.LabyrinthSolver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import java.util.Queue;

public class WallFollowerTest {

    private final LabyrinthSolver solver = new WallFollowerSolver();

    @Test
    public void checkRightSolveSuccess() {
        Labyrinth source = DefaultLabyrinthGridValuesTest.getLabyrinthWithCycle();
        source.setStart(DefaultLabyrinthGridValuesTest.start());
        source.setFinish(DefaultLabyrinthGridValuesTest.finish());

        Optional<Queue<Cell>> solve = solver.solve(source);
        Optional<Queue<Cell>> denied = Optional.empty();

        Assertions.assertNotEquals(denied, solve);
    }

    @Test
    public void checkNoSolveSuccess() {
        Labyrinth source = DefaultLabyrinthGridValuesTest.getLabyrinthWithoutSolve();
        source.setStart(DefaultLabyrinthGridValuesTest.start());
        source.setFinish(DefaultLabyrinthGridValuesTest.finish());

        Optional<Queue<Cell>> solve = solver.solve(source);
        Optional<Queue<Cell>> denied = Optional.empty();

        Assertions.assertEquals(denied, solve);
    }
}
