package com.zalopay.gameplay.receptionist.cache;

import com.zalopay.gameplay.receptionist.model.AiTrans;
import com.zalopay.gameplay.receptionist.model.RequestAI;
import com.zalopay.gameplay.receptionist.utils.RedisKeyUtils;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class CacheClient {
    @Autowired
    RedissonClient redissonClient;

    @Autowired
    RedisKeyUtils redisKeyUtils;

    public boolean saveToCache(AiTrans trans){
        try {
            String urlKey = redisKeyUtils.getRequestAIKey(trans.getRequestUrl());
            RBucket<AiTrans> bucket = redissonClient.getBucket(urlKey);
            bucket.set(trans);
            trans.setSavedToCache(true);
            return true;
        }catch (Exception e){
            trans.setSavedToCache(false);
            return false;
        }
    }

    public AiTrans getTransFromCache(String url){
        try{
            String transAiKey = redisKeyUtils.getRequestAIKey(url);
            RBucket<AiTrans> requestAI = redissonClient.getBucket(transAiKey);
            return requestAI.get();
        }catch (Exception e){
            return null;
        }
    }
}
