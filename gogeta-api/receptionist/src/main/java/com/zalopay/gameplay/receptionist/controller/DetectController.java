package com.zalopay.gameplay.receptionist.controller;

import com.zalopay.gameplay.receptionist.config.QueueConfig;
import com.zalopay.gameplay.receptionist.handler.handle.DetectObjectHandler;
import com.zalopay.gameplay.receptionist.model.RequestDetectEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DetectController {

    @Autowired
    QueueConfig queueConfig;

    @Autowired
    DetectObjectHandler detectObjectHandler;

    @PostMapping(path = "/yolo/images")
    public ResponseEntity<Object> detectObjectInImage(@RequestBody RequestDetectEntity requestDetectEntity){
        detectObjectHandler.handleDetect(requestDetectEntity);
        System.out.println(queueConfig.getDetectObjectKafkaQueue());
        return  new ResponseEntity<>(requestDetectEntity, HttpStatus.OK);
    }
}
