package amqp;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExampleAmqpConfiguration {

    @Bean
    public SimpleMessageListenerContainer messageListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(rabbitConnectionFactory());
        container.setQueueNames("test.queue", "test2.queue" );
        container.setMessageListener(exampleListener());
        return container;
    }

    @Bean
    public ConnectionFactory rabbitConnectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        return connectionFactory;
    }

    @Bean
    public StringMessageListener exampleListener() {
        return new StringMessageListener();

    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(rabbitConnectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(rabbitConnectionFactory());
    }

    @Bean
    public Queue myQueueTest() {
        return new Queue("test.queue");
    }


    @Bean
    public Queue myQueueTest2() {
        return new Queue("test2.queue");
    }


    @Bean
    public DirectExchange myExchange()
    {
        return new DirectExchange( "test.exchange" );
    }

    @Bean
    public Binding ServiceBinding()
    {
        return BindingBuilder.bind( myQueueTest()).to( myExchange() ).with("test.*") ;
    }

    @Bean
    public Binding Service2Binding()
    {
        return BindingBuilder.bind( myQueueTest2()).to( myExchange() ).with("test.*" );
    }


}