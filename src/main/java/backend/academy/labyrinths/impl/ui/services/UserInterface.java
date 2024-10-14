package backend.academy.labyrinths.impl.ui.services;

import backend.academy.labyrinths.enums.AlgorithmType;
import backend.academy.labyrinths.enums.GeneratorType;
import backend.academy.labyrinths.enums.Messages;
import backend.academy.labyrinths.enums.SolverType;

public class UserInterface {

    private final UserDataManager dataManager = new UserDataManager();

    public void printGreeting() {
        dataManager.write(Messages.GREETING.toString());
    }

    public void printMenu() {
        dataManager.write(Messages.MENU.toString());
    }

    public void printChooseVariant() {
        printNewLine();
        dataManager.write(Messages.CHOOSE_VARIANT.toString());
    }

    public void printInputError() {
        dataManager.write(Messages.INPUT_ERROR_MESSAGE.toString());
        printNewLine();
    }

    public void printSessionAgain() {
        printNewLine();
        dataManager.write(Messages.AGAIN_SESSION_MESSAGE.toString());
        printNewLine();
        dataManager.write(Messages.YES_NO_MENU.toString());
    }

    public void printByeMessage() {
        printNewLine();
        dataManager.write(Messages.BYE_MESSAGE.toString());
        printNewLine();
    }

    public void printSetLabyrinthWidth(int maxWidth) {
    dataManager.write(Messages.CHOOSE_LABYRINTH_WIDTH.toString().formatted(maxWidth));
    }

    public void printSetLabyrinthHeight(int maxHeight) {
        dataManager.write(Messages.CHOOSE_LABYRINTH_HEIGHT.toString().formatted(maxHeight));
    }

    public void printSetGenerationAlgorithm(AlgorithmType[] types) {
        printSetAlgorithmMenu(types, Messages.GENERATION_ALGORITHM_MENU_LABEL);
    }

    public void printGenerateAlgorithmName(GeneratorType type) {
        printNewLine();
        dataManager.write(Messages.GENERATE_ALGORITHM_NAME.toString().formatted(type.toString()));
    }

    public void printSetStartFinishPositions() {
        printNewLine();
        dataManager.write(Messages.START_FINISH_POSITIONS.toString());
    }

    public void printSetResolvedAlgorithm(AlgorithmType[] types) {
        printSetAlgorithmMenu(types, Messages.SOLVER_ALGORITHM_MENU_LABEL);
    }

    public void printSolveLabyrinthLabel(SolverType type) {
        printNewLine();
        dataManager.write(Messages.SOLVED_LABYRINTH_LABEL.toString().formatted(type.toString()));
    }

    public void printSolveNotFound() {
        dataManager.write(Messages.SOLVE_NOT_FOUND.toString());
        printNewLine();
    }

    public String read() {
        return dataManager.read();
    }

    private void printSetAlgorithmMenu(AlgorithmType[] types, Messages label) {
        StringBuilder resultString = new StringBuilder();
        for (int i = 0; i < types.length; i++) {
            resultString.append(i + 1).append(". ").append(types[i]).append("\n");
        }
        resultString.append(Messages.RANDOM_CHOICE);

        printNewLine();
        dataManager.write(label.toString());
        dataManager.write(resultString.toString());
    }

    private void printNewLine() {
        dataManager.write("\n");
    }
}
