package backend.academy.labyrinths.impl.solvers;

import backend.academy.labyrinths.entites.Cell;
import backend.academy.labyrinths.entites.Coordinates;
import backend.academy.labyrinths.entites.Labyrinth;
import backend.academy.labyrinths.enums.CellType;
import backend.academy.labyrinths.interfaces.solvers.LabyrinthSolver;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;
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
        Cell exit = grid[labyrinth.finish().getFirst().y()][labyrinth.finish().getFirst().x()];

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
        Coordinates newCoordinates = switch (currentDirection) {
            case UP -> new Coordinates(currentCell.coordinates().x() + 1, currentCell.coordinates().y());
            case RIGHT -> new Coordinates(currentCell.coordinates().x(), currentCell.coordinates().y() + 1);
            case DOWN -> new Coordinates(currentCell.coordinates().x() - 1, currentCell.coordinates().y());
            case LEFT -> new Coordinates(currentCell.coordinates().x(), currentCell.coordinates().y() - 1);
        };

        if (coordinatesInGrid(newCoordinates)) {
            return grid[newCoordinates.y()][newCoordinates.x()].type() != CellType.WALL;
        } else {
            return false;
        }
    }

    /**
     * Метод, проверяющий, можно ли совершить поворот налево
     * @return true, если поворот возможен, false в ином случае
     */
    private boolean canRotateLeft() {
        Coordinates newCoordinates = switch (currentDirection) {
            case UP -> new Coordinates(currentCell.coordinates().x() - 1, currentCell.coordinates().y());
            case RIGHT -> new Coordinates(currentCell.coordinates().x(), currentCell.coordinates().y() - 1);
            case DOWN -> new Coordinates(currentCell.coordinates().x() + 1, currentCell.coordinates().y());
            case LEFT -> new Coordinates(currentCell.coordinates().x(), currentCell.coordinates().y() + 1);
        };

        if (coordinatesInGrid(newCoordinates)) {
            return grid[newCoordinates.y()][newCoordinates.x()].type() != CellType.WALL;
        } else {
            return false;
        }
    }

    /**
     * Метод, проверяющий следующую клетку, является ли она проходом или нет
     * @return true, если следующая клетка - проход, false в ином случае
     */
    private boolean checkForwardCell() {
        Coordinates newCoordinates = getNewCoordinatesForForwardCell();
        if (coordinatesInGrid(newCoordinates)) {
            return grid[newCoordinates.y()][newCoordinates.x()].type() != CellType.WALL;
        } else {
            return false;
        }
    }

    /**
     * Метод, отвечающий за переход алгоритма к следующей доступной клетке
     * @return следующую доступную для рассмотрения алгоритма клетку
     * @throws NoSuchElementException - исключение, выбрасываемое, если координаты объекта клетки
     * выходят за границы поля лабиринта
     */
    private Cell moveForward() {
        Coordinates newCoordinates = getNewCoordinatesForForwardCell();
        if (coordinatesInGrid(newCoordinates)) {
            return grid[newCoordinates.y()][newCoordinates.x()];
        } else {
            throw new NoSuchElementException();
        }
    }

    /**
     * Метод, возвращающий объект координат в зависимости от текущего состояния поля currentDirection
     * @return объект класса Coordinates
     */
    private Coordinates getNewCoordinatesForForwardCell() {
        return switch (currentDirection) {
            case UP -> new Coordinates(currentCell.coordinates().x(), currentCell.coordinates().y() - 1);
            case RIGHT -> new Coordinates(currentCell.coordinates().x() + 1, currentCell.coordinates().y());
            case DOWN -> new Coordinates(currentCell.coordinates().x(), currentCell.coordinates().y() + 1);
            case LEFT -> new Coordinates(currentCell.coordinates().x() - 1, currentCell.coordinates().y());
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
     * Метод проверки координат на нахождение внутри допустимых границ поля лабиринта
     * @param coordinates - координаты, для проверки
     * @return true, если координаты входят в границы поля лабиринта, false, если нет
     */
    private boolean coordinatesInGrid(Coordinates coordinates) {
        return coordinates.x() >= 0 && coordinates.x() < grid[0].length
            && coordinates.y() >= 0 && coordinates.y() < grid.length;
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
