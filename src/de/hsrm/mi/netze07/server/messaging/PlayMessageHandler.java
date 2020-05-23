package de.hsrm.mi.netze07.server.messaging;

import de.hsrm.mi.netze07.server.NetworkService;
import de.hsrm.mi.netze07.shared.game.Card;
import de.hsrm.mi.netze07.shared.game.GameStatus;
import de.hsrm.mi.netze07.shared.game.Table;
import de.hsrm.mi.netze07.shared.messaging.Message;

public class PlayMessageHandler implements IMessageHandler {

    @Override
    public void handleMessage(Message message, Table table, NetworkService service) {
      
        
	
        service.write(MessageGenerator.gameStart());
	
        table.setDeck();
        table.getDeck().shuffle();
       
        table.addPlayerCard(table.getDeck().drawCard());
        service.write(MessageGenerator.playerCard(table.getPlayerCard()));

        table.getDeck().shuffle();
        table.addDealerCard(table.getDeck().drawCard());
        service.write(MessageGenerator.dealerCard(table.getDealerCard()));
        
        table.getDeck().shuffle();
        table.addPlayerCard(table.getDeck().drawCard());
        service.write(MessageGenerator.playerCard(table.getPlayerCard()));
       
        if(table.getPlayerValue() == 21){
            service.write(MessageGenerator.gameEnd(GameStatus.WIN));
            table.setTerminated(true);
            
        }

        table.getDeck().shuffle();
        table.addDealerCard(table.getDeck().drawCard());
        service.write(MessageGenerator.hiddenDealerCard());

       
        

    }
    
}