package backend.academy.labyrinths.enums;

import lombok.Getter;

/**
 * Enum, содержащий тип клетки: ПРОХОД, СТЕНА, ВХОД, ВЫХОД
 */
@Getter
public enum CellType {

    WALL("⬛", 0),

    PASSAGE("⬜", 2),

    START("\uD83D\uDFE9", 2),

    FINISH("\uD83D\uDFE5", 2),

    SOLVE("\uD83D\uDD25", 0),

    BARRIER("❌", 1),

    BOUNTY("$", 3);

    private final String color;

    private final int cost;

    CellType(String color, int cost) {
        this.color = color;
        this.cost = cost;
    }
}
