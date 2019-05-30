package quick.boot.rabbitmq.producer;

import java.time.LocalDateTime;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Topic模式生产者.
 */
@Component
public class TopicProducer {

  /**
   * exchange.
   */
  private static final String TOPIC_EXCHANGE_NAME = "spring.rabbit.topic";

  /**
   * queue.
   */
  private static final String TOPIC_QUEUE1_NAME = "test.topic.queue1";
  private static final String TOPIC_QUEUE2_NAME = "test.topic.queue2";


  @Autowired
  private AmqpTemplate template;

  public void send1() {
    String message = LocalDateTime.now().toString() + " 1";
    System.out.println("producer send a topic message 1: " + message);
    template.convertAndSend(TOPIC_EXCHANGE_NAME, TOPIC_QUEUE1_NAME, message);
  }

  public void send2() {
    String message = LocalDateTime.now().toString() + " 2";
    System.out.println("producer send a topic message 2: " + message);
    template.convertAndSend(TOPIC_EXCHANGE_NAME, TOPIC_QUEUE2_NAME, message);
  }
}
