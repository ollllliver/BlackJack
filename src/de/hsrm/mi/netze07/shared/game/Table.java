package de.hsrm.mi.netze07.shared.game;

import java.util.ArrayList;
import java.util.List;

public class Table {
	List<Card> dealerCards, playerCards;
	private boolean terminated;
	
	public Table() {
		dealerCards = new ArrayList<Card>();
		playerCards = new ArrayList<Card>();
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

	public boolean isTerminated() {
	
        return terminated;
	
	}
	
	public void setTerminated(boolean terminated){
		this.terminated = terminated;
	}
}
