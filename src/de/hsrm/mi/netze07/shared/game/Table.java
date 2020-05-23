package de.hsrm.mi.netze07.shared.game;

import java.util.ArrayList;
import java.util.List;

public class Table {
	List<Card> dealerCards, playerCards;
	private boolean terminated;
	private Deck deck;
	private Card playercard;
	private Card dealercard;
	public Table() {
		dealerCards = new ArrayList<Card>();
		playerCards = new ArrayList<Card>();
	}
	
	public void addPlayerCard(Card card) {
		playerCards.add(card);
		this.playercard = card;
	}
	public Card getPlayerCard(){
		return playercard;
	}
	
	public void addDealerCard(Card casd) {
		this.dealercard = casd;
		dealerCards.add(casd);
	}
	public Card getDealerCard(){
		return dealercard;
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

	public void setDeck(){
		deck = new Deck();
	}

	public Deck getDeck(){
		return deck;
	}
}
