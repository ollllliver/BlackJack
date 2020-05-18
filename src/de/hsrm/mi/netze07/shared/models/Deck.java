package de.hsrm.mi.netze07.shared.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cardList;

    Deck() {
        cardList = new ArrayList<>();
        Arrays.stream(CardType.values()).forEach(t ->
                Arrays.stream(CardValue.values()).forEach(v -> cardList.add(new Card(t, v)))
        );
    }

    public void shuffle(){
        Collections.shuffle(cardList);
    }

    public Card drawCard() {
        return this.cardList.remove(0);
    }

}
