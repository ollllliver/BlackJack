package de.hsrm.mi.netze07.client.game;

import de.hsrm.mi.netze07.shared.game.GameStatus;

import java.util.Arrays;
import java.util.stream.Collectors;

public class GameService {
    public static void welcome() {
        System.out.println("Welcome to ConsoleBlackJack");
    }

    public static void gameStart() {
        System.out.println("It's you against the dealer. The game started!");
    }

    public static void availableCommands(GameCommand... command) {
        System.out.println("Currently available commands: " +
                Arrays.stream(command).map(GameCommand::toString).collect(Collectors.joining(", "))
        );
    }

    public static void requestName() {
        System.out.println("Enter your name:");
    }

    public static void playerHand(ClientTable table) {
        System.out.println("Your cards:");
        table.getPlayerCards().forEach(c -> System.out.println("\t" + c.toString()));
        System.out.println("Your score is: " + table.getPlayerValue());
    }

    public static void dealerHand(ClientTable table) {
        System.out.println("Dealer cards:");
        table.getDealerCards().forEach(c -> System.out.println("\t" + c.toString()));
        System.out.println("His score is: " + table.getDealerValue());
    }

    public static void hiddenDealerCard() {
        System.out.println("The dealer got his hidden card.");
    }

    public static void showDealerCard() {
        System.out.println("The dealer shows his hidden card");
    }

    public static void gameEnd(int state) {
        System.out.println("The game just ended");
        GameStatus status = GameStatus.fromValue(state);
        if(status == null) {
            return;
        }
        switch (status) {
            case WIN: {
                System.out.println("YOU WON!");
                break;
            }
            case LOOSE:{
                System.out.println("You lost!");
                break;
            }
            case DRAW: {
                System.out.println("Damn, it was a draw..");
            }
        }
    }
}