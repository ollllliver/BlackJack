package de.hsrm.mi.netze07.shared.game;

public enum DealerName {
	OLIVER(4),
    MARVIN(0),
    NINA(3),
	OLLI(1),
	PHILLIP(2);
    private final int value;

    DealerName(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static DealerName fromValue(int value) {
        for (DealerName n : DealerName.values()) {
            if (n.value == value) return n;
        }
        return null;
    }

}
