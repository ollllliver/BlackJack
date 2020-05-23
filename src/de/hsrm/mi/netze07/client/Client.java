package de.hsrm.mi.netze07.client;

import de.hsrm.mi.netze07.client.game.ClientTable;
import de.hsrm.mi.netze07.client.game.GameCommand;
import de.hsrm.mi.netze07.client.game.GameService;
import de.hsrm.mi.netze07.client.messaging.MessageGenerator;
import de.hsrm.mi.netze07.shared.game.Card;
import de.hsrm.mi.netze07.shared.messaging.Message;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class Client {

    private BufferedWriter outToServer;
    private BufferedReader inFromServer;
    private final Socket clientSocket;
    private ClientTable table;
    private Thread listenThread;

    public Client(String HOST, int PORT) throws IOException {
        this.clientSocket = new Socket(HOST, PORT);
    }

    public void initialize() throws IOException {
        outToServer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    private void outToServer(String sentence) throws IOException {
        outToServer.write(sentence);
        outToServer.flush();
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
                    if (!Thread.interrupted()) {
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

    private void handleMessage(String raw) throws IOException {
        Message message = Message.rawToMessage(raw);
        switch (message.getType()) {
            case GAME_START: {
            	table = new ClientTable();
            	//TODO initializeCardListener() in GUI controller;
        		GameService.gameStart();
                GameService.availableCommands(GameCommand.TABLE_READY);
        		tableReady();
                break;
            }
            case PLAYER_CARD: {
                table.addPlayerCard(Card.fromMessage(message.getContent().get("t"), message.getContent().get("v")));
                GameService.playerHand(table);
                GameService.availableCommands(GameCommand.DRAW, GameCommand.END_TURN);
                break;
            }
            case DEALER_CARD: {
                table.addDealerCard(Card.fromMessage(message.getContent().get("t"), message.getContent().get("v")));
                GameService.dealerHand(table);
                break;
            }
            case HIDDEN_DEALER_CARD: {
                GameService.hiddenDealerCard();
                break;
            }
            case SHOW_DEALER_CARD: {
                GameService.showDealerCard();
                table.addDealerCard(Card.fromMessage(message.getContent().get("t"), message.getContent().get("v")));
                GameService.dealerHand(table);
                break;
            }
            case GAME_END: {
                GameService.gameEnd(Integer.parseInt(message.getContent().get("s")));
                GameService.availableCommands(GameCommand.QUIT, GameCommand.PLAY_AGAIN);
                break;
            }
		default:
			break;
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

    public void playAgain(String player) throws IOException {
        Message message = MessageGenerator.playAgain(player);
        outToServer(message.toRaw());
    }
    
    public ClientTable getTable() {
		return table;
	}

	public void tableReady() throws IOException {
		Message message = MessageGenerator.tableReady();
        outToServer(message.toRaw());
	}


}
