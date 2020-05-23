package de.hsrm.mi.netze07.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client {

	private DataOutputStream outToServer;
	private BufferedReader inFromServer;
	private Socket clientSocket;
	private Thread listenThread;

	public Client(String HOST, int PORT) throws UnknownHostException, IOException {
		this.clientSocket = new Socket(HOST, PORT);
	}

	public void initialize() throws IOException {
		outToServer = new DataOutputStream(clientSocket.getOutputStream());
		inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	}

	public void outToServer(String sentence) throws IOException {
		outToServer.writeBytes(sentence + '\n');
	}

	public String inFromServer() throws IOException {
		return inFromServer.readLine();
	}

	public void closeConnection() throws IOException {
		clientSocket.close();
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

					// handleMessage(raw);
					System.out.println(raw);

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
		// TODO Auto-generated method stub

	}

}
