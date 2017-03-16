package com.forte.androidtv.rest;

import com.forte.androidtv.dao.MediaDAO;
import com.forte.androidtv.entities.Media;
import com.forte.androidtv.utils.DirectoryStructureParser;

import java.util.List;
import java.util.Map;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Component;

@Component
@Path(value = "/media")
public class MediaRESTService {
    MediaDAO mediaDAO;

    public void setMediaDAO(MediaDAO mediaDAO) {
        this.mediaDAO = mediaDAO;
    }

    @GET
    @Path(value = "/getAllMediafiles")
    @Produces(value = {"application/json"})
    public List<Media> getAllMediafiles() {
        return this.mediaDAO.getAllMediaFiles();
    }

    @GET
    @Path(value = "/getContentDirectoryStructure")
    @Produces(value = {"application/json"})
    public Map<String, List<String>> getContentDirectoryStructure() {
        return new DirectoryStructureParser().getContentDirectoryStructure();
    }
}
