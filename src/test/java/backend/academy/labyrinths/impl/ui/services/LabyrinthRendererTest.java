package backend.academy.labyrinths.impl.ui.services;

import backend.academy.labyrinths.entites.Cell;
import backend.academy.labyrinths.entites.Coordinates;
import backend.academy.labyrinths.entites.Labyrinth;
import backend.academy.labyrinths.enums.CellType;
import backend.academy.labyrinths.interfaces.ui.services.Renderer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayDeque;
import java.util.Queue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LabyrinthRendererTest {

    private final Renderer render = new LabyrinthRenderer();

    private Labyrinth labyrinth;

    @BeforeEach
    void setUp() {
        labyrinth = mock(Labyrinth.class);
    }

    @Test
    void checkPrintLabyrinthDelay() throws InterruptedException {
        Cell cell1 = new Cell(new Coordinates(0, 0), CellType.PASSAGE);
        Cell cell2 = new Cell(new Coordinates(0, 1), CellType.WALL);
        Cell[] row1 = {cell1, cell2};
        Cell[][] grid = {row1};

        // Мокаем метод grid() у лабиринта
        when(labyrinth.grid()).thenReturn(grid);

        // Печать с задержкой в 0 миллисекунд
        render.printLabyrinthDelay(labyrinth, 0);

        // Проверяем, что был вызван метод grid() у объекта Labyrinth
        verify(labyrinth, times(1)).grid();
    }

    @Test
    void testRender() {
        Cell cell1 = new Cell(new Coordinates(0, 0), CellType.PASSAGE);
        Cell cell2 = new Cell(new Coordinates(0, 1), CellType.PASSAGE);
        Cell cell3 = new Cell(new Coordinates(1, 0), CellType.START);
        Cell cell4 = new Cell(new Coordinates(1, 1), CellType.FINISH);

        Queue<Cell> solveCells = new ArrayDeque<>();
        solveCells.add(cell1);
        solveCells.add(cell2);
        solveCells.add(cell3);
        solveCells.add(cell4);

        // Рендерим клетки
        render.render(solveCells);

        // Проверяем, что клетки, которые не являются START и FINISH, получили тип SOLVE
        assertEquals(CellType.SOLVE, cell1.type());
        assertEquals(CellType.SOLVE, cell2.type());

        // START и FINISH не должны измениться
        assertEquals(CellType.START, cell3.type());
        assertEquals(CellType.FINISH, cell4.type());
    }
}
