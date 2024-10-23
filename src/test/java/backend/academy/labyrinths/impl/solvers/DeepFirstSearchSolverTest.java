package backend.academy.labyrinths.impl.solvers;

import backend.academy.labyrinths.entites.Cell;
import backend.academy.labyrinths.entites.Coordinates;
import backend.academy.labyrinths.entites.Labyrinth;
import backend.academy.labyrinths.enums.CellType;
import backend.academy.labyrinths.impl.def.values.DefaultLabyrinthGridValuesTest;
import backend.academy.labyrinths.interfaces.solvers.LabyrinthSolver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayDeque;
import java.util.Optional;
import java.util.Queue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeepFirstSearchSolverTest {

    private LabyrinthSolver solver;

    private Labyrinth labyrinth;

    private Cell[][] grid;

    private Cell startCell;

    private Cell finishCell;

    @BeforeEach
    void setUp() {
        solver = new DeepFirstSearchSolver();

        labyrinth = new Labyrinth(
            new Cell[][] {
                new Cell[] {
                    new Cell(new Coordinates(0, 0), CellType.PASSAGE),
                    new Cell(new Coordinates(0, 1), CellType.PASSAGE),
                    new Cell(new Coordinates(0, 2), CellType.PASSAGE)
                },
                new Cell[] {
                    new Cell(new Coordinates(1, 0), CellType.PASSAGE),
                    new Cell(new Coordinates(1, 1), CellType.PASSAGE),
                    new Cell(new Coordinates(1, 2), CellType.PASSAGE)
                },
                new Cell[] {
                    new Cell(new Coordinates(2, 0), CellType.PASSAGE),
                    new Cell(new Coordinates(2, 1), CellType.PASSAGE),
                    new Cell(new Coordinates(2, 2), CellType.PASSAGE)
                }
            });
        this.grid = labyrinth.grid();
    }

    @Test
    void testSolvePathExists() {
        labyrinth.setStart(new Coordinates(0, 0));
        labyrinth.setFinish(new Coordinates(2, 2));
        this.startCell = labyrinth.grid()[labyrinth.start().y()][labyrinth.start().x()];
        this.finishCell = labyrinth.grid()[labyrinth.finish().getFirst().y()][labyrinth.finish().getFirst().x()];

        // Проверяем нахождение пути в простом лабиринте
        Optional<Queue<Cell>> result = solver.solve(labyrinth);
        assertTrue(result.isPresent(), "Алгоритм должен найти путь.");
        assertFalse(result.get().isEmpty(), "Путь не должен быть пустым.");
        assertEquals(startCell, result.get().peek(), "Первый элемент пути должен быть стартовой клеткой.");
        assertEquals(finishCell, ((ArrayDeque<Cell>) result.get()).peekLast(), "Последний элемент пути должен быть финишной клеткой.");
    }

    @Test
    void testSolveNoPath() {
        labyrinth.setStart(new Coordinates(0, 0));
        labyrinth.setFinish(new Coordinates(2, 2));
        this.startCell = labyrinth.grid()[labyrinth.start().y()][labyrinth.start().x()];
        this.finishCell = labyrinth.grid()[labyrinth.finish().getFirst().y()][labyrinth.finish().getFirst().x()];

        // Блокируем путь к финишу
        grid[2][1].type(CellType.WALL);
        grid[1][2].type(CellType.WALL);

        Optional<Queue<Cell>> result = solver.solve(labyrinth);
        assertTrue(result.isEmpty(), "Алгоритм не должен находить путь, если путь заблокирован.");
    }

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
