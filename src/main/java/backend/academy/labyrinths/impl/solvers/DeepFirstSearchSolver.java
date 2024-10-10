package backend.academy.labyrinths.impl.solvers;

import backend.academy.labyrinths.entites.Cell;
import backend.academy.labyrinths.entites.Labyrinth;
import backend.academy.labyrinths.enums.CellType;
import backend.academy.labyrinths.interfaces.solvers.LabyrinthSolver;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.Random;

public class DeepFirstSearchSolver implements LabyrinthSolver {

    private final Random random = new Random();

    private Deque<Cell> deque;

    @Override
    public Optional<Queue<Cell>> solve(Labyrinth labyrinth) {
        deque = new ArrayDeque<>();
        Cell currentCell;

        Cell next;
        Cell exit = labyrinth.grid()[labyrinth.finish().X()][labyrinth.finish().Y()];
        Cell start = labyrinth.grid()[labyrinth.start().X()][labyrinth.start().Y()];
        currentCell = start;

        do {
            visitCell(currentCell);
            List<Cell> neighbor = checkNeighbors(labyrinth, currentCell);
            if (neighbor.isEmpty()) {
                next = deque.pollLast();
                currentCell.type(CellType.PASSAGE);
            } else {
                if (deque.peekLast() != currentCell) {
                    deque.addLast(currentCell);
                }
                next = neighbor.get(random.nextInt(neighbor.size()));
            }
            currentCell = next;

            if (currentCell.equals(start)) {
                return Optional.empty();
            }

        } while (!currentCell.equals(exit));
        deque.addLast(exit);
        exit.type(CellType.FINISH);
        start.type(CellType.START);
        return Optional.of(deque);
    }

    private List<Cell> checkNeighbors(Labyrinth labyrinth, Cell currentCell) {
        List<Cell> neighbor = new ArrayList<>();
        // up
        if (checkCell(labyrinth, currentCell.coordinates().Y(), currentCell.coordinates().X() - 1)) {
            neighbor.add(labyrinth.grid()[currentCell.coordinates().Y()][currentCell.coordinates().X() - 1]);
        }
        // down
        if (checkCell(labyrinth, currentCell.coordinates().Y() + 1, currentCell.coordinates().X())) {
            neighbor.add(labyrinth.grid()[currentCell.coordinates().Y() + 1][currentCell.coordinates().X()]);
        }
        // right
        if (checkCell(labyrinth, currentCell.coordinates().Y(), currentCell.coordinates().X() + 1)) {
            neighbor.add(labyrinth.grid()[currentCell.coordinates().Y()][currentCell.coordinates().X() + 1]);
        }
        // left
        if (checkCell(labyrinth, currentCell.coordinates().Y() - 1, currentCell.coordinates().X())) {
            neighbor.add(labyrinth.grid()[currentCell.coordinates().Y() - 1][currentCell.coordinates().X()]);
        }
        return neighbor;
    }

    private boolean checkCell(Labyrinth labyrinth, int y, int x) {
        if (isCoordinateInLabyrinth(labyrinth, x, y)) {
            return !labyrinth.grid()[y][x].isVisited()
                && labyrinth.grid()[y][x].type() != CellType.WALL;
        } else return false;
    }

    private void visitCell(Cell cell) {
        if (!cell.isVisited()) {
            deque.add(cell);
            cell.isVisited(true);
        }
    }

    private boolean isCoordinateInLabyrinth(Labyrinth labyrinth, int x, int y) {
        return x >= 0 && x < labyrinth.width() && y >= 0 && y < labyrinth.height();
    }
}
