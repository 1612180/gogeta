package com.zalopay.gameplay.receptionist.handler;

import com.zalopay.gameplay.receptionist.model.RequestDetectEntity;
import com.zalopay.gameplay.receptionist.model.ResponseDetectEntity;

public interface DetectObject {
    ResponseDetectEntity handleDetect(RequestDetectEntity requestDetectEntity);
}
