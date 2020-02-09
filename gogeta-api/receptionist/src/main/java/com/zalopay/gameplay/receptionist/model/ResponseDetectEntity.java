package com.zalopay.gameplay.receptionist.model;

import lombok.*;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class ResponseDetectEntity extends BaseResponse{
    private String requestUrl;
    private String responseUrl;
    private long transId;
}
