package backend.academy.labyrinths.enums;

public enum GeneratorType {
    ELLER_GENERATOR("Алгоритм Эллера"), DFS("DFS");

    private final String name;

    GeneratorType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
