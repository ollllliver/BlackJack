package de.hsrm.mi.netze07.shared.game;

public enum CardType {
    DIAMONDS(1),
    HEARTS(2),
    SPADES(3),
    CLUBS(4);

    private final int value;

    CardType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
