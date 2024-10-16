package backend.academy.labyrinths.impl.generators;

import backend.academy.labyrinths.entites.Cell;
import backend.academy.labyrinths.entites.Coordinates;
import backend.academy.labyrinths.entites.Labyrinth;
import backend.academy.labyrinths.enums.CellType;
import backend.academy.labyrinths.interfaces.generators.LabyrinthGenerator;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Класс-генератор поля лабиринта по алгоритму Эллера
 */
public class EllerGenerator implements LabyrinthGenerator {

    private static final Random RANDOM = new Random();

    /**
     * Поле лабиринта
     */
    private Cell[][] labyrinth;

    /**
     * Ширина лабиринта
     */
    private int width;

    /**
     * Высота лабиринта
     */
    private int height;

    /**
     * Список значений строки лабиринта для определения,
     * когда необходимо генерировать горизонтальные\вертикальные стены
     */
    private List<Integer> line;

    /**
     * Счетчик для генерации уникальных значений
     */
    private int counter;

    /**
     * Терминальный метод генерации лабиринта
     * @param width - желаемая ширина лабиринта
     * @param height - желаемая высота лабиринта
     * @return объект лабиринта
     */
    @Override
    public Labyrinth generate(int width, int height) {
        this.width = width / 2 + 1;
        this.height = height / 2 + 1;
        this.line = new ArrayList<>();
        this.counter = 0;
        return new Labyrinth(generateGrid());
    }

    /**
     * Метод генерации поля лабиринта
     * @return поле лабиринта
     */
    private Cell[][] generateGrid() {
        initializeLabyrinthField();
        fillEmptyValue();
        for (int i = 0; i < height - 1; i++) {
            uniqueSet();
            addVerticalWall(i);
            addHorizontalWall(i);
            checkHorizontalWall(i);
            preparationNewLine(i);
        }
        addEndLine();
        return labyrinth;
    }

    /**
     * Метод инициализации поля лабиринта: генерация стен вокруг поля, заполнение поля проходами
     */
    private void initializeLabyrinthField() {
        labyrinth = new Cell[height * 2 + 1][width * 2 + 1];
        for (int i = 0; i < labyrinth.length; i++) {
            for (int j = 0; j < labyrinth[0].length; j++) {
                if (i == 0 || i == labyrinth.length - 1 || j == 0 || j == labyrinth[0].length - 1) {
                    labyrinth[i][j] = new Cell(new Coordinates(j, i), CellType.WALL);
                } else {
                    labyrinth[i][j] = new Cell(new Coordinates(j, i), CellType.PASSAGE);
                }
            }
        }
    }

    /**
     * Метод заполнения списка чисел default значениями
     */
    private void fillEmptyValue() {
        for (int i = 0; i < width; i++) {
            line.add(0);
        }
    }

    /**
     * Инициализация уникального списка чисел
     */
    private void uniqueSet() {
        for (int i = 0; i < width; i++) {
            if (line.get(i) == 0) {
                line.set(i, counter);
                counter++;
            }
        }
    }

    /**
     * Метод генерации стены, находящейся справа от переданной клетки
     * @param cell - клетка, относительно которой стоит генерировать стену
     */
    private void setRightWall(Cell cell) {
        labyrinth[cell.coordinates().y() - 1][cell.coordinates().x() + 1].type(CellType.WALL);
        labyrinth[cell.coordinates().y()][cell.coordinates().x() + 1].type(CellType.WALL);
        labyrinth[cell.coordinates().y() + 1][cell.coordinates().x() + 1].type(CellType.WALL);
    }

    /**
     * Метод генерации стены, находящейся снизу от переданной клетки
     * @param cell - клетка, относительно которой стоит генерировать стену
     */
    private void setDownWall(Cell cell) {
        labyrinth[cell.coordinates().y() + 1][cell.coordinates().x() - 1].type(CellType.WALL);
        labyrinth[cell.coordinates().y() + 1][cell.coordinates().x()].type(CellType.WALL);
        labyrinth[cell.coordinates().y() + 1][cell.coordinates().x() + 1].type(CellType.WALL);
    }

    /**
     * Метод удаления стены снизу от переданной клетки: создание вместо стены прохода
     * @param cell - клетка, относительно которой стоит генерировать проход
     */
    private void removeDownWall(Cell cell) {
        labyrinth[cell.coordinates().y() + 1][cell.coordinates().x()].type(CellType.PASSAGE);
    }

    /**
     * Метод удаления стены справа от переданной клетки: создание вместо стены прохода
     * @param cell - клетка, относительно которой стоит генерировать проход
     */
    private void removeVerticalWall(Cell cell) {
        labyrinth[cell.coordinates().y()][cell.coordinates().x() + 1].type(CellType.PASSAGE);
    }

    /**
     * Метод заполнения line для последующей генерации поля лабиринта
     * @param index - индекс, от которого определяется опорный элемент set
     * @param element - элемент, который встанет на замену элементам, равным set
     */
    private void merge(int index, int element) {
        int set = line.get(index + 1);
        for (int i = 0; i < width; i++) {
            if (line.get(i) == set) {
                line.set(i, element);
            }
        }
    }

    /**
     * Метод случайного добавления вертикальных стен на строке поля лабиринта
     * @param row - индекс строки поля лабиринта
     */
    private void addVerticalWall(int row) {
        for (int i = 0; i < width - 1; i++) {
            if (RANDOM.nextInt() % 2 == 0 || line.get(i).equals(line.get(i + 1))) {
                setRightWall(labyrinth[row * 2 + 1][i * 2 + 1]);
            } else {
                merge(i, line.get(i));
            }
        }
    }

    /**
     * Метод случайного добавления горизонтальных стен на строке поля лабиринта
     * @param row - индекс строки поля лабиринта
     */
    private void addHorizontalWall(int row) {
        for (int i = 0; i < width; i++) {
            if (countElementInSet(line.get(i)) != 1 && RANDOM.nextInt() % 2 == 0) {
                setDownWall(labyrinth[row * 2 + 1][i * 2 + 1]);
            }
        }
    }

    /**
     * Метод подсчета количества элементов в line
     * @param element - искомый элемент
     * @return - количество искомых элементов в списке line
     */
    private int countElementInSet(int element) {
        return Math.toIntExact(line.stream().filter(e -> e == element).count());
    }

    /**
     * Метод проверки и редактирования количества горизонтальных стен в строке лабиринта
     * @param row - индекс строки лабиринта
     */
    private void checkHorizontalWall(int row) {
        for (int i = 0; i < width; i++) {
            if (countHorizontalWall(line.get(i), row * 2 + 1) == 0) {
                removeDownWall(labyrinth[row * 2 + 1][i * 2 + 1]);
            }
        }
    }

    /**
     * Метод подсчета горизонтальных стен на строке поля лабиринта
     * @param element - опорный элемент
     * @param row - индекс строки поля лабиринта
     * @return - количество горизонтальных стен в строке
     */
    private int countHorizontalWall(int element, int row) {
        int count = 0;
        for (int i = 0; i < width; i++) {
            if (line.get(i) == element && labyrinth[row + 1][i * 2 + 1].type() == CellType.PASSAGE) {
                count++;
            }
        }
        return count;
    }

    /**
     * Метод заполнения line значениями 0 в зависимости от сгенерированных стен на текущей строке лабиринта
     * @param row - индекс строки лабиринта
     */
    private void preparationNewLine(int row) {
        for (int i = 0; i < width; i++) {
            if (labyrinth[row * 2 + 2][i * 2 + 1].type() == CellType.WALL) {
                line.set(i, 0);
            }
        }
    }

    /**
     * Метод добавления последней строки лабиринта
     */
    private void addEndLine() {
        uniqueSet();
        addVerticalWall(height - 1);
        checkEndLine();
    }

    /**
     * Метод проверки и редактирования последней строки лабиринта
     */
    private void checkEndLine() {
        for (int i = 0; i < width - 1; i++) {
            if (!line.get(i).equals(line.get(i + 1))) {
                removeVerticalWall(labyrinth[height * 2 - 1][i * 2 + 1]);
                merge(i, line.get(i));
            }
        }
    }
}
