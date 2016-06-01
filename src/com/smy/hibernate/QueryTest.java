package com.smy.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.smy.hibernate.domain.User;

public class QueryTest {

	public static void main(String[] args) {
		User user = new User();
		user.setBirthday(new Date());
		user.setId(1);
		user.setName("smy");
		HibernateUtil.add(user);

		query(user.getName());
	}
	static void query(String name){
		Session s = null;
		try {
			s = HibernateUtil.getSession();
			String hql = "from User as user where user.name = ?";
			Query query = s.createQuery(hql);
			query.setString(0, name);
			Object obj = query.uniqueResult();
			System.out.println("obi" + obj);
			List<User> list = query.list();//rs.executeQuery();
			for(User user : list){
				System.out.println(user.getName());
			}
		} finally {
			if (s != null)
				s.close();
		}
	}

}
