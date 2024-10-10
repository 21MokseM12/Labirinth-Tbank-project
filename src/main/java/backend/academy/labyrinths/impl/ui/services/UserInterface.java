package backend.academy.labyrinths.impl.ui.services;

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
        printNewLine();
        dataManager.write(Messages.CHOOSE_LABYRINTH_WIDTH.toString().formatted(maxWidth));
    }

    public void printSetLabyrinthHeight(int maxHeight) {
        dataManager.write(Messages.CHOOSE_LABYRINTH_HEIGHT.toString().formatted(maxHeight));
    }

    public void printSetDiffLevel() {
        printNewLine();
        dataManager.write(Messages.CHOOSE_DIFFICULT_LEVEL.toString());
    }

    public void printStartPositionMenu() {
        printNewLine();
        dataManager.write(Messages.START_POSITION_MESSAGE.toString());
        printNewLine();
        printStartFinishMenu();
    }

    public void printFinishPositionMenu() {
        printNewLine();
        dataManager.write(Messages.FINISH_POSITION_MESSAGE.toString());
        printNewLine();
        printStartFinishMenu();
    }

    public void printAlgorithmMenu() {
        printNewLine();
        dataManager.write(Messages.ALGORITHM_MENU.toString());
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

    private void printNewLine() {
        dataManager.write("\n");
    }

    private void printDoubleNewLine() {
        printNewLine();
        printNewLine();
    }

    private void printStartFinishMenu() {
        dataManager.write(Messages.POSITION_MENU.toString());
    }
}
