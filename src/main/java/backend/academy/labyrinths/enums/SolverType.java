package backend.academy.labyrinths.enums;

public enum SolverType implements AlgorithmType{

    DEEP_FIRST_SEARCH("Поиск в глубину"), WALL_FOLLOWER("Следование вдоль стен");

    private final String name;

    SolverType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {return this.name;}
}
