package amqp;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class StringMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        System.out.println("Listener Receive :"+ message.toString() );
    }
}