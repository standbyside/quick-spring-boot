package quick.boot.rabbitmq.consumer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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


/**
 * 异常测试消费者.
 */
@Component
public class ExceptionConsumer {

  /**
   * exchange.
   */
  private static final String EXCEPTION_EXCHANGE_NAME = "spring.rabbit.exception";
  private static final String EXCEPTION_EXCHANGE_NAME_DL = "spring.rabbit.exception.dl";

  /**
   * queue.
   */
  private static final String EXCEPTION_QUEUE_NAME = "test.exception.queue";
  private static final String EXCEPTION_QUEUE_NAME_DL = "test.exception.queue.dl";

  /**
   * 初始化exchange.
   */
  @Bean
  public TopicExchange exceptionExchange() {
    return new TopicExchange(EXCEPTION_EXCHANGE_NAME);
  }

  /**
   * 初始化queue.
   */
  @Bean
  public Queue exceptionQueue() {
    Map<String, Object> args = new HashMap<>(16);
    args.put("x-dead-letter-exchange", EXCEPTION_EXCHANGE_NAME_DL);
    args.put("x-dead-letter-routing-key", EXCEPTION_QUEUE_NAME_DL);
    return new Queue(EXCEPTION_QUEUE_NAME, true, false, false, args);
  }

  /**
   * 绑定.
   */
  @Bean
  Binding bindingExceptionMessage(@Qualifier("exceptionQueue") Queue queue,
                                  @Qualifier("exceptionExchange") TopicExchange exchange) {
    return BindingBuilder.bind(queue).to(exchange).with(EXCEPTION_QUEUE_NAME);
  }

  /**
   * 初始化exchangeDL.
   */
  @Bean
  public TopicExchange exceptionExchangeDL() {
    return new TopicExchange(EXCEPTION_EXCHANGE_NAME_DL);
  }

  /**
   * 初始化queueDL.
   */
  @Bean
  public Queue exceptionQueueDL() {
    return new Queue(EXCEPTION_QUEUE_NAME_DL);
  }

  /**
   * 绑定DL.
   */
  @Bean
  Binding bindingExceptionMessageDL(@Qualifier("exceptionQueueDL") Queue queue,
                                    @Qualifier("exceptionExchangeDL") TopicExchange exchange) {
    return BindingBuilder.bind(queue).to(exchange).with(EXCEPTION_QUEUE_NAME_DL);
  }

  /**
   * 计数器.
   */
  private static Integer count = 0;

  /**
   * 监听器监听指定的Queue.
   */
  @RabbitListener(queues = EXCEPTION_QUEUE_NAME)
  public void receive(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag)
      throws Exception {
    System.out.println("consumer receive a exception message : " + message);
    channel.basicNack(tag, false, false);
    throw new Exception("count = " + (count++));
  }

  /**
   * 监听器监听指定的DLQueue.
   */
  @RabbitListener(queues = EXCEPTION_QUEUE_NAME_DL)
  public void receiveDL(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag)
      throws IOException {
    System.out.println("consumer receive a exception message dl: " + message);
    channel.basicAck(tag, false);
  }
}
