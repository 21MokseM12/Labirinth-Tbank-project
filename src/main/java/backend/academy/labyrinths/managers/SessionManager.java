package backend.academy.labyrinths.managers;

import backend.academy.labyrinths.impl.services.Session;
import backend.academy.labyrinths.impl.ui.services.UserInterface;

public class SessionManager {

    private final UserInterface ui = new UserInterface();

    public void start() throws InterruptedException {
        ui.printGreeting();
        while (true) {
            chooseMenuVariant();
            getSessionAgain();
        }
    }

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
