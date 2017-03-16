package com.forte.androidtv.dao;


import com.forte.androidtv.dao.PlaylistDAO;
import com.forte.androidtv.entities.Media;
import com.forte.androidtv.entities.Playlist;
import com.forte.androidtv.entities.Screen;
import com.forte.androidtv.utils.FilePathConverter;
import com.forte.androidtv.utils.NotificationManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.transform.ResultTransformer;

public class PlaylistDAOImpl implements PlaylistDAO {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Playlist getPlaylistById(Long id) {
        Playlist playlist = new Playlist();
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        playlist = (Playlist) session.get(Playlist.class, (Serializable) id);
        session.getTransaction().commit();
        return playlist;
    }

    @Override
    public List<Playlist> getAllPlaylists() {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        List playlists = session.createCriteria(Playlist.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        session.getTransaction().commit();
        return playlists;
    }

    @Override
    public Long createPlayList(Playlist playlist) {
        List mediaList = playlist.getMedia();
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save((Object) playlist);
        session.getTransaction().commit();
        return playlist.getPlaylistId();
    }

    @Override
    public void deletePlaylist(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Playlist playlist = (Playlist) session.load(Playlist.class, (Serializable) id);
        Query query = session.createQuery("from Screen where playlist = :playlist ");
        query.setParameter("playlist", (Object) playlist);
        List<Screen> screenList = query.list();
        for (Screen screen : screenList) {
            screen.setPlaylist(null);
            session.update((Object) screen);
            NotificationManager.post((String) screen.getGoogleId());
        }
        if (playlist != null) {
            session.delete((Object) playlist);
        }
        session.getTransaction().commit();
    }

    @Override
    public Playlist getPlaylistForScreen(Long screenId) {
        Playlist playlist = new Playlist();
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Screen screen = (Screen) session.load(Screen.class, (Serializable) screenId);
        if (screen != null) {
            playlist = screen.getPlaylist();
        }
        session.getTransaction().commit();
        if (playlist != null) {
            for (Media media : playlist.getMedia()) {
                String localFileName = media.getFilename();
                media.setFilename(FilePathConverter.convertToURL((String) localFileName));
            }
        }
        return playlist;
    }

    @Override
    public void updatePlaylist(Playlist updatedPlaylist) {
        Playlist playlist = null;
        Long playlistId = updatedPlaylist.getPlaylistId();
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        if (playlistId != null && (playlist = (Playlist) session.get(Playlist.class, (Serializable) playlistId)) != null) {
            playlist.setName(updatedPlaylist.getName());
            for (Media media : playlist.getMedia()) {
                media.setPlaylists(null);
                session.delete((Object) media);
            }
            playlist.setMedia(updatedPlaylist.getMedia());
            session.saveOrUpdate((Object) playlist);
        }
        List<Screen> screenList = this.getScreensForNotification(playlist, (org.hibernate.Session) session);
        session.getTransaction().commit();
        this.notifyScreens(screenList);
    }

    private List<Screen> getScreensForNotification(Playlist playlist, org.hibernate.Session session) {
        List screenList = new ArrayList<Screen>();
        if (playlist != null) {
            Query query = session.createQuery("from Screen where playlist = :playlist ");
            query.setParameter("playlist", (Object) playlist);
            screenList = query.list();
        }
        return screenList;
    }

    private void notifyScreens(List<Screen> screens) {
        for (Screen screen : screens) {
            NotificationManager.post((String) screen.getGoogleId());
        }
    }
}