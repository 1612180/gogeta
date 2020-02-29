package com.zalopay.gameplay.receptionist.queue;

import com.zalopay.gameplay.receptionist.cache.CacheClient;
import com.zalopay.gameplay.receptionist.config.QueueConfig;
import com.zalopay.gameplay.receptionist.constant.DetectTransStatusEnum;
import com.zalopay.gameplay.receptionist.model.DetectTransEntity;
import com.zalopay.gameplay.receptionist.model.image.DetectImageRequest;
import com.zalopay.gameplay.receptionist.model.image.DetectImageResponse;
import com.zalopay.gameplay.receptionist.utils.ImageApiUtils;
import com.zalopay.gameplay.receptionist.utils.QueueUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class GetStatusDetectObjectListener {

    @Autowired
    ImageApiUtils imageApiUtils;

    @Autowired
    CacheClient cacheClient;

    @Autowired
    QueueUtils queueUtils;

    @Autowired
    QueueConfig queueConfig;

    @Value( "${service.image.max-retry}" )
    private int MAX_RETRY_DETECT;

    @Value( "${service.image.interval-time}" )
    private int INTERVAL_TIME;

    @KafkaListener(topics = "${kafka.topic.getStatusDetectQueue}",groupId = "${kafka.getStatusDetectQueue.groupId}")
    public void processDetectObject(DetectTransEntity trans){
        System.out.println("on get status");
        if(trans == null || trans.getRequestUrl().equals("")){
            return;
        }
        DetectImageResponse response = processDetectByCallImageService(trans);
        if(response == null){
            trans.setTransStatus(DetectTransStatusEnum.CALL_IMAGE_SERVICE_DETECT_FAIL.getStatus());
            cacheClient.saveToCache(trans);
            return;
        }
        System.out.println("response: " + response.getUrl());
        transferInfoResponseToTrans(trans,response);
        if(response.getStatus() == DetectTransStatusEnum.PROCESSING.getStatus()){
            if(trans.getNumberRetryDetect() > MAX_RETRY_DETECT){
                trans.setTransStatus(DetectTransStatusEnum.CALL_IMAGE_SERVICE_DETECT_MAX_RETRY.getStatus());
                cacheClient.saveToCache(trans);
                return;
            }
            trans.inCreaseNumberRetryDetect();
            try {
                TimeUnit.SECONDS.sleep(INTERVAL_TIME);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
            String topicGetStatus = queueConfig.getGetStatusDetectObjectQueue();
            if(!queueUtils.sendMessage(trans,topicGetStatus)){
                trans.setTransStatus(DetectTransStatusEnum.SEND_MESSAGE_DETECT_OBJECT_QUEUE_FAIL.getStatus());
                cacheClient.saveToCache(trans);
                return;
            }
        }

        if(response.getStatus() == DetectTransStatusEnum.SUCCESSFUL.getStatus()){
            trans.setTransStatus(DetectTransStatusEnum.SUCCESSFUL.getStatus());
            cacheClient.saveToCache(trans);
            return;
        }
    }
    private void transferInfoResponseToTrans(DetectTransEntity trans, DetectImageResponse response) {
        trans.setIdDetectImageResponse(response.getUuid());
        trans.setResponseUrl(response.getUrl());
        trans.setTransStatus(response.getStatus());
    }
    private DetectImageResponse processDetectByCallImageService(DetectTransEntity trans){
        return imageApiUtils.getStatusDetectObject(trans.getIdDetectImageResponse());
    }
}
