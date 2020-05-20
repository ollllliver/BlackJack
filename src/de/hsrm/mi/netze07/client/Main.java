package de.hsrm.mi.netze07.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {

	public static final int PORT = 29000;
	public static final String HOST = "127.0.0.1";
	static String sentence;
	static String modifiedSentence;

	public static void main(String[] args) throws UnknownHostException, IOException {

		// listen methode wie im tic tac toe, damit client die ganze zeit änderngen
		// erwartet
		// damit fluss steht, client bekommt nachricht, client sendet nachricht

		BufferedReader playerInput = new BufferedReader(new InputStreamReader((System.in)));
		Client client = new Client(HOST, PORT);

		client.initialize();
		client.listen();
		
		sentence = playerInput.readLine();
		
		client.outToServer(sentence);
		
		// modifiedSentence = client.inFromServer();
		// System.out.println("Antwort vom Dealer: " + modifiedSentence);

		//client.closeConnection();

	}
}
