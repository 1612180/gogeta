package com.zalopay.gameplay.receptionist.constant;

import java.util.HashMap;
import java.util.Map;

public enum DetectTransStatusEnum {
    PROCESSING(2),
    SUCCESSFUL(1),
    EXCEPTION(-1),
    INVALID_REQUEST_DETECT_OBJECT(-2),
    UNAVAILABLE_VALUE_REQUEST_DETECT_OBJECT(-3),
    IS_DETECTED_THIS_OBJECT(-4),
    SEND_MESSAGE_DETECT_OBJECT_QUEUE_FAIL(-5),
    SEND_MESSAGE_GET_STATUS_DETECT_OBJECT_FAIL(-6),
    TRANS_IS_DETECTED(-7),
    SAVE_TRANS_TO_CACHE_FAIL(-8),
    CALL_IMAGE_SERVICE_DETECT_FAIL(-9),
    CALL_IMAGE_SERVICE_DETECT_MAX_RETRY(-10),
    INVALID_REQUEST_GET_STATUS_DETECT_OBJECT(-11),
    INVALID_TRANS_ID_WHEN_GET_STATUS(-12),;

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
