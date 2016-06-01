package com.smy.hibernate;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.smy.hibernate.domain.User;

public class Base {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setBirthday(new Date());
		user.setId(1);
		user.setName("smy");
		addUser(user);
		System.out.println("id : " + user.getId());

		User u = getUser(user.getId());
		System.out.println("name : " + u.getName());

		System.out.println("end");
	}

	static User getUser(int id) {
		Session s = null;
		try {
			s = HibernateUtil.getSession();
			Class clazz = User.class;
//			User user = (User) s.get(clazz, id);
			User user = (User)s.load(clazz, id);
			System.out.println("name : " + user.getName());
			System.out.println("name1 : " + user.getClass().getName());
			return user;

		} finally {
			if (s != null)
				s.close();
		}
	}

	static void addUser(User user) {
		Session s = null;
		Transaction tx = null;
		try {
			s = HibernateUtil.getSession();
			tx = s.beginTransaction();
			s.save(user);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		} finally {
			if (s != null)
				s.close();
		}
	}

}
