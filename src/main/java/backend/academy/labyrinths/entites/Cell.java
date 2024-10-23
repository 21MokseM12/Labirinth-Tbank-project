package backend.academy.labyrinths.entites;

import backend.academy.labyrinths.enums.CellType;
import lombok.Getter;
import lombok.Setter;
import java.util.Objects;

/**
 * Класс клетки лабиринта
 * Содержит:
 * - координаты клетки в лабиринте
 * - тип клетки: ВХОД, ВЫХОД, ПРОХОД, СТЕНА
 */
@Getter
@Setter
public class Cell {

    private Coordinates coordinates;

    private CellType type;

    private int cost;

    private boolean isVisited;

    public Cell(Coordinates coordinates, CellType type) {
        this.coordinates = coordinates;
        this.type = type;
        this.cost = type.cost();
        this.isVisited = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return this.isVisited == cell.isVisited && this.cost == cell.cost
            && this.type.equals(cell.type)
            && this.coordinates.x() == cell.coordinates.x() && this.coordinates.y() == cell.coordinates.y();
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinates, type);
    }


    @Override
    public String toString() {
        return this.type.color();
    }
}
