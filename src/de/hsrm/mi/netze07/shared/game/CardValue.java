package de.hsrm.mi.netze07.shared.game;

public enum CardValue {
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10),
    QUEEN(10),
    KING(10),
    ACE(11);
    private final int value;
    CardValue(int value){
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static CardValue fromValue(int value) {
        for (CardValue t : CardValue.values()) {
            if (t.value == value) return t;
        }
        return null;
    }
}
