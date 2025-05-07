package com.event1.daoImpl;

import com.event1.dao.VenueDao;
import com.event1.entity.Venue;
import com.event1.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Scanner;

public class VenueDaoImpl implements VenueDao {

	// Method to save a new venue to the database
	@Override
	public void createVenue(Venue venue) {
		Transaction tx = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			tx = session.beginTransaction();
			session.save(venue);  // Save the venue entity to the database
			tx.commit();
			System.out.println("Venue created successfully!");
		} catch (Exception e) {
			if (tx != null) tx.rollback();  // Rollback transaction if an error occurs
			e.printStackTrace();
		}
	}

	// Method to retrieve a venue by its ID
	@Override
	public Venue getVenue(String venueId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Venue venue = session.get(Venue.class, venueId);  // Get the venue entity by ID
			if (venue != null) {
				return venue;  // Return the venue if found
			} else {
				System.out.println("Venue not found!");
				return null;
			}
		}
	}

	// Method to retrieve all venues from the database
	@Override
	public List<Venue> getAllVenues() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM Venue", Venue.class).list();  // Return a list of all venues
		}
	}

	// Method to update an existing venue in the database
	@Override
	public void updateVenue(Venue venue) {
		Transaction tx = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			tx = session.beginTransaction();
			session.update(venue);  // Update the venue entity
			tx.commit();
			System.out.println("Venue updated successfully!");
		} catch (Exception e) {
			if (tx != null) tx.rollback();  // Rollback transaction in case of an error
			e.printStackTrace();
		}
	}

	// Method to delete a venue by its ID
	@Override
	public void deleteVenue(String venueId) {
	    Transaction tx = null;
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        tx = session.beginTransaction();
	        Venue venue = session.get(Venue.class, venueId);
	        if (venue != null) {
	            session.delete(venue);
	        }
	        tx.commit();
	    } catch (Exception e) {
	        if (tx != null) {
	            tx.rollback();
	        }
	        throw e; // Re-throw the exception to handle it in the service layer
	    }
	}
	// Method to retrieve venues based on the owner's name
	@Override
	public List<Venue> getVenuesByOwner(String owner) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM Venue WHERE V_Owner = :owner", Venue.class)  // Query venues by owner
					.setParameter("owner", owner)
					.list();
		}
	}

	// Method to retrieve venues based on the address
	@Override
	public List<Venue> getVenuesByAddress(String address) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM Venue WHERE V_Address = :address", Venue.class)  // Query venues by address
					.setParameter("address", address)
					.list();
		}
	}
}
