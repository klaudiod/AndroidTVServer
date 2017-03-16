package com.forte.androidtv.entities;


import com.forte.androidtv.entities.Media;
import com.forte.androidtv.entities.Screen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@XmlRootElement
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "playlist")
public class Playlist implements Serializable {
    @Id
    @Column(name = "playlist_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playlistId;
    @Column(name = "name")
    private String name;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "playlist_media", joinColumns = {@JoinColumn(name = "playlist_id")}, inverseJoinColumns = {@JoinColumn(name = "media_id")})
    @OrderBy(value = "sequenceNumber ASC")
    private List<Media> media = new ArrayList<Media>(0);
    @OneToMany(mappedBy = "playlist")
    private Set<Screen> screens = new HashSet<Screen>(0);

    public Playlist() {
    }

    public Playlist(Long id) {
        this.playlistId = id;
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

    public List<Media> getMedia() {
        return this.media;
    }

    public void setMedia(List<Media> mediaList) {
        this.media = mediaList;
    }

    @JsonIgnore
    public Set<Screen> getScreens() {
        return this.screens;
    }

    public void setScreens(Set<Screen> screens) {
        this.screens = screens;
    }
}