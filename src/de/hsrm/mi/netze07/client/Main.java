package de.hsrm.mi.netze07.client;

import de.hsrm.mi.netze07.client.game.GameCommand;
import de.hsrm.mi.netze07.client.game.GameService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static final int PORT = 29000;
    public static final String HOST = "127.0.0.1";

    public static void main(String[] args) throws IOException {
        BufferedReader playerInput = new BufferedReader(new InputStreamReader((System.in)));
        Client client = new Client(HOST, PORT);
        String name = "Username";
        
        client.initialize();
        client.listen();

        GameService.welcome();
        GameService.availableCommands(GameCommand.PLAY);
        while (true) {
            String command = playerInput.readLine();
            switch (GameCommand.valueOf(command)) {
                case PLAY: {
                    GameService.requestName();
                    name = playerInput.readLine();
                    client.startGame(name);
                    break;
                }
                case DRAW: {
                    client.takeCard();
                    break;
                }
                case END_TURN: {
                    client.endTurn();
                    break;
                }
                case QUIT: {
                    client.shutdown();
                    System.exit(0);
                    break;
                }
                case PLAY_AGAIN: {
                	System.out.println("again");
                    client.playAgain(name);
                    break;
                }
            }
        }
    }
}
