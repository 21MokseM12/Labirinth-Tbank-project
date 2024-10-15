package backend.academy.labyrinths.entites;

import backend.academy.labyrinths.enums.CellType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Класс клетки лабиринта
 * Содержит:
 * - координаты клетки в лабиринте
 * - тип клетки: ВХОД, ВЫХОД, ПРОХОД, СТЕНА
 */
@Getter
@Setter
@EqualsAndHashCode
public class Cell {

    private Coordinates coordinates;

    private CellType type;

    private boolean isVisited;

    public Cell(Coordinates coordinates, CellType type) {
        this.coordinates = coordinates;
        this.type = type;
        this.isVisited = false;
    }

    @Override
    public String toString() {
        return this.type.color();
    }
}
