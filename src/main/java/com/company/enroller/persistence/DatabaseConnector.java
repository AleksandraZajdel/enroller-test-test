package com.company.enroller.persistence;

import org.hibernate.Session;

import com.company.enroller.model.Participant;

public class DatabaseConnector {

	protected static DatabaseConnector instance = null;

	public static DatabaseConnector getInstance() {
		if (instance == null) {
			instance = new DatabaseConnector();
		}
		return instance;
	}

	private org.hibernate.classic.Session session;

	private DatabaseConnector() {
		session = HibernateUtil.getSessionFactory().openSession();
	}

	public void teardown() {
		session.close();
		HibernateUtil.shutdown();
		instance = null;
	}

	public org.hibernate.classic.Session getSession() {
		return session;
	}

	public Participant get(Class<Participant> class1, String login) {
		return null;
	}

}
