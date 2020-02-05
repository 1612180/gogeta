package com.zalopay.gameplay.receptionist.constant;

import java.util.HashMap;
import java.util.Map;

public enum TypeRequestAIEnum {
    DETECT(0),
    TRANSPARENT(1);

    private final int type;

    private static Map listRequestType = new HashMap<>();

    TypeRequestAIEnum(final int value){
        this.type = value;
    }

    static {
        for (TypeRequestAIEnum typeRequestAIEnum : TypeRequestAIEnum.values()) {
            listRequestType.put(typeRequestAIEnum.getType(), typeRequestAIEnum);
        }
    }

    public int getType() {
        return type;
    }

    public static TypeRequestAIEnum valueof(int value){
        TypeRequestAIEnum typeRequestAIEnum = (TypeRequestAIEnum) listRequestType.get(value);
        if (typeRequestAIEnum == null){
            throw new IllegalArgumentException("Not Enum constant was found for value : " + value);
        }
        return typeRequestAIEnum;
    }
    public static boolean isExistStatus(TypeRequestAIEnum typeRequestAIEnum){
        return listRequestType.containsValue(typeRequestAIEnum);
    }
}
