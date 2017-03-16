package com.forte.androidtv.dao;


import com.forte.androidtv.entities.Playlist;
import java.util.List;

public interface PlaylistDAO {
    public Playlist getPlaylistById(Long var1);

    public List<Playlist> getAllPlaylists();

    public Long createPlayList(Playlist var1);

    public void deletePlaylist(Long var1);

    public Playlist getPlaylistForScreen(Long var1);

    public void updatePlaylist(Playlist var1);
}