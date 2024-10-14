package backend.academy.labyrinths.enums;

public enum CellType {

    WALL("\u001B[40m\u001B[30m...\u001B[0m"),

    PASSAGE("\u001B[47m\u001B[37m...\u001B[0m"),

    START("\u001B[42m\u001B[32m...\u001B[0m"),

    FINISH("\u001B[41m\u001B[31m...\u001B[0m"),

    SOLVE("\u001B[43m\u001B[33m...\u001B[0m");

    private final String color;

    CellType(String color) {
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }
}
