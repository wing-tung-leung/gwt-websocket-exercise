package be.oak3.gwt.websocket.server;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketHandler;

public class JettyWebSocketChatServer extends WebSocketHandler {
	
	private SimpleChatRoom room = new SimpleChatRoom();

	@Override
	public WebSocket doWebSocketConnect(HttpServletRequest request, String arg1) {
		System.out.println("doWebSocketConnect(): " + arg1);
		SimpleChatWebSocket ws = new SimpleChatWebSocket(room, request.getHeader("User-Agent"));
		return ws;
	}
	
	public static void main(String[] args) {
        try {
            Server server = new Server(8081);
            JettyWebSocketChatServer chatWebSocketHandler = new JettyWebSocketChatServer();
            chatWebSocketHandler.setHandler(new DefaultHandler());
            server.setHandler(chatWebSocketHandler);
            server.start();
            server.join();
            
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}
