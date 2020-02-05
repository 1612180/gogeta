package com.zalopay.gameplay.receptionist.handler.handle;

import com.zalopay.gameplay.receptionist.cache.CacheClient;
import com.zalopay.gameplay.receptionist.config.QueueConfig;
import com.zalopay.gameplay.receptionist.constant.TransStatusEnum;
import com.zalopay.gameplay.receptionist.handler.DetectObject;
import com.zalopay.gameplay.receptionist.model.AiTrans;
import com.zalopay.gameplay.receptionist.model.RequestAI;
import com.zalopay.gameplay.receptionist.utils.AppUtils;
import com.zalopay.gameplay.receptionist.utils.QueueUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class DetectObjectHandler extends BaseHandler implements DetectObject {

    @Autowired
    AppUtils appUtils;

    @Autowired
    CacheClient cacheClient;

    @Autowired
    QueueUtils queueUtils;

    @Autowired
    QueueConfig queueConfig;

    private static final Logger logger = Logger.getLogger(DetectObjectHandler.class.getSimpleName());


    @Override
    public boolean detectObjectOnImage(RequestAI requestAI) {
        AiTrans trans = new AiTrans();
        if(!validateRequest(requestAI)){
            return false;
        }
        if(!getInfoToTrans(requestAI, trans)){
            return false;
        }
        if(checkDuplicate(requestAI)){
            return false;
        }
        if(!saveTransToCache(trans)){
            return false;
        }
        if(!processDetect(trans)){
            return false;
        }
        return true;
    }

    public boolean processDetect(AiTrans trans){
        String topicDetectObject = queueConfig.getDetectObjectKafkaQueue();
        if(!queueUtils.sendMessage(trans,topicDetectObject)){
            return false;
        }
        return true;
    }

    public boolean validateRequest(RequestAI requestAI){
        if(requestAI == null || requestAI.getUrl() == null || requestAI.getUrl().equals("")){
            return false;
        }
        return true;
    }

    public boolean getInfoToTrans(RequestAI requestAI, AiTrans trans) {
        try{
            trans.setTransAiID(appUtils.generateUniqueID());
            trans.setTransStatus(TransStatusEnum.AI_TRANS_PROCESSING);
            trans.setRequestUrl(requestAI.getUrl());
        }catch (Exception e){
            return false;
        }
        return true;
    }
    public boolean checkDuplicate(RequestAI requestAI){
        if(cacheClient.getTransFromCache(requestAI.getUrl()) != null){
            return true;
        }
        return false;
    }
    public boolean saveTransToCache(AiTrans trans){
        return cacheClient.saveToCache(trans);
    }
}
