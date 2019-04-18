package quick.spring.boot.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import java.io.IOException;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;


/**
 * Topic模式消费者.
 */
@Component
public class TopicConsumer {

  /**
   * queue.
   */
  private static final String TOPIC_QUEUE1_NAME = "test.topic.queue1";
  private static final String TOPIC_QUEUE2_NAME = "test.topic.queue2";
  private static final String TOPIC_QUEUE3_NAME = "test.topic.queue3";

  /**
   * 初始化queue1.
   */
  @Bean
  public Queue topicQueue1() {
    return new Queue(TOPIC_QUEUE1_NAME);
  }

  /**
   * 初始化queue2.
   */
  @Bean
  public Queue topicQueue2() {
    return new Queue(TOPIC_QUEUE2_NAME);
  }

  /**
   * 初始化queue3.
   */
  @Bean
  public Queue topicQueue3() {
    return new Queue(TOPIC_QUEUE3_NAME);
  }

  /**
   * 监听器监听指定的Queue1.
   */
  @RabbitListener(queues = TOPIC_QUEUE1_NAME)
  public void receive1(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag)
      throws IOException {
    System.out.println("consumer receive a topic message 1: " + message);
    channel.basicAck(tag, false);
  }

  /**
   * 监听器监听指定的Queue2.
   */
  @RabbitListener(queues = TOPIC_QUEUE2_NAME)
  public void receive2(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag)
      throws IOException {
    System.out.println("consumer receive a topic message 2: " + message);
    channel.basicAck(tag, false);
  }

  /**
   * 监听器监听指定的Queue3.
   */
  @RabbitListener(queues = TOPIC_QUEUE3_NAME)
  public void receive3(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag)
      throws IOException {
    System.out.println("consumer receive a topic message 3: " + message);
    channel.basicAck(tag, false);
  }
}
