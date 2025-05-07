package com.event1.daoImpl;

import com.event1.dao.OrganizerDao;
import com.event1.entity.Organizer;
import com.event1.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Scanner;

public class OrganizerDaoImpl implements OrganizerDao {

	// Method to save a new organizer to the database
	@Override
	public void createOrganizer(Organizer organizer) {
		Transaction tx = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			tx = session.beginTransaction();
			session.save(organizer);
			tx.commit();
			System.out.println("Organizer created successfully!");
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		}
	}

	// Method to get one organizer by their ID
	@Override
	public Organizer getOrganizer(String organizerId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Organizer organizer = session.get(Organizer.class, organizerId);
			if (organizer != null) {
				return organizer;
			} else {
				System.out.println("Organizer not found!");
				return null;
			}
		}
	}

	// Method to get all organizers from the database
	@Override
	public List<Organizer> getAllOrganizers() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM Organizer", Organizer.class).list();
		}
	}

	// Method to update an existing organizer's details
	@Override
	public void updateOrganizer(Organizer organizer) {
		Transaction tx = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			tx = session.beginTransaction();
			session.update(organizer);
			tx.commit();
			System.out.println("Organizer updated successfully!");
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		}
	}

	// Method to delete an organizer using their ID
	@Override
	public void deleteOrganizer(String organizerId) {
	    Transaction tx = null;
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        tx = session.beginTransaction();
	        Organizer organizer = session.get(Organizer.class, organizerId);
	        if (organizer != null) {
	            session.delete(organizer);
	        }
	        tx.commit();
	    } catch (Exception e) {
	        if (tx != null) {
	            tx.rollback();
	        }
	        throw e; // Re-throw the exception to handle it in the service layer
	    }
	}
	// Get list of organizers based on their role
	@Override
	public List<Organizer> getOrganizersByRole(String role) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM Organizer WHERE O_Role = :role", Organizer.class)
					.setParameter("role", role)
					.list();
		}
	}

	// Get list of organizers based on their email address
	@Override
	public List<Organizer> getOrganizersByEmail(String email) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM Organizer WHERE O_Email = :email", Organizer.class)
					.setParameter("email", email)
					.list();
		}
	}
}
