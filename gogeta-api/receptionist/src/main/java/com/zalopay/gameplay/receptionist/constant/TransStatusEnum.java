package com.zalopay.gameplay.receptionist.constant;

import java.util.HashMap;
import java.util.Map;

public enum TransStatusEnum {
    AI_TRANS_PROCESSING(0),
    AI_TRANS_SUCCESSFUL(1),
    AI_TRANS_FAIL(-1),
    AI_TRANS_FAIL_SEND_MESSAGE_TO_KAFKA_DETECT_OBJECT(-2);

    private final int status;

    private static Map listGameType = new HashMap<>();

    TransStatusEnum(final int status){
        this.status = status;
    }

    static {
        for (TransStatusEnum transStatus : TransStatusEnum.values()) {
            listGameType.put(transStatus.getStatus(), transStatus);
        }
    }

    public int getStatus() {
        return status;
    }

    public static TransStatusEnum valueof(int value){
        TransStatusEnum gameType = (TransStatusEnum) listGameType.get(value);
        if (gameType == null){
            throw new IllegalArgumentException("Not Enum constant was found for value : " + value);
        }
        return gameType;
    }
    public static boolean isExistStatus(TransStatusEnum gameType){
        return listGameType.containsValue(gameType);
    }
}
