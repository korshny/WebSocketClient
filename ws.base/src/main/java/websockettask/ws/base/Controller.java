package websockettask.ws.base;
/*
 * Singleton class, that provides sources for message and url.
 */
public class Controller implements Configuratable {
	private static Controller controller = new Controller();
    private MessageProvider messageProvider;
    private URIprovider uriProvider;
    
    public static Controller getController() {
    	return controller;
    }

    private Controller() {}
    
	public void setMessageProvider(MessageProvider provider) {
		this.messageProvider = provider;
	}

	public MessageProvider getMessageProvider() {
		return messageProvider;
	}

	public URIprovider getUriProvider() {
		return uriProvider;
	}

	public void setUriProvider(URIprovider uriProvider) {
		this.uriProvider = uriProvider;
	}

	public String getURI() {
		if (uriProvider == null) uriProvider = new DefaultURIprovider();
		return uriProvider.getURI();
	}

	public String getMessage() {
		  if (messageProvider == null) messageProvider = new DefaultMessageProvider();
	      return messageProvider.getMessage();
	}
 
}
