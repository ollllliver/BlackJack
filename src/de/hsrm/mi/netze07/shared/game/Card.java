package de.hsrm.mi.netze07.shared.game;

public class Card {
    private final CardType type;
    private final CardValue value;

    Card(CardType t, CardValue v) {
        type = t;
        value = v;
    }

    public CardType getType() {
        return type;
    }

    public CardValue getValue() {
        return value;
    }
}
