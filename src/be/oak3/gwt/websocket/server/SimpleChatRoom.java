package be.oak3.gwt.websocket.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.websocket.WebSocket.Connection;

public class SimpleChatRoom {
	
	private List<Connection> connections = new ArrayList<>();
	
	public void register(Connection c) {
		connections.add(c);
		System.out.println("Registered total of connections: " + connections.size());
	}
	
	public void unregister(Connection c) {
		connections.remove(c);
		System.out.println("Dropped connection, left = " + connections.size());
	}
	
	public void shout(String msg) {
		for (Connection c: connections) {
			try {
				c.sendMessage(msg);
			} catch (IOException e) {
				System.err.println("Failed sending message due to " + e.getMessage());
			}
		}
	}

}
