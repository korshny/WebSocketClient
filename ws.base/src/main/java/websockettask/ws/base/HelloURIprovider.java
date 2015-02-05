package websockettask.ws.base;

public class HelloURIprovider implements URIprovider {

	public String getURI() {
		return "ws://localhost:9000/api/v1/ws";
	}
	
}
