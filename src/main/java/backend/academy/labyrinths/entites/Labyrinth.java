package backend.academy.labyrinths.entites;

import backend.academy.labyrinths.enums.CellType;
import lombok.Getter;

@Getter
public final class Labyrinth {

    private final int height;

    private final int width;

    private final Cell[][] grid;

    public Labyrinth(int width, int height, Cell[][] grid) {
        this.width = width;
        this.height = height;
        this.grid = grid;
    }

    public void setStart(Coordinates coordinates) {
        grid[coordinates.X()][coordinates.Y()].type(CellType.START);
    }

    public void setFinish(Coordinates coordinates) {
        grid[coordinates.X()][coordinates.Y()].type(CellType.FINISH);
    }
}
