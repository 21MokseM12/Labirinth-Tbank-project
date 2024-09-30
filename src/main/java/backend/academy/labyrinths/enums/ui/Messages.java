package backend.academy.labyrinths.enums.ui;

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

    BYE_MESSAGE("До свидания!");

    private final String message;

    Messages(final String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
