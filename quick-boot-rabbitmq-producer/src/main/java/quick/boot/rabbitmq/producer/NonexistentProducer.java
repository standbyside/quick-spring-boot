package quick.boot.rabbitmq.producer;

import java.time.LocalDateTime;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class NonexistentProducer {

    /**
     * exchange.
     */
    private static final String NONEXISTENT_EXCHANGE_NAME = "spring.rabbit.nonexistent";
    private static final String EXISTENT_EXCHANGE_NAME = "spring.rabbit.existent";
    /**
     * queue.
     */
    private static final String NONEXISTENT_QUEUE_NAME = "test.nonexistent.queue";
    private static final String EXISTENT_QUEUE_NAME = "test.existent.queue";

    @Autowired
    private AmqpTemplate template;

    /**
     * 交换器和队列都不存在，会打印 Error 日志提示交换器不存在.
     */
    public void send1() {
        String message = LocalDateTime.now().toString();
        System.out.println("producer send a nonexistent message 1: " + message);
        template.convertAndSend(NONEXISTENT_EXCHANGE_NAME, NONEXISTENT_QUEUE_NAME, message);
    }

    /**
     * 交换器存在，队列不存在，没有任何提示.
     */
    public void send2() {
        String message = LocalDateTime.now().toString();
        System.out.println("producer send a nonexistent message 2: " + message);
        template.convertAndSend(EXISTENT_EXCHANGE_NAME, NONEXISTENT_QUEUE_NAME, message);
    }

    /**
     * 交换器存在，队列不存在，会打印 Error 日志提示交换器不存在.
     */
    public void send3() {
        String message = LocalDateTime.now().toString();
        System.out.println("producer send a nonexistent message 3: " + message);
        template.convertAndSend(NONEXISTENT_EXCHANGE_NAME, EXISTENT_QUEUE_NAME, message);
    }
}
