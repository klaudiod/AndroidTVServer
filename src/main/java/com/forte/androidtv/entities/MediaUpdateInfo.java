package com.forte.androidtv.entities;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MediaUpdateInfo implements Serializable {
    private Long mediaId;
    private Long duration;
    private String filename;
    private Integer sequenceNumber;

    public Long getMediaId() {
        return this.mediaId;
    }

    public void setMediaId(Long mediaId) {
        this.mediaId = mediaId;
    }

    public Long getDuration() {
        return this.duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Integer getSequenceNumber() {
        return this.sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getFilename() {
        return this.filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}