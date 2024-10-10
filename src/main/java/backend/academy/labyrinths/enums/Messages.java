package backend.academy.labyrinths.enums;

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

    CHOOSE_DIFFICULT_LEVEL("""
        Уровни сложности:
        1. ...
        2. Средний
        3. ...
        """),

    POSITION_MENU("""
        1. Левый верхний угол
        2. Правый верхний угол
        3. Левый нижний угол
        4. Правый нижний угол
        """),

    START_POSITION_MESSAGE("Выберите точку лабиринта для старта: "),

    FINISH_POSITION_MESSAGE("Выберите точку лабиринта для финиша: "),

    ALGORITHM_MENU("""
        Выберите алгоритм нахождения пути:
        1. Поиск в глубину
        2. ...
        3. Случайный алгоритм"""),

    SOLVED_LABYRINTH_LABEL("""
        РЕШЕНИЕ ЛАБИРИНТА
        Алгоритм нахождения пути: %s
        """),

    SOLVE_NOT_FOUND("Решение не было найдено...");

    private final String message;

    Messages(final String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
