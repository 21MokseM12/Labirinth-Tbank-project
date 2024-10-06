package backend.academy.labyrinths.impl.generators;

import backend.academy.labyrinths.entites.Cell;
import backend.academy.labyrinths.entites.Labyrinth;
import backend.academy.labyrinths.enums.CellType;
import backend.academy.labyrinths.interfaces.generators.LabyrinthGenerator;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class EllerGenerator implements LabyrinthGenerator {

    private final Random random = new Random();

    private Cell[][] labyrinth;

    private int width;

    private int height;

    private final List<Integer> line = new ArrayList<>();

    private int counter = 0;

    @Override
    public Labyrinth generate(int width, int height) {
        this.width = width;
        this.height = height;
        return new Labyrinth(
            width,
            height,
            generateGrid(width, height)
        );
    }

    public Cell[][] generateGrid(int width, int height) {
        fillMaze(height, width);
        fillEmptyValue();
        for (int i = 0; i < height - 1; i++) {
            uniqueSet();
            addVerticalWall(i);
            addHorizontalWall(i);
            checkHorizontalWall(i);
            preparationNewLine(i);
        }
        addEndLine();
        print();
        return labyrinth;
    }

    public void fillMaze(int height, int width) {
        labyrinth = new Cell[height * 2 + 1][width * 2 + 1];
        for (int i = 0; i < labyrinth.length; i++) {
            for (int j = 0; j < labyrinth[0].length; j++) {
                if (i == 0 || i == labyrinth.length - 1 || j == 0 || j == labyrinth[0].length - 1) {
                    labyrinth[i][j] = new Cell(j, i, CellType.WALL);
                } else {
                    labyrinth[i][j] = new Cell(j, i, CellType.PASSAGE);
                }
            }
        }
    }

    private void fillEmptyValue() {
        for (int i = 0; i < width; i++) {
            line.add(0);
        }
    }

    private void uniqueSet() {
        for (int i = 0; i < width; i++) {
            if (line.get(i) == 0) {
                line.set(i, counter);
                counter++;
            }
        }
    }

    private void setRightWall(Cell cell) {
        labyrinth[cell.Y() - 1][cell.X() + 1].type(CellType.WALL);
        labyrinth[cell.Y()][cell.X() + 1].type(CellType.WALL);
        labyrinth[cell.Y() + 1][cell.X() + 1].type(CellType.WALL);
    }

    private void setDownWall(Cell cell) {
        labyrinth[cell.Y() + 1][cell.X() - 1].type(CellType.WALL);
        labyrinth[cell.Y() + 1][cell.X()].type(CellType.WALL);
        labyrinth[cell.Y() + 1][cell.X() + 1].type(CellType.WALL);
    }

    private void removeDownWall(Cell cell) {
        labyrinth[cell.Y() + 1][cell.X()].type(CellType.PASSAGE);
    }

    private void removeVerticalWall(Cell cell) {
        labyrinth[cell.Y()][cell.X() + 1].type(CellType.PASSAGE);
    }

    private void merge(int index, int element) {
        int set = line.get(index + 1);
        for (int i = 0; i < width; i++) {
            if (line.get(i) == set) {
                line.set(i, element);
            }
        }
    }

    private void addVerticalWall(int row) {
        for (int i = 0; i < width - 1; i++) {
            if (random.nextInt() % 2 == 0 || line.get(i).equals(line.get(i + 1))) {
                setRightWall(labyrinth[row * 2 + 1][i * 2 + 1]);
            } else {
                merge(i, line.get(i));
            }
        }
    }

    private void addHorizontalWall(int row) {
        for (int i = 0; i < width; i++) {
            if (countElementInSet(line.get(i)) != 1 && random.nextInt() % 2 == 0) {
                setDownWall(labyrinth[row * 2 + 1][i * 2 + 1]);
            }
        }
    }

    private int countElementInSet(int element) {
        return Math.toIntExact(line.stream().filter(e -> e == element).count());
    }

    private void checkHorizontalWall(int row) {
        for (int i = 0; i < width; i++) {
            if (countHorizontalWall(line.get(i), row * 2 + 1) == 0) {
                removeDownWall(labyrinth[row * 2 + 1][i * 2 + 1]);
            }
        }
    }

    private int countHorizontalWall(int element, int row) {
        int count = 0;
        for (int i = 0; i < width; i++) {
            if (line.get(i) == element && labyrinth[row + 1][i * 2 + 1].type() == CellType.PASSAGE) {
                count++;
            }
        }
        return count;
    }

    private void preparationNewLine(int row) {
        for (int i = 0; i < width; i++) {
            if (labyrinth[row * 2 + 2][i * 2 + 1].type() == CellType.WALL) {
                line.set(i, 0);
            }
        }
    }

    private void addEndLine() {
        uniqueSet();
        addVerticalWall(height - 1);
        checkEndLine();
    }

    private void checkEndLine() {
        for (int i = 0; i < width - 1; i++) {
            if (!line.get(i).equals(line.get(i + 1))) {
                removeVerticalWall(labyrinth[height * 2 - 1][i * 2 + 1]);
                merge(i, line.get(i));
            }
        }
    }

    //TODO delete after testing
    private void print() {
        for (Cell[] arr : labyrinth) {
            Stream.of(arr).forEach(System.out::print);
            System.out.println();
        }
    }
}
