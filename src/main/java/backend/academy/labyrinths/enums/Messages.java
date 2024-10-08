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

    CHOOSE_LABYRINTH_WIDTH("Введите ширину лабиринта в клетках: "),

    CHOOSE_LABYRINTH_HEIGHT("Введите высоту лабиринта в клетках: "),

    CHOOSE_DIFFICULT_LEVEL("""
        Уровни сложности:
        1. Легкий
        2. Средний
        3. Сложный
        """),

    POSITION_MENU("""
        1. Сверху по центру
        2. Снизу по центру
        3. Слева по центру
        4. Справа по центру
        5. Случайный выбор
        """),

    START_POSITION_MESSAGE("Выберите точку лабиринта для старта: "),

    FINISH_POSITION_MESSAGE("Выберите точку лабиринта для финиша: ");

    private final String message;

    Messages(final String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
