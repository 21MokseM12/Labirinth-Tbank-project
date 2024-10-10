package backend.academy.labyrinths;

import backend.academy.labyrinths.managers.SessionManager;

public final class App {

    private static final SessionManager sessionManager = new SessionManager();

    public static void main(String[] args) throws InterruptedException {
        sessionManager.start();
    }
}
