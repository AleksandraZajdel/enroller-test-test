package com.company.enroller.persistence;

import java.util.Collection;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.company.enroller.model.Meeting;
import com.company.enroller.model.Participant;

@Component("meetingService")
public class MeetingService {

	Session session;

	public MeetingService() {
		session = (Session) DatabaseConnector.getInstance().getSession();
	}

	public Collection<Meeting> getAll() {
		String hql = "FROM Meeting";
		Query query = session.createQuery(hql);
		return query.list();
	}

	public Meeting findById(Long id) {
		return (Meeting) session.get(Meeting.class, id);
	}

	public void add(Meeting meeting) {
		org.hibernate.Transaction transaction = session.beginTransaction();
		session.save(meeting);
		transaction.commit();
	}

	public void addParticipant(Long id, String login) {

		Meeting meeting = this.findById(id);
		Participant participant = (Participant) DatabaseConnector.getInstance().getSession().get(Participant.class,
				login);

		meeting.addParticipant(participant);
		Transaction transaction = session.beginTransaction();
		session.save(meeting);
		transaction.commit();

	}

	public void delete(Meeting meeting) {
		org.hibernate.Transaction transaction = session.beginTransaction();
		session.delete(meeting);
		transaction.commit();

	}

	public void removeParticipants(long id) {
		Meeting meeting = this.findById(id);
		Collection<Participant> participants = meeting.getParticipants();
		participants.clear();
		Transaction transaction = session.beginTransaction();
		session.save(meeting);
		transaction.commit();
	}

	public void update(Meeting meeting) {
		org.hibernate.Transaction transaction = session.beginTransaction();
		session.merge(meeting);
		transaction.commit();

	}

	public void removeParticipant(long id, Participant participant) {
		org.hibernate.Transaction transaction = session.beginTransaction();
		session.delete(participant);
		transaction.commit();
	}
}
