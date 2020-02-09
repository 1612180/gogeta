package com.zalopay.gameplay.receptionist.handler.handle;

import com.zalopay.gameplay.receptionist.buz.DetectBuz;
import com.zalopay.gameplay.receptionist.cache.CacheClient;
import com.zalopay.gameplay.receptionist.config.QueueConfig;
import com.zalopay.gameplay.receptionist.constant.DetectTransStatusEnum;
import com.zalopay.gameplay.receptionist.handler.DetectObject;
import com.zalopay.gameplay.receptionist.model.DetectTransEntity;
import com.zalopay.gameplay.receptionist.model.LogDetectEntity;
import com.zalopay.gameplay.receptionist.model.RequestDetectEntity;
import com.zalopay.gameplay.receptionist.model.ResponseDetectEntity;
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

    @Autowired
    DetectBuz detectBuz;


    private static final Logger logger = Logger.getLogger(DetectObjectHandler.class.getSimpleName());


    @Override
    public ResponseDetectEntity handleDetect(RequestDetectEntity requestDetectEntity) {
        DetectTransEntity trans = new DetectTransEntity();
        LogDetectEntity logDetectEntity = new LogDetectEntity();
        detectBuz.setTrans(trans);
        if(!getInfoRequest(requestDetectEntity, logDetectEntity)){
            return logDetectEntity.getResponseDetectEntity();
        }
        if(!validateRequest(logDetectEntity)){
            return logDetectEntity.getResponseDetectEntity();
        }

        detectBuz.processDetect(logDetectEntity);
        getInfoFromTrans(logDetectEntity,trans);
        return logDetectEntity.getResponseDetectEntity();
    }


    private void getInfoFromTrans(LogDetectEntity logDetectEntity, DetectTransEntity trans) {
        System.out.println(trans);
        logDetectEntity.getResponseDetectEntity().setTransId(trans.getDetectTransId());
        logDetectEntity.getResponseDetectEntity().setReturnCode(trans.getTransStatus().getStatus());
    }

    public boolean validateRequest(LogDetectEntity logDetectEntity){
        RequestDetectEntity request = logDetectEntity.getRequestDetectEntity();
        System.out.println(request);
        ResponseDetectEntity response = logDetectEntity.getResponseDetectEntity();
        System.out.println(response);

        if( request.getUrl().equals("")){
            response.setRequestUrl(request.getUrl());
            response.setReturnCode(DetectTransStatusEnum.UNAVAILABLE_VALUE_REQUEST_DETECT_OBJECT.getStatus());
            return false;
        }
        response.setRequestUrl(request.getUrl());
        return true;
    }

    public boolean getInfoRequest( RequestDetectEntity requestDetectEntity,LogDetectEntity logDetectEntity) {
        if(requestDetectEntity == null){
            logDetectEntity.getResponseDetectEntity().setReturnCode(DetectTransStatusEnum.INVALID_REQUEST_DETECT_OBJECT.getStatus());
            return false;
        }
        logDetectEntity.setRequestDetectEntity(requestDetectEntity);
        return true;
    }
}
