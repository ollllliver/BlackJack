package de.hsrm.mi.netze07.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.HashMap;

import de.hsrm.mi.netze07.server.messaging.IMessageHandler;
import de.hsrm.mi.netze07.server.messaging.MessageGenerator;
import de.hsrm.mi.netze07.shared.game.Table;

import de.hsrm.mi.netze07.shared.messaging.Message;
import de.hsrm.mi.netze07.shared.messaging.MessageType;

public class Server {

    private int port;

    private HashMap<SocketAddress, Table> gameStates;

    private HashMap<MessageType, IMessageHandler> messageHandlers;

    public Server(int port) {

        this.port = port;

        this.messageHandlers = new HashMap<>();

        this.gameStates = new HashMap<>();

    }

    public void registerHandler(MessageType command, IMessageHandler handler) {
        messageHandlers.put(command, handler);
    }

    public void start() {
        try {
            @SuppressWarnings("resource")
			final ServerSocket serverSocket = new ServerSocket(port);

            while (true) {
                try {
                    final Socket socket = serverSocket.accept();
                    System.out.println("Neue Verbindung: " + socket.getInetAddress());

                    Table table = new Table();

                    gameStates.put(socket.getRemoteSocketAddress(), table);

                    NetworkService networkService = new NetworkService();

                    networkService.setup(socket);

                    final Thread thread = new Thread(() -> {

                       

                        try {
                                while (!table.isTerminated()) {
                                Message message = networkService.read();

                                if (message == null) {

                                    System.out.println("Nachricht ungültig (Format)");

                                    networkService.write(MessageGenerator.invalidProtocol());

                                }

                                System.out.printf("Nachricht empfangen: %s%n", message);

                                MessageType type = message.getType();

                                
                                    IMessageHandler targetHandler = messageHandlers.get(type);
                                    System.out.printf("Handler ausgewählt: %s%n", targetHandler.getClass().getName());

                                    targetHandler.handleMessage(message, table, networkService);
                            }
                        }catch(NullPointerException e){

                            System.out.println("Nachricht ungültig (Protokollversion)");

                            networkService.write(MessageGenerator.invalidProtocol());

                            //  continue;
                        
                        

                        } catch (IOException e) {

                            networkService.write(MessageGenerator.invalidProtocol());
                            e.printStackTrace();

                        } catch (ConnectionLostException e) {

                            System.out.println("Verbindung zu Cient verloren");
                            
                        }

                        

                        gameStates.remove(socket.getRemoteSocketAddress());

                    });

                    thread.start();

                } catch (IOException e) {

                    e.printStackTrace();

                }

            }

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

}