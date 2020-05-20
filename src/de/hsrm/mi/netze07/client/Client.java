package de.hsrm.mi.netze07.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {

	DataOutputStream outToServer;
	BufferedReader inFromServer;

	public void initialize(Socket clientSocket) throws IOException {
		outToServer = new DataOutputStream(clientSocket.getOutputStream());
		inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	}
	

	public void outToServer(String sentence) throws IOException {
		outToServer.writeBytes(sentence + '\n');
	}
	

	public String inFromServer() throws IOException {
		return inFromServer.readLine();
	}

}
