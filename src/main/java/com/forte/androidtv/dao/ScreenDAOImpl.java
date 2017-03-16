package com.forte.androidtv.dao;


import com.forte.androidtv.dao.ScreenDAO;
import com.forte.androidtv.entities.Playlist;
import com.forte.androidtv.entities.Screen;
import com.forte.androidtv.utils.NotificationManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.transform.ResultTransformer;

public class ScreenDAOImpl implements ScreenDAO {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Screen> getAllScreens() {
        List<Screen> screenList = new ArrayList();
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        screenList = session.createCriteria(Screen.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        session.getTransaction().commit();
        return screenList;
    }

    @Override
    public Long registerScreen(Screen screen) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save((Object) screen);
        session.getTransaction().commit();
        return screen.getScreenId();
    }

    @Override
    public void updateScreen(Long screenId, String screenName, Long defaultPlaylistId, Long playlistStartTime) {
        boolean shouldNotify = false;
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Screen screen = (Screen) session.get(Screen.class, (Serializable) screenId);
        if (defaultPlaylistId != null) {
            Playlist playlist = (Playlist) session.get(Playlist.class, (Serializable) defaultPlaylistId);
            if (screen != null && playlist != null) {
                screen.setPlaylist(playlist);
                shouldNotify = true;
            }
        } else if (screen != null && screenName != null && !screenName.isEmpty()) {
            screen.setName(screenName);
        } else if (screen != null && playlistStartTime != null) {
            screen.setPlaylistStartTime(playlistStartTime);
        }
        session.update((Object) screen);
        session.getTransaction().commit();
        if (shouldNotify) {
            NotificationManager.post((String) screen.getGoogleId());
        }
    }

    @Override
    public void activateScreen(Long screenId, String googleId) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Screen screen = (Screen) session.load(Screen.class, (Serializable) screenId);
        screen.setGoogleId(googleId);
        screen.setLastActivity(new Date());
        session.update((Object) screen);
        session.getTransaction().commit();
    }

    @Override
    public void deleteScreen(Long screenId) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Screen screen = (Screen) session.load(Screen.class, (Serializable) screenId);
        session.delete((Object) screen);
        session.getTransaction().commit();
    }
}
