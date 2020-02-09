package com.zalopay.gameplay.receptionist.model;

import lombok.*;

@Setter
@Getter
@ToString
public class LogDetectEntity {
    private RequestDetectEntity requestDetectEntity;
    private ResponseDetectEntity responseDetectEntity;

    public LogDetectEntity(){
        requestDetectEntity = new RequestDetectEntity();
        responseDetectEntity = new ResponseDetectEntity();
    }
}
