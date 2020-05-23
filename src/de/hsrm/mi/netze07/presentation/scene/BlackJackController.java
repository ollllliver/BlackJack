package de.hsrm.mi.netze07.presentation.scene;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class BlackJackController implements Initializable {

	@FXML
	private AnchorPane startPane;

	@FXML
	private TextField inputPlayerName;

	@FXML
	private Button buttonStartGame;

	@FXML
	private AnchorPane tablePane;

	@FXML
	private ImageView dCardPos_0;

	@FXML
	private ImageView dCardPos_1;

	@FXML
	private ImageView dCardPos_2;

	@FXML
	private ImageView dCardPos_3;

	@FXML
	private ImageView dCardPos_4;

	@FXML
	private ImageView dCardPos_5;

	@FXML
	private ImageView pCardPos_0;

	@FXML
	private ImageView pCardPos_1;

	@FXML
	private ImageView pCardPos_2;

	@FXML
	private ImageView pCardPos_3;

	@FXML
	private ImageView pCardPos_4;

	@FXML
	private ImageView pCardPos_5;

	@FXML
	private Button buttonDraw;

	@FXML
	private Button buttonHold;

	@FXML
	private Button buttonExitGame;

	@FXML
	private Button buttonPlayAgain;

	@FXML
	void draw(ActionEvent event) {

	}

	@FXML
	void exitGame(ActionEvent event) {
		System.exit(0);
	}

	@FXML
	void hold(ActionEvent event) {

	}

	@FXML
	void playAgain(ActionEvent event) {

	}

	@FXML
	void setPlayerName(ActionEvent event) {

	}

	@FXML
	void startGame(ActionEvent event) {
		scrollUp();
	}

	void scrollUp() {
		TranslateTransition tr1 = new TranslateTransition();
		tr1.setDuration(Duration.millis(300));
		tr1.setToX(0);
		tr1.setToY(-500);
		tr1.setNode(startPane);
		TranslateTransition tr2 = new TranslateTransition();
		tr2.setDuration(Duration.millis(300));
		tr2.setFromX(0);
		tr2.setFromY(500);
		tr2.setToX(0);
		tr2.setToY(0);
		tr2.setNode(tablePane);
		ParallelTransition pt = new ParallelTransition(tr1, tr2);
		pt.play();
	}

	void scrollDown() {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}