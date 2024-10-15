package backend.academy.labyrinths.enums;

/**
 * Enum, содержащий тип генератора лабиринта
 */
public enum GeneratorType implements AlgorithmType {

    ELLER_GENERATOR("Алгоритм Эллера"),

    DFS("DFS");

    private final String name;

    GeneratorType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
