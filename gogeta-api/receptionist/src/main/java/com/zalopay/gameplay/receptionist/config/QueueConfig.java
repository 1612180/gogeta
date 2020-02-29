package com.zalopay.gameplay.receptionist.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class QueueConfig {
    @Value( "${kafka.topic.detectObjectKafkaQueue}" )
    private String detectObjectKafkaQueue;

    @Value( "${kafka.topic.numberPartitionDetectObject}" )
    private int numberPartitionDetectObjectQueue;

    @Value("${kafka.detectObjectQueue.groupId}")
    private String groupIdDetectObjectQueue;

    @Value( "${kafka.topic.getStatusDetectQueue}" )
    private String getStatusDetectObjectQueue;

    @Value( "${kafka.topic.numberPartitionGetStatusDetect}" )
    private int numberPartitionGetStatusDetectQueue;

    @Value("${kafka.getStatusDetectQueue.groupId}")
    private String groupIdGetStatusDetectQueue;


    public QueueConfig(){

    }
    public String getDetectObjectKafkaQueue() {
        return detectObjectKafkaQueue;
    }
    public int getNumberPartitionDetectObjectQueue(){
        return numberPartitionDetectObjectQueue;
    }

    public int getNumberPartitionGetStatusDetectQueue() {
        return numberPartitionGetStatusDetectQueue;
    }

    public String getGetStatusDetectObjectQueue() {
        return getStatusDetectObjectQueue;
    }

    public String getGroupIdDetectObjectQueue() {
        return groupIdDetectObjectQueue;
    }

    public String getGroupIdGetStatusDetectQueue() {
        return groupIdGetStatusDetectQueue;
    }
}
