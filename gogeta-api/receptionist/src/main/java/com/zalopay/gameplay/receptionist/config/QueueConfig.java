package com.zalopay.gameplay.receptionist.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
public class QueueConfig {
    @Value( "${kafka.topic.detectObjectKafkaQueue}" )
    private String detectObjectKafkaQueue;

    @Value( "${kafka.topic.numberPartition}" )
    private int numberPartition;

    public QueueConfig(){

    }
    public String getDetectObjectKafkaQueue() {
        return detectObjectKafkaQueue;
    }
    public int getNumberPartition(){
        return numberPartition;
    }



}
