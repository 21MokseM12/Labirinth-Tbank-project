package backend.academy.labyrinths.impl.ui.services;

import backend.academy.labyrinths.entites.Cell;
import backend.academy.labyrinths.entites.Labyrinth;
import backend.academy.labyrinths.enums.CellType;
import backend.academy.labyrinths.interfaces.ui.services.Renderer;
import java.util.Queue;
import java.util.stream.Stream;

public class LabyrinthRenderer implements Renderer {

    private final UserDataManager dataManager = new UserDataManager();

    @Override
    public void printLabyrinthDelay(Labyrinth labyrinth, long msDelay) throws InterruptedException {
        printNewLine();
        for (Cell[] arr : labyrinth.grid()) {
            Stream.of(arr).forEach(System.out::print);
            printNewLine();
            Thread.sleep(msDelay);
        }
    }

    @Override
    public void render(Queue<Cell> solveCells) {
        while (!solveCells.isEmpty()) {
            Cell cell = solveCells.poll();
            if (!cell.type().equals(CellType.START) &&
                !cell.type().equals(CellType.FINISH)) {
                cell.type(CellType.SOLVE);
            }
        }
    }

    private void printNewLine() {
        dataManager.write("\n");
    }
}
