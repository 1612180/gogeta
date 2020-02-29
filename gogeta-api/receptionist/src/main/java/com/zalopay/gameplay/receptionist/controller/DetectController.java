package com.zalopay.gameplay.receptionist.controller;

import com.zalopay.gameplay.receptionist.config.QueueConfig;
import com.zalopay.gameplay.receptionist.handler.GetStatusDetectObject;
import com.zalopay.gameplay.receptionist.handler.handle.DetectObjectHandler;
import com.zalopay.gameplay.receptionist.handler.handle.GetStatusDetectObjectHandler;
import com.zalopay.gameplay.receptionist.model.RequestDetectEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DetectController {

    @Autowired
    QueueConfig queueConfig;

    @Autowired
    DetectObjectHandler detectObjectHandler;

    @Autowired
    GetStatusDetectObjectHandler getStatusDetectObjectHandler;

    @PostMapping(path = "/images")
    public ResponseEntity<Object> detectObjectInImage(@RequestBody RequestDetectEntity requestDetectEntity) {
        return new ResponseEntity<>(detectObjectHandler.handleDetect(requestDetectEntity), HttpStatus.OK);
    }
    @GetMapping("/images/{transId}")
    public ResponseEntity<Object> getStatusDetectObjectOnImage(@PathVariable Long transId) {
        return new ResponseEntity<>(getStatusDetectObjectHandler.getStatusDetect(transId), HttpStatus.OK);
    }
}
