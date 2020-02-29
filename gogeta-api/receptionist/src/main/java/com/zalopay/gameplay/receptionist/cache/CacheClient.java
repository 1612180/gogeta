package com.zalopay.gameplay.receptionist.cache;

import com.zalopay.gameplay.receptionist.model.DetectTransEntity;
import com.zalopay.gameplay.receptionist.utils.RedisKeyUtils;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CacheClient {
    @Autowired
    RedissonClient redissonClient;

    @Autowired
    RedisKeyUtils redisKeyUtils;

    public boolean saveToCache(DetectTransEntity trans){
        try {
            String urlKey = redisKeyUtils.getRequestAIKey(trans.getDetectTransId());
            RBucket<DetectTransEntity> bucket = redissonClient.getBucket(urlKey);
            bucket.set(trans);
            trans.setSavedToCache(true);
            return true;
        }catch (Exception e){
            trans.setSavedToCache(false);
            return false;
        }
    }

    public DetectTransEntity getTransFromCache(long transId){
        try{
            String transAiKey = redisKeyUtils.getRequestAIKey(transId);
            RBucket<DetectTransEntity> requestAI = redissonClient.getBucket(transAiKey);
            return requestAI.get();
        }catch (Exception e){
            return null;
        }
    }
}
