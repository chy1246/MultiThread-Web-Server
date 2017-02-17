import java.net.ServerSocket;
import java.net.Socket;

public class webServer {
	public static void main(String argv[]) throws Exception
	 {
	     int port =8000;
	     ServerSocket WebSocket = new ServerSocket(port);
	     
	     while (true) {
	      // Listen for a TCP connection request.
	      Socket connectionSocket = WebSocket.accept();
	      //Construct object to process HTTP request message
	      ConnectionHandler handler = new ConnectionHandler(connectionSocket);
	      
	      handler.start(); //start thread
	     }
	 }
}
