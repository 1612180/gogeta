package com.zalopay.gameplay.receptionist.model;

import com.zalopay.gameplay.receptionist.constant.TransStatusEnum;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
@Setter
@Getter
@ToString
public class AiTrans {
    private long transAiID;
    private String requestUrl;
    private String responseUrl;
    private String message;
    private int returnCode;
    private boolean isSavedToCache;
    private TransStatusEnum transStatus;

    public AiTrans(){
        isSavedToCache = false;
        message = "";
        returnCode=0;
        requestUrl="";
        responseUrl="";
        transStatus = null;
    }
}
