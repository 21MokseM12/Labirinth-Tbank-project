package backend.academy.labyrinths.impl.services;

import backend.academy.labyrinths.entites.Cell;
import backend.academy.labyrinths.entites.Coordinates;
import backend.academy.labyrinths.entites.Labyrinth;
import backend.academy.labyrinths.enums.AlgorithmType;
import backend.academy.labyrinths.enums.GeneratorType;
import backend.academy.labyrinths.enums.SolverType;
import backend.academy.labyrinths.factories.LabyrinthGeneratorFactory;
import backend.academy.labyrinths.factories.LabyrinthSolverFactory;
import backend.academy.labyrinths.impl.ui.services.LabyrinthRenderer;
import backend.academy.labyrinths.impl.ui.services.UserInterface;
import backend.academy.labyrinths.impl.validators.InputSettingsValidator;
import backend.academy.labyrinths.interfaces.ui.services.Renderer;
import backend.academy.labyrinths.interfaces.validators.InputDataValidator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.Random;

/**
 * Класс сессии с пользователем
 */
public class Session {

    private final static Random RANDOM = new Random();

    /**
     * Сервис пользовательского интерфейса
     */
    private final UserInterface ui;

    /**
     * Сервис валидации пользовательских данных
     */
    private final InputDataValidator inputDataValidator = new InputSettingsValidator();

    /**
     * Фабрика алгоритмов генерации лабиринта
     */
    private final LabyrinthGeneratorFactory generatorFactory = new LabyrinthGeneratorFactory();

    /**
     * Фабрика алгоритмов поиска пути в лабиринте
     */
    private final LabyrinthSolverFactory solverFactory = new LabyrinthSolverFactory();

    /**
     * Сервис рендеринга и вывода лабиринта на консоль
     */
    private final Renderer renderer = new LabyrinthRenderer();

    /**
     * Максимальная ширина лабиринта
     */
    private final static int MAX_LABYRINTH_WIDTH = 30;

    /**
     * Максимальная высота лабиринта
     */
    private final static int MAX_LABYRINTH_HEIGHT = 20;

    /**
     * Минимальный размер лабиринта
     */
    private final static int MIN_LABYRINTH_SIZE = 2;

    /**
     * Задержка при выводе лабиринта на консоль
     */
    private static final int DELAY = 250;

    public Session(UserInterface ui) {
        this.ui = ui;
    }

    /**
     * Основной метод, реализующий игровую сессию
     * @throws InterruptedException - исключение, выбрасываемое, если задержка вывода лабиринта на консоль не валидна
     */
    public void start() throws InterruptedException {
        int[] labyrinthParams = getLabyrinthSettings();
        GeneratorType type = getGenerationLabyrinthAlgorithm();

        Labyrinth labyrinth = generatorFactory.get(type).generate(labyrinthParams[0], labyrinthParams[1]);
        SolverType solverType;

        if (type != GeneratorType.MANY_EXIT_GENERATOR) {
            setStartFinishPositions(labyrinth);
            ui.printGenerateAlgorithmName(type);
            renderer.printLabyrinthDelay(labyrinth, DELAY);
            solverType = getSolveLabyrinthAlgorithm();
        } else {
            setManyFinishPositions(labyrinth);
            ui.printGenerateAlgorithmName(type);
            renderer.printLabyrinthDelay(labyrinth, DELAY);
            solverType = SolverType.A_STAR;
        }

        Optional<Queue<Cell>> solve = solverFactory.get(solverType).solve(labyrinth);
        if (solve.isPresent()) {
            ui.printSolveLabyrinthLabel(solverType);
            renderer.render(solve.orElseThrow());
            renderer.printLabyrinthDelay(labyrinth, DELAY);
        } else {
            ui.printSolveNotFound();
        }
    }

    /**
     * Метод, собирающий с пользователя размеры лабиринта: ширина и высота
     * @return - массив типа int из двух элементов, где 1 элемент - ширина, 2 элемент - высота
     */
    private int[] getLabyrinthSettings() {
        while (true) {
            ui.printSetLabyrinthWidth(MAX_LABYRINTH_WIDTH);
            String width = ui.read();
            ui.printSetLabyrinthHeight(MAX_LABYRINTH_HEIGHT);
            String height = ui.read();

            if (inputDataValidator.isValidNumber(width, MIN_LABYRINTH_SIZE, MAX_LABYRINTH_WIDTH)
                && inputDataValidator.isValidNumber(height, MIN_LABYRINTH_SIZE, MAX_LABYRINTH_HEIGHT)) {
                return new int[] {Integer.parseInt(width), Integer.parseInt(height)};
            } else {
                ui.printInputError();
            }
        }
    }

    /**
     * Метод запроса у пользователя выбора алгоритма генерации лабиринта
     * @return - выбранный тип генератора
     */
    private GeneratorType getGenerationLabyrinthAlgorithm() {
        return (GeneratorType) getAlgorithmType(GeneratorType.values());
    }

    /**
     * Метод запроса у пользователя выбора алгоритма поиска пути в лабиринте
     * @return - выбранный тип алгоритма
     */
    private SolverType getSolveLabyrinthAlgorithm() {
        return (SolverType) getAlgorithmType(SolverType.values());
    }

    /**
     * Метод запроса у пользователя выбора точек старта и финиша в лабиринте с установлением этих точек в лабиринт
     * @param labyrinth - объект лабиринта
     */
    private void setStartFinishPositions(Labyrinth labyrinth) {
        boolean exitFlag = false;

        List<Coordinates> coordinates = List.of(
            // left up corner
            new Coordinates(1, 1),
            // right up corner
            new Coordinates(1, labyrinth.width() - 2),
            // left down corner
            new Coordinates(labyrinth.height() - 2, 1),
            // right down corner
            new Coordinates(labyrinth.height() - 2, labyrinth.width() - 2)
        );

        while (true) {
            ui.printSetStartFinishPositions();
            ui.printChooseVariant();
            String response = ui.read();

            switch (response) {
                case "1":
                    labyrinth.setStart(coordinates.getFirst());
                    labyrinth.setFinish(coordinates.getLast());
                    exitFlag = true;
                    break;
                case "2":
                    labyrinth.setStart(coordinates.get(1));
                    labyrinth.setFinish(coordinates.get(2));
                    exitFlag = true;
                    break;
                default:
                    ui.printInputError();
            }

            if (exitFlag) {
                return;
            }
        }
    }

    /**
     * Метод для установления одного входа и множества выходов в лабиринте
     * @param labyrinth - объект лабиринта
     */
    private void setManyFinishPositions(Labyrinth labyrinth) {
        List<Coordinates> coordinates = new ArrayList<>();
        coordinates.add(new Coordinates(1, 1));
        coordinates.add(new Coordinates(1, labyrinth.width() - 2));
        coordinates.add(new Coordinates(labyrinth.height() - 2, 1));
        coordinates.add(new Coordinates(labyrinth.height() - 2, labyrinth.width() - 2));

        int startIndex = RANDOM.nextInt(coordinates.size());
        labyrinth.setStart(coordinates.get(startIndex));
        coordinates.remove(startIndex);
        labyrinth.setFinish(coordinates);
    }

    /**
     * Метод для вывода на консоль меню возможных алгоритмов и запроса у пользователя на выбор одного из них
     * @param types - массив доступных алгоритмов
     * @return - выбранный тип алгоритма
     */
    private AlgorithmType getAlgorithmType(AlgorithmType[] types) {
        while (true) {
            if (types instanceof GeneratorType[]) {
                ui.printSetGenerationAlgorithm(types);
            } else if (types instanceof SolverType[]) {
                ui.printSetResolvedAlgorithm(types);
            }
            ui.printChooseVariant();
            String response = ui.read();

            if (inputDataValidator.isValidNumber(response, 1, types.length + 1)) {
                if (Integer.parseInt(response) != types.length + 1) {
                    return types[Integer.parseInt(response) - 1];
                } else {
                    return types[RANDOM.nextInt(types.length)];
                }
            } else {
                ui.printInputError();
            }
        }
    }
}
