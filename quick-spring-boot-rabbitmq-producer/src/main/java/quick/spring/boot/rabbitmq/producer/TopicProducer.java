package quick.spring.boot.rabbitmq.producer;

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

  /**
   * 初始化exchange.
   */
  @Bean(name = "topicExchange")
  public TopicExchange topicExchange() {
    return new TopicExchange(TOPIC_EXCHANGE_NAME);
  }

  /**
   * 初始化queue1.
   */
  @Bean(name = "topicQueue1")
  public Queue topicQueue1() {
    return new Queue(TOPIC_QUEUE1_NAME);
  }

  /**
   * 初始化queue2.
   */
  @Bean(name = "topicQueue2")
  public Queue topicQueue2() {
    return new Queue(TOPIC_QUEUE2_NAME);
  }

  /**
   * 绑定1.
   */
  @Bean
  Binding bindingTopicMessage1(@Qualifier("topicQueue1") Queue queue,
                               @Qualifier("topicExchange") TopicExchange exchange) {
    return BindingBuilder.bind(queue).to(exchange).with("test.topic.queue1");
  }

  /**
   * 绑定2.
   */
  @Bean
  Binding bindingTopicMessage2(@Qualifier("topicQueue2") Queue queue,
                               @Qualifier("topicExchange") TopicExchange exchange) {
    // *表示一个词，#表示0个或多个词
    return BindingBuilder.bind(queue).to(exchange).with("test.topic.*");
  }

  @Autowired
  private AmqpTemplate template;

  public void send1() {
    String message = "hello world 1";
    System.out.println("producer send a topic message 1: " + message);
    template.convertAndSend(TOPIC_EXCHANGE_NAME, TOPIC_QUEUE1_NAME, message);
  }

  public void send2() {
    String message = "hello world 2";
    System.out.println("producer send a topic message 2: " + message);
    template.convertAndSend(TOPIC_EXCHANGE_NAME, TOPIC_QUEUE2_NAME, message);
  }
}
