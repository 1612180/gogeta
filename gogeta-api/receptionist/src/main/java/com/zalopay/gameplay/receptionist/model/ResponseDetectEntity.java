package com.zalopay.gameplay.receptionist.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class ResponseDetectEntity extends BaseResponse{
    @JsonProperty("request_url")
    private String requestUrl;
    @JsonProperty("response_url")
    private String responseUrl;
    @JsonProperty("trans_id")
    private long transId;
}
