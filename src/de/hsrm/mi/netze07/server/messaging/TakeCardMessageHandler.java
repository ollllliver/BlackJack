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
			//show hidden dealer card
			Card dealerCard = table.addDealerCard(table.getHiddenDealerCard());
			service.write(MessageGenerator.showDealerCard(dealerCard));
			//take dealer cards until minimum 17
			while(table.getDealerValue()<17) {
				dealerCard= table.addDealerCard();
				service.write(MessageGenerator.showDealerCard(dealerCard));
			}
			
			if(table.getDealerValue()==21) {
				service.write(MessageGenerator.gameEnd(GameStatus.DRAW));
	            table.setTerminated(true);
			}
			else {
				service.write(MessageGenerator.gameEnd(GameStatus.WIN));
	            table.setTerminated(true);
			}
		}
	}

}
