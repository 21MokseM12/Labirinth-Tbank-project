package backend.academy.labyrinths.impl.services;

import backend.academy.labyrinths.impl.ui.services.UserInterface;
import backend.academy.labyrinths.impl.validators.InputSettingsValidator;
import backend.academy.labyrinths.interfaces.services.Startable;
import backend.academy.labyrinths.interfaces.validators.Validator;

public class Session implements Startable {

    private final UserInterface ui;

    private final Validator validator = new InputSettingsValidator();

    public Session(UserInterface ui) {
        this.ui = ui;
    }

    @Override
    public void start() {
        int [] settings = getLabyrinthSettings();

    }

    private int[] getLabyrinthSettings() {
        while (true) {
            ui.printSetLabyrinthWidth();
            String width = ui.read();
            ui.printSetLabyrinthHeight();
            String height = ui.read();

            if (validator.isValid(width) && validator.isValid(height)) {
                return new int[] {Integer.parseInt(width), Integer.parseInt(height)};
            } else ui.printInputError();
        }
    }
}
