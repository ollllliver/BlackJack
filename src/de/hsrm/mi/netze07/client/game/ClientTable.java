package de.hsrm.mi.netze07.client.game;

import de.hsrm.mi.netze07.shared.game.Card;

import java.util.ArrayList;
import java.util.List;

public class ClientTable {
    private final List<Card> dealerCards, playerCards;

    public List<Card> getDealerCards() {
        return dealerCards;
    }

    public List<Card> getPlayerCards() {
        return playerCards;
    }

    public ClientTable() {
        dealerCards = new ArrayList<>();
        playerCards = new ArrayList<>();
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
}