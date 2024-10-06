package backend.academy.labyrinths.impl.validators;

import backend.academy.labyrinths.interfaces.validators.InputDataValidator;

public class InputSettingsValidator implements InputDataValidator {

    private static final int MAX_VALUE = 20;

    @Override
    public boolean isValid(String data) {
        if (data.matches("[0-9]+")) return Integer.parseInt(data) <= MAX_VALUE;
        else return false;
    }
}
