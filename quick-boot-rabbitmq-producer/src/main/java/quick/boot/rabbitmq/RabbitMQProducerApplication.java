package quick.boot.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RabbitMQProducerApplication {

  public static void main(String[] args) {
    SpringApplication.run(RabbitMQProducerApplication.class, args);
  }
}
