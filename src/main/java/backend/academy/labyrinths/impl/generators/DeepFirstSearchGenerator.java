package backend.academy.labyrinths.impl.generators;

import backend.academy.labyrinths.entites.Cell;
import backend.academy.labyrinths.entites.Coordinates;
import backend.academy.labyrinths.entites.Labyrinth;
import backend.academy.labyrinths.enums.CellType;
import backend.academy.labyrinths.interfaces.generators.LabyrinthGenerator;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Random;

public class DeepFirstSearchGenerator implements LabyrinthGenerator {

    private final static Random RANDOM = new Random();

    private Deque<Cell> stack;

    private  Cell[][] maze;

    private Cell currentCell;

    private int height;

    private int width;

    @Override
    public Labyrinth generate(int width, int height) {
        this.height = height;
        this.width = width;
        this.stack = new ArrayDeque<>();
        return new Labyrinth(generateGrid());
    }

    public Cell[][] generateGrid() {
        fillMaze();
        currentCell = maze[1][1];
        Cell next;

        do {
            markCellAsVisit(currentCell);
            List<Cell> neighbor = checkNeighbors();

            if (neighbor.isEmpty()) {
                next = stack.poll();
            } else {
                next = neighbor.get(RANDOM.nextInt(neighbor.size()));
                breakWall(currentCell, next);
            }
            currentCell = next;

        } while (!stack.isEmpty());

        clearVisited();
        return maze;
    }

    private void fillMaze() {
        maze = new Cell[height + 2][width + 2];
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (i == 0 || i == maze.length - 1 || j == 0 || j == maze[0].length - 1) {
                    maze[i][j] = new Cell(new Coordinates(j, i), CellType.WALL);
                } else if (i % 2 == j % 2 && i % 2 == 1) {
                    maze[i][j] = new Cell(new Coordinates(j, i), CellType.PASSAGE);
                } else {
                    maze[i][j] = new Cell(new Coordinates(j, i), CellType.WALL);
                }
            }
        }
    }

    private boolean checkCell(int y, int x) {
        return y >= 1
            && y <= maze.length - 2
            && x >= 1
            && x <= maze[0].length - 2
            && !maze[y][x].isVisited();
    }

    private List<Cell> checkNeighbors() {
        List<Cell> neighbor = new ArrayList<>();
        if (checkCell(currentCell.coordinates().Y(), currentCell.coordinates().X() - 2)) {
            neighbor.add(maze[currentCell.coordinates().Y()][currentCell.coordinates().X() - 2]);
        }
        if (checkCell(currentCell.coordinates().Y() + 2, currentCell.coordinates().X())) {
            neighbor.add(maze[currentCell.coordinates().Y() + 2][currentCell.coordinates().X()]);
        }
        if (checkCell(currentCell.coordinates().Y(), currentCell.coordinates().X() + 2)) {
            neighbor.add(maze[currentCell.coordinates().Y()][currentCell.coordinates().X() + 2]);
        }
        if (checkCell(currentCell.coordinates().Y() - 2, currentCell.coordinates().X())) {
            neighbor.add(maze[currentCell.coordinates().Y() - 2][currentCell.coordinates().X()]);
        }
        return neighbor;
    }

    private void breakWall(Cell first, Cell second) {
        if (first.coordinates().X() == second.coordinates().X()) {
            if (first.coordinates().Y() > second.coordinates().Y()) {
                maze[first.coordinates().Y() - 1][first.coordinates().X()].type(CellType.PASSAGE);
            } else {
                maze[second.coordinates().Y() - 1][second.coordinates().X()].type(CellType.PASSAGE);
            }
        } else {
            if (first.coordinates().X() > second.coordinates().X()) {
                maze[first.coordinates().Y()][first.coordinates().X() - 1].type(CellType.PASSAGE);
            } else {
                maze[second.coordinates().Y()][second.coordinates().X() - 1].type(CellType.PASSAGE);
            }
        }
    }

    private void markCellAsVisit(Cell cell) {
        if (!cell.isVisited()) {
            stack.addFirst(cell);
            cell.isVisited(true);
        }
    }

    public void clearVisited() {
        for (Cell[] cells : maze) {
            for (int j = 0; j < maze[0].length; j++) {
                cells[j].isVisited(false);
            }
        }
    }
}
