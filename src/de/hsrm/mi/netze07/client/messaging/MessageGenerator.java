package de.hsrm.mi.netze07.client.messaging;

import de.hsrm.mi.netze07.shared.messaging.Message;
import de.hsrm.mi.netze07.shared.messaging.MessageType;

import java.util.HashMap;

public class MessageGenerator {
    public static Message play(String playerName) {
        HashMap<String, String> body = new HashMap<>();
        body.put("playerName", playerName);
        return new Message(MessageType.PLAY, body);
    }

    public static Message takeCard() {
        return new Message(MessageType.TAKE_CARD, new HashMap<>());
    }

    public static Message endTurn() {
        return new Message(MessageType.END_TURN, new HashMap<>());
    }

    public static Message playAgain(String playerName) {
        HashMap<String, String> body = new HashMap<>();
        body.put("playerName", playerName);
        return new Message(MessageType.PLAY_AGAIN, body);
    }

	public static Message tableReady() {
		return new Message(MessageType.TABLE_READY, new HashMap<>());
	}
}
