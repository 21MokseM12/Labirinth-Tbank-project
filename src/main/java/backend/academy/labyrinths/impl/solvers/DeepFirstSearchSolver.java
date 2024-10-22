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

/**
 * Класс поиска пути в лабиринте алгоритмом поиска в глубину
 */
public class DeepFirstSearchSolver implements LabyrinthSolver {

    private final Random random = new Random();

    /**
     * Очередь пройденного алгоритмом пути
     */
    private Deque<Cell> deque;

    /**
     * Терминальный метод поиска пути в лабиринте
     * @param labyrinth - объект лабиринта
     * @return Optional объект, в котором находится либо очередь клеток - найденный путь,
     * либо null, если путь найден не был
     */
    @Override
    public Optional<Queue<Cell>> solve(Labyrinth labyrinth) {
        deque = new ArrayDeque<>();
        Cell currentCell;

        Cell next;
        Cell exit = labyrinth.grid()[labyrinth.finish().getFirst().x()][labyrinth.finish().getFirst().y()];
        Cell start = labyrinth.grid()[labyrinth.start().x()][labyrinth.start().y()];
        currentCell = start;

        do {
            markCellAsVisit(currentCell);
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

    /**
     * Метод добавления соседей текущей клетки, если они подходят под условие
     * @param labyrinth - объект лабиринта
     * @param currentCell - объект текущей клетки
     * @return список доступных соседей текущей клетки
     */
    private List<Cell> checkNeighbors(Labyrinth labyrinth, Cell currentCell) {
        List<Cell> neighbor = new ArrayList<>();
        // up
        if (checkSuitableCell(labyrinth, currentCell.coordinates().y(), currentCell.coordinates().x() - 1)) {
            neighbor.add(labyrinth.grid()[currentCell.coordinates().y()][currentCell.coordinates().x() - 1]);
        }
        // down
        if (checkSuitableCell(labyrinth, currentCell.coordinates().y() + 1, currentCell.coordinates().x())) {
            neighbor.add(labyrinth.grid()[currentCell.coordinates().y() + 1][currentCell.coordinates().x()]);
        }
        // right
        if (checkSuitableCell(labyrinth, currentCell.coordinates().y(), currentCell.coordinates().x() + 1)) {
            neighbor.add(labyrinth.grid()[currentCell.coordinates().y()][currentCell.coordinates().x() + 1]);
        }
        // left
        if (checkSuitableCell(labyrinth, currentCell.coordinates().y() - 1, currentCell.coordinates().x())) {
            neighbor.add(labyrinth.grid()[currentCell.coordinates().y() - 1][currentCell.coordinates().x()]);
        }
        return neighbor;
    }

    /**
     * Метод проверки, является ли клетка, находящаяся по заданным координатам, подходящей для прохождения через нее
     * @param labyrinth - объект лабиринта
     * @param y - координата Y клетки
     * @param x - координата X клетки
     * @return true, если клетка подходит, false в ином случае
     */
    private boolean checkSuitableCell(Labyrinth labyrinth, int y, int x) {
        if (isCoordinateInLabyrinth(labyrinth, x, y)) {
            return !labyrinth.grid()[y][x].isVisited()
                && labyrinth.grid()[y][x].type() != CellType.WALL;
        } else {
            return false;
        }
    }

    /**
     * Метод маркирования заданной клетки как посещенной
     * @param cell - клетка, которую необходимо пометить как посещенную
     */
    private void markCellAsVisit(Cell cell) {
        if (!cell.isVisited()) {
            deque.add(cell);
            cell.isVisited(true);
        }
    }

    /**
     * Метод проверки, входят ли поданные координаты частью поля лабиринта
     * @param labyrinth - объект лабиринта
     * @param x - координата X
     * @param y - координата Y
     * @return true, если координаты входя в поле лабиринта, false в ином случае
     */
    private boolean isCoordinateInLabyrinth(Labyrinth labyrinth, int x, int y) {
        return x >= 0 && x < labyrinth.grid()[0].length && y >= 0 && y < labyrinth.grid().length;
    }
}
