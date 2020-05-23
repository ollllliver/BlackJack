package de.hsrm.mi.netze07.client;

import de.hsrm.mi.netze07.client.game.GameCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static final int PORT = 29000;
    public static final String HOST = "127.0.0.1";

    public static void main(String[] args) throws IOException {
        BufferedReader playerInput = new BufferedReader(new InputStreamReader((System.in)));
        Client client = new Client(HOST, PORT);

        client.initialize();
        client.listen();

        System.out.println("Welcome to ConsoleBlackJack \nCurrently available commands: " + GameCommand.PLAY.toString());
        while (true) {
            String command = playerInput.readLine();
            switch (GameCommand.valueOf(command)) {
                case PLAY: {
                    System.out.println("Enter your name:");
                    String name = playerInput.readLine();
                    client.startGame(name);
                    break;
                }
                case DRAW: {
                    client.takeCard();
                    break;
                }
                case FOLD: {
                    client.endTurn();
                    break;
                }
                case QUIT: {
                    client.shutdown();
                    System.exit(0);
                    break;
                }
            }
        }
    }
}
