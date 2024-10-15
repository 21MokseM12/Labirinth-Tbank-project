package backend.academy.labyrinths.impl.ui.services;

import backend.academy.labyrinths.entites.Cell;
import backend.academy.labyrinths.entites.Labyrinth;
import backend.academy.labyrinths.enums.CellType;
import backend.academy.labyrinths.interfaces.ui.services.Renderer;
import java.util.Queue;
import java.util.stream.Stream;

/**
 * Класс, реализующий рендер и вывод лабиринта в консоль
 */
public class LabyrinthRenderer implements Renderer {

    private final UserDataManager dataManager = new UserDataManager();

    /**
     * Метод печати лабиринта в консоль с задержкой
     * @param labyrinth - объект лабиринта
     * @param msDelay - задержка вывода в консоль
     * @throws InterruptedException - исключение, выбрасываемое в случае, если задержка не валидна
     */
    @Override
    public void printLabyrinthDelay(Labyrinth labyrinth, long msDelay) throws InterruptedException {
        printNewLine();
        for (Cell[] arr : labyrinth.grid()) {
            Stream.of(arr).forEach(System.out::print);
            printNewLine();
            Thread.sleep(msDelay);
        }
    }

    /**
     * Метод рендеринга лабиринта
     * @param solveCells - очередь клеток, которым нужно присвоить тип SOLVE
     */
    @Override
    public void render(Queue<Cell> solveCells) {
        while (!solveCells.isEmpty()) {
            Cell cell = solveCells.poll();
            if (!cell.type().equals(CellType.START)
                && !cell.type().equals(CellType.FINISH)) {
                cell.type(CellType.SOLVE);
            }
        }
    }

    /**
     * Метод перехода на новую строку
     */
    private void printNewLine() {
        dataManager.write("\n");
    }
}
