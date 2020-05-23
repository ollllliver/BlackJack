package de.hsrm.mi.netze07.server.messaging;

import de.hsrm.mi.netze07.server.NetworkService;
import de.hsrm.mi.netze07.shared.game.Card;
import de.hsrm.mi.netze07.shared.game.GameStatus;
import de.hsrm.mi.netze07.shared.game.Table;
import de.hsrm.mi.netze07.shared.messaging.Message;

public class TakeCardMessageHandler implements IMessageHandler{

	@Override
	public void handleMessage(Message message, Table table, NetworkService service) {
		//Take card for player
		Card playerCard = table.addPlayerCard();
        service.write(MessageGenerator.playerCard(playerCard));
		if(table.getPlayerValue()>21) {
            service.write(MessageGenerator.gameEnd(GameStatus.LOOSE));
            table.setTerminated(true);
		}
		else if(table.getPlayerValue()==21) {
			IMessageHandler endTurnHandler = new EndTurnMessageHandler();
			endTurnHandler.handleMessage(message, table, service);
		}
	}

}
