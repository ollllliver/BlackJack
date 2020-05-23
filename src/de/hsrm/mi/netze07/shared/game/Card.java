package de.hsrm.mi.netze07.shared.game;

public class Card {
    private final CardType type;
    private final CardValue value;

    public Card(CardType t, CardValue v) {
        type = t;
        value = v;
    }

    public CardType getType() {
        return type;
    }

    public CardValue getValue() {
        return value;
    }

    public static Card fromMessage(String type, String value) {
        CardType t = CardType.fromValue(Integer.parseInt(type));
        CardValue v = CardValue.fromValue(Integer.parseInt(value));
        return new Card(t,v);
    }
}
