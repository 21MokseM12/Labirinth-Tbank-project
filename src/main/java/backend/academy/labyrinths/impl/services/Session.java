package backend.academy.labyrinths.impl.services;

import backend.academy.labyrinths.entites.Labyrinth;
import backend.academy.labyrinths.enums.GeneratorType;
import backend.academy.labyrinths.factories.LabyrinthGeneratorFactory;
import backend.academy.labyrinths.impl.ui.services.LabyrinthRenderer;
import backend.academy.labyrinths.impl.ui.services.UserInterface;
import backend.academy.labyrinths.impl.validators.InputSettingsValidator;
import backend.academy.labyrinths.interfaces.services.Startable;
import backend.academy.labyrinths.interfaces.ui.services.Renderer;
import backend.academy.labyrinths.interfaces.validators.InputDataValidator;

public class Session implements Startable {

    private final UserInterface ui;

    private final InputDataValidator inputDataValidator = new InputSettingsValidator();

    private final LabyrinthGeneratorFactory generatorFactory = new LabyrinthGeneratorFactory();

    private final Renderer renderer = new LabyrinthRenderer();

    public Session(UserInterface ui) {
        this.ui = ui;
    }

    @Override
    public void start() {
        int [] labyrinthParams = getLabyrinthSettings();
        GeneratorType type = getDifficultLLabyrinthLevel();

        Labyrinth labyrinth = generatorFactory.get(type).generate(labyrinthParams[0], labyrinthParams[1]);
        renderer.printLabyrinth(labyrinth);
        //TODO finish this main method
    }

    private int[] getLabyrinthSettings() {
        while (true) {
            ui.printSetLabyrinthWidth();
            String width = ui.read();
            ui.printSetLabyrinthHeight();
            String height = ui.read();

            if (inputDataValidator.isValid(width) && inputDataValidator.isValid(height)) {
                return new int[] {Integer.parseInt(width), Integer.parseInt(height)};
            } else ui.printInputError();
        }
    }

    private GeneratorType getDifficultLLabyrinthLevel() {
        while (true) {
            ui.printSetDiffLevel();
            ui.printChooseVariant();
            String response = ui.read();

            switch (response) {
                case "1":
                    //TODO second generator
                    break;
                case "2":
                    return GeneratorType.ELLER_GENERATOR;
                default: ui.printInputError();
            }
        }
    }
}
