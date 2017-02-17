import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class HttpResponse {
  HttpRequest _req;
  String _response;
  String _root;
  StringBuilder sb = new StringBuilder();
  public HttpResponse(HttpRequest req, String root){
	  _req = req;
	  _root = root;
	  if(!req._requestMethod.equals("GET")){ //check the request method 400
		  _response = "HTTP/1.0 400 \r\n";
		  _response += "Server: MyServer/1.0 \r\n";
		  _response += "Content-Type:"+ contentType(req._fileName) + " \r\n";
		  _response += "\r\n";
		  _response += "<HTML> <HEAD><TITLE>Not Found</TITLE></HEAD> <BODY>You Use The Invalid Method On CHY's Multithreaded WebServer</BODY></HTML>";
	  }else{
	  File file = new File(_root + req._fileName);
	  if(!file.canRead()){ // check read authority 403
		  _response = "HTTP/1.0 403 \r\n";
		  _response += "Server: MyServer/1.0 \r\n";
		  _response += "Content-Type:"+ contentType(req._fileName) + " \r\n";
		  _response += "\r\n";
		  _response += "<HTML> <HEAD><TITLE>Not Found</TITLE></HEAD> <BODY>You Can Not Get The File On CHY's Multithreaded WebServer</BODY></HTML>";
	  }else{
	  try{
		  _response = "HTTP/1.0 200 \r\n";
		  _response += "Server: MyServer/1.0 \r\n";
		  _response += "Content-Type:"+ contentType(req._fileName) + " \r\n";
		  _response += "Content-Length: " + file.length() + " \r\n";
		  _response += "\r\n";
		  FileInputStream fis = new FileInputStream(file);
		  int end = 0;
		  System.out.println(_response);
		  while(end != -1){
			  end = fis.read();
			  _response += (char)end;
		  }
		  fis.close();
	  }catch(FileNotFoundException e){ //The file can not be found in this server 404
		  //_response.replace("200","404");
		  _response = "HTTP/1.0 404 \r\n";
		  _response += "Server: MyServer/1.0 \r\n";
		  _response += "Content-Type: text/html \r\n";
		  _response += "\r\n";
		  _response += "<HTML> <HEAD><TITLE>Not Found</TITLE></HEAD> <BODY>Not Found on CHY's Multithreaded WebServer</BODY></HTML>";
		  System.out.println(_response);
	  }catch(Exception e){
		  _response.replace("200", "500");
	  }
	  }
	  }
  }
  public String contentType(String fileName){
	  if(fileName.endsWith(".htm") || fileName.endsWith(".html"))
		  return "text/html";
		 if(fileName.endsWith(".jpg"))
		  return "image/jpeg";
		 if(fileName.endsWith(".gif"))
		  return "image/gif";
		 if(fileName.endsWith(".css"))
	      return "text/css";
		 if(fileName.endsWith(".js"))
		  return "application/javascript";
		 return "application/octet-stream";
  }
}
