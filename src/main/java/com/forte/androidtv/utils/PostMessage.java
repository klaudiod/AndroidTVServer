package com.forte.androidtv.utils;


import com.forte.androidtv.utils.Data;

import java.io.Serializable;

public class PostMessage implements Serializable {
    private Data data;
    private String[] registration_ids;

    public Data getData() {
        return this.data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String[] getRegistration_ids() {
        return this.registration_ids;
    }

    public void setRegistration_ids(String[] registration_ids) {
        this.registration_ids = registration_ids;
    }
}