
public class HttpRequest {
  String _fileName;
  String _requestMethod;
  int _port;
  @SuppressWarnings("deprecation")
public HttpRequest(String req){
	  String[] bfRequest = req.split("\n");
	  _requestMethod =bfRequest[0].split(" ")[0];
	  _fileName = java.net.URLDecoder.decode(bfRequest[0].split(" ")[1]);
	  if(_fileName.equals("/")){
		  _fileName = "/Santa Clara University.htm";
	  }
	  System.out.println(_fileName);
  }
}
