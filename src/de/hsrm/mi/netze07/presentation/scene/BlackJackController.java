package de.hsrm.mi.netze07.presentation.scene;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import de.hsrm.mi.netze07.client.Client;
import de.hsrm.mi.netze07.client.game.ClientTable;
import de.hsrm.mi.netze07.shared.game.Card;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class BlackJackController implements Initializable {

	private Client client;
	private String playerName;

	@FXML
	private AnchorPane startPane;

	@FXML
	private Label labelGAME_END_X;

	@FXML
	private Label labelDEALER_SCORE;

	@FXML
	private Label labelPLAYER_SCORE;

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

	private ArrayList<ImageView> dCardPos = new ArrayList<ImageView>();

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

	private ArrayList<ImageView> pCardPos = new ArrayList<ImageView>();

	@FXML
	private Button buttonDraw;

	@FXML
	private Button buttonHold;

	@FXML
	private Button buttonExitGame;

	@FXML
	private Button buttonPlayAgain;

	@FXML
	void draw(ActionEvent event) throws IOException {
		client.takeCard();

	}

	@FXML
	void exitGame(ActionEvent event) throws IOException {
		client.shutdown();
		System.exit(0);
	}

	@FXML
	void hold(ActionEvent event) throws IOException {
		client.endTurn();
	}

	@FXML
	void playAgain(ActionEvent event) throws IOException {

		for (ImageView d : dCardPos) {
			d.setImage(null);
		}

		for (ImageView p : pCardPos) {
			p.setImage(null);
		}

		client.playAgain(playerName);

	}

	@FXML
	void setPlayerName(ActionEvent event) throws IOException {
		// TODO if input is not empty, call following method:
		// startGame(event);
	}

	@FXML
	void startGame(ActionEvent event) throws IOException {
		playerName = inputPlayerName.getText();
		client.startGame(playerName);
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

		buttonStartGame.disableProperty().bind(Bindings.isEmpty(inputPlayerName.textProperty()));
		dCardPos.add(dCardPos_0);
		dCardPos.add(dCardPos_1);
		dCardPos.add(dCardPos_2);
		dCardPos.add(dCardPos_3);
		dCardPos.add(dCardPos_4);
		dCardPos.add(dCardPos_5);
		pCardPos.add(pCardPos_0);
		pCardPos.add(pCardPos_1);
		pCardPos.add(pCardPos_2);
		pCardPos.add(pCardPos_3);
		pCardPos.add(pCardPos_4);
		pCardPos.add(pCardPos_5);

	}

	private void initializeCardListener() {
		client.currentTableProperty().get().observablePlayerCardsList().addListener(new ListChangeListener<Card>() {

			@Override
			public void onChanged(Change<? extends Card> arg0) {
				addGuiCard(arg0.getList(), pCardPos);
				
			}

		});

		client.currentTableProperty().get().observableDealerCardsList().addListener(new ListChangeListener<Card>() {

			@Override
			public void onChanged(Change<? extends Card> arg0) {
				addGuiCard(arg0.getList(), dCardPos);
			}

		});

	}

	private void addGuiCard(ObservableList<? extends Card> list, ArrayList<ImageView> possitions) {
		// TODO Auto-generated method stub
		String t = list.get(list.size() - 1).getType().toString().substring(0, 1).toUpperCase();
		String v = list.get(list.size() - 1).getValue().toString();
		System.out.println("imagename: " + v + t);

		Image img = new Image(
				getClass().getResource("/de/hsrm/mi/netze07/presentation/assets/" + v + t + ".png").toExternalForm());
		int pos = 0;
		for (int i = 0; possitions.get(i).getImage() != null && i < 5; i++) {
			pos++;
		}
		System.out.println(pos);
		possitions.get(pos).setImage(img);
	}

	private void initializeTableListener() {
		client.currentTableProperty().addListener(new ChangeListener<ClientTable>() {

			@Override
			public void changed(ObservableValue<? extends ClientTable> arg0, ClientTable arg1, ClientTable arg2) {

				initializeCardListener();

			}

		});
	}

	public void setClient(Client client) {
		this.client = client;
		initializeTableListener();
	}

}
