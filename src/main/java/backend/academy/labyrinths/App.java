package backend.academy.labyrinths;

import backend.academy.labyrinths.managers.SessionManager;

/**
 * Терминальный класс, служит для запуска всего приложения
 */
@SuppressWarnings("uncommentedmain")
public final class App {

    private static final SessionManager SESSION_MANAGER = new SessionManager();

    private App() {

    }

    public static void main(String[] args) throws InterruptedException {
        SESSION_MANAGER.start();
    }
}
