package com.company.enroller.persistence;

import java.util.Collection;

import javax.transaction.Transaction;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import com.company.enroller.model.Participant;

@Component("participantService")
public class ParticipantService {

	Session session;

	public ParticipantService() {
		session = (Session) DatabaseConnector.getInstance().getSession();
	}

	public Collection<Participant> getAll() {
		return session.createCriteria(Participant.class).list();
	}

	public Participant findByLogin(String login) {
		return (Participant) session.get(Participant.class, login);
		
	}

	public void add(Participant participant) {
		org.hibernate.Transaction transaction = session.beginTransaction();
		session.save(participant);
		transaction.commit();		
	}

	public void delete(Participant participant) {
		org.hibernate.Transaction transaction = session.beginTransaction();
		session.delete(participant);
		transaction.commit();		
	}
	
	public void update(Participant participant) {
		org.hibernate.Transaction transaction = session.beginTransaction();
		session.merge(participant);
		transaction.commit();		
	}
		
}
