package com.forte.androidtv.entities;


import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ScreenActivationData implements Serializable {
    private Long screenId;
    private String googleId;

    public Long getScreenId() {
        return this.screenId;
    }

    public void setScreenId(Long screenId) {
        this.screenId = screenId;
    }

    public String getGoogleId() {
        return this.googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }
}