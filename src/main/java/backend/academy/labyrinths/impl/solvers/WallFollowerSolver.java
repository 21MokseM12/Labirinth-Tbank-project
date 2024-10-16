package backend.academy.labyrinths.impl.solvers;

import backend.academy.labyrinths.entites.Cell;
import backend.academy.labyrinths.entites.Labyrinth;
import backend.academy.labyrinths.enums.CellType;
import backend.academy.labyrinths.interfaces.solvers.LabyrinthSolver;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;
import java.util.Queue;

/**
 * Класс поиска пути в лабиринте алгоритмом следования вдоль стен
 */
public class WallFollowerSolver implements LabyrinthSolver {

    /**
     * Поле лабиринта
     */
    private Cell[][] grid;

    /**
     * Текущее направление, которое рассматривает алгоритм
     */
    private Direction currentDirection;

    /**
     * Текущая клетка, которую рассматривает алгоритм
     */
    private Cell currentCell;

    /**
     * Очередь, содержащая все точки найденного пути
     */
    private Deque<Cell> deque;

    /**
     * Терминальный метод, отвечающий за генерацию лабиринта
     * @param labyrinth - объект лабиринта
     * @return Optional объект, содержащий либо очередь со ссылками на клетки найденного пути, либо null,
     * если путь найден не был
     */
    @Override
    public Optional<Queue<Cell>> solve(Labyrinth labyrinth) {
        this.grid = labyrinth.grid();
        deque = new ArrayDeque<>();
        currentDirection = Direction.RIGHT;

        Cell start = grid[labyrinth.start().y()][labyrinth.start().x()];
        Cell exit = grid[labyrinth.finish().y()][labyrinth.finish().x()];

        currentCell = start;

        while (!currentCell.equals(exit)) {

            if (canRotateRight()) {
                rightRotate();
            } else if (!checkForwardCell() && canRotateLeft()) {
                leftRotate();
            } else if (!checkForwardCell()) {
                rightRotate();
                rightRotate();
                currentCell.isVisited(true);
                deque.addLast(currentCell);
            }
            markCellAsVisit();

            currentCell = moveForward();

            if (currentCell.equals(start)) {
                return Optional.empty();
            }
        }
        deque.addLast(exit);
        start.type(CellType.START);
        exit.type(CellType.FINISH);

        return Optional.of(deque);
    }

    /**
     * Метод, определяющий, в какую сторону следовать дальше при повороте налево
     */
    private void rightRotate() {
        currentDirection = switch (currentDirection) {
            case UP -> Direction.RIGHT;
            case RIGHT -> Direction.DOWN;
            case DOWN -> Direction.LEFT;
            case LEFT -> Direction.UP;
        };
    }

    /**
     * Метод, определяющий, в какую сторону следовать дальше при повороте направо
     */
    private void leftRotate() {
        currentDirection = switch (currentDirection) {
            case UP -> Direction.LEFT;
            case LEFT -> Direction.DOWN;
            case DOWN -> Direction.RIGHT;
            case RIGHT -> Direction.UP;
        };
    }

    /**
     * Метод, проверяющий, можно ли совершить поворот направо
     * @return true, если поворот возможен, false в ином случае
     */
    private boolean canRotateRight() {
        return switch (currentDirection) {
            case UP -> grid[currentCell.coordinates().y()][currentCell.coordinates().x() + 1].type() != CellType.WALL;
            case RIGHT ->
                grid[currentCell.coordinates().y() + 1][currentCell.coordinates().x()].type() != CellType.WALL;
            case DOWN -> grid[currentCell.coordinates().y()][currentCell.coordinates().x() - 1].type() != CellType.WALL;
            case LEFT -> grid[currentCell.coordinates().y() - 1][currentCell.coordinates().x()].type() != CellType.WALL;
        };
    }

    /**
     * Метод, проверяющий, можно ли совершить поворот налево
     * @return true, если поворот возможен, false в ином случае
     */
    private boolean canRotateLeft() {
        return switch (currentDirection) {
            case UP -> grid[currentCell.coordinates().y()][currentCell.coordinates().x() - 1].type() != CellType.WALL;
            case RIGHT ->
                grid[currentCell.coordinates().y() - 1][currentCell.coordinates().x()].type() != CellType.WALL;
            case DOWN -> grid[currentCell.coordinates().y()][currentCell.coordinates().x() + 1].type() != CellType.WALL;
            case LEFT -> grid[currentCell.coordinates().y() + 1][currentCell.coordinates().x()].type() != CellType.WALL;
        };
    }

    /**
     * Метод, проверяющий следующую клетку, является ли она проходом или нет
     * @return true, если следующая клетка - проход, false в ином случае
     */
    private boolean checkForwardCell() {
        return switch (currentDirection) {
            case UP -> grid[currentCell.coordinates().y() - 1][currentCell.coordinates().x()].type() != CellType.WALL;
            case RIGHT ->
                grid[currentCell.coordinates().y()][currentCell.coordinates().x() + 1].type() != CellType.WALL;
            case DOWN -> grid[currentCell.coordinates().y() + 1][currentCell.coordinates().x()].type() != CellType.WALL;
            case LEFT -> grid[currentCell.coordinates().y()][currentCell.coordinates().x() - 1].type() != CellType.WALL;
        };
    }

    /**
     * Метод, отвечающий за переход алгоритма к следующей доступной клетке
     * @return следующую доступную для рассмотрения алгоритма клетку
     */
    private Cell moveForward() {
        return switch (currentDirection) {
            case UP -> grid[currentCell.coordinates().y() - 1][currentCell.coordinates().x()];
            case RIGHT -> grid[currentCell.coordinates().y()][currentCell.coordinates().x() + 1];
            case DOWN -> grid[currentCell.coordinates().y() + 1][currentCell.coordinates().x()];
            case LEFT -> grid[currentCell.coordinates().y()][currentCell.coordinates().x() - 1];
        };
    }

    /**
     * Метод, помечающий текущую клетку как посещенную
     */
    private void markCellAsVisit() {
        if (!currentCell.isVisited()) {
            deque.addLast(currentCell);
            currentCell.isVisited(true);
        } else {
            if (moveForward().isVisited()) {
                deque.pollLast();
                currentCell.type(CellType.PASSAGE);
            }
        }
    }

    /**
     * Enum, содержащий все возможные расстояния: ВВЕРХ, ВНИЗ, ПРАВО, ЛЕВО
     */
    private enum Direction {
        UP,
        RIGHT,
        DOWN,
        LEFT
    }
}
