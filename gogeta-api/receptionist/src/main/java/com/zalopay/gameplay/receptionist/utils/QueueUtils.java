package com.zalopay.gameplay.receptionist.utils;


import com.zalopay.gameplay.receptionist.config.QueueConfig;
import com.zalopay.gameplay.receptionist.constant.TransStatusEnum;
import com.zalopay.gameplay.receptionist.handler.handle.DetectObjectHandler;
import com.zalopay.gameplay.receptionist.model.AiTrans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;


@Component
public class QueueUtils {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    GsonUtils gsonUtils;

    @Autowired
    QueueConfig queueConfig;

    private static final Logger logger = Logger.getLogger(QueueUtils.class.getSimpleName());


    public boolean sendMessage(AiTrans trans, String topic) {
        try{
            Message<String> message = MessageBuilder
                    .withPayload(gsonUtils.toJsonString(trans))
                    .setHeader(KafkaHeaders.TOPIC,topic)
                    .build();
            kafkaTemplate.send(message);
            return true;
        }catch (Exception e){
            logger.info("process detect false on send message to kafka log detect");
            trans.setTransStatus(TransStatusEnum.AI_TRANS_FAIL_SEND_MESSAGE_TO_KAFKA_DETECT_OBJECT);
            return false;
        }

    }
}
