package de.hsrm.mi.netze07.client;

import de.hsrm.mi.netze07.client.messaging.MessageGenerator;
import de.hsrm.mi.netze07.shared.game.Card;
import de.hsrm.mi.netze07.shared.messaging.Message;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

public class Client {

    private DataOutputStream outToServer;
    private BufferedReader inFromServer;
    private final Socket clientSocket;
    private Thread listenThread;

    public Client(String HOST, int PORT) throws IOException {
        this.clientSocket = new Socket(HOST, PORT);
    }

    public void initialize() throws IOException {
        outToServer = new DataOutputStream(clientSocket.getOutputStream());
        inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    private void outToServer(String sentence) throws IOException {
        outToServer.writeBytes(sentence + '\n');
    }

    public void listen() {
        if (listenThread != null) {
            listenThread.interrupt();
            listenThread = null;
        }

        listenThread = new Thread(() -> {

            while (!Thread.interrupted()) {

                try {

                    final String raw = inFromServer.readLine();

                    if (Thread.interrupted()) {
                        break;
                    }

                    if (raw == null) {
                        break;
                    }

                    handleMessage(raw);

                } catch (SocketException e) {
                    if (Thread.interrupted()) {
                        System.out.println("Thread endet wie gewünscht");
                    } else {
                        e.printStackTrace();
                    }

                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
            }

        });

        listenThread.start();

    }

    private void handleMessage(String raw) {
        Message message = Message.rawToMessage(raw);
        switch (message.getType()) {
            case GAME_START: {
                System.out.println("Game starts..");
                break;
            }
            case PLAYER_CARD: {
                Card playerCard = Card.fromMessage(message.getContent().get("t"), message.getContent().get("v"));
                System.out.println("Player card was:" + message.getContent().get("t") + message.getContent().get("v"));
                break;
            }
            case DEALER_CARD: {
                Card dealerCard = Card.fromMessage(message.getContent().get("t"), message.getContent().get("v"));
                System.out.println("Dealer card was:" + message.getContent().get("t") + message.getContent().get("v"));
                break;
            }
            case HIDDEN_DEALER_CARD: {
                System.out.println("Dealer got his second card");
                break;
            }
            case SHOW_DEALER_CARD: {
                System.out.println("Dealer's second card was:");
                break;
            }
            case GAME_END: {
                System.out.println("Game just ended.");
                break;
            }
        }
    }

    public void shutdown() throws IOException {
        if (listenThread != null) {
            listenThread.interrupt();
            clientSocket.close();
            listenThread = null;
        }
    }

    public void startGame(String player) throws IOException {
        Message message = MessageGenerator.play(player);
        outToServer(message.toRaw());
    }

    public void takeCard() throws IOException {
        Message message = MessageGenerator.takeCard();
        outToServer(message.toRaw());
    }

    public void endTurn() throws IOException {
        Message message = MessageGenerator.endTurn();
        outToServer(message.toRaw());
    }

}
