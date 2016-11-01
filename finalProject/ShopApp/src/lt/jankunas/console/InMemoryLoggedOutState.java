package lt.jankunas.console;

import java.util.Scanner;

import lt.jankunas.shop.*;
import lt.jankunas.shop.helpers.CommandLineParser;

public class InMemoryLoggedOutState implements LoggedOutState {

    private Scanner in;
    private User currentUser;
    private LogIn login;
    private CommandLineParser parser;
    private String[] entries;

    public InMemoryLoggedOutState(LogIn login, Scanner in, CommandLineParser parser) {
        this.login = login;
        this.in = in;
        this.parser = parser;
    }

    public User getUser() {
        System.out.println("Please login");
        String command = in.nextLine();
        entries = parser.parse(command);
        executeLogInCommand(entries);
        return currentUser;
    }

    private void executeLogInCommand(String[] entries) {
        if ("login".equalsIgnoreCase(entries[0]) && entries.length == 3) {
            String username = entries[1];
            String password = entries[2];
            if (login.isLoggedIn(username, password))
                this.currentUser = login.getUser();
        } else
            System.out.println("Invalid number of arguments or command does not exist");
    }
}
