package com.zalopay.gameplay.receptionist.controller;

import com.zalopay.gameplay.receptionist.config.QueueConfig;
import com.zalopay.gameplay.receptionist.model.RequestAI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AIController {

    @Autowired
    QueueConfig queueConfig;

    @PostMapping(path = "/yolo/images")
    public ResponseEntity<Object> detectObjectInImage(@RequestBody RequestAI requestAI){
        System.out.println(queueConfig.getDetectObjectKafkaQueue());
        return  new ResponseEntity<>(requestAI, HttpStatus.OK);
    }
}
