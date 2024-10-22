package backend.academy.labyrinths.enums;

/**
 * Enum, содержащий сообщения для пользователя
 */
public enum Messages {

    GREETING("""
        ЛАБИРИНТЫ
        Приветствую тебя, пользователь!
        """),

    MENU("""

        МЕНЮ
        1. Построить лабиринт
        2. Выйти
        """),

    CHOOSE_VARIANT("Выбери вариант, который подходит тебе, и введи соответствующее число: "),

    INPUT_ERROR_MESSAGE("Ваш ввод некорректен, попробуйте еще раз"),

    AGAIN_SESSION_MESSAGE("Вы хотите построить еще один лабиринт?"),

    YES_NO_MENU("""
        1. Да
        2. Нет
        """),

    BYE_MESSAGE("До свидания!"),

    CHOOSE_LABYRINTH_WIDTH("Введите ширину лабиринта в клетках (не более %s): "),

    CHOOSE_LABYRINTH_HEIGHT("Введите высоту лабиринта в клетках (не более %s): "),

    GENERATION_ALGORITHM_MENU_LABEL("""
        АЛГОРИТМ ГЕНЕРАЦИИ:
        """),

    GENERATE_ALGORITHM_NAME("Алгоритм генерации лабиринта: %s"),

    START_FINISH_POSITIONS("""
        ПОЗИЦИИ СТАРТ-ФИНИШ
        1. Левый верхний угол - Правый нижний угол
        2. Правый верхний угол - Левый нижний угол
        """),

    SOLVER_ALGORITHM_MENU_LABEL("""
        АЛГОРИТМ НАХОЖДЕНИЯ ПУТИ:
        """),

    SOLVED_LABYRINTH_LABEL("""
        РЕШЕНИЕ ЛАБИРИНТА
        Алгоритм нахождения пути: %s
        """),

    SOLVE_NOT_FOUND("Решение не было найдено..."),

    RANDOM_CHOICE("%s. Случайный выбор");

    private final String message;

    Messages(final String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
