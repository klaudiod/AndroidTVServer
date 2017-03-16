package com.forte.androidtv.entities;

import com.forte.androidtv.entities.Playlist;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;

@XmlRootElement
@Entity
@Table(name = "media")
public class Media implements Serializable {
    @Id
    @Column(name = "media_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mediaId;
    @Column(name = "filename")
    private String filename;
    @Column(name = "duration")
    private Long duration;
    @Column(name = "sequence_number")
    private Integer sequenceNumber;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "media")
    private Set<Playlist> playlists = new HashSet<Playlist>(0);

    public Long getMediaId() {
        return this.mediaId;
    }

    public void setMediaId(Long mediaId) {
        this.mediaId = mediaId;
    }

    public String getFilename() {
        return this.filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
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

    @JsonIgnore
    public Set<Playlist> getPlaylists() {
        return this.playlists;
    }

    public void setPlaylists(Set<Playlist> playlists) {
        this.playlists = playlists;
    }
}