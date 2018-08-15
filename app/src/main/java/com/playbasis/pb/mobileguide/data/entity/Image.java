package com.playbasis.pb.mobileguide.data.entity;

public class Image  {

    private String mobile_id;
    private String id;
    private String url;

    public Image(String mobile_id, String id, String url) {
        this.mobile_id = mobile_id;
        this.id = id;
        this.url = url;
    }

    public String getMobile_id() {
        return mobile_id;
    }

    public void setMobile_id(String mobile_id) {
        this.mobile_id = mobile_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
