package de.hsrm.mi.netze07.server

import de.hsrm.mi.netze07.server.messaging.EndTurnMessageHandler
import de.hsrm.mi.netze07.server.messaging.PlayAgainMessageHandler
import de.hsrm.mi.netze07.server.messaging.PlayMessageHandler
import de.hsrm.mi.netze07.server.messaging.TableReadyMessageHandler
import de.hsrm.mi.netze07.server.messaging.TakeCardMessageHandler
import de.hsrm.mi.netze07.shared.messaging.MessageType

class Main {
	public static final int PORT = 29000
	public static final String HOST = "127.0.0.1"

	def static void main(String[] args) {
		var Server server = new Server(PORT)
		server.registerHandler(MessageType.PLAY, new PlayMessageHandler())
		server.registerHandler(MessageType.TABLE_READY, new TableReadyMessageHandler())
		server.registerHandler(MessageType.TAKE_CARD, new TakeCardMessageHandler())
		server.registerHandler(MessageType.END_TURN, new EndTurnMessageHandler())
		server.registerHandler(MessageType.PLAY_AGAIN, new PlayAgainMessageHandler())
		server.start()
	}
}
