package com.zalopay.gameplay.receptionist.factory;

import com.zalopay.gameplay.receptionist.constant.TypeRequestAIEnum;
import com.zalopay.gameplay.receptionist.handler.handle.BaseHandler;
import com.zalopay.gameplay.receptionist.handler.handle.DetectObjectHandler;
import com.zalopay.gameplay.receptionist.model.RequestDetectEntity;
import org.springframework.stereotype.Component;

@Component
public class HandlerFactory {
    public BaseHandler getHandler(RequestDetectEntity requestDetectEntity){
        TypeRequestAIEnum type = TypeRequestAIEnum.valueof(requestDetectEntity.getType());
        BaseHandler baseHandler = null;
        switch (type){
            case DETECT:
            default:
                baseHandler = new DetectObjectHandler();
                break;

        }
        return baseHandler;
    }
}
