package backend.academy.labyrinths.impl.validators;

import backend.academy.labyrinths.interfaces.validators.InputDataValidator;

public class InputSettingsValidator implements InputDataValidator {

    @Override
    public boolean isValidNumber(String data, int maxValue) {
        if (data.matches("[0-9]+")) return Integer.parseInt(data) <= maxValue;
        else return false;
    }
}
