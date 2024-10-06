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

    public void setStart(int X, int Y) {
        if (X != 0 || X != height-1 || Y != 0 || Y != width-1) {
            throw new IllegalArgumentException("Старт не может быть в этом месте");
        }
        grid[X][Y] = new Cell(X, Y, CellType.START);
    }

    public void setFinish(int X, int Y) {
        if (X != 0 || X != height-1 || Y != 0 || Y != width-1) {
            throw new IllegalArgumentException("Финиш не может быть в этом месте");
        }
        grid[X][Y] = new Cell(X, Y, CellType.FINISH);
    }
}
