package com.zalopay.gameplay.receptionist.constant;

import java.util.HashMap;
import java.util.Map;

public enum DetectTransStatusEnum {
    DETECT_OBJECT_PROCESSING(0),
    DETECT_OBJECT_SUCCESSFUL(1),
    DETECT_OBJECT_EXCEPTION(-1),
    INVALID_REQUEST_DETECT_OBJECT(-2),
    UNAVAILABLE_VALUE_REQUEST_DETECT_OBJECT(-3),
    IS_DETECTED_THIS_OBJECT(-4);

    private final int status;

    private static Map listGameType = new HashMap<>();

    DetectTransStatusEnum(final int status){
        this.status = status;
    }

    static {
        for (DetectTransStatusEnum transStatus : DetectTransStatusEnum.values()) {
            listGameType.put(transStatus.getStatus(), transStatus);
        }
    }

    public int getStatus() {
        return status;
    }

    public static DetectTransStatusEnum valueof(int value){
        DetectTransStatusEnum gameType = (DetectTransStatusEnum) listGameType.get(value);
        if (gameType == null){
            throw new IllegalArgumentException("Not Enum constant was found for value : " + value);
        }
        return gameType;
    }
    public static boolean isExistStatus(DetectTransStatusEnum gameType){
        return listGameType.containsValue(gameType);
    }
}
