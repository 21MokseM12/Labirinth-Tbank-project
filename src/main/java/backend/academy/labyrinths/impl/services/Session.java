package backend.academy.labyrinths.impl.services;

import backend.academy.labyrinths.impl.generators.EllerGenerator;
import backend.academy.labyrinths.impl.ui.services.UserInterface;
import backend.academy.labyrinths.impl.validators.InputSettingsValidator;
import backend.academy.labyrinths.interfaces.generators.LabyrinthGenerator;
import backend.academy.labyrinths.interfaces.services.Startable;
import backend.academy.labyrinths.interfaces.validators.InputDataValidator;

public class Session implements Startable {

    private final UserInterface ui;

    private final InputDataValidator inputDataValidator = new InputSettingsValidator();

    private final LabyrinthGenerator ellerGenerator = new EllerGenerator();

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

            if (inputDataValidator.isValid(width) && inputDataValidator.isValid(height)) {
                return new int[] {Integer.parseInt(width), Integer.parseInt(height)};
            } else ui.printInputError();
        }
    }
}
