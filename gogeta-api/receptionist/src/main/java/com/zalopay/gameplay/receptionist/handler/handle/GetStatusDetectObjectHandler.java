package com.zalopay.gameplay.receptionist.handler.handle;

import com.zalopay.gameplay.receptionist.cache.CacheClient;
import com.zalopay.gameplay.receptionist.config.QueueConfig;
import com.zalopay.gameplay.receptionist.constant.DetectTransStatusEnum;
import com.zalopay.gameplay.receptionist.handler.GetStatusDetectObject;
import com.zalopay.gameplay.receptionist.model.DetectTransEntity;
import com.zalopay.gameplay.receptionist.model.LogDetectEntity;
import com.zalopay.gameplay.receptionist.model.ResponseDetectEntity;
import com.zalopay.gameplay.receptionist.utils.QueueUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetStatusDetectObjectHandler implements GetStatusDetectObject {

    @Autowired
    CacheClient cacheClient;

    @Autowired
    QueueConfig queueConfig;

    @Autowired
    QueueUtils queueUtils;

    @Override
    public ResponseDetectEntity getStatusDetect(Long transId) {
        LogDetectEntity logDetectEntity = new LogDetectEntity();
        if(validateGetStatusRequest(transId) == false){
            logDetectEntity.getResponseDetectEntity().setReturnCode(DetectTransStatusEnum.INVALID_REQUEST_GET_STATUS_DETECT_OBJECT.getStatus());
            return logDetectEntity.getResponseDetectEntity();
        }
        DetectTransEntity trans = getTransFromCache(transId);
        if(trans == null){
            logDetectEntity.getResponseDetectEntity().setReturnCode(DetectTransStatusEnum.INVALID_TRANS_ID_WHEN_GET_STATUS.getStatus());
            return logDetectEntity.getResponseDetectEntity();
        }
        getInfoFromTrans(logDetectEntity,trans);
        return logDetectEntity.getResponseDetectEntity();
    }

    private void getInfoFromTrans(LogDetectEntity log, DetectTransEntity trans){
        log.getResponseDetectEntity().setReturnCode(trans.getTransStatus());
        log.getResponseDetectEntity().setRequestUrl(trans.getRequestUrl());
        log.getResponseDetectEntity().setResponseUrl(trans.getResponseUrl());
        log.getResponseDetectEntity().setTransId(trans.getDetectTransId());
    }

    private DetectTransEntity getTransFromCache(Long transId) {
        return cacheClient.getTransFromCache(transId);
    }

    private boolean validateGetStatusRequest(Long transId) {
        if(transId == null || transId <=  0){
            return false;
        }
        return true;
    }
}
