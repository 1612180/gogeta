package com.zalopay.gameplay.receptionist.service;
import com.zalopay.gameplay.receptionist.factory.HandlerFactory;
import com.zalopay.gameplay.receptionist.handler.handle.BaseHandler;
import com.zalopay.gameplay.receptionist.model.RequestAI;
import com.zalopay.gameplay.receptionist.model.ResponseAI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessRequestService {

    @Autowired
    HandlerFactory handlerFactory;

    public void processRequest(RequestAI requestAI){
        BaseHandler baseHandler = handlerFactory.getHandler(requestAI);
        baseHandler.detectObjectOnImage(requestAI);
    }
    public ResponseAI initResponse
}
