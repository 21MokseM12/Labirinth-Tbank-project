package backend.academy.labyrinths.impl.factories;

import backend.academy.labyrinths.impl.services.Session;
import backend.academy.labyrinths.interfaces.factories.Factory;

public class SessionFactory implements Factory<Session> {

    @Override
    public Session get() {
        return new Session();
    }
}
