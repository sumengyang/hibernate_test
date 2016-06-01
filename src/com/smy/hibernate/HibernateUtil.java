package com.smy.hibernate;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public final class HibernateUtil {
	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static Session getSession() {
		return sessionFactory.openSession();
	}
	
	public static void add(Object object){
		Session s = null;
		Transaction tx = null;
		try {
			s = HibernateUtil.getSession();
			tx = s.beginTransaction();
			s.save(object);
			tx.commit();
		} finally {
			if (s != null)
				s.close();
		}
	}
	
	public static void update(Object object){
		Session s = null;
		Transaction tx = null;
		try {
			s = HibernateUtil.getSession();
			tx = s.beginTransaction();
			s.update(object);
			tx.commit();
		} finally {
			if (s != null)
				s.close();
		}
	}
	public static void delete(Object object){
		Session s = null;
		Transaction tx = null;
		try {
			s = HibernateUtil.getSession();
			tx = s.beginTransaction();
			s.update(object);
			tx.commit();
		} finally {
			if (s != null)
				s.close();
		}
	}
	
	public static Object get(Class clazz,Serializable id){
		Session s = null;
		try {
			s = HibernateUtil.getSession();
			Object object = s.get(clazz, id);
			return object;
		} finally {
			if (s != null)
				s.close();
		}
	}
	
	
	private HibernateUtil() {
	}

	static {
		Configuration cfg = new Configuration();
		cfg.configure();// cfg.configure("hibernate.cfg.xml");
		sessionFactory = cfg.buildSessionFactory();
	}

}
