package de.hsrm.mi.netze07.presentation.scene;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import de.hsrm.mi.netze07.client.Client;
import de.hsrm.mi.netze07.client.game.ClientTable;
import de.hsrm.mi.netze07.shared.game.Card;
import de.hsrm.mi.netze07.shared.game.GameStatus;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableStringValue;
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
	private String playerName, dealerName;
	
    @FXML
    private ImageView imageDealer;

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

		labelGAME_END_X.setText(null);

		client.playAgain(playerName);

		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				showHoldDraw();
				hideQuitAgain();
			}

		});
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

	private void initializeAfterTableListener() {
		client.currentTableProperty().get().observablePlayerCardsList().addListener(new ListChangeListener<Card>() {

			@Override
			public void onChanged(Change<? extends Card> arg0) {
				addGuiCard(arg0.getList(), pCardPos);

				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						labelPLAYER_SCORE.setText(playerName + ": " + client.currentTableProperty().get().getPlayerValue());
					}
				});

			}

		});

		client.currentTableProperty().get().observableDealerCardsList().addListener(new ListChangeListener<Card>() {

			@Override
			public void onChanged(Change<? extends Card> arg0) {
				addGuiCard(arg0.getList(), dCardPos);
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						labelDEALER_SCORE.setText(dealerName + ": " + client.currentTableProperty().get().getDealerValue());
						Image img = new Image(getClass().getResource("/de/hsrm/mi/netze07/presentation/assets/dealer_" + dealerName + ".png").toExternalForm());
						imageDealer.setImage(img);
					}
				});
			}

		});

		client.currentTableProperty().get().currentState().addListener(new ChangeListener<Integer>() {

			@Override
			public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {

				System.out.println("tablechanged");

				Platform.runLater(new Runnable() {

					@Override
					public void run() {

						hideHoldDraw();
						showQuitAgain();

						GameStatus status = GameStatus.fromValue(arg2);

						switch (status) {
						case LOOSE:
							labelGAME_END_X.setText("YOU LOST");
							break;
						case WIN:
							labelGAME_END_X.setText("YOU WON");
							break;
						case DRAW:
							labelGAME_END_X.setText("DRAW");
							break;
						default:
							break;
						}

					}

				});

			}
		});
		
		client.currentTableProperty().get().currentDealerName().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				dealerName = arg2;
				
			}
			
		});

	}

	private void hideHoldDraw() {
		buttonDraw.setDisable(true);
		buttonHold.setDisable(true);
	}

	private void showQuitAgain() {
		buttonExitGame.setDisable(false);
		buttonPlayAgain.setDisable(false);
	}

	private void hideQuitAgain() {
		buttonExitGame.setDisable(true);
		buttonPlayAgain.setDisable(true);
	}

	private void showHoldDraw() {
		buttonDraw.setDisable(false);
		buttonHold.setDisable(false);
	}

	private void addGuiCard(ObservableList<? extends Card> list, ArrayList<ImageView> possitions) {
		// TODO Auto-generated method stub
		String t = list.get(list.size() - 1).getType().toString().substring(0, 1).toUpperCase();
		String v = list.get(list.size() - 1).getValue().toString();
		Image img = new Image(
				getClass().getResource("/de/hsrm/mi/netze07/presentation/assets/" + v + t + ".png").toExternalForm());
		int pos = 0;
		// TODO HiddenCard for dealer
		for (int i = 0; possitions.get(i).getImage() != null && i < 5; i++) {
			pos++;
		}
		// add new card in GUI
		possitions.get(pos).setImage(img);
	}

	private void initializeTableListener() {
		client.currentTableProperty().addListener(new ChangeListener<ClientTable>() {

			@Override
			public void changed(ObservableValue<? extends ClientTable> arg0, ClientTable arg1, ClientTable arg2) {

				initializeAfterTableListener();

			}

		});
	}

	public void setClient(Client client) {
		this.client = client;
		initializeTableListener();
		hideQuitAgain();
	}

}
