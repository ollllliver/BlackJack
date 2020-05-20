package de.hsrm.mi.netze07.shared.game;

public enum GameStatus {
    LOOSE(0),
    WIN(1),
    THREE(2);
    private final int value;
    GameStatus(int value){
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
