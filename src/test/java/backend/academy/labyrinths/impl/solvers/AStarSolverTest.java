package backend.academy.labyrinths.impl.solvers;

import backend.academy.labyrinths.entites.Cell;
import backend.academy.labyrinths.entites.Coordinates;
import backend.academy.labyrinths.entites.Labyrinth;
import backend.academy.labyrinths.enums.CellType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AStarSolverTest {

    private AStarSolver solver;

    private Labyrinth labyrinth;

    private Cell[][] grid;

    private Cell startCell;

    private Cell finishCell;

    @BeforeEach
    void setUp() {
        solver = new AStarSolver();
        labyrinth = mock(Labyrinth.class);
        grid = new Cell[3][3];

        // Инициализируем сетку с пустыми клетками (тип PASSAGE)
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                grid[y][x] = mock(Cell.class);
                when(grid[y][x].coordinates()).thenReturn(new Coordinates(x, y));
                when(grid[y][x].type()).thenReturn(CellType.PASSAGE);
//                when(grid[y][x].isVisited()).thenReturn(false);
            }
        }

        // Настройка стартовой и конечной клеток
        startCell = grid[0][0];
        finishCell = grid[2][2];

        when(labyrinth.grid()).thenReturn(grid);
        when(labyrinth.start()).thenReturn(new Coordinates(0, 0));
        when(labyrinth.finish()).thenReturn(List.of(new Coordinates(2, 2)));
    }

    @Test
    void testSolvePathExists() {
        // Проверяем нахождение пути в простом лабиринте
        Optional<Queue<Cell>> result = solver.solve(labyrinth);
        assertTrue(result.isPresent(), "Алгоритм должен найти путь.");
        assertFalse(result.get().isEmpty(), "Путь не должен быть пустым.");
        assertEquals(startCell, ((ArrayDeque<Cell>) result.get()).peekLast(), "Первый элемент пути должен быть стартовой клеткой.");
        assertEquals(finishCell, result.get().peek(), "Последний элемент пути должен быть финишной клеткой.");
    }

    @Test
    void testSolveNoPath() {
        // Блокируем путь к финишу
        when(grid[1][1].type()).thenReturn(CellType.WALL);
        when(grid[1][2].type()).thenReturn(CellType.WALL);
        when(grid[2][1].type()).thenReturn(CellType.WALL);

        Optional<Queue<Cell>> result = solver.solve(labyrinth);
        assertTrue(result.isEmpty(), "Алгоритм не должен находить путь, если путь заблокирован.");
    }

    @Test
    void testSolveSingleCellLabyrinth() {
        // Тест для лабиринта из одной клетки
        when(labyrinth.start()).thenReturn(new Coordinates(0, 0));
        when(labyrinth.finish()).thenReturn(List.of(new Coordinates(0, 0)));

        Optional<Queue<Cell>> result = solver.solve(labyrinth);
        assertTrue(result.isPresent(), "Путь должен быть найден, даже если лабиринт состоит из одной клетки.");
        assertEquals(1, result.get().size(), "Путь должен содержать одну клетку.");
        assertEquals(startCell, result.get().peek(), "Единственная клетка в пути должна быть стартовой клеткой.");
    }

    @Test
    void testSolveMultipleExits() {
        // Создаем лабиринт, где нужно использовать эвристику для поиска пути
        Cell finishCell2 = grid[1][1];  // Финиш 2 (ближе)

        when(labyrinth.finish()).thenReturn(List.of(new Coordinates(2, 2), new Coordinates(1, 1)));

        // Настраиваем сетку, чтобы пути были возможны
        when(grid[0][1].type()).thenReturn(CellType.PASSAGE);
        when(grid[1][1].type()).thenReturn(CellType.PASSAGE);  // Один из выходов
        when(grid[1][2].type()).thenReturn(CellType.PASSAGE);
        when(grid[2][2].type()).thenReturn(CellType.FINISH);

        // Запускаем алгоритм A*
        Optional<Queue<Cell>> result = solver.solve(labyrinth);

        // Проверяем, что путь был найден
        assertTrue(result.isPresent(), "Алгоритм должен найти путь.");
        assertFalse(result.get().isEmpty(), "Путь не должен быть пустым.");

        // Проверяем, что эвристика нашла ближайший выход
        Cell lastCell = ((ArrayDeque<Cell>) result.get()).peekFirst();
        assertTrue(lastCell.equals(finishCell2),
            "Алгоритм должен выбрать ближайший выход.");
    }
}
