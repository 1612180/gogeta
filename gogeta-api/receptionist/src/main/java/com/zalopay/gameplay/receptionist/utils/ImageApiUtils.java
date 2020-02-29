package com.zalopay.gameplay.receptionist.utils;

import com.zalopay.gameplay.receptionist.model.image.DetectImageRequest;
import com.zalopay.gameplay.receptionist.model.image.DetectImageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
public class ImageApiUtils {
    @Value( "${service.image.host}" )
    private String serviceImageUrl;

    @Autowired
    RestTemplate restTemplate;

    private HttpHeaders getHttpHeader(){
        HttpHeaders headers = new HttpHeaders();
        // set `content-type` header
        headers.setContentType(MediaType.APPLICATION_JSON);
        // set `accept` header
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return headers;
    }

    public DetectImageResponse processDetectObject(DetectImageRequest request){
        // create headers
        HttpHeaders headers = getHttpHeader();
        // build the request
        HttpEntity<DetectImageRequest> entity = new HttpEntity<>(request, headers);
        // send POST request
        ResponseEntity<DetectImageResponse> response = restTemplate.postForEntity(serviceImageUrl, entity, DetectImageResponse.class);
        // check response status code
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }
        return null;
    }

    public DetectImageResponse getStatusDetectObject(String uuid){

        String getStatusDetectObjectUrl = serviceImageUrl + "/" + uuid;

        // create headers
        HttpHeaders headers = getHttpHeader();

        // build the request
        HttpEntity request = new HttpEntity(headers);

        // make an HTTP GET request with headers
        ResponseEntity<DetectImageResponse> response = restTemplate.exchange(
            getStatusDetectObjectUrl,
            HttpMethod.GET,
            request,
            DetectImageResponse.class
        );
        // check response
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }
        return null;
    }
}
