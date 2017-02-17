import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.util.StringTokenizer;

public class ConnectionHandler extends Thread {
	 Socket _socket;
	 BufferedReader _br;
	 DataOutputStream _os;
	 // Constructor
	 public ConnectionHandler(Socket socket) throws Exception
	 {
	  this._socket = socket;
	  _br = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
	  _os = new DataOutputStream(_socket.getOutputStream());
	 }
	 
	@Override
	public void run() {
		String[] temp = new String[2];
		try {
			temp = getIPFileName().split(" ");
		} catch (IOException e) {
			e.printStackTrace();
		}
		HttpRequest req = new HttpRequest(temp[0], temp[1]);
		HttpResponse response;
		try {
			response = new HttpResponse(req);
			_os.writeBytes(response.getStatus());
			_os.writeBytes(response.getContent());
			_os.writeBytes("\r\n");
			_os.writeBytes(response.getResponse());
			_os.close(); //Close streams and socket.
		    _br.close();
			_socket.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	public String getIPFileName() throws IOException{
		String requestLine = _br.readLine();
		InetAddress incomingAddress = _socket.getInetAddress();
		String ipString= incomingAddress.getHostAddress();
		System.out.println("The incoming address is:   " + ipString);
		StringTokenizer tokens = new StringTokenizer(requestLine);
	    tokens.nextToken();  // skip over the method, which should be “GET”
	    String fileName = tokens.nextToken();
	    String headerLine = null;
	    while ((headerLine = _br.readLine()).length() != 0) { //While the header still has text, print it
	     System.out.println(headerLine);
	    }
	    return ipString +" " + fileName;
	}
}
