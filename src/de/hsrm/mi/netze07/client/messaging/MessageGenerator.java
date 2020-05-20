package de.hsrm.mi.netze07.client.messaging;

import de.hsrm.mi.netze07.shared.messaging.Message;
import de.hsrm.mi.netze07.shared.messaging.MessageType;

import java.util.HashMap;

public class MessageGenerator {
    public static Message play() {
        return new Message(MessageType.PLAY, new HashMap<>());
    }

    public static Message takeCard() {
        return new Message(MessageType.TAKE_CARD, new HashMap<>());
    }

    public static Message endTurn() {
        return new Message(MessageType.END_TURN, new HashMap<>());
    }
}
