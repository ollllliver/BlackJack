package de.hsrm.mi.netze07.shared.game;

import java.util.ArrayList;
import java.util.List;

public class Table {
	List<Card> dealerCards, playerCards;
	private boolean terminated;
	private Deck deck;
	private Card hiddenDealerCard;
	public Table() {
		dealerCards = new ArrayList<Card>();
		playerCards = new ArrayList<Card>();
		deck = new Deck();
		deck.shuffle();
	}
	
	public Card addPlayerCard() {
		Card card = deck.drawCard();
		playerCards.add(card);
		return card;
	}

	public Card addDealerCard() {
		Card card = deck.drawCard();
		dealerCards.add(card);
		
		return card;
	}

	public void addHiddenDealerCard(){
		this.hiddenDealerCard = deck.drawCard();
		dealerCards.add(hiddenDealerCard);
	}
	public Card getHiddenDealerCard(){
		return hiddenDealerCard;
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

	public boolean isTerminated() {
	
        return terminated;
	
	}
	
	public void setTerminated(boolean terminated){
		this.terminated = terminated;
	}


	
}
