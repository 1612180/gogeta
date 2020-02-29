package com.zalopay.gameplay.receptionist.handler;

import com.zalopay.gameplay.receptionist.model.ResponseDetectEntity;

public interface GetStatusDetectObject {
    ResponseDetectEntity getStatusDetect(Long transId);
}
