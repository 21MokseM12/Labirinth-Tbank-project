package backend.academy.labyrinths;

import backend.academy.labyrinths.impl.services.SessionManager;
import backend.academy.labyrinths.interfaces.services.Startable;

public final class App {

    private static final Startable sessionManager = new SessionManager();

    public static void main(String[] args) {
        sessionManager.start();
    }
}
