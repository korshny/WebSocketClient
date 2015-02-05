package websockettask.ws.base;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.StatusCode;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;



@WebSocket
public class MySocketConversation {

    private final CountDownLatch closeLatch;

    private Session session;

    public MySocketConversation() {
        this.closeLatch = new CountDownLatch(1);
    }

    public boolean awaitClose(int duration, TimeUnit unit) throws InterruptedException {
        return this.closeLatch.await(duration, unit);
    }
    

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
        System.out.println("Connection closed: statusCode-" + statusCode + " reason-" + reason);
        this.session = null;
        this.closeLatch.countDown();
    }

    @OnWebSocketConnect
    public void onConnect(Session session) {
        System.out.println("Connected: " + session);
        this.session = session;
        Controller controller = Controller.getController();
        controller.setMessageProvider(new HelloMessageProvider()); 
        try {
            Future<Void> fut;
            fut = session.getRemote().sendStringByFuture(controller.getMessage());
            fut.get(2, TimeUnit.SECONDS);
            session.close();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    @OnWebSocketMessage
    public void onMessage(String msg) {
        System.out.println("Answer: " + msg);
    }
}
