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
	
        
       
        
        service.write(MessageGenerator.playerCard(table.addPlayerCard()));

       
        
        service.write(MessageGenerator.dealerCard(table.addDealerCard()));
        
       
        
        service.write(MessageGenerator.playerCard(table.addPlayerCard()));
       
        table.addHiddenDealerCard();
        service.write(MessageGenerator.hiddenDealerCard());

        if(table.getPlayerValue() == 21){
            if(table.getDealerValue() == 21){
                service.write(MessageGenerator.showDealerCard(table.getHiddenDealerCard()));
                service.write(MessageGenerator.gameEnd(GameStatus.DRAW));
                
            }
            else{
                service.write(MessageGenerator.gameEnd(GameStatus.WIN));
               
            }
           
            
        }
       
        

    }
    
}