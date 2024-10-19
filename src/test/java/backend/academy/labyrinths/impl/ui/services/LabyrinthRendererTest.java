package backend.academy.labyrinths.impl.ui.services;

import backend.academy.labyrinths.entites.Cell;
import backend.academy.labyrinths.entites.Labyrinth;
import backend.academy.labyrinths.enums.CellType;
import backend.academy.labyrinths.impl.def.values.DefaultLabyrinthGridValuesTest;
import backend.academy.labyrinths.interfaces.ui.services.Renderer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Queue;

public class LabyrinthRendererTest {

    private final Renderer render = new LabyrinthRenderer();

    @Test
    public void checkRenderOfCycleLabyrinthSuccess() {
        final Labyrinth source = DefaultLabyrinthGridValuesTest.getLabyrinthWithCycle();
        Queue<Cell> solve = DefaultLabyrinthGridValuesTest.getSolveCycleLabyrinth(source);

        render.render(solve);

        boolean assertTrue = true;
        while (!solve.isEmpty()) {
            if (!solve.poll().type().equals(CellType.SOLVE)) {
                assertTrue = false;
            }
        }

        Assertions.assertTrue(assertTrue);
    }

    @Test
    public void checkRenderOfCycleLabyrinthDenied() {
        final Labyrinth source = DefaultLabyrinthGridValuesTest.getLabyrinthWithCycle();
        Queue<Cell> solve = DefaultLabyrinthGridValuesTest.getSolveCycleLabyrinth(source);

        render.render(solve);

        boolean assertTrue = false;
        while (!solve.isEmpty()) {
            if (!solve.poll().type().equals(CellType.SOLVE)) {
                assertTrue = true;
            }
        }

        Assertions.assertFalse(assertTrue);
    }

    @Test
    public void checkRenderOfNoCycleLabyrinthSuccess() {
        final Labyrinth source = DefaultLabyrinthGridValuesTest.getLabyrinthWithoutCycle();
        Queue<Cell> solve = DefaultLabyrinthGridValuesTest.getSolveNoCycleLabyrinth(source);

        render.render(solve);

        boolean assertTrue = true;
        while (!solve.isEmpty()) {
            if (!solve.poll().type().equals(CellType.SOLVE)) {
                assertTrue = false;
            }
        }

        Assertions.assertTrue(assertTrue);
    }

    @Test
    public void checkRenderOfNoCycleLabyrinthDenied() {
        final Labyrinth source = DefaultLabyrinthGridValuesTest.getLabyrinthWithoutCycle();
        Queue<Cell> solve = DefaultLabyrinthGridValuesTest.getSolveNoCycleLabyrinth(source);

        render.render(solve);

        boolean assertTrue = false;
        while (!solve.isEmpty()) {
            if (!solve.poll().type().equals(CellType.SOLVE)) {
                assertTrue = true;
            }
        }

        Assertions.assertFalse(assertTrue);
    }
}
