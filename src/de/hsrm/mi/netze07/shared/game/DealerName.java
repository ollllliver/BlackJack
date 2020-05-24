package de.hsrm.mi.netze07.shared.game;

public enum DealerName {
	OLIVER(0),
    MARVIN(1),
    NINA(2),
	OLLI(3),
	PHILLIP(4);
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
