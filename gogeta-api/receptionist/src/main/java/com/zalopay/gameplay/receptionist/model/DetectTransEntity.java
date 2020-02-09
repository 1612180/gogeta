package com.zalopay.gameplay.receptionist.model;

import com.zalopay.gameplay.receptionist.constant.DetectTransStatusEnum;
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
    private DetectTransStatusEnum transStatus;

    public DetectTransEntity(){
        isSavedToCache = false;
        requestUrl="";
        responseUrl="";
        transStatus = null;
    }
}
