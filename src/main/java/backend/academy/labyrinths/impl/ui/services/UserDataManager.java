package backend.academy.labyrinths.impl.ui.services;

import backend.academy.labyrinths.interfaces.ui.services.Readable;
import backend.academy.labyrinths.interfaces.ui.services.Writeable;
import java.io.PrintStream;
import java.util.Scanner;

public class UserDataManager implements Readable, Writeable {

    private final Scanner scanner = new Scanner(System.in);

    private final PrintStream printer = System.out;

    @Override
    public void write(String data) {
        printer.print(data);
    }

    @Override
    public String read() {
        return scanner.nextLine();
    }
}
