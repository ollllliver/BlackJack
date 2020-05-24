package de.hsrm.mi.netze07.shared.game;

import java.util.ArrayList;
import java.util.List;

public class Table {
	private List<Card> dealerCards, playerCards;
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
		System.out.println("getPlayerValue");
		int value = 0;
		for(Card card:playerCards) {
			value += card.getValue().getValue();
		}
		value = reduceValue(value, numberOfAss(playerCards));
		return value;
	}

	public int getDealerValue() {
		System.out.println("getDealerValue");
		int value = 0;
		for(Card card:dealerCards) {
			value += card.getValue().getValue();
		}
		value = reduceValue(value, numberOfAss(dealerCards));
		return value;
	}
	
	private static int numberOfAss(List<Card> cards) {
		int numberOfAce = 0;
		for(Card card:cards) {
			if(card.getValue().getValue()==11)
			numberOfAce++;
		}
		return numberOfAce;
	}
	
	private int reduceValue(int value, int numOfAces) {
		if (value>21) {
			int tooMuch = value-21;
			int neededAces = (int) Math.ceil((double)tooMuch/(double)10);
			if(neededAces<=numOfAces) {
				value=value-(10*neededAces);
			}
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
