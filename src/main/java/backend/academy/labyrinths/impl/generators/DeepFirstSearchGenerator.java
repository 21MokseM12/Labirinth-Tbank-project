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

    private Cell[][] labyrinth;

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
        currentCell = labyrinth[1][1];
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
        if (width % 2 == 0) {
            fillRightWallToPassage();
        }
        if (height % 2 == 0) {
            fillDownWallToPassage();
        }

        return labyrinth;
    }

    private void fillMaze() {
        labyrinth = new Cell[height + 2][width + 2];
        for (int i = 0; i < labyrinth.length; i++) {
            for (int j = 0; j < labyrinth[0].length; j++) {
                if (i == 0 || i == labyrinth.length - 1 || j == 0 || j == labyrinth[0].length - 1) {
                    labyrinth[i][j] = new Cell(new Coordinates(j, i), CellType.WALL);
                } else if (i % 2 == j % 2 && i % 2 == 1) {
                    labyrinth[i][j] = new Cell(new Coordinates(j, i), CellType.PASSAGE);
                } else {
                    labyrinth[i][j] = new Cell(new Coordinates(j, i), CellType.WALL);
                }
            }
        }
    }

    private boolean checkCell(int y, int x) {
        return y >= 1
            && y <= labyrinth.length - 2
            && x >= 1
            && x <= labyrinth[0].length - 2
            && !labyrinth[y][x].isVisited();
    }

    private List<Cell> checkNeighbors() {
        List<Cell> neighbor = new ArrayList<>();
        if (checkCell(currentCell.coordinates().y(), currentCell.coordinates().x() - 2)) {
            neighbor.add(labyrinth[currentCell.coordinates().y()][currentCell.coordinates().x() - 2]);
        }
        if (checkCell(currentCell.coordinates().y() + 2, currentCell.coordinates().x())) {
            neighbor.add(labyrinth[currentCell.coordinates().y() + 2][currentCell.coordinates().x()]);
        }
        if (checkCell(currentCell.coordinates().y(), currentCell.coordinates().x() + 2)) {
            neighbor.add(labyrinth[currentCell.coordinates().y()][currentCell.coordinates().x() + 2]);
        }
        if (checkCell(currentCell.coordinates().y() - 2, currentCell.coordinates().x())) {
            neighbor.add(labyrinth[currentCell.coordinates().y() - 2][currentCell.coordinates().x()]);
        }
        return neighbor;
    }

    private void breakWall(Cell first, Cell second) {
        if (first.coordinates().x() == second.coordinates().x()) {
            if (first.coordinates().y() > second.coordinates().y()) {
                labyrinth[first.coordinates().y() - 1][first.coordinates().x()].type(CellType.PASSAGE);
            } else {
                labyrinth[second.coordinates().y() - 1][second.coordinates().x()].type(CellType.PASSAGE);
            }
        } else {
            if (first.coordinates().x() > second.coordinates().x()) {
                labyrinth[first.coordinates().y()][first.coordinates().x() - 1].type(CellType.PASSAGE);
            } else {
                labyrinth[second.coordinates().y()][second.coordinates().x() - 1].type(CellType.PASSAGE);
            }
        }
    }

    private void markCellAsVisit(Cell cell) {
        if (!cell.isVisited()) {
            stack.addFirst(cell);
            cell.isVisited(true);
        }
    }

    private void clearVisited() {
        for (Cell[] cells : labyrinth) {
            for (int j = 0; j < labyrinth[0].length; j++) {
                cells[j].isVisited(false);
            }
        }
    }

    private void fillRightWallToPassage() {
        labyrinth[2][width].type(CellType.PASSAGE);
        for (int i = 2; i < height; i++) {
            labyrinth[i][width].type(CellType.PASSAGE);
        }
    }

    private void fillDownWallToPassage() {
        labyrinth[height][2].type(CellType.PASSAGE);
        for (int i = 2; i < width; i++) {
            labyrinth[height][i].type(CellType.PASSAGE);
        }
    }
}
