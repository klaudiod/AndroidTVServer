package com.forte.androidtv.utils;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Data implements Serializable {
    private String screenId;

    public String getScreenId() {
        return this.screenId;
    }

    public void setScreenId(String screenId) {
        this.screenId = screenId;
    }
}
