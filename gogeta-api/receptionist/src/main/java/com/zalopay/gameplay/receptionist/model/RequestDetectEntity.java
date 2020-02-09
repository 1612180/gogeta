package com.zalopay.gameplay.receptionist.model;

import lombok.*;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class RequestDetectEntity extends BaseRequest {
    private String url;
    private int type;
}
