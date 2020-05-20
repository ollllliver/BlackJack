package de.hsrm.mi.netze07.server;

import de.hsrm.mi.netze07.shared.messaging.MessageType;

public class Main {
    public static final int PORT = 29000;
    public static final String HOST = "127.0.0.1";

    public static void main(String[] args) {
        Server server = new Server(PORT);
	
        
	
        //server.registerMessageHandler(MessageType.PLAY, new PlayerSetupMessageHandler());
	
       // server.registerMessageHandler(MessageType.TURN, new RequestTurnMessageHandler());
	
	
        server.start();
    }
}
