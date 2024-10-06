package backend.academy.labyrinths.entites;

import backend.academy.labyrinths.enums.CellType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class Cell {

    private int X;

    private int Y;

    private CellType type;

    @Override
    public String toString() {return this.type.getColor();}
}
