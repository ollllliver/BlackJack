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
        //System.out.println(type + value);
       // CardType t = CardType.fromValue(Integer.parseInt(type));
       // CardValue v = CardValue.fromValue(Integer.parseInt(value));
      
       CardType t = CardType.valueOf(type);
       CardValue v = CardValue.valueOf(value);
      
       
        return new Card(t,v);
    }
    @Override
    public String toString() {
        return String.format("%s - %s", type.toString(), value.toString());
    }
}
