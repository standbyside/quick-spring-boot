package quick.spring.boot.rabbitmq.consumer;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * FanoutExchange（广播）模式消费者.
 */
@Component
public class FanoutExchangeConsumer {

  /**
   * exchange.
   */
  private static final String FANOUT_EXCHANGE_NAME = "spring.rabbit.fanout";

  /**
   * queue.
   */
  private static final String FANOUT_QUEUE1_NAME = "test.fanout.queue1";
  private static final String FANOUT_QUEUE2_NAME = "test.fanout.queue2";
  private static final String FANOUT_QUEUE3_NAME = "test.fanout.queue3";

  /**
   * 初始化exchange.
   */
  @Bean
  FanoutExchange fanoutExchange() {
    return new FanoutExchange(FANOUT_EXCHANGE_NAME);
  }

  /**
   * 初始化queue1.
   */
  @Bean(name = "fanoutQueue1")
  public Queue fanoutQueue1() {
    return new Queue(FANOUT_QUEUE1_NAME);
  }

  /**
   * 初始化queue2.
   */
  @Bean(name = "fanoutQueue2")
  public Queue fanoutQueue2() {
    return new Queue(FANOUT_QUEUE2_NAME);
  }

  /**
   * 初始化queue3.
   */
  @Bean(name = "fanoutQueue3")
  public Queue fanoutQueue3() {
    return new Queue(FANOUT_QUEUE3_NAME);
  }

  @Bean
  Binding bindingFanoutExchange1(@Qualifier("fanoutQueue1") Queue queue, FanoutExchange exchange) {
    return BindingBuilder.bind(queue).to(exchange);
  }

  @Bean
  Binding bindingFanoutExchange2(@Qualifier("fanoutQueue2") Queue queue, FanoutExchange exchange) {
    return BindingBuilder.bind(queue).to(exchange);
  }

  @Bean
  Binding bindingFanoutExchange3(@Qualifier("fanoutQueue3") Queue queue, FanoutExchange exchange) {
    return BindingBuilder.bind(queue).to(exchange);
  }

  @RabbitListener(queues = FANOUT_QUEUE1_NAME)
  public void receive1(String message) {
    System.out.println("consumer receive a fanout message 1: " + message);
  }

  @RabbitListener(queues = FANOUT_QUEUE2_NAME)
  public void receive2(String message) {
    System.out.println("consumer receive a fanout message 2: " + message);
  }

  @RabbitListener(queues = FANOUT_QUEUE3_NAME)
  public void receive3(String message) {
    System.out.println("consumer receive a fanout message 3: " + message);
  }
}
