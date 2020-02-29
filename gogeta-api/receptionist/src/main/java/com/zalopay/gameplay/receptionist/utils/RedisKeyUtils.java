package com.zalopay.gameplay.receptionist.utils;

import org.springframework.stereotype.Component;

@Component
public class RedisKeyUtils {

    public static String getRequestAIKey(long transId)
    {
        return new StringBuilder("AITRANS")
                .append("|")
                .append(transId)
                .toString();
    }
}
