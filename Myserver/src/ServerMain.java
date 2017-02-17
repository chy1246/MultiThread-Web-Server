import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
   ServerSocket _serverSocket;
   public static void main(String[] args) throws Exception{
	   String root = args[1];
	   int port = Integer.valueOf(args[3]);
	   if(port < 8000 || port > 9999){
		   throw new IllegalArgumentException("Wrong Port Number");
	   }
	   new ServerMain().run(port, root);
   }
   
   public void run(int port, String root) throws Exception{
	   System.out.println("Server is running");
	   _serverSocket = new ServerSocket(port);
	   acceptRequests(root);
   }
   
   private void acceptRequests(String root) throws Exception{
	   while(true){
		   Socket s = _serverSocket.accept();
		   ConnectionHandler handler = new ConnectionHandler(s, root);
		   handler.start();
	   }
   }
}
