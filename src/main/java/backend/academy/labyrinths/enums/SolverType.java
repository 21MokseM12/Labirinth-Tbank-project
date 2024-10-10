package backend.academy.labyrinths.enums;

public enum SolverType {

    DEEP_FIRST_SEARCH("Поиск в глубину");

    private final String name;

    SolverType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {return this.name;}
}
