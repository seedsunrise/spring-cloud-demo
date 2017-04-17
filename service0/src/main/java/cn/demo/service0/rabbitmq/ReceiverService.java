package cn.demo.service0.rabbitmq;

import cn.demo.service0.rabbitmq.entity.EventPublish;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class ReceiverService {

//    @RabbitListener(queues = AmqpConfig.RabbitmqQueue.TRANSACTION_QUEUE)
    public void receive(EventPublish eventPublish) {
        System.out.println("Received contract<" + JSONObject.toJSONString(eventPublish) + ">");
    }
}
