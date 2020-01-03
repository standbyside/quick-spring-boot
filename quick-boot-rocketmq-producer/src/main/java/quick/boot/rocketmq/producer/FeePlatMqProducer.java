package quick.boot.rocketmq.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quick.boot.rocketmq.config.ParamConfigService;


@Service
public class FeePlatMqProducer {

    @Autowired
    private DefaultMQProducer defaultMQProducer;
    @Autowired
    private ParamConfigService paramConfigService ;

    public SendResult openAccountMsg(String msgInfo) {
        // 可以不使用config中的Group
        defaultMQProducer.setProducerGroup(paramConfigService.feePlatGroup);
        SendResult sendResult = null;
        try {
            Message sendMsg = new Message(
                    paramConfigService.feePlatTopic,
                    paramConfigService.feeAccountTag,
                    "fee_open_account_key",
                    msgInfo.getBytes()
            );
            sendResult = defaultMQProducer.send(sendMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendResult ;
    }
}