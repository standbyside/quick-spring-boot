package quick.boot.rabbitmq.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 延迟队列生产者.
 */
@Component
public class DelayProducer {

    /**
     * exchange.
     */
    private static final String DELAY_EXCHANGE_NAME = "spring.rabbit.delay";

    /**
     * queue.
     */
    private static final String DELAY_QUEUE_NAME = "test.delay.queue";


    @Autowired
    private AmqpTemplate template;

    public void send() {
        String message = LocalDateTime.now().toString();
        System.out.println("producer send a delay message : " + message);
        // 给延迟队列发送消息
        template.convertAndSend(DELAY_EXCHANGE_NAME, DELAY_QUEUE_NAME, message, messagePostProcessor -> {
            // 给消息设置延迟毫秒值
            messagePostProcessor.getMessageProperties().setExpiration(String.valueOf(10 * 1000));
            return messagePostProcessor;
        });
    }
}
