package com.zalopay.gameplay.receptionist.utils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class AppUtils {
    private static final AtomicLong LAST_TIME_MS = new AtomicLong();

    public  long generateUniqueID() {
        long now = System.currentTimeMillis();
        while (true) {
            long lastTime = LAST_TIME_MS.get();
            if (lastTime >= now)
                now = LAST_TIME_MS.incrementAndGet();
            if (LAST_TIME_MS.compareAndSet(lastTime, now))
                return now;
        }
    }
}
