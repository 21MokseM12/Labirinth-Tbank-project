package backend.academy.labyrinths.interfaces.validators;

/**
 * Интерфейс валидации входных пользовательских данных
 */
public interface InputDataValidator {
    boolean isValidNumber(String data, int minValue, int maxValue);
}
