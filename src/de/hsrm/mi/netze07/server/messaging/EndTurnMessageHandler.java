package de.hsrm.mi.netze07.server.messaging;

import de.hsrm.mi.netze07.server.NetworkService;
import de.hsrm.mi.netze07.shared.game.Card;
import de.hsrm.mi.netze07.shared.game.GameStatus;
import de.hsrm.mi.netze07.shared.game.Table;
import de.hsrm.mi.netze07.shared.messaging.Message;

public class EndTurnMessageHandler implements IMessageHandler {

    @Override
    public void handleMessage(Message message, Table table, NetworkService service) {
        
        

        service.write(MessageGenerator.showDealerCard(table.getHiddenDealerCard()));
        while(table.getDealerValue() < 17){
           service.write(MessageGenerator.dealerCard(table.addDealerCard()));
        }

        if(table.getDealerValue() < table.getPlayerValue() || table.getDealerValue() > 21){
            service.write(MessageGenerator.gameEnd(GameStatus.WIN));
        }
        else if(table.getDealerValue() == 21 || table.getDealerValue() > table.getPlayerValue()){
            service.write(MessageGenerator.gameEnd(GameStatus.LOOSE));
        }else{
            service.write(MessageGenerator.gameEnd(GameStatus.DRAW));
        }
        table.setTerminated(true);

    }
    
}