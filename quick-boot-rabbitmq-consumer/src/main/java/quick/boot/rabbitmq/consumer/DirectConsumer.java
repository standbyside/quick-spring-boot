package quick.boot.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import java.io.IOException;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;


/**
 * Direct模式消费者.
 */
@Component
public class DirectConsumer {

  /**
   * queue.
   */
  private static final String DIRECT_QUEUE_NAME = "test.direct.queue";

  /**
   * 初始化queue.
   */
  @Bean
  public Queue directQueue() {
    return new Queue(DIRECT_QUEUE_NAME);
  }

  /**
   * 监听器监听指定的Queue.
   */
  @RabbitListener(queues = DIRECT_QUEUE_NAME)
  public void receive1(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag)
      throws IOException {
    System.out.println("consumer receive a direct message 1: " + message);
    channel.basicAck(tag, false);
  }

  /**
   * 监听器监听指定的Queue.
   */
  @RabbitListener(queues = DIRECT_QUEUE_NAME)
  public void receive2(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag)
      throws IOException {
    System.out.println("consumer receive a direct message 2: " + message);
    channel.basicAck(tag, false);
  }
}
