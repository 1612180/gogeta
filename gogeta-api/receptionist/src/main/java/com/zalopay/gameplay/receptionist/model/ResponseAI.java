package com.zalopay.gameplay.receptionist.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ResponseAI {
    private String requestUrl;
    private String responseUrl;
    private String status;
    private long transId;
    private int returnCode;
}
