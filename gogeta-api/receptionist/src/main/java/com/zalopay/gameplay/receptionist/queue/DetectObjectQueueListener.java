package com.zalopay.gameplay.receptionist.queue;

import com.zalopay.gameplay.receptionist.model.DetectTransEntity;
import com.zalopay.gameplay.receptionist.utils.GsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class DetectObjectQueueListener {

//    @KafkaListener(topics = "${kafka.topic.detectObjectKafkaQueue}",groupId = "${kafka.detectObjectQueue.groupId}")
//    public void consumerMessage(DetectTransEntity trans){
//        if(trans == null){
//            return;
//        }
//        System.out.println(trans.getDetectTransId() + " on message");
//    }


    @KafkaListener(topicPartitions = @TopicPartition(topic = "${kafka.topic.detectObjectKafkaQueue}",
            partitionOffsets = {@PartitionOffset(partition = "0", initialOffset = "0"),
                    @PartitionOffset(partition = "2", initialOffset = "0")
            }),containerFactory = "kafkaListenerContainerFactory")
    public void listenToParition1(@Payload DetectTransEntity message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        System.out.println(message.getDetectTransId() + " in even" + partition);
    }

    @KafkaListener(topicPartitions = @TopicPartition(topic = "${kafka.topic.detectObjectKafkaQueue}",
            partitionOffsets = {
                    @PartitionOffset(partition = "1", initialOffset = "0")
            }),containerFactory = "kafkaListenerContainerFactory")
    public void listenToParition2(@Payload DetectTransEntity message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        System.out.println(message.getDetectTransId() + " in odd" + partition);
    }
}
