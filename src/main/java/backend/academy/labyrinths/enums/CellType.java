package backend.academy.labyrinths.enums;

import lombok.Getter;

/**
 * Enum, содержащий тип клетки: ПРОХОД, СТЕНА, ВХОД, ВЫХОД
 */
@Getter
public enum CellType {

    WALL("⬛", Integer.MAX_VALUE),

    PASSAGE("⬜", 2),

    START("\uD83D\uDFE9", 0),

    FINISH("\uD83D\uDFE5", 0),

    SOLVE("\uD83D\uDD25", 0),

    BARRIER("❌", 3),

    BOUNTY("⭐", 1);

    private final String color;

    private final int cost;

    CellType(String color, int cost) {
        this.color = color;
        this.cost = cost;
    }
}
