package backend.academy.labyrinths.impl.ui.services;

import backend.academy.labyrinths.interfaces.ui.services.Readable;
import backend.academy.labyrinths.interfaces.ui.services.Writeable;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Класс ввода-вывода данных с консоли и на консоль
 */
public class UserDataManager implements Readable, Writeable {

    private final Scanner scanner = new Scanner(System.in);

    private final PrintStream printer = System.out;

    /**
     * Метод печати данных на консоль
     * @param data - данные для печати
     */
    @Override
    public void write(String data) {
        printer.print(data);
    }

    /**
     * Метод считывания данных с консоли
     * @return - данные с консоли
     */
    @Override
    public String read() {
        return scanner.nextLine();
    }
}
