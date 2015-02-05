package websockettask.ws.base;
/*
 * If controller havn't explicit message provider,
 *  controller use default DefaultMessageProvider.
 */
public class DefaultMessageProvider implements MessageProvider {

	public String getMessage() {
        return "No available message!";

	}


}
