package backend.academy.labyrinths.impl.services;

import backend.academy.labyrinths.entites.Coordinates;
import backend.academy.labyrinths.entites.Labyrinth;
import backend.academy.labyrinths.enums.GeneratorType;
import backend.academy.labyrinths.factories.LabyrinthGeneratorFactory;
import backend.academy.labyrinths.impl.ui.services.LabyrinthRenderer;
import backend.academy.labyrinths.impl.ui.services.UserInterface;
import backend.academy.labyrinths.impl.validators.InputSettingsValidator;
import backend.academy.labyrinths.interfaces.services.Startable;
import backend.academy.labyrinths.interfaces.ui.services.Renderer;
import backend.academy.labyrinths.interfaces.validators.InputDataValidator;
import java.util.List;
import java.util.Random;

public class Session implements Startable {

    private final UserInterface ui;

    private final InputDataValidator inputDataValidator = new InputSettingsValidator();

    private final LabyrinthGeneratorFactory generatorFactory = new LabyrinthGeneratorFactory();

    private final Renderer renderer = new LabyrinthRenderer();

    private final static int MAX_LABYRINTH_WIDTH = 30;

    private final static int MAX_LABYRINTH_HEIGHT = 20;

    public Session(UserInterface ui) {
        this.ui = ui;
    }

    @Override
    public void start() {
        int[] labyrinthParams = getLabyrinthSettings();
        GeneratorType type = getDifficultLLabyrinthLevel();

        Labyrinth labyrinth = generatorFactory.get(type).generate(labyrinthParams[0], labyrinthParams[1]);
        setStartFinishPositions(labyrinth);

        renderer.printLabyrinth(labyrinth);
//        TODO finish this main method
    }

    private int[] getLabyrinthSettings() {
        while (true) {
            ui.printSetLabyrinthWidth();
            String width = ui.read();
            ui.printSetLabyrinthHeight();
            String height = ui.read();

            if (inputDataValidator.isValidNumber(width, MAX_LABYRINTH_WIDTH) &&
                inputDataValidator.isValidNumber(height, MAX_LABYRINTH_HEIGHT)) {
                return new int[] {Integer.parseInt(width), Integer.parseInt(height)};
            } else {
                ui.printInputError();
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
                    //TODO first generator
                    break;
                case "2":
                    return GeneratorType.ELLER_GENERATOR;
                case "3":
                    //TODO third generator
                    break;
                default:
                    ui.printInputError();
            }
        }
    }

    private void setStartFinishPositions(Labyrinth labyrinth) {
        List<Coordinates> coordinates = List.of(
            new Coordinates(0, labyrinth.width()),
            new Coordinates(labyrinth.height() * 2, labyrinth.width()),
            new Coordinates(labyrinth.height(), 0),
            new Coordinates(labyrinth.height(), labyrinth.width() * 2)
        );

        while (true) {
            ui.printStartPositionMenu();
            ui.printChooseVariant();
            String startResponse = ui.read();

            ui.printFinishPositionMenu();
            ui.printChooseVariant();
            String finishResponse = ui.read();

            if (inputDataValidator.isValidNumber(startResponse, coordinates.size() + 1) &&
                inputDataValidator.isValidNumber(finishResponse, coordinates.size() + 1)) {
                int start = Integer.parseInt(startResponse), finish = Integer.parseInt(finishResponse);
                if (start != finish || (start == 5)) {
                    if (Integer.parseInt(startResponse) == coordinates.size() + 1) {
                        labyrinth.setStart(coordinates.get(new Random().nextInt(0, coordinates.size())));
                    } else {
                        labyrinth.setStart(coordinates.get(Integer.parseInt(startResponse) - 1));
                    }
                    if (Integer.parseInt(finishResponse) == coordinates.size() + 1) {
                        labyrinth.setFinish(coordinates.get(new Random().nextInt(0, coordinates.size())));
                    } else {
                        labyrinth.setFinish(coordinates.get(Integer.parseInt(finishResponse) - 1));
                    }
                    return;
                } else {
                    ui.printInputError();
                }
            } else {
                ui.printInputError();
            }
        }
    }
}
