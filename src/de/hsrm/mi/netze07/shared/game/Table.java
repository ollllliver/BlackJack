package de.hsrm.mi.netze07.shared.game;

import java.util.ArrayList;

public class Table {
	ArrayList<Card> dealerCards, playerCards;
	
	public Table() {
		
	}
	
	public void addPlayerCard(Card card) {
		playerCards.add(card);
	}
	
	public void addDealerCard(Card casd) {
		dealerCards.add(casd);
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
