package backend.academy.labyrinths.impl.ui.services;

import backend.academy.labyrinths.enums.AlgorithmType;
import backend.academy.labyrinths.enums.GeneratorType;
import backend.academy.labyrinths.enums.Messages;
import backend.academy.labyrinths.enums.SolverType;

/**
 * Класс, реализующий интерфейс общения с пользователем
 */
public class UserInterface {

    /**
     * Поле, отвечающее за ввод-вывод с консоли - на консоль
     */
    private final UserDataManager dataManager = new UserDataManager();

    /**
     * Метод вывода на экран приветственного сообщения
     */
    public void printGreeting() {
        dataManager.write(Messages.GREETING.toString());
    }

    /**
     * Метод вывода на экран начального меню
     */
    public void printMenu() {
        dataManager.write(Messages.MENU.toString());
    }

    /**
     * Метод вывода на экран сообщения с просьбой выбрать вариант из представленных в меню выше
     */
    public void printChooseVariant() {
        printNewLine();
        dataManager.write(Messages.CHOOSE_VARIANT.toString());
    }

    /**
     * Метод вывода на экран сообщения об ошибке
     */
    public void printInputError() {
        dataManager.write(Messages.INPUT_ERROR_MESSAGE.toString());
        printNewLine();
    }

    /**
     * Метод вывода на экран сообщения о возможности начать еще одну сессию
     */
    public void printSessionAgain() {
        printNewLine();
        dataManager.write(Messages.AGAIN_SESSION_MESSAGE.toString());
        printNewLine();
        dataManager.write(Messages.YES_NO_MENU.toString());
    }

    /**
     * Метод вывода на экран прощального сообщения
     */
    public void printByeMessage() {
        printNewLine();
        dataManager.write(Messages.BYE_MESSAGE.toString());
        printNewLine();
    }

    /**
     * Метод вывода на экран сообщения с просьбой ввести ширину лабиринта
     */
    public void printSetLabyrinthWidth(int maxWidth) {
        dataManager.write(Messages.CHOOSE_LABYRINTH_WIDTH.toString().formatted(maxWidth));
    }

    /**
     * Метод вывода на экран сообщения с просьбой ввести высоту лабиринта
     */
    public void printSetLabyrinthHeight(int maxHeight) {
        dataManager.write(Messages.CHOOSE_LABYRINTH_HEIGHT.toString().formatted(maxHeight));
    }

    /**
     * Метод вывода на экран меню с выбором алгоритма генерации лабиринта
     */
    public void printSetGenerationAlgorithm(AlgorithmType[] types) {
        printSetAlgorithmMenu(types, Messages.GENERATION_ALGORITHM_MENU_LABEL);
    }

    /**
     * Метод вывода на экран сообщения о выбранном алгоритме генерации лабиринта
     */
    public void printGenerateAlgorithmName(GeneratorType type) {
        printNewLine();
        dataManager.write(Messages.GENERATE_ALGORITHM_NAME.toString().formatted(type.toString()));
    }

    /**
     * Метод вывода на экран меню с выбором точек старта и финиша в лабиринте
     */
    public void printSetStartFinishPositions() {
        printNewLine();
        dataManager.write(Messages.START_FINISH_POSITIONS.toString());
    }

    /**
     * Метод вывода на экран меню с выбором алгоритма поиска пути в лабиринте
     */
    public void printSetResolvedAlgorithm(AlgorithmType[] types) {
        printSetAlgorithmMenu(types, Messages.SOLVER_ALGORITHM_MENU_LABEL);
    }

    /**
     * Метод вывода на экран сообщения о выбранном алгоритме поиска пути в лабиринте
     */
    public void printSolveLabyrinthLabel(SolverType type) {
        printNewLine();
        dataManager.write(Messages.SOLVED_LABYRINTH_LABEL.toString().formatted(type.toString()));
    }

    /**
     * Метод вывода на экран сообщения об отсутствии пути в лабиринте
     */
    public void printSolveNotFound() {
        dataManager.write(Messages.SOLVE_NOT_FOUND.toString());
        printNewLine();
    }

    /**
     * Метод ввода с экрана пользовательских данных
     */
    public String read() {
        return dataManager.read();
    }

    /**
     * Метод вывода меню выбора алгоритмов генерации и поиска пути в лабиринте через соответствующие enum'ы
     * @param types - массив, содержащий объекты enum'а
     * @param label - топик меню
     */
    private void printSetAlgorithmMenu(AlgorithmType[] types, Messages label) {
        StringBuilder resultString = new StringBuilder();
        for (int i = 0; i < types.length; i++) {
            resultString.append(i + 1).append(". ").append(types[i]).append("\n");
        }
        resultString.append(Messages.RANDOM_CHOICE.toString().formatted(types.length+1));

        printNewLine();
        dataManager.write(label.toString());
        dataManager.write(resultString.toString());
    }

    /**
     * Метод перехода на новую строку
     */
    private void printNewLine() {
        dataManager.write("\n");
    }
}
