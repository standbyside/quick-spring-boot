package quick.spring.boot.rabbitmq.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * FanoutExchange（广播）模式生产者.
 */
@Component
public class FanoutExchangeProducer {

  /**
   * exchange.
   */
  private static final String FANOUT_EXCHANGE_NAME = "spring.rabbit.fanout";

  /**
   * 初始化exchange.
   */
  @Bean
  FanoutExchange fanoutExchange() {
    return new FanoutExchange(FANOUT_EXCHANGE_NAME);
  }

  @Autowired
  private AmqpTemplate template;

  public void send() {
    String message = "hello world";
    System.out.println("producer send a fanout message: " + message);
    // 参数2将被忽略
    template.convertAndSend(FANOUT_EXCHANGE_NAME, "", message);
  }
}
