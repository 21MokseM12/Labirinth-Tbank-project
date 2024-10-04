package backend.academy.labyrinths.impl.validators;

import backend.academy.labyrinths.interfaces.validators.Validator;

public class InputSettingsValidator implements Validator {
    @Override
    public boolean isValid(String data) {
        return data.matches("[0-9]+");
    }
}
