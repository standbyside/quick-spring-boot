package quick.boot.rabbitmq.producer;

import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PubConfirmAndReturn implements RabbitTemplate.ReturnCallback, RabbitTemplate.ConfirmCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        rabbitTemplate.setReturnCallback(this);
        rabbitTemplate.setConfirmCallback(this);
    }

    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {

        log.warn("消息主体 message：{}", message);
        log.warn("消息主体 message：{}", i);
        log.warn("描述：{}", s);
        log.warn("消息使用的交换器 exchange：{}", s1);
        log.warn("消息使用的路由键 routing：{}", s2);
    }


    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {

        log.warn("消息唯一标识：{}", correlationData);
        log.warn("确认结果：{}", b);
        log.warn("失败原因：{}", s);
    }
}