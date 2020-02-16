package com.zalopay.gameplay.receptionist.utils;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class AppUtils {
    private AtomicLong currentTime;

    public AppUtils(){
        currentTime =  new AtomicLong();
    }

    public long generateUniqueID() {
        long prev;
        long next = System.currentTimeMillis();
        do {
            prev = currentTime.get();
            next = next > prev ? next : prev + 1;
        } while (!currentTime.compareAndSet(prev, next));
        return next;
    }
}
