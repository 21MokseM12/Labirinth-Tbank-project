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

//todo javadoc
public class AStarSolver implements LabyrinthSolver {

    private static final int[] DX = {-1, 1, 0, 0};

    private static final int[] DY = {0, 0, -1, 1};

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
            for (int i = 0; i < 4; i++) {
                int newX = current.coordinates().x() + DX[i];
                int newY = current.coordinates().y() + DY[i];

                if (isValid(newX, newY, labyrinth.grid())) {
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
                            closedList.add(neighbor); // Добавляем в closedList сразу, чтобы не добавлять повторно
                        }
                    }
                }
            }

        }

        return Optional.empty();
    }

    // Метод для вычисления Манхеттенского расстояния до ближайшего выхода
    private int calculateHeuristic(Cell currentCell, List<Coordinates> exits) {
        int minHeuristic = Integer.MAX_VALUE;
        for (Coordinates exit : exits) {
            int distance = Math.abs(currentCell.coordinates().x() - exit.x()) +
                Math.abs(currentCell.coordinates().y() - exit.y());
            minHeuristic = Math.min(minHeuristic, distance);
        }
        return minHeuristic;
    }

    // Проверка, находится ли клетка внутри лабиринта
    private boolean isValid(int x, int y, Cell[][] grid) {
        return x >= 0 && x < grid[0].length && y >= 0 && y < grid.length;
    }

    // Восстановление пути
    private Queue<Cell> reconstructPath(Map<Cell, Cell> cameFrom, Cell current) {
        Queue<Cell> path = new ArrayDeque<>();
        while (current != null) {
            path.add(current);
            current = cameFrom.get(current);
        }
        return path;
    }
}
