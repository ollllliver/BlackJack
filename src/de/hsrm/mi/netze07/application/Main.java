package de.hsrm.mi.netze07.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {

			// Parent root = FXMLLoader.load(getClass().getResource("MainTimer.fxml"));
			Parent root = FXMLLoader.load(getClass().getResource("/de/hsrm/mi/netze07/presentation/scene/BlackJack.fxml"));
			
			Scene scene = new Scene(root);
			primaryStage.initStyle(StageStyle.DECORATED);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void main (String [] args) {
		launch(args);
	}
}
