package quick.boot.rabbitmq.producer;

import java.time.LocalDateTime;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


/**
 * Direct模式生产者.
 */
@Component
public class DirectProducer {

  /**
   * queue.
   */
  private static final String DIRECT_QUEUE_NAME = "test.direct.queue";

  @Autowired
  private AmqpTemplate template;

  public void send() {
    String message = LocalDateTime.now().toString();
    System.out.println("producer send a direct message: " + message);
    template.convertAndSend(DIRECT_QUEUE_NAME, message);
  }
}
