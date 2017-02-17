import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HttpResponse {
	  HttpRequest _req;
	  String _response;
	  String _root = "/Users/chy/Desktop/root";
	  final static String CRLF = "\r\n";//For convenience
	  String _statusLine; //Set initial values to null
	  String _contentTypeLine;
	  public HttpResponse(HttpRequest req) throws IOException{
		  _req = req;
		   // Open the requested file.
		   FileInputStream fis = null;
		   boolean fileExists = true;
		    try{
		    fis = new FileInputStream(_root + _req._fileName);
		    }catch (FileNotFoundException e){
		    	fileExists = false;
		    }
		    if (fileExists) {
		   _statusLine = "HTTP/1.1 200 OK: ";
		   _contentTypeLine = "Content-Type: " +
		    contentType(_req._fileName) + CRLF;
		   int end = 0;
		   while(end != -1){
			   end = fis.read();
			   _response += (char)end;
		   		}
		   System.out.println(_response);
		   } else {
		   _statusLine = "HTTP/1.1 404 Not Found: ";
		   _contentTypeLine = "Content-Type: text/html" + CRLF;
		   _response = "<HTML> <HEAD><TITLE>Not Found</TITLE></HEAD> <BODY>Not Found on CHY's Multithreaded WebServer</BODY></HTML>";
		   }
	  }
	  public String contentType(String fileName){
		  if(fileName.endsWith(".htm") || fileName.endsWith(".html"))
			  return "text/html";
			 if(fileName.endsWith(".jpg"))
			  return "text/jpg";
			 if(fileName.endsWith(".gif"))
			  return "text/gif";
			 return "application/octet-stream";
	  }
	  public String getStatus(){
		  return _statusLine;
	  }
	  public String getContent(){
		  return _contentTypeLine;
	  }
	  public String getResponse(){
		  return _response;
	  }
}
