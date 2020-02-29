package com.zalopay.gameplay.receptionist.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@ToString
public class DetectTransEntity extends BaseEntity{
    private long detectTransId;
    private String requestUrl;
    private String responseUrl;
    private boolean isSavedToCache;
    private int transStatus;
    private int numberRetryDetect;
    private String idDetectImageResponse;


    public DetectTransEntity(){
        isSavedToCache = false;
        requestUrl="";
        responseUrl="";
        transStatus = 0;
        numberRetryDetect =0;
        idDetectImageResponse= "";
    }
    public boolean isProcessing(){
        if(transStatus != 0)
            return false;
        return true;
    }
    public void inCreaseNumberRetryDetect(){
        numberRetryDetect++;
    }
}
