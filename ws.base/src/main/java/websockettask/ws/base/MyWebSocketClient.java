package websockettask.ws.base;
import java.net.URI;
import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;

public class MyWebSocketClient {

    public static void main(String[] args){
    	Controller controller = Controller.getController();
    	controller.setUriProvider(new HelloURIprovider());
        String destUri = controller.getURI();
        if (args.length > 0) {
            destUri = args[0];
        }
        WebSocketClient client = new WebSocketClient();
        MySocketConversation socket = new MySocketConversation();
        try {
            client.start();
            URI uri = new URI(destUri);
            ClientUpgradeRequest request = new ClientUpgradeRequest();
            client.connect(socket, uri, request);
            System.out.println("Connecting to " + uri);
            socket.awaitClose(7, TimeUnit.SECONDS);
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            try {
                client.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}