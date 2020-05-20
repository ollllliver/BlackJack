package de.hsrm.mi.netze07.shared.messaging;

public interface IMessageHandler {
    void handleMessage(Message message /*table, service*/);
}
