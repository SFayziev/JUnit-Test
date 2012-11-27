import com.rabbitmq.client.*;
import com.rabbitmq.client.MessageProperties;
import org.junit.Assert;

import org.junit.Test;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import java.io.IOException;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 11/24/12
 * Time: 2:36 PM
 * To change this template use File | Settings | File Templates.
 */

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations =  "classpath*:spring-config.xml")
public class RabbitMQClientTest {

//    @Autowired private AmqpAdmin admin;
//    @Autowired private AmqpTemplate template;

//    @Ignore
    @Test
    public void simpleProducerConsumerTest() throws IOException {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        Connection connection = null;
     connection= connectionFactory.newConnection();


        Queue queue= new Queue("test.queue");
        Channel channel=null;

            channel= connection.createChannel();

        channel.basicPublish("test.exchange", "test.test", MessageProperties.TEXT_PLAIN, "for text".getBytes());



//        QueueingConsumer  consumer=new  QueueingConsumer(channel);
//        channel.basicPublish("test.exchange", "test.route",
//                MessageProperties.PERSISTENT_TEXT_PLAIN,
//                "testing".getBytes()) ;


        System.out.println("consumer resive : " + "'");



   }




    @Test
   public void  RabbitBySpring() throws Exception{

        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        RabbitTemplate template= new RabbitTemplate(connectionFactory);


        String sent = "Catch the rabbit! " + new Date();


        template.convertAndSend("test2.queue",  sent );
        String received = (String)template.receiveAndConvert("test2.queue");
        System.out.println( "Msg: " + received );
        Assert.assertEquals(sent, received);

   }
}