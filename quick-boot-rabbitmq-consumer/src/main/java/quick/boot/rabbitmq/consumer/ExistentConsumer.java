package quick.boot.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import java.io.IOException;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class ExistentConsumer {

    /**
     * exchange.
     */
    private static final String EXISTENT_EXCHANGE_NAME = "spring.rabbit.existent";
    /**
     * queue.
     */
    private static final String EXISTENT_QUEUE_NAME = "test.existent.queue";

    /**
     * 初始化exchange.
     */
    @Bean
    public TopicExchange existentExchange() {
        return new TopicExchange(EXISTENT_EXCHANGE_NAME);
    }

    /**
     * 初始化queue.
     */
    @Bean
    public Queue existentQueue() {
        return new Queue(EXISTENT_QUEUE_NAME);
    }

    /**
     * 绑定.
     */
    @Bean
    Binding bindingExistentMessage(@Qualifier("existentQueue") Queue queue,
                                   @Qualifier("existentExchange") TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(EXISTENT_QUEUE_NAME);
    }


    /**
     * 监听器监听指定的Queue.
     */
    @RabbitListener(queues = EXISTENT_QUEUE_NAME)
    public void receive(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag)
            throws IOException {
        System.out.println("consumer receive a existent message: " + message);
        channel.basicAck(tag, false);
    }
}
