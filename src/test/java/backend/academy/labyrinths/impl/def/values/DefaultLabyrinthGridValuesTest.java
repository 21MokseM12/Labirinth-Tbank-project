package backend.academy.labyrinths.impl.def.values;

import backend.academy.labyrinths.entites.Cell;
import backend.academy.labyrinths.entites.Coordinates;
import backend.academy.labyrinths.entites.Labyrinth;
import backend.academy.labyrinths.enums.CellType;
import lombok.Getter;
import java.util.ArrayDeque;
import java.util.Queue;

public class DefaultLabyrinthGridValuesTest {

    @Getter private final static Coordinates start = new Coordinates(1, 1);

    @Getter private final static Coordinates finish = new Coordinates(9, 9);

    public static Labyrinth getLabyrinthWithoutCycle() {
        return new Labyrinth(new Cell[][] {
            new Cell[] {
                new Cell(new Coordinates(0, 0), CellType.WALL),
                new Cell(new Coordinates(1, 0), CellType.WALL),
                new Cell(new Coordinates(2, 0), CellType.WALL),
                new Cell(new Coordinates(3, 0), CellType.WALL),
                new Cell(new Coordinates(4, 0), CellType.WALL),
                new Cell(new Coordinates(5, 0), CellType.WALL),
                new Cell(new Coordinates(6, 0), CellType.WALL),
                new Cell(new Coordinates(7, 0), CellType.WALL),
                new Cell(new Coordinates(8, 0), CellType.WALL),
                new Cell(new Coordinates(9, 0), CellType.WALL),
                new Cell(new Coordinates(10,0), CellType.WALL)
            },
            new Cell[] {
                new Cell(new Coordinates(0, 1), CellType.WALL),
                new Cell(new Coordinates(1, 1), CellType.PASSAGE),
                new Cell(new Coordinates(2, 1), CellType.PASSAGE),
                new Cell(new Coordinates(3, 1), CellType.PASSAGE),
                new Cell(new Coordinates(4, 1), CellType.WALL),
                new Cell(new Coordinates(5, 1), CellType.PASSAGE),
                new Cell(new Coordinates(6, 1), CellType.PASSAGE),
                new Cell(new Coordinates(7, 1), CellType.PASSAGE),
                new Cell(new Coordinates(8, 1), CellType.WALL),
                new Cell(new Coordinates(9, 1), CellType.PASSAGE),
                new Cell(new Coordinates(10, 1), CellType.WALL)
            },
            new Cell[] {
                new Cell(new Coordinates(0, 2), CellType.WALL),
                new Cell(new Coordinates(1, 2), CellType.WALL),
                new Cell(new Coordinates(2, 2), CellType.WALL),
                new Cell(new Coordinates(3, 2), CellType.PASSAGE),
                new Cell(new Coordinates(4, 2), CellType.WALL),
                new Cell(new Coordinates(5, 2), CellType.WALL),
                new Cell(new Coordinates(6, 2), CellType.WALL),
                new Cell(new Coordinates(7, 2), CellType.PASSAGE),
                new Cell(new Coordinates(8, 2), CellType.WALL),
                new Cell(new Coordinates(9, 2), CellType.PASSAGE),
                new Cell(new Coordinates(10, 2), CellType.WALL)
            },
            new Cell[] {
                new Cell(new Coordinates(0, 3), CellType.WALL),
                new Cell(new Coordinates(1, 3), CellType.PASSAGE),
                new Cell(new Coordinates(2, 3), CellType.PASSAGE),
                new Cell(new Coordinates(3, 3), CellType.PASSAGE),
                new Cell(new Coordinates(4, 3), CellType.PASSAGE),
                new Cell(new Coordinates(5, 3), CellType.PASSAGE),
                new Cell(new Coordinates(6, 3), CellType.PASSAGE),
                new Cell(new Coordinates(7, 3), CellType.PASSAGE),
                new Cell(new Coordinates(8, 3), CellType.WALL),
                new Cell(new Coordinates(9, 3), CellType.PASSAGE),
                new Cell(new Coordinates(10, 3), CellType.WALL)
            },
            new Cell[] {
                new Cell(new Coordinates(0, 4), CellType.WALL),
                new Cell(new Coordinates(1, 4), CellType.PASSAGE),
                new Cell(new Coordinates(2, 4), CellType.WALL),
                new Cell(new Coordinates(3, 4), CellType.PASSAGE),
                new Cell(new Coordinates(4, 4), CellType.WALL),
                new Cell(new Coordinates(5, 4), CellType.WALL),
                new Cell(new Coordinates(6, 4), CellType.WALL),
                new Cell(new Coordinates(7, 4), CellType.PASSAGE),
                new Cell(new Coordinates(8, 4), CellType.WALL),
                new Cell(new Coordinates(9, 4), CellType.PASSAGE),
                new Cell(new Coordinates(10, 4), CellType.WALL)
            },
            new Cell[] {
                new Cell(new Coordinates(0, 5), CellType.WALL),
                new Cell(new Coordinates(1, 5), CellType.PASSAGE),
                new Cell(new Coordinates(2, 5), CellType.WALL),
                new Cell(new Coordinates(3, 5), CellType.PASSAGE),
                new Cell(new Coordinates(4, 5), CellType.PASSAGE),
                new Cell(new Coordinates(5, 5), CellType.PASSAGE),
                new Cell(new Coordinates(6, 5), CellType.WALL),
                new Cell(new Coordinates(7, 5), CellType.PASSAGE),
                new Cell(new Coordinates(8, 5), CellType.PASSAGE),
                new Cell(new Coordinates(9, 5), CellType.PASSAGE),
                new Cell(new Coordinates(10, 5), CellType.WALL)
            },
            new Cell[] {
                new Cell(new Coordinates(0, 6), CellType.WALL),
                new Cell(new Coordinates(1, 6), CellType.WALL),
                new Cell(new Coordinates(2, 6), CellType.WALL),
                new Cell(new Coordinates(3, 6), CellType.PASSAGE),
                new Cell(new Coordinates(4, 6), CellType.WALL),
                new Cell(new Coordinates(5, 6), CellType.PASSAGE),
                new Cell(new Coordinates(6, 6), CellType.WALL),
                new Cell(new Coordinates(7, 6), CellType.PASSAGE),
                new Cell(new Coordinates(8, 6), CellType.WALL),
                new Cell(new Coordinates(9, 6), CellType.PASSAGE),
                new Cell(new Coordinates(10, 6), CellType.WALL)
            },
            new Cell[] {
                new Cell(new Coordinates(0, 7), CellType.WALL),
                new Cell(new Coordinates(1, 7), CellType.PASSAGE),
                new Cell(new Coordinates(2, 7), CellType.PASSAGE),
                new Cell(new Coordinates(3, 7), CellType.PASSAGE),
                new Cell(new Coordinates(4, 7), CellType.WALL),
                new Cell(new Coordinates(5, 7), CellType.PASSAGE),
                new Cell(new Coordinates(6, 7), CellType.WALL),
                new Cell(new Coordinates(7, 7), CellType.PASSAGE),
                new Cell(new Coordinates(8, 7), CellType.WALL),
                new Cell(new Coordinates(9, 7), CellType.PASSAGE),
                new Cell(new Coordinates(10, 7), CellType.WALL)
            },
            new Cell[] {
                new Cell(new Coordinates(0, 8), CellType.WALL),
                new Cell(new Coordinates(1, 8), CellType.PASSAGE),
                new Cell(new Coordinates(2, 8), CellType.WALL),
                new Cell(new Coordinates(3, 8), CellType.WALL),
                new Cell(new Coordinates(4, 8), CellType.WALL),
                new Cell(new Coordinates(5, 8), CellType.WALL),
                new Cell(new Coordinates(6, 8), CellType.WALL),
                new Cell(new Coordinates(7, 8), CellType.PASSAGE),
                new Cell(new Coordinates(8, 8), CellType.WALL),
                new Cell(new Coordinates(9, 8), CellType.PASSAGE),
                new Cell(new Coordinates(10, 8), CellType.WALL)
            },
            new Cell[] {
                new Cell(new Coordinates(0, 9), CellType.WALL),
                new Cell(new Coordinates(1, 9), CellType.PASSAGE),
                new Cell(new Coordinates(2, 9), CellType.PASSAGE),
                new Cell(new Coordinates(3, 9), CellType.PASSAGE),
                new Cell(new Coordinates(4, 9), CellType.PASSAGE),
                new Cell(new Coordinates(5, 9), CellType.PASSAGE),
                new Cell(new Coordinates(6, 9), CellType.WALL),
                new Cell(new Coordinates(7, 9), CellType.PASSAGE),
                new Cell(new Coordinates(8, 9), CellType.WALL),
                new Cell(new Coordinates(9, 9), CellType.PASSAGE),
                new Cell(new Coordinates(10, 9), CellType.WALL)
            },
            new Cell[] {
                new Cell(new Coordinates(0, 10), CellType.WALL),
                new Cell(new Coordinates(1, 10), CellType.WALL),
                new Cell(new Coordinates(2, 10), CellType.WALL),
                new Cell(new Coordinates(3, 10), CellType.WALL),
                new Cell(new Coordinates(4, 10), CellType.WALL),
                new Cell(new Coordinates(5, 10), CellType.WALL),
                new Cell(new Coordinates(6, 10), CellType.WALL),
                new Cell(new Coordinates(7, 10), CellType.WALL),
                new Cell(new Coordinates(8, 10), CellType.WALL),
                new Cell(new Coordinates(9, 10), CellType.WALL),
                new Cell(new Coordinates(10, 10), CellType.WALL)
            }
        });
    }

    public static Labyrinth getLabyrinthWithCycle() {
        return new Labyrinth(new Cell[][] {
            new Cell[] {
                new Cell(new Coordinates(0, 0), CellType.WALL),
                new Cell(new Coordinates(1, 0), CellType.WALL),
                new Cell(new Coordinates(2, 0), CellType.WALL),
                new Cell(new Coordinates(3, 0), CellType.WALL),
                new Cell(new Coordinates(4, 0), CellType.WALL),
                new Cell(new Coordinates(5, 0), CellType.WALL),
                new Cell(new Coordinates(6, 0), CellType.WALL),
                new Cell(new Coordinates(7, 0), CellType.WALL),
                new Cell(new Coordinates(8, 0), CellType.WALL),
                new Cell(new Coordinates(9, 0), CellType.WALL),
                new Cell(new Coordinates(10, 0), CellType.WALL)
            },
            new Cell[] {
                new Cell(new Coordinates(0, 1), CellType.WALL),
                new Cell(new Coordinates(1, 1), CellType.PASSAGE),
                new Cell(new Coordinates(2, 1), CellType.PASSAGE),
                new Cell(new Coordinates(3, 1), CellType.PASSAGE),
                new Cell(new Coordinates(4, 1), CellType.PASSAGE),
                new Cell(new Coordinates(5, 1), CellType.PASSAGE),
                new Cell(new Coordinates(6, 1), CellType.PASSAGE),
                new Cell(new Coordinates(7, 1), CellType.PASSAGE),
                new Cell(new Coordinates(8, 1), CellType.WALL),
                new Cell(new Coordinates(9, 1), CellType.PASSAGE),
                new Cell(new Coordinates(10,1), CellType.WALL)
            },
            new Cell[] {
                new Cell(new Coordinates(0, 2), CellType.WALL),
                new Cell(new Coordinates(1, 2), CellType.WALL),
                new Cell(new Coordinates(2, 2), CellType.WALL),
                new Cell(new Coordinates(3, 2), CellType.PASSAGE),
                new Cell(new Coordinates(4, 2), CellType.WALL),
                new Cell(new Coordinates(5, 2), CellType.WALL),
                new Cell(new Coordinates(6, 2), CellType.WALL),
                new Cell(new Coordinates(7, 2), CellType.PASSAGE),
                new Cell(new Coordinates(8, 2), CellType.WALL),
                new Cell(new Coordinates(9, 2), CellType.PASSAGE),
                new Cell(new Coordinates(10, 2),CellType.WALL)
            },
            new Cell[] {
                new Cell(new Coordinates(0, 3), CellType.WALL),
                new Cell(new Coordinates(1, 3), CellType.PASSAGE),
                new Cell(new Coordinates(2, 3), CellType.PASSAGE),
                new Cell(new Coordinates(3, 3), CellType.PASSAGE),
                new Cell(new Coordinates(4, 3), CellType.PASSAGE),
                new Cell(new Coordinates(5, 3), CellType.PASSAGE),
                new Cell(new Coordinates(6, 3), CellType.PASSAGE),
                new Cell(new Coordinates(7, 3), CellType.PASSAGE),
                new Cell(new Coordinates(8, 3), CellType.WALL),
                new Cell(new Coordinates(9, 3), CellType.PASSAGE),
                new Cell(new Coordinates(10, 3),CellType.WALL)
            },
            new Cell[] {
                new Cell(new Coordinates(0, 4), CellType.WALL),
                new Cell(new Coordinates(1, 4), CellType.PASSAGE),
                new Cell(new Coordinates(2, 4), CellType.WALL),
                new Cell(new Coordinates(3, 4), CellType.PASSAGE),
                new Cell(new Coordinates(4, 4), CellType.WALL),
                new Cell(new Coordinates(5, 4), CellType.WALL),
                new Cell(new Coordinates(6, 4), CellType.WALL),
                new Cell(new Coordinates(7, 4), CellType.PASSAGE),
                new Cell(new Coordinates(8, 4), CellType.WALL),
                new Cell(new Coordinates(9, 4), CellType.PASSAGE),
                new Cell(new Coordinates(10, 4),CellType.WALL)
            },
            new Cell[] {
                new Cell(new Coordinates(0, 5), CellType.WALL),
                new Cell(new Coordinates(1, 5), CellType.PASSAGE),
                new Cell(new Coordinates(2, 5), CellType.WALL),
                new Cell(new Coordinates(3, 5), CellType.PASSAGE),
                new Cell(new Coordinates(4, 5), CellType.PASSAGE),
                new Cell(new Coordinates(5, 5), CellType.PASSAGE),
                new Cell(new Coordinates(6, 5), CellType.WALL),
                new Cell(new Coordinates(7, 5), CellType.PASSAGE),
                new Cell(new Coordinates(8, 5), CellType.PASSAGE),
                new Cell(new Coordinates(9, 5), CellType.PASSAGE),
                new Cell(new Coordinates(10, 5),CellType.WALL)
            },
            new Cell[] {
                new Cell(new Coordinates(0, 6), CellType.WALL),
                new Cell(new Coordinates(1, 6), CellType.WALL),
                new Cell(new Coordinates(2, 6), CellType.WALL),
                new Cell(new Coordinates(3, 6), CellType.PASSAGE),
                new Cell(new Coordinates(4, 6), CellType.WALL),
                new Cell(new Coordinates(5, 6), CellType.PASSAGE),
                new Cell(new Coordinates(6, 6), CellType.WALL),
                new Cell(new Coordinates(7, 6), CellType.PASSAGE),
                new Cell(new Coordinates(8, 6), CellType.WALL),
                new Cell(new Coordinates(9, 6), CellType.PASSAGE),
                new Cell(new Coordinates(10, 6),CellType.WALL)
            },
            new Cell[] {
                new Cell(new Coordinates(0, 7), CellType.WALL),
                new Cell(new Coordinates(1, 7), CellType.PASSAGE),
                new Cell(new Coordinates(2, 7), CellType.PASSAGE),
                new Cell(new Coordinates(3, 7), CellType.PASSAGE),
                new Cell(new Coordinates(4, 7), CellType.WALL),
                new Cell(new Coordinates(5, 7), CellType.PASSAGE),
                new Cell(new Coordinates(6, 7), CellType.WALL),
                new Cell(new Coordinates(7, 7), CellType.PASSAGE),
                new Cell(new Coordinates(8, 7), CellType.WALL),
                new Cell(new Coordinates(9, 7), CellType.PASSAGE),
                new Cell(new Coordinates(10, 7),CellType.WALL)
            },
            new Cell[] {
                new Cell(new Coordinates(0, 8), CellType.WALL),
                new Cell(new Coordinates(1, 8), CellType.PASSAGE),
                new Cell(new Coordinates(2, 8), CellType.WALL),
                new Cell(new Coordinates(3, 8), CellType.WALL),
                new Cell(new Coordinates(4, 8), CellType.WALL),
                new Cell(new Coordinates(5, 8), CellType.WALL),
                new Cell(new Coordinates(6, 8), CellType.WALL),
                new Cell(new Coordinates(7, 8), CellType.PASSAGE),
                new Cell(new Coordinates(8, 8), CellType.WALL),
                new Cell(new Coordinates(9, 8), CellType.PASSAGE),
                new Cell(new Coordinates(10, 8),CellType.WALL)
            },
            new Cell[] {
                new Cell(new Coordinates(0, 9), CellType.WALL),
                new Cell(new Coordinates(1, 9), CellType.PASSAGE),
                new Cell(new Coordinates(2, 9), CellType.PASSAGE),
                new Cell(new Coordinates(3, 9), CellType.PASSAGE),
                new Cell(new Coordinates(4, 9), CellType.PASSAGE),
                new Cell(new Coordinates(5, 9), CellType.PASSAGE),
                new Cell(new Coordinates(6, 9), CellType.WALL),
                new Cell(new Coordinates(7, 9), CellType.PASSAGE),
                new Cell(new Coordinates(8, 9), CellType.WALL),
                new Cell(new Coordinates(9, 9), CellType.PASSAGE),
                new Cell(new Coordinates(10, 9),CellType.WALL)
            },
            new Cell[] {
                new Cell(new Coordinates(0, 10), CellType.WALL),
                new Cell(new Coordinates(1, 10), CellType.WALL),
                new Cell(new Coordinates(2, 10), CellType.WALL),
                new Cell(new Coordinates(3, 10), CellType.WALL),
                new Cell(new Coordinates(4, 10), CellType.WALL),
                new Cell(new Coordinates(5, 10), CellType.WALL),
                new Cell(new Coordinates(6, 10), CellType.WALL),
                new Cell(new Coordinates(7, 10), CellType.WALL),
                new Cell(new Coordinates(8, 10), CellType.WALL),
                new Cell(new Coordinates(9, 10), CellType.WALL),
                new Cell(new Coordinates(10, 10),CellType.WALL)
            }
        });
    }

    public static Labyrinth getLabyrinthWithoutSolve() {
        return new Labyrinth(new Cell[][] {
            new Cell[] {
                new Cell(new Coordinates(0, 0), CellType.WALL),
                new Cell(new Coordinates(1, 0), CellType.WALL),
                new Cell(new Coordinates(2, 0), CellType.WALL),
                new Cell(new Coordinates(3, 0), CellType.WALL),
                new Cell(new Coordinates(4, 0), CellType.WALL),
                new Cell(new Coordinates(5, 0), CellType.WALL),
                new Cell(new Coordinates(6, 0), CellType.WALL),
                new Cell(new Coordinates(7, 0), CellType.WALL),
                new Cell(new Coordinates(8, 0), CellType.WALL),
                new Cell(new Coordinates(9, 0), CellType.WALL),
                new Cell(new Coordinates(10, 0), CellType.WALL)
            },
            new Cell[] {
                new Cell(new Coordinates(0, 1), CellType.WALL),
                new Cell(new Coordinates(1, 1), CellType.PASSAGE),
                new Cell(new Coordinates(2, 1), CellType.PASSAGE),
                new Cell(new Coordinates(3, 1), CellType.WALL),
                new Cell(new Coordinates(4, 1), CellType.PASSAGE),
                new Cell(new Coordinates(5, 1), CellType.PASSAGE),
                new Cell(new Coordinates(6, 1), CellType.PASSAGE),
                new Cell(new Coordinates(7, 1), CellType.PASSAGE),
                new Cell(new Coordinates(8, 1), CellType.WALL),
                new Cell(new Coordinates(9, 1), CellType.PASSAGE),
                new Cell(new Coordinates(10,1), CellType.WALL)
            },
            new Cell[] {
                new Cell(new Coordinates(0, 2), CellType.WALL),
                new Cell(new Coordinates(1, 2), CellType.WALL),
                new Cell(new Coordinates(2, 2), CellType.WALL),
                new Cell(new Coordinates(3, 2), CellType.PASSAGE),
                new Cell(new Coordinates(4, 2), CellType.WALL),
                new Cell(new Coordinates(5, 2), CellType.WALL),
                new Cell(new Coordinates(6, 2), CellType.WALL),
                new Cell(new Coordinates(7, 2), CellType.PASSAGE),
                new Cell(new Coordinates(8, 2), CellType.WALL),
                new Cell(new Coordinates(9, 2), CellType.PASSAGE),
                new Cell(new Coordinates(10, 2),CellType.WALL)
            },
            new Cell[] {
                new Cell(new Coordinates(0, 3), CellType.WALL),
                new Cell(new Coordinates(1, 3), CellType.PASSAGE),
                new Cell(new Coordinates(2, 3), CellType.PASSAGE),
                new Cell(new Coordinates(3, 3), CellType.PASSAGE),
                new Cell(new Coordinates(4, 3), CellType.PASSAGE),
                new Cell(new Coordinates(5, 3), CellType.PASSAGE),
                new Cell(new Coordinates(6, 3), CellType.PASSAGE),
                new Cell(new Coordinates(7, 3), CellType.PASSAGE),
                new Cell(new Coordinates(8, 3), CellType.WALL),
                new Cell(new Coordinates(9, 3), CellType.PASSAGE),
                new Cell(new Coordinates(10, 3),CellType.WALL)
            },
            new Cell[] {
                new Cell(new Coordinates(0, 4), CellType.WALL),
                new Cell(new Coordinates(1, 4), CellType.PASSAGE),
                new Cell(new Coordinates(2, 4), CellType.WALL),
                new Cell(new Coordinates(3, 4), CellType.PASSAGE),
                new Cell(new Coordinates(4, 4), CellType.WALL),
                new Cell(new Coordinates(5, 4), CellType.WALL),
                new Cell(new Coordinates(6, 4), CellType.WALL),
                new Cell(new Coordinates(7, 4), CellType.PASSAGE),
                new Cell(new Coordinates(8, 4), CellType.WALL),
                new Cell(new Coordinates(9, 4), CellType.PASSAGE),
                new Cell(new Coordinates(10, 4),CellType.WALL)
            },
            new Cell[] {
                new Cell(new Coordinates(0, 5), CellType.WALL),
                new Cell(new Coordinates(1, 5), CellType.PASSAGE),
                new Cell(new Coordinates(2, 5), CellType.WALL),
                new Cell(new Coordinates(3, 5), CellType.PASSAGE),
                new Cell(new Coordinates(4, 5), CellType.PASSAGE),
                new Cell(new Coordinates(5, 5), CellType.PASSAGE),
                new Cell(new Coordinates(6, 5), CellType.WALL),
                new Cell(new Coordinates(7, 5), CellType.PASSAGE),
                new Cell(new Coordinates(8, 5), CellType.PASSAGE),
                new Cell(new Coordinates(9, 5), CellType.PASSAGE),
                new Cell(new Coordinates(10, 5), CellType.WALL)
            },
            new Cell[] {
                new Cell(new Coordinates(0, 6), CellType.WALL),
                new Cell(new Coordinates(1, 6), CellType.WALL),
                new Cell(new Coordinates(2, 6), CellType.WALL),
                new Cell(new Coordinates(3, 6), CellType.PASSAGE),
                new Cell(new Coordinates(4, 6), CellType.WALL),
                new Cell(new Coordinates(5, 6), CellType.PASSAGE),
                new Cell(new Coordinates(6, 6), CellType.WALL),
                new Cell(new Coordinates(7, 6), CellType.PASSAGE),
                new Cell(new Coordinates(8, 6), CellType.WALL),
                new Cell(new Coordinates(9, 6), CellType.PASSAGE),
                new Cell(new Coordinates(10, 6),CellType.WALL)
            },
            new Cell[] {
                new Cell(new Coordinates(0, 7), CellType.WALL),
                new Cell(new Coordinates(1, 7), CellType.PASSAGE),
                new Cell(new Coordinates(2, 7), CellType.PASSAGE),
                new Cell(new Coordinates(3, 7), CellType.PASSAGE),
                new Cell(new Coordinates(4, 7), CellType.WALL),
                new Cell(new Coordinates(5, 7), CellType.PASSAGE),
                new Cell(new Coordinates(6, 7), CellType.WALL),
                new Cell(new Coordinates(7, 7), CellType.PASSAGE),
                new Cell(new Coordinates(8, 7), CellType.WALL),
                new Cell(new Coordinates(9, 7), CellType.PASSAGE),
                new Cell(new Coordinates(10, 7),CellType.WALL)
            },
            new Cell[] {
                new Cell(new Coordinates(0, 8), CellType.WALL),
                new Cell(new Coordinates(1, 8), CellType.PASSAGE),
                new Cell(new Coordinates(2, 8), CellType.WALL),
                new Cell(new Coordinates(3, 8), CellType.WALL),
                new Cell(new Coordinates(4, 8), CellType.WALL),
                new Cell(new Coordinates(5, 8), CellType.WALL),
                new Cell(new Coordinates(6, 8), CellType.WALL),
                new Cell(new Coordinates(7, 8), CellType.PASSAGE),
                new Cell(new Coordinates(8, 8), CellType.WALL),
                new Cell(new Coordinates(9, 8), CellType.PASSAGE),
                new Cell(new Coordinates(10, 8),CellType.WALL)
            },
            new Cell[] {
                new Cell(new Coordinates(0, 9), CellType.WALL),
                new Cell(new Coordinates(1, 9), CellType.PASSAGE),
                new Cell(new Coordinates(2, 9), CellType.PASSAGE),
                new Cell(new Coordinates(3, 9), CellType.PASSAGE),
                new Cell(new Coordinates(4, 9), CellType.PASSAGE),
                new Cell(new Coordinates(5, 9), CellType.PASSAGE),
                new Cell(new Coordinates(6, 9), CellType.WALL),
                new Cell(new Coordinates(7, 9), CellType.PASSAGE),
                new Cell(new Coordinates(8, 9), CellType.WALL),
                new Cell(new Coordinates(9, 9), CellType.PASSAGE),
                new Cell(new Coordinates(10, 9),CellType.WALL)
            },
            new Cell[] {
                new Cell(new Coordinates(0, 10), CellType.WALL),
                new Cell(new Coordinates(1, 10), CellType.WALL),
                new Cell(new Coordinates(2, 10), CellType.WALL),
                new Cell(new Coordinates(3, 10), CellType.WALL),
                new Cell(new Coordinates(4, 10), CellType.WALL),
                new Cell(new Coordinates(5, 10), CellType.WALL),
                new Cell(new Coordinates(6, 10), CellType.WALL),
                new Cell(new Coordinates(7, 10), CellType.WALL),
                new Cell(new Coordinates(8, 10), CellType.WALL),
                new Cell(new Coordinates(9, 10), CellType.WALL),
                new Cell(new Coordinates(10, 10), CellType.WALL)
            },
        });
    }

    public static Queue<Cell> getSolveCycleLabyrinth(Labyrinth source) {
        Queue<Cell> result = new ArrayDeque<>();
        result.add(source.grid()[2][1]);
        result.add(source.grid()[3][1]);
        result.add(source.grid()[4][1]);
        result.add(source.grid()[5][1]);
        result.add(source.grid()[6][1]);
        result.add(source.grid()[7][1]);
        result.add(source.grid()[7][2]);
        result.add(source.grid()[7][3]);
        result.add(source.grid()[7][4]);
        result.add(source.grid()[7][5]);
        result.add(source.grid()[8][5]);
        result.add(source.grid()[9][5]);
        result.add(source.grid()[9][6]);
        result.add(source.grid()[9][7]);
        result.add(source.grid()[9][8]);
        return result;
    }

    public static Queue<Cell> getSolveNoCycleLabyrinth(Labyrinth source) {
        Queue<Cell> result = new ArrayDeque<>();
        result.add(source.grid()[2][1]);
        result.add(source.grid()[3][1]);
        result.add(source.grid()[3][2]);
        result.add(source.grid()[3][3]);
        result.add(source.grid()[4][3]);
        result.add(source.grid()[5][3]);
        result.add(source.grid()[6][3]);
        result.add(source.grid()[7][3]);
        result.add(source.grid()[7][4]);
        result.add(source.grid()[7][5]);
        result.add(source.grid()[8][5]);
        result.add(source.grid()[9][5]);
        result.add(source.grid()[9][6]);
        result.add(source.grid()[9][7]);
        result.add(source.grid()[9][8]);
        return result;
    }

}

