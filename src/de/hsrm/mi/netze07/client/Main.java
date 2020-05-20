package de.hsrm.mi.netze07.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
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

		BufferedReader playerInput = new BufferedReader(new InputStreamReader((System.in)));
		Socket clientSocket = new Socket(HOST, PORT);
		Client client = new Client();
		
		client.initialize(clientSocket);
		

		sentence = playerInput.readLine();
		client.outToServer(sentence);
		
		modifiedSentence = client.inFromServer();
		System.out.println("Antwort vom Dealer: " + modifiedSentence);
		
		clientSocket.close();
		
	}
}
