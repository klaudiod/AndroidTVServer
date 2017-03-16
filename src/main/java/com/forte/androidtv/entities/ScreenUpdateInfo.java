package com.forte.androidtv.entities;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ScreenUpdateInfo {
    private Long screenId;
    private Long playlistId;
    private String name;
    private Long playlistStartTime;

    public Long getScreenId() {
        return this.screenId;
    }

    public void setScreenId(Long screenId) {
        this.screenId = screenId;
    }

    public Long getPlaylistId() {
        return this.playlistId;
    }

    public void setPlaylistId(Long playlistId) {
        this.playlistId = playlistId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPlaylistStartTime() {
        return this.playlistStartTime;
    }

    public void setPlaylistStartTime(Long playlistStartTime) {
        this.playlistStartTime = playlistStartTime;
    }
}