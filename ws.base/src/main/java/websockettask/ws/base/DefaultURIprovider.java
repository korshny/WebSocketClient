package websockettask.ws.base;
/*
 * If controller havn't explicit uri provider,
 *  controller use default DefaultURIprovider.
 */
public class DefaultURIprovider implements URIprovider {

	public String getURI() {
		return "No available URI!";

	}
}
