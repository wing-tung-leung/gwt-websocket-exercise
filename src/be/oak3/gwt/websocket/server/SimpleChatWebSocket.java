package be.oak3.gwt.websocket.server;

import java.io.IOException;
import java.util.Date;

import org.eclipse.jetty.websocket.WebSocket;

public final class SimpleChatWebSocket implements WebSocket.OnTextMessage {
	
	private Connection connection;
	private SimpleChatRoom room;
	private String browser = "[unknown]";
	
	public SimpleChatWebSocket(SimpleChatRoom room, String browser) {
		this.room = room;
		this.browser = browser;
	}

	@Override
	public void onClose(int arg0, String arg1) {
		System.out.println("closing websocket " + arg0 + ", " + arg1);
		room.unregister(connection);
		connection = null;
	}

	@Override
	public void onMessage(String msg) {
		try {
			connection.sendMessage(new Date().toString() + ": REPLY: " + msg);
			room.shout("from " + browser + ": " + msg.toUpperCase() + " !!!!");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void onOpen(Connection con) {
		System.out.println("open websocket ; " + con.getProtocol());
		this.connection = con;
		room.register(connection);
	}
}