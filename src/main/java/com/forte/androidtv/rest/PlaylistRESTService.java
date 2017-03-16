package com.forte.androidtv.rest;

import com.forte.androidtv.dao.PlaylistDAO;
import com.forte.androidtv.entities.Playlist;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Component;

@Component
@Path(value = "/playlist")
public class PlaylistRESTService {
    PlaylistDAO playlistDAO;

    public void setPlaylistDAO(PlaylistDAO playlistDAO) {
        this.playlistDAO = playlistDAO;
    }

    @GET
    @Path(value = "/getAllPlaylists")
    @Produces(value = {"application/json"})
    public List<Playlist> getAllPlaylists() {
        return this.playlistDAO.getAllPlaylists();
    }

    @GET
    @Path(value = "/getPlaylistById/{playlistId}")
    @Produces(value = {"application/json"})
    public Playlist getPlaylistById(@PathParam(value = "playlistId") Long playlistId) {
        return this.playlistDAO.getPlaylistById(playlistId);
    }

    @GET
    @Path(value = "/getPlaylistForScreen/{screenId}")
    @Produces(value = {"application/json"})
    public Playlist getPlaylistForScreen(@PathParam(value = "screenId") Long screenId) {
        return this.playlistDAO.getPlaylistForScreen(screenId);
    }

    @POST
    @Path(value = "/createPlayList")
    @Consumes(value = {"application/json"})
    @Produces(value = {"application/json"})
    public Long createPlayList(Playlist playlist) {
        return this.playlistDAO.createPlayList(playlist);
    }

    @DELETE
    @Path(value = "/deletePlaylist/{id}")
    @Consumes(value = {"application/json"})
    public void deletePlaylist(@PathParam(value = "id") Long id) {
        this.playlistDAO.deletePlaylist(id);
    }

    @POST
    @Path(value = "updatePlaylist")
    @Consumes(value = {"application/json"})
    @Produces(value = {"application/json"})
    public void updatePlayList(Playlist playlist) {
        this.playlistDAO.updatePlaylist(playlist);
    }
}