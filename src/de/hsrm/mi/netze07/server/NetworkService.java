package de.hsrm.mi.netze07.server;

import java.io.*;
import java.net.Socket;

import de.hsrm.mi.netze07.shared.messaging.Message;

public class NetworkService {

    BufferedReader reader;
	
    BufferedWriter writer;
	
	
    public void setup(Socket socket) throws IOException {
	
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	
        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
	
    }
	
	
    public Message read() throws IOException{
	
        String raw = reader.readLine();
	
        if(raw == null) {
	
            System.out.println("null von readLine()");
	
            //throw new ConnectionLostException();
	
        }
	
	
        System.out.printf("Empfangen: %s%n", raw);
	
        return Message.rawToMessage(raw);
	
    }
	
	
    public void write(Message message) {
	
        try {
	
            System.out.printf("Nachricht an Client senden: %s%n", message);
	
            String raw = message.toRaw();
	
            writer.write(raw);
	
            writer.flush();
	
            System.out.printf("Gesendet: %s", raw);
	
        } catch (IOException e) {
	
            e.printStackTrace();
	
        }
	
    }

    
}