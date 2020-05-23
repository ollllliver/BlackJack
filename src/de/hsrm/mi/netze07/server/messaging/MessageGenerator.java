package de.hsrm.mi.netze07.server.messaging;

import java.util.HashMap;

import de.hsrm.mi.netze07.shared.game.Card;
import de.hsrm.mi.netze07.shared.game.GameStatus;
import de.hsrm.mi.netze07.shared.messaging.Message;
import de.hsrm.mi.netze07.shared.messaging.MessageType;

public class MessageGenerator {
	public static Message gameStart() {
		HashMap<String, String> body = new HashMap<>();
        body.put("playerName", "Dieter");
        return new Message(MessageType.GAME_START, body);
	}
	
	public static Message playerCard(Card card) {
		HashMap<String, String> body = new HashMap<>();
        body.put("t", card.getType().toString());
        body.put("v", card.getValue().toString());
        return new Message(MessageType.PLAYER_CARD, body);
	}
	
	public static Message dealerCard(Card card) {
		HashMap<String, String> body = new HashMap<>();
        body.put("t", card.getType().toString());
        body.put("v", card.getValue().toString());
        return new Message(MessageType.DEALER_CARD, body);
	}
	
	public static Message hiddenDealerCard() {
        return new Message(MessageType.HIDDEN_DEALER_CARD,  new HashMap<>() );
	}
	
	public static Message showDealerCard(Card card) {
		HashMap<String, String> body = new HashMap<>();
        body.put("t", card.getType().toString());
        body.put("v", card.getValue().toString());
        return new Message(MessageType.SHOW_DEALER_CARD, body);
	}
	
	public static Message gameEnd(GameStatus status) {
		HashMap<String, String> body = new HashMap<>();
        body.put("s", Integer.toString(status.getValue()));
        return new Message(MessageType.GAME_END, body);
	}

	public static Message invalidProtocol() {
        return new Message(MessageType.INVALID_PROTOCOL, new HashMap<>());
	
    }
}
