package backend.academy.labyrinths.entites;

import backend.academy.labyrinths.enums.CellType;
import lombok.Getter;

@Getter
public final class Labyrinth {

    private final int height;

    private final int width;

    private Coordinates start;

    private Coordinates finish;

    private final Cell[][] grid;

    public Labyrinth(int width, int height, Cell[][] grid) {
        this.width = width * 2 + 1;
        this.height = height * 2 + 1;
        this.grid = grid;
    }

    public void setStart(Coordinates coordinates) {
        grid[coordinates.X()][coordinates.Y()].type(CellType.START);
        this.start = coordinates;
    }

    public void setFinish(Coordinates coordinates) {
        grid[coordinates.X()][coordinates.Y()].type(CellType.FINISH);
        this.finish = coordinates;
    }
}
