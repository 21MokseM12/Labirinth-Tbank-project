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
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WallFollowerSolverTest {

    private LabyrinthSolver solver;

    private Labyrinth labyrinth;

    private Cell[][] grid;

    @BeforeEach
    public void setUp() {
        solver = new WallFollowerSolver();
        labyrinth = mock(Labyrinth.class);
        grid = new Cell[3][3];

        // Создаем mock для клеток лабиринта
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                grid[y][x] = mock(Cell.class);
                when(grid[y][x].coordinates()).thenReturn(new Coordinates(x, y));
            }
        }

        // Стартовая и финишная клетки
        when(labyrinth.start()).thenReturn(new Coordinates(0, 0));
        when(labyrinth.finish()).thenReturn(List.of(new Coordinates(2, 2)));
        when(labyrinth.grid()).thenReturn(grid);
    }

    @Test
    public void checkSolveFindsPath() {
        // Устанавливаем типы клеток
        when(grid[0][0].type()).thenReturn(CellType.START);
        when(grid[0][1].type()).thenReturn(CellType.PASSAGE);
        when(grid[0][2].type()).thenReturn(CellType.PASSAGE);
        when(grid[1][2].type()).thenReturn(CellType.PASSAGE);
        when(grid[2][2].type()).thenReturn(CellType.FINISH);

        // Устанавливаем тип для клеток, чтобы они не были стенами
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if (grid[y][x].type() == null) {
                    when(grid[y][x].type()).thenReturn(CellType.PASSAGE);
                }
            }
        }

        // Запуск метода поиска пути
        Optional<Queue<Cell>> result = solver.solve(labyrinth);

        // Проверка, что путь найден
        assertTrue(result.isPresent());
        Queue<Cell> path = result.get();
        assertEquals(5, path.size()); // путь должен содержать 5 клеток (включая старт и финиш)
    }

    @Test
    public void checkSolveNoPath() {
        // Блокируем путь к финишу
        when(grid[2][1].type()).thenReturn(CellType.WALL);
        when(grid[1][2].type()).thenReturn(CellType.WALL);

        // Запуск метода поиска пути
        Optional<Queue<Cell>> result = solver.solve(labyrinth);

        // Проверка, что путь не найден
        assertTrue(result.isEmpty());
    }

    @Test
    void checkRightRotationDuringSolve() {
        // Устанавливаем типы клеток (например, все клетки проходимы)
        when(grid[0][0].type()).thenReturn(CellType.START);
        when(grid[0][1].type()).thenReturn(CellType.PASSAGE);
        when(grid[0][2].type()).thenReturn(CellType.PASSAGE);
        when(grid[1][2].type()).thenReturn(CellType.PASSAGE);
        when(grid[2][2].type()).thenReturn(CellType.FINISH);

        // Устанавливаем тип для всех остальных клеток, чтобы они не были стенами
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if (grid[y][x].type() == null) {
                    when(grid[y][x].type()).thenReturn(CellType.PASSAGE);
                }
            }
        }

        // Запускаем метод solve, который внутри использует rightRotate()
        Optional<Queue<Cell>> result = solver.solve(labyrinth);

        // Проверяем, что путь найден
        assertTrue(result.isPresent());

        // Дополнительно можно проверить, что алгоритм не зациклился и использовал повороты корректно
        Queue<Cell> path = result.get();
        assertEquals(5, path.size()); // путь должен содержать 5 клеток (включая старт и финиш)
    }

    @Test
    void checkMoveForwardDuringSolve() {
        // Устанавливаем типы клеток (например, все клетки проходимы)
        when(grid[0][0].type()).thenReturn(CellType.START);
        when(grid[0][1].type()).thenReturn(CellType.PASSAGE);
        when(grid[0][2].type()).thenReturn(CellType.PASSAGE);
        when(grid[1][2].type()).thenReturn(CellType.PASSAGE);
        when(grid[2][2].type()).thenReturn(CellType.FINISH);

        // Устанавливаем тип для всех остальных клеток
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if (grid[y][x].type() == null) {
                    when(grid[y][x].type()).thenReturn(CellType.PASSAGE);
                }
            }
        }

        // Запуск метода solve
        Optional<Queue<Cell>> result = solver.solve(labyrinth);

        // Проверяем, что путь был найден
        assertTrue(result.isPresent());

        // Получаем путь, по которому прошел алгоритм
        Queue<Cell> path = result.get();

        // Проверяем, что первая клетка - старт
        assertEquals(grid[0][0], path.poll()); // первая клетка должна быть стартом

        // Проверяем, что алгоритм перемещался правильно, и конечная клетка - это финиш
        while (!path.isEmpty()) {
            Cell cell = path.poll();
            if (path.isEmpty()) {
                assertEquals(grid[2][2], cell);
            }
        }
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

        assertEquals(denied, solve);
    }
}

