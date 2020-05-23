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
		deck = new Deck();
		deck.shuffle();
	}
	
	public void addPlayerCard() {
		this.playercard=deck.drawCard();
		playerCards.add(playercard);
	}
	public Card getPlayerCard(){
		return playercard;
	}
	
	public void addDealerCard() {
		this.dealercard = deck.drawCard();
		dealerCards.add(dealercard);
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


	
}
