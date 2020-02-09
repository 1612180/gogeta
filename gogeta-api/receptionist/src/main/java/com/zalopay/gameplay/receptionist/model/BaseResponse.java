package com.zalopay.gameplay.receptionist.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class BaseRespone {
    private int returnCode;
    private String returnMessage;
}
