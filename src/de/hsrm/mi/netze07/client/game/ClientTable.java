package de.hsrm.mi.netze07.client.game;

import de.hsrm.mi.netze07.shared.game.Card;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class ClientTable {
//	private SimpleObjectProperty<Card> newPlayerCard, newDealerCard;
	private ObservableList<Card> playerCards, dealerCards;

    public List<Card> getDealerCards() {
        return dealerCards;
    }

    public List<Card> getPlayerCards() {
        return playerCards;
    }

    public ClientTable() {
        dealerCards = FXCollections.observableArrayList();
        playerCards = FXCollections.observableArrayList();
    }

    public void addPlayerCard(Card card) {
        playerCards.add(card);
    }

    public void addDealerCard(Card card) {
        dealerCards.add(card);
    }


    public int getPlayerValue() {
        int value = 0;
        for(Card card:playerCards) {
            value += card.getValue().getValue();
        }
        return value;
    }

    public int getDealerValue() {
        int value = 0;
        for(Card card:dealerCards) {
            value += card.getValue().getValue();
        }
        return value;
    }
    
    public ObservableList<Card> observablePlayerCardsList() {
		return playerCards;
	}
    
    public ObservableList<Card> observableDealerCardsList() {
		return dealerCards;
	}
}