package backend.academy.labyrinths.impl.solvers;

import backend.academy.labyrinths.entites.Cell;
import backend.academy.labyrinths.entites.Labyrinth;
import backend.academy.labyrinths.enums.CellType;
import backend.academy.labyrinths.interfaces.solvers.LabyrinthSolver;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;
import java.util.Queue;

public class WallFollowerSolver implements LabyrinthSolver {

    private Cell[][] grid;

    private Direction currentDirection;

    private Cell currentCell;

    private Deque<Cell> deque;

    @Override
    public Optional<Queue<Cell>> solve(Labyrinth labyrinth) {
        this.grid = labyrinth.grid();
        deque = new ArrayDeque<>();
        currentDirection = Direction.RIGHT;

        Cell start = grid[labyrinth.start().Y()][labyrinth.start().X()];
        Cell exit = grid[labyrinth.finish().Y()][labyrinth.finish().X()];

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

    private void rightRotate() {
        currentDirection = switch (currentDirection) {
            case UP -> Direction.RIGHT;
            case RIGHT -> Direction.DOWN;
            case DOWN -> Direction.LEFT;
            case LEFT ->  Direction.UP;
        };
    }

    private void leftRotate() {
        currentDirection = switch (currentDirection) {
            case UP -> Direction.LEFT;
            case LEFT -> Direction.DOWN;
            case DOWN -> Direction.RIGHT;
            case RIGHT ->  Direction.UP;
        };
    }

    private boolean canRotateRight() {
        return switch (currentDirection) {
            case UP -> grid[currentCell.coordinates().Y()][currentCell.coordinates().X() + 1].type() != CellType.WALL;
            case RIGHT -> grid[currentCell.coordinates().Y() + 1][currentCell.coordinates().X()].type() != CellType.WALL;
            case DOWN -> grid[currentCell.coordinates().Y()][currentCell.coordinates().X() - 1].type() != CellType.WALL;
            case LEFT -> grid[currentCell.coordinates().Y() - 1][currentCell.coordinates().X()].type() != CellType.WALL;
        };
    }

    private boolean canRotateLeft() {
        return switch (currentDirection) {
            case UP -> grid[currentCell.coordinates().Y()][currentCell.coordinates().X() - 1].type() != CellType.WALL;
            case RIGHT -> grid[currentCell.coordinates().Y() - 1][currentCell.coordinates().X()].type() != CellType.WALL;
            case DOWN -> grid[currentCell.coordinates().Y()][currentCell.coordinates().X() + 1].type() != CellType.WALL;
            case LEFT -> grid[currentCell.coordinates().Y() + 1][currentCell.coordinates().X()].type() != CellType.WALL;
        };
    }

    private boolean checkForwardCell() {
        return switch (currentDirection) {
            case UP -> grid[currentCell.coordinates().Y() - 1][currentCell.coordinates().X()].type() != CellType.WALL;
            case RIGHT -> grid[currentCell.coordinates().Y()][currentCell.coordinates().X() + 1].type() != CellType.WALL;
            case DOWN -> grid[currentCell.coordinates().Y() + 1][currentCell.coordinates().X()].type() != CellType.WALL;
            case LEFT -> grid[currentCell.coordinates().Y()][currentCell.coordinates().X() - 1].type() != CellType.WALL;
        };
    }

    private Cell moveForward() {
        return switch (currentDirection) {
            case UP -> grid[currentCell.coordinates().Y() - 1][currentCell.coordinates().X()];
            case RIGHT -> grid[currentCell.coordinates().Y()][currentCell.coordinates().X() + 1];
            case DOWN -> grid[currentCell.coordinates().Y() + 1][currentCell.coordinates().X()];
            case LEFT -> grid[currentCell.coordinates().Y()][currentCell.coordinates().X() - 1];
        };
    }

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

    private enum Direction {
        UP,
        RIGHT,
        DOWN,
        LEFT
    }
}
