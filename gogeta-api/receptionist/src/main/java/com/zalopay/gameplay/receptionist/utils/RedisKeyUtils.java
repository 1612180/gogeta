package com.zalopay.gameplay.receptionist.utils;

import org.springframework.stereotype.Component;

@Component
public class RedisKeyUtils {

    public static String getRequestAIKey(String url)
    {
        return new StringBuilder("AITRANS")
                .append("|")
                .append(url)
                .toString();
    }
}
