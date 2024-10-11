package backend.academy.labyrinths.impl.services;

import backend.academy.labyrinths.entites.Cell;
import backend.academy.labyrinths.entites.Coordinates;
import backend.academy.labyrinths.entites.Labyrinth;
import backend.academy.labyrinths.enums.GeneratorType;
import backend.academy.labyrinths.enums.SolverType;
import backend.academy.labyrinths.factories.LabyrinthGeneratorFactory;
import backend.academy.labyrinths.factories.LabyrinthSolverFactory;
import backend.academy.labyrinths.impl.ui.services.LabyrinthRenderer;
import backend.academy.labyrinths.impl.ui.services.UserInterface;
import backend.academy.labyrinths.impl.validators.InputSettingsValidator;
import backend.academy.labyrinths.interfaces.ui.services.Renderer;
import backend.academy.labyrinths.interfaces.validators.InputDataValidator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Queue;
import java.util.Random;

public class Session {

    private final UserInterface ui;

//    private final InputDataValidator inputDataValidator = new InputSettingsValidator();

    private final LabyrinthGeneratorFactory generatorFactory = new LabyrinthGeneratorFactory();

    private final LabyrinthSolverFactory solverFactory = new LabyrinthSolverFactory();

    private final Renderer renderer = new LabyrinthRenderer();

//    private final static int MAX_LABYRINTH_WIDTH = 30;
//
//    private final static int MAX_LABYRINTH_HEIGHT = 20;

    private final static int[] SMALL_LABYRINTH_SIZE = new int[] {11, 11};

    private final static int[] AVERAGE_LABYRINTH_SIZE = new int[] {21, 21};

    private final static int[] LARGE_LABYRINTH_SIZE = new int[] {31, 31};

//    private final static int COUNT_VARIANTS_START_FINISH_POSITIONS = 4;

    private static final int DELAY = 250;

    public Session(UserInterface ui) {
        this.ui = ui;
    }

    public void start() throws InterruptedException {
        int[] labyrinthParams = getLabyrinthSettings();
        GeneratorType type = getDifficultLLabyrinthLevel();

        Labyrinth labyrinth = generatorFactory.get(type).generate(labyrinthParams[0], labyrinthParams[1]);
        setStartFinishPositions(labyrinth);

        ui.printGenerateAlgorithmName(type);
        renderer.printLabyrinthDelay(labyrinth, DELAY);

        SolverType solverType = chooseSolveAlgorithm();
        Optional<Queue<Cell>> solve = solverFactory.get(solverType).solve(labyrinth);
        if (solve.isPresent()) {
            ui.printSolveLabyrinthLabel(solverType);
            renderer.render(solve.get());
            renderer.printLabyrinthDelay(labyrinth, DELAY);
        } else {
            ui.printSolveNotFound();
        }
    }

    //TODO сделать ввод не вариантным, а любым для пользователя, проработать четные числа
    private int[] getLabyrinthSettings() {
        while (true) {
//            ui.printSetLabyrinthWidth(MAX_LABYRINTH_WIDTH);
//            String width = ui.read();
//            ui.printSetLabyrinthHeight(MAX_LABYRINTH_HEIGHT);
//            String height = ui.read();

//            if (inputDataValidator.isValidNumber(width, MAX_LABYRINTH_WIDTH) &&
//                inputDataValidator.isValidNumber(height, MAX_LABYRINTH_HEIGHT)) {
//                return new int[] {Integer.parseInt(width), Integer.parseInt(height)};
//            } else {
//                ui.printInputError();
            ui.printLabyrinthSizeMenu();
            ui.printChooseVariant();
            String response = ui.read();

            switch (response) {
                case "1":
                    return SMALL_LABYRINTH_SIZE;
                case "2":
                    return AVERAGE_LABYRINTH_SIZE;
                case "3":
                    return LARGE_LABYRINTH_SIZE;
                default: ui.printInputError();
            }
        }
    }

    private GeneratorType getDifficultLLabyrinthLevel() {
        while (true) {
            ui.printSetDiffLevel();
            ui.printChooseVariant();
            String response = ui.read();

            switch (response) {
                case "1":
                    return GeneratorType.DFS;
                case "2":
                    return GeneratorType.ELLER_GENERATOR;
                case "3":
                    GeneratorType[] generatorTypes = GeneratorType.values();
                    return generatorTypes[new Random().nextInt(generatorTypes.length)];
                default:
                    ui.printInputError();
            }
        }
    }

    private void setStartFinishPositions(Labyrinth labyrinth) {
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
                    return;
                case "2":
                    labyrinth.setStart(coordinates.get(1));
                    labyrinth.setFinish(coordinates.get(2));
                    return;
                default: ui.printInputError();
            }
//            ui.printStartPositionMenu();
//            ui.printChooseVariant();
//            String startResponse = ui.read();
//
//            ui.printFinishPositionMenu();
//            ui.printChooseVariant();
//            String finishResponse = ui.read();
//
//            if (inputDataValidator.isValidNumber(startResponse, COUNT_VARIANTS_START_FINISH_POSITIONS) &&
//                inputDataValidator.isValidNumber(finishResponse, COUNT_VARIANTS_START_FINISH_POSITIONS)) {
//                int start = Integer.parseInt(startResponse), finish = Integer.parseInt(finishResponse);
//                if (start != finish) {
//                    labyrinth.setStart(coordinates.get(Integer.parseInt(startResponse) - 1));
//                    labyrinth.setFinish(coordinates.get(Integer.parseInt(finishResponse) - 1));
//                    return;
//                } else {
//                    ui.printInputError();
//                }
//            } else {
//                ui.printInputError();
//            }
        }
    }

    private SolverType chooseSolveAlgorithm() {
        while (true) {
            ui.printAlgorithmMenu();
            ui.printChooseVariant();
            String response = ui.read();

            switch (response) {
                case "1":
                    return SolverType.DEEP_FIRST_SEARCH;
                case "2":
                    //TODO second solver
                    throw new NoSuchElementException();
                case "3":
                    SolverType[] values = SolverType.values();
                    return values[new Random().nextInt(values.length)];
                default:
                    ui.printInputError();
            }
        }
    }
}
