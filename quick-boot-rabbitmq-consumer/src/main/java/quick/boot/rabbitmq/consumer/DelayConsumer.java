package quick.boot.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 延迟队列消费者.
 */
@Component
public class DelayConsumer {


    /**
     * exchange.
     */
    private static final String DELAY_EXCHANGE_NAME = "spring.rabbit.delay";
    private static final String DELAY_EXCHANGE_NAME_DL = "spring.rabbit.delay.dl";

    /**
     * queue.
     */
    private static final String DELAY_QUEUE_NAME = "test.delay.queue";
    private static final String DELAY_QUEUE_NAME_DL = "test.delay.queue.dl";

    /**
     * 初始化exchange.
     */
    @Bean(name = "delayExchange")
    public TopicExchange delayExchange() {
        return new TopicExchange(DELAY_EXCHANGE_NAME);
    }

    /**
     * 初始化queue.
     */
    @Bean
    public Queue delayQueue() {
        Map<String, Object> params = new HashMap<>();
        // x-dead-letter-exchange 声明了队列里的死信转发到的DLX名称，
        params.put("x-dead-letter-exchange", DELAY_EXCHANGE_NAME_DL);
        // x-dead-letter-routing-key 声明了这些死信在转发时携带的 routing-key 名称。
        params.put("x-dead-letter-routing-key", DELAY_QUEUE_NAME_DL);
        // 设置消息超时时间，也可以在发送消息时设置单条消息的超时时间，二者兼容，默认是时间小的优先
        params.put("x-message-ttl", 15 * 1000);
        return new Queue(DELAY_QUEUE_NAME, true, false, false, params);
    }

    /**
     * 绑定.
     */
    @Bean
    Binding bindingDelayMessage(@Qualifier("delayQueue") Queue queue,
                                @Qualifier("delayExchange") TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(DELAY_QUEUE_NAME);
    }

    /**
     * 初始化exchangeDL.
     */
    @Bean(name = "delayExchangeDL")
    public TopicExchange delayExchangeDL() {
        return new TopicExchange(DELAY_EXCHANGE_NAME_DL);
    }

    /**
     * 初始化queueDL.
     */
    @Bean(name = "delayQueueDL")
    public Queue delayQueueDL() {
        return new Queue(DELAY_QUEUE_NAME_DL);
    }

    /**
     * 绑定DL.
     */
    @Bean
    Binding bindingDelayMessageDL(@Qualifier("delayQueueDL") Queue queue,
                                  @Qualifier("delayExchangeDL") TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(DELAY_QUEUE_NAME_DL);
    }

    /**
     * 只设置 DL Queue 的监听，不设置 Queue 的监听，发送的消息找不到消费者，超时就会发送到 DL Queue.
     */
    @RabbitListener(queues = DELAY_QUEUE_NAME_DL)
    public void receiveDL(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag)
            throws IOException {
        System.out.println("consumer receive a delay message dl: " + message);
        channel.basicAck(tag, false);
    }
}
