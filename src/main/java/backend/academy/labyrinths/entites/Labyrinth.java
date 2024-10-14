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

    public Labyrinth(Cell[][] grid) {
        this.width = grid[0].length;
        this.height = grid.length;
        this.grid = grid;
    }

    public void setStart(Coordinates coordinates) {
        grid[coordinates.x()][coordinates.y()].type(CellType.START);
        this.start = coordinates;
    }

    public void setFinish(Coordinates coordinates) {
        grid[coordinates.x()][coordinates.y()].type(CellType.FINISH);
        this.finish = coordinates;
    }
}
