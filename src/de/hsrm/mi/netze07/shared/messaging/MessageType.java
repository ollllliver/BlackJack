package de.hsrm.mi.netze07.shared.messaging;

public enum MessageType {
    PLAY,
    GAME_START,
    PLAYER_CARD,
    DEALER_CARD,
    HIDDEN_DEALER_CARD,
    SHOW_DEALER_CARD,
    TAKE_CARD,
    END_TURN,
    GAME_END,
    INVALID_PROTOCOL, 
    PLAY_AGAIN
}
