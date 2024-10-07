package backend.academy.labyrinths.impl.ui.services;

import backend.academy.labyrinths.entites.Cell;
import backend.academy.labyrinths.entites.Labyrinth;
import backend.academy.labyrinths.interfaces.ui.services.Renderer;
import java.util.stream.Stream;

public class LabyrinthRenderer implements Renderer {

    private final UserDataManager dataManager = new UserDataManager();

    @Override
    public void printLabyrinth(Labyrinth labyrinth) {
        printNewLine();
        for (Cell[] arr : labyrinth.grid()) {
            Stream.of(arr).forEach(System.out::print);
            printNewLine();
        }
    }

    private void printNewLine() {
        dataManager.write("\n");
    }
}
