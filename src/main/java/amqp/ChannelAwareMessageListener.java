package amqp;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.Message;

public interface ChannelAwareMessageListener {
    void onMessage(Message message, AMQP.Channel channel) throws Exception;
}