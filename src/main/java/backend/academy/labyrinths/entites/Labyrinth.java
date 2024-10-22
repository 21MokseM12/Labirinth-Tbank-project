package backend.academy.labyrinths.entites;

import backend.academy.labyrinths.enums.CellType;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

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

    private final List<Coordinates> finish;

    private final Cell[][] grid;

    public Labyrinth(Cell[][] grid) {
        this.width = grid[0].length;
        this.height = grid.length;
        this.grid = grid;
        this.finish = new ArrayList<>();
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
     * Метод установления координат финиша в лабиринте
     * @param coordinates - список координат финиша
     */
    public void setFinish(List<Coordinates> coordinates) {
        coordinates.forEach(coordinate -> {
            grid[coordinate.x()][coordinate.y()].type(CellType.FINISH);
            finish.add(coordinate);
        });
    }

    /**
     * Метод установления координат финиша в лабиринте
     * @param coordinates - координаты финиша
     */
    public void setFinish(Coordinates coordinates) {
        grid[coordinates.x()][coordinates.y()].type(CellType.FINISH);
        this.finish.add(coordinates);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (Cell[] cells : this.grid) {
            for (Cell cell : cells) {
                result.append(cell);
            }
            result.append("\n");
        }

        return result.toString();
    }
}
