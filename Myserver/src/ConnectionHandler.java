import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectionHandler extends Thread {
	 Socket _s;
	 BufferedReader _br;
	 DataOutputStream _os;
	 String _root;
     public ConnectionHandler (Socket s, String root) throws Exception{
    	 _s = s;
    	 _br = new BufferedReader(new InputStreamReader(s.getInputStream()));
    	 _os = new DataOutputStream(s.getOutputStream());
    	 _root = root;
     }
     
     @Override
     public void run(){
    	 try{
    	 String reqS = "";
    	 while(_br.ready() || reqS.length() == 0){
    		 reqS +=(char)_br.read();
    	 }
    	 System.out.println(reqS);
    	 
    	 HttpRequest req = new HttpRequest(reqS.toString());
    	 HttpResponse res = new HttpResponse(req, _root);
    	 _os.writeBytes(res._response);
    	 _os.close();
    	 _br.close();
    	 _s.close();
    	 }catch(Exception e){
    	  e.printStackTrace();
    	 }
     }
}
