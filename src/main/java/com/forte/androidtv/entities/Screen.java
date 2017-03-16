package com.forte.androidtv.entities;


import com.forte.androidtv.entities.Playlist;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@XmlRootElement
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "screen")
public class Screen implements Serializable {
    @Id
    @Column(name = "screen_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long screenId;
    @Column(name = "last_activity")
    private Date lastActivity;
    @Column(name = "name")
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "playlist_id", nullable = true)
    private Playlist playlist;
    @Column(name = "googleId")
    private String googleId;
    @Column(name = "playlist_start_time")
    private Long playlistStartTime;

    public Screen() {
    }

    public Screen(Long id) {
        this.screenId = id;
    }

    public Long getScreenId() {
        return this.screenId;
    }

    public void setScreenId(Long screenId) {
        this.screenId = screenId;
    }

    public Date getLastActivity() {
        return this.lastActivity;
    }

    public void setLastActivity(Date lastActivity) {
        this.lastActivity = lastActivity;
    }

    public Playlist getPlaylist() {
        return this.playlist;
    }

    public void setPlaylist(Playlist defaultPlaylist) {
        this.playlist = defaultPlaylist;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGoogleId() {
        return this.googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public Long getPlaylistStartTime() {
        return this.playlistStartTime;
    }

    public void setPlaylistStartTime(Long playlistStartTime) {
        this.playlistStartTime = playlistStartTime;
    }
}