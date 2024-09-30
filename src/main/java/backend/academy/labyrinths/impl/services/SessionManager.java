package backend.academy.labyrinths.impl.services;

import backend.academy.labyrinths.impl.factories.SessionFactory;
import backend.academy.labyrinths.impl.ui.services.UserInterface;
import backend.academy.labyrinths.interfaces.factories.Factory;
import backend.academy.labyrinths.interfaces.services.Startable;

public class SessionManager implements Startable {

    private final UserInterface ui = new UserInterface();

    private final Factory<Session> sessionFactory = new SessionFactory();

    @Override
    public void start() {
        ui.printGreeting();
        menu();
    }

    private void menu() {
        while (true) {
            chooseMenuVariant();
            getSessionAgain();
        }
    }

    private void chooseMenuVariant() {
        boolean exitMenuFlag = false;

        while (!exitMenuFlag) {
            ui.printMenu();

            ui.printChooseVariant();
            String chosenMenuVariant = ui.read();

            switch (chosenMenuVariant) {
                case "1":
                    sessionFactory.get().start();
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
