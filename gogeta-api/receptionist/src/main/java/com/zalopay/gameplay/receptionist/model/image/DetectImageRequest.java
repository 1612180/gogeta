package com.zalopay.gameplay.receptionist.model.image;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zalopay.gameplay.receptionist.model.DetectTransEntity;

public class DetectImageRequest {
    @JsonProperty("url")
    private String url;

    public DetectImageRequest(DetectTransEntity detectTransEntity){
        this.url = detectTransEntity.getRequestUrl();
    }

    public DetectImageRequest(String url){
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
