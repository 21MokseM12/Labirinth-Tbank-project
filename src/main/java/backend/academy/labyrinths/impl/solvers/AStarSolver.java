package backend.academy.labyrinths.impl.solvers;

import backend.academy.labyrinths.entites.Cell;
import backend.academy.labyrinths.entites.Coordinates;
import backend.academy.labyrinths.entites.Labyrinth;
import backend.academy.labyrinths.enums.CellType;
import backend.academy.labyrinths.interfaces.solvers.LabyrinthSolver;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * Класс поиска пути в лабиринте алгоритмом А-стар
 */
public class AStarSolver implements LabyrinthSolver {

    /**
     * Массив с возможными направлениями движения в лабиринте по оси Х
     */
    private static final int[] DX = {-1, 1, 0, 0};

    /**
     * Массив с возможными направлениями движения в лабиринте по оси Y
     */
    private static final int[] DY = {0, 0, -1, 1};

    /**
     * Терминальный метод, отвечающий за генерацию лабиринта
     * @param labyrinth - объект лабиринта
     * @return Optional объект, содержащий либо очередь со ссылками на клетки найденного пути, либо null,
     * если путь найден не был
     */
    @Override
    public Optional<Queue<Cell>> solve(Labyrinth labyrinth) {
        PriorityQueue<Cell> openList = new PriorityQueue<>(Comparator.comparingInt(Cell::cost));
        Set<Cell> closedList = new HashSet<>();
        Map<Cell, Integer> gCosts = new HashMap<>();
        Map<Cell, Cell> cameFrom = new HashMap<>();

        gCosts.put(labyrinth.grid()[labyrinth.start().y()][labyrinth.start().x()], 0);
        openList.add(labyrinth.grid()[labyrinth.start().y()][labyrinth.start().x()]);

        while (!openList.isEmpty()) {
            Cell current = openList.poll();  // Берём узел с наименьшей стоимостью f

            // Если мы достигли одного из выходов, возвращаем путь
            if (labyrinth.finish().contains(current.coordinates())) {
                return Optional.of(reconstructPath(cameFrom, current));
            }

            closedList.add(current);
            for (int i = 0; i < DX.length; i++) {
                int newX = current.coordinates().x() + DX[i];
                int newY = current.coordinates().y() + DY[i];

                if (isValidCoordinates(newX, newY, labyrinth.grid())) {
                    Cell neighbor = labyrinth.grid()[newY][newX];

                    // Если сосед непроходим или уже в закрытом списке, пропускаем его
                    if (neighbor.type() == CellType.WALL || closedList.contains(neighbor)) {
                        continue;
                    }

                    int tentativeGCost = gCosts.get(current) + neighbor.type().cost();

                    // Если новый путь к соседу короче или он ещё не был проверен
                    if (tentativeGCost < gCosts.getOrDefault(neighbor, Integer.MAX_VALUE)) {
                        cameFrom.put(neighbor, current);
                        gCosts.put(neighbor, tentativeGCost);
                        neighbor.cost(tentativeGCost + calculateHeuristic(neighbor, labyrinth.finish())); // f = g + h

                        if (!openList.contains(neighbor)) {
                            openList.add(neighbor);
                            closedList.add(neighbor);
                        }
                    }
                }
            }

        }

        return Optional.empty();
    }

    /**
     * Метод для вычисления Манхеттенского расстояния до ближайшего выхода
     * @param currentCell - текущая клетка, на которой находится алгоритм
     * @param exits - список возможных выходов из лабиринта
     * @return минимальное расстояние до выхода
     */
    private int calculateHeuristic(Cell currentCell, List<Coordinates> exits) {
        int minHeuristic = Integer.MAX_VALUE;
        for (Coordinates exit : exits) {
            int distance = Math.abs(currentCell.coordinates().x() - exit.x())
                + Math.abs(currentCell.coordinates().y() - exit.y());
            minHeuristic = Math.min(minHeuristic, distance);
        }
        return minHeuristic;
    }

    /**
     * Метод проверки, находится ли клетка внутри лабиринта
     * @param x - координата Х
     * @param y - координата Y
     * @param grid - поле лабиринта
     * @return true, если клетка входит в границы поля лабиринта, false, если нет
     */
    private boolean isValidCoordinates(int x, int y, Cell[][] grid) {
        return x >= 0 && x < grid[0].length && y >= 0 && y < grid.length;
    }

    // Восстановление пути

    /**
     * Метод восстановления пути, пройденного алгоритмом
     * @param cameFrom - Map, где ключом является следующая клетка пути, значением - предыдущая клетка
     *                 пройденного пути
     * @param finishCell - клетка, содержащая ссылку на клетку выхода из лабиринта, до которой дошел алгоритм
     * @return Queue, содержащая путь от клетки старта до клетки финиша в лабиринте
     */
    private Queue<Cell> reconstructPath(Map<Cell, Cell> cameFrom, Cell finishCell) {
        Cell current = finishCell;

        Queue<Cell> path = new ArrayDeque<>();
        while (current != null) {
            path.add(current);
            current = cameFrom.get(current);
        }
        return path;
    }
}
