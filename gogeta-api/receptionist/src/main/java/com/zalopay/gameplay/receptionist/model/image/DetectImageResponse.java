package com.zalopay.gameplay.receptionist.model.image;


public class DetectImageResponse {
    private int status;

    private String url;

    private String uuid;

    public DetectImageResponse(){

    }

    public DetectImageResponse(int status, String url){
        this.status = status;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public int getStatus() {
        return status;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
