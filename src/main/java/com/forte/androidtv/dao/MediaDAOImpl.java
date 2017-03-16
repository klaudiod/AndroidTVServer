package com.forte.androidtv.dao;

import com.forte.androidtv.dao.MediaDAO;
import com.forte.androidtv.entities.Media;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

public class MediaDAOImpl
        implements MediaDAO {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Media> getAllMediaFiles() {
        List<Media> mediaList = new ArrayList();
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        mediaList = session.createCriteria(Media.class).list();
        session.getTransaction().commit();
        return mediaList;
    }
}