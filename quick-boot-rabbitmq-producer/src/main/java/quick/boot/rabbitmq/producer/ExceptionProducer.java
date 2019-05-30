package quick.boot.rabbitmq.producer;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


/**
 * 异常测试模式生产者.
 */
@Component
public class ExceptionProducer {

  /**
   * exchange.
   */
  private static final String EXCEPTION_EXCHANGE_NAME = "spring.rabbit.exception";

  /**
   * queue.
   */
  private static final String EXCEPTION_QUEUE_NAME = "test.exception.queue";


  @Autowired
  private AmqpTemplate template;

  public void send() {
    String message = LocalDateTime.now().toString();
    System.out.println("producer send a exception message: " + message);
    template.convertAndSend(EXCEPTION_EXCHANGE_NAME, EXCEPTION_QUEUE_NAME, message);
  }
}
