package de.hsrm.mi.netze07.application;

import java.io.IOException;

import de.hsrm.mi.netze07.client.Client;
import de.hsrm.mi.netze07.presentation.scene.BlackJackController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    public static final int PORT = 29000;
    public static final String HOST = "127.0.0.1";
    private Client client;
    private BlackJackController controller;
    

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {

			// Parent root = FXMLLoader.load(getClass().getResource("MainTimer.fxml"));
			FXMLLoader fxmlLoader =  new FXMLLoader(getClass().getResource("/de/hsrm/mi/netze07/presentation/scene/BlackJack.fxml"));
			Parent root = (Parent)fxmlLoader.load();
			controller = fxmlLoader.<BlackJackController>getController();
			controller.setClient(client);
			Scene scene = new Scene(root);
			primaryStage.initStyle(StageStyle.DECORATED);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void init() throws IOException{
        client = new Client(HOST, PORT);
        client.initialize();
        client.listen();
	}
	
    public static void main(String[] args) throws IOException {
    	launch(args);
    }}
