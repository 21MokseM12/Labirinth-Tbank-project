package backend.academy.labyrinths.impl.validators;

import backend.academy.labyrinths.interfaces.validators.InputDataValidator;

/**
 * Класс валидации входных пользовательских данных
 */
public class InputSettingsValidator implements InputDataValidator {

    /**
     * Метод, проверяющий, валидно ли число, введенное пользователем
     * @param data - введенные данные
     * @param minValue - минимальное значение, которое должно быть соблюдено
     * @param maxValue - максимальное значение, которое должно быть соблюдено
     * @return - возвращает true, если все условия соблюдены, иначе возвращает false
     */
    @Override
    public boolean isValidNumber(String data, int minValue, int maxValue) {
        if (data.matches("[0-9]+") || data.matches("-[0-9]+")) {
            return Integer.parseInt(data) <= maxValue && Integer.parseInt(data) >= minValue;
        } else {
            return false;
        }
    }
}
