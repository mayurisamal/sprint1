package com.event1.daoImpl;

import com.event1.dao.ParticipantsDao;
import com.event1.entity.Participants;
import com.event1.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Scanner;

public class ParticipantsDaoImpl implements ParticipantsDao {

	// Method to save a new participant to the database
	@Override
	public void createParticipant(Participants participant) {
		Transaction tx = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			tx = session.beginTransaction();
			session.save(participant);
			tx.commit();
			System.out.println("Participant created successfully!");
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		}
	}

	// Method to get a participant by their ID
	@Override
	public Participants getParticipant(String participantId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Participants participant = session.get(Participants.class, participantId);
			if (participant != null) {
				return participant;
			} else {
				System.out.println("Participant not found!");
				return null;
			}
		}
	}

	// Method to get all participants from the database
	@Override
	public List<Participants> getAllParticipants() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM Participants", Participants.class).list();
		}
	}

	// Method to update an existing participant's details
	@Override
	public void updateParticipant(Participants participant) {
		Transaction tx = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			tx = session.beginTransaction();
			session.update(participant);
			tx.commit();
			System.out.println("Participant updated successfully!");
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		}
	}

	// Method to delete a participant using their ID
	@Override
	public void deleteParticipant(String participantId) {
		Transaction tx = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Participants participant = session.get(Participants.class, participantId);
			if (participant != null) {
				System.out.println("Are you sure you want to delete this participant? (yes/no)");
				Scanner sc = new Scanner(System.in);
				String confirmation = sc.nextLine();
				if ("yes".equalsIgnoreCase(confirmation)) {
					tx = session.beginTransaction();
					session.delete(participant);
					tx.commit();
					System.out.println("Participant deleted successfully!");
				} else {
					System.out.println("Deletion cancelled.");
				}
			} else {
				System.out.println("Participant not found!");
			}
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		}
	}

	// Get list of participants based on their status (e.g., active, inactive)
	@Override
	public List<Participants> getParticipantsByStatus(String status) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM Participants WHERE P_Status = :status", Participants.class)
					.setParameter("status", status)
					.list();
		}
	}
}
