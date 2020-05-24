package de.hsrm.mi.netze07.client.game;

import de.hsrm.mi.netze07.shared.game.Card;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class ClientTable {
//	private SimpleObjectProperty<Card> newPlayerCard, newDealerCard;
	private ObservableList<Card> playerCards, dealerCards;
	private SimpleObjectProperty<Integer> currentState;
	private SimpleObjectProperty<String> currentDealername;

	public List<Card> getDealerCards() {
		return dealerCards;
	}

	public List<Card> getPlayerCards() {
		return playerCards;
	}

	public ClientTable() {
		dealerCards = FXCollections.observableArrayList();
		playerCards = FXCollections.observableArrayList();
		currentState = new SimpleObjectProperty<>();
		currentDealername = new SimpleObjectProperty<String>();
	}

	public void addPlayerCard(Card card) {
		playerCards.add(card);
	}

	public void addDealerCard(Card card) {
		dealerCards.add(card);
	}

	public int getPlayerValue() {
		System.out.println("getPlayerValue");
		int value = 0;
		for (Card card : playerCards) {
			value += card.getValue().getValue();
		}
		value = reduceValue(value, numberOfAss(playerCards));
		return value;
	}

	public int getDealerValue() {
		System.out.println("getDealerValue");
		int value = 0;
		for (Card card : dealerCards) {
			value += card.getValue().getValue();
		}
		value = reduceValue(value, numberOfAss(dealerCards));
		return value;
	}

	private static int numberOfAss(List<Card> cards) {
		int numberOfAce = 0;
		for (Card card : cards) {
			if (card.getValue().getValue() == 11)
				numberOfAce++;
		}
		return numberOfAce;
	}

	private int reduceValue(int value, int numOfAces) {
		if (value > 21) {
			int tooMuch = value - 21;
			int neededAces = (int) Math.ceil((double) tooMuch / (double) 10);
			if (neededAces <= numOfAces) {
				value = value - (10 * neededAces);
			}
		}
		return value;
	}

	public ObservableList<Card> observablePlayerCardsList() {
		return playerCards;
	}

	public ObservableList<Card> observableDealerCardsList() {
		return dealerCards;
	}
	
	public SimpleObjectProperty<Integer> currentState() {
		return currentState;
	}
	
	public SimpleObjectProperty<String> currentDealerName() {
		return currentDealername;
	}

	public void changeState(int state) {
		currentState.set(state);
	}

	public void setDealerName(String dealername) {
		this.currentDealername.set(dealername);
	}

	public void changeDealer(String dealerName) {
		currentDealername.set(dealerName);
		
	}
}