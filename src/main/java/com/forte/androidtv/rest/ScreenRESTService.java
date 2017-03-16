package com.forte.androidtv.rest;


import com.forte.androidtv.dao.ScreenDAO;
import com.forte.androidtv.entities.Screen;
import com.forte.androidtv.entities.ScreenActivationData;
import com.forte.androidtv.entities.ScreenUpdateInfo;

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
@Path(value = "/screen")
public class ScreenRESTService {
    ScreenDAO screenDAO;

    public void setScreenDAO(ScreenDAO screenDAO) {
        this.screenDAO = screenDAO;
    }

    @GET
    @Path(value = "/getAllScreens")
    @Produces(value = {"application/json"})
    public List<Screen> getAllScreens() {
        return this.screenDAO.getAllScreens();
    }

    @POST
    @Path(value = "/registerScreen")
    @Produces(value = {"application/json"})
    @Consumes(value = {"application/json"})
    public Long registerScreen(Screen screen) {
        return this.screenDAO.registerScreen(screen);
    }

    @POST
    @Path(value = "/updateScreen")
    @Produces(value = {"application/json"})
    @Consumes(value = {"application/json"})
    public void updateScreen(ScreenUpdateInfo screenUpdateInfo) {
        if (screenUpdateInfo != null) {
            this.screenDAO.updateScreen(screenUpdateInfo.getScreenId(), screenUpdateInfo.getName(), screenUpdateInfo.getPlaylistId(), screenUpdateInfo.getPlaylistStartTime());
        }
    }

    @POST
    @Path(value = "/activateScreen")
    @Consumes(value = {"application/json"})
    public void activateScreen(ScreenActivationData activationData) {
        if (activationData != null) {
            Long screenId = activationData.getScreenId();
            String googleId = activationData.getGoogleId();
            if (screenId != null && googleId != null) {
                this.screenDAO.activateScreen(screenId, googleId);
            }
        }
    }

    @DELETE
    @Path(value = "/deleteScreen/{id}")
    @Consumes(value = {"application/json"})
    public void deleteScreen(@PathParam(value = "id") Long id) {
        this.screenDAO.deleteScreen(id);
    }
}