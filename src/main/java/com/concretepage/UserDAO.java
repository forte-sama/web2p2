package com.concretepage;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class UserDAO {
    private static Session newSession() {
        return HibernateUtil.getSessionFactory().openSession();
    }

	public static void save(User user) {
        Session session = newSession();

		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		session.close();
	}

    public static Long getId () {
        Session session = newSession();

		String hql = "select max(user.id) from User user";
		Query query = session.createQuery(hql);

        List<Integer> results = query.list();
		Long userId = 1L;

        if (results.get(0) != null ) {
        	userId = results.get(0) + 1L;
        }

        return userId;
	}
}
