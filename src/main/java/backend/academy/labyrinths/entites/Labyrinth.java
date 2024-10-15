package backend.academy.labyrinths.entites;

import backend.academy.labyrinths.enums.CellType;
import lombok.Getter;

/**
 * Класс лабиринта
 * Содержит:
 * - Параметры лабиринта: высота и ширина
 * - Координаты старта и финиша в лабиринте
 * - Массив клеток - сам лабиринт
 */

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

    /**
     * Метод установления координат старта в лабиринте
     * @param coordinates - координаты старта
     */
    public void setStart(Coordinates coordinates) {
        grid[coordinates.x()][coordinates.y()].type(CellType.START);
        this.start = coordinates;
    }

    /**
     * Метод установления координат старта в лабиринте
     * @param coordinates - координаты старта
     */
    public void setFinish(Coordinates coordinates) {
        grid[coordinates.x()][coordinates.y()].type(CellType.FINISH);
        this.finish = coordinates;
    }
}
