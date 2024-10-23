package backend.academy.labyrinths.enums;

/**
 * Enum, содержащий тип алгоритма решения лабиринта
 */

public enum SolverType implements AlgorithmType {

    DEEP_FIRST_SEARCH("Поиск в глубину"),

    WALL_FOLLOWER("Следование вдоль стен"),

    A_STAR("А*");

    private final String name;

    SolverType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
