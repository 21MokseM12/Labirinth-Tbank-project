package backend.academy.labyrinths.managers;

import backend.academy.labyrinths.impl.services.Session;
import backend.academy.labyrinths.impl.ui.services.UserInterface;

/**
 * Класс начального пользовательского меню
 */
public class SessionManager {

    /**
     * Сервис пользовательского интерфейса
     */
    private final UserInterface ui = new UserInterface();

    /**
     * Метод начала игры
     * @throws InterruptedException - исключение, выбрасываемое в случае, если задержка вывода лабиринта не валидна
     */
    public void start() throws InterruptedException {
        ui.printGreeting();
        while (true) {
            chooseMenuVariant();
            getSessionAgain();
        }
    }

    /**
     * Метод начального меню
     * @throws InterruptedException - исключение, выбрасываемое в случае, если задержка вывода лабиринта не валидна
     */
    private void chooseMenuVariant() throws InterruptedException {
        boolean exitMenuFlag = false;

        while (!exitMenuFlag) {
            ui.printMenu();

            ui.printChooseVariant();
            String chosenMenuVariant = ui.read();

            switch (chosenMenuVariant) {
                case "1":
                    new Session(ui).start();
                    exitMenuFlag = true;
                    break;
                case "2":
                    ui.printByeMessage();
                    System.exit(0);
                    break;
                default:
                    ui.printInputError();
                    break;
            }
        }
    }

    /**
     * Метод вопроса о повторной игровой сессии
     */
    private void getSessionAgain() {
        boolean exitAgainMenuFlag = false;

        while (!exitAgainMenuFlag) {
            ui.printSessionAgain();

            ui.printChooseVariant();
            String chosenSessionAgainVariant = ui.read();

            switch (chosenSessionAgainVariant) {
                case "1":
                    exitAgainMenuFlag = true;
                    break;
                case "2":
                    ui.printByeMessage();
                    System.exit(0);
                    break;
                default:
                    ui.printInputError();
                    break;
            }
        }
    }

}
