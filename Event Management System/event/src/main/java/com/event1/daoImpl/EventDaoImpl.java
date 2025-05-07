package com.event1.daoImpl;

import com.event1.dao.EventDao;
import com.event1.entity.Event;
import com.event1.entity.Participants;
import com.event1.entity.Vendor;
import com.event1.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Scanner;

public class EventDaoImpl implements EventDao {

	// Method to save a new event to the database
	@Override
	public void createEvent(Event event) {
		Transaction tx = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			tx = session.beginTransaction();
			session.save(event);
			tx.commit();
			System.out.println("Event created successfully!");
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		}
	}

	// Method to get an event by its ID
	@Override
	public Event getEvent(String eventId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Event event = session.get(Event.class, eventId);
			if (event != null) {
				return event;
			} else {
				System.out.println("Event not found!");
				return null;
			}
		}
	}

	// Method to get all events from the database
	@Override
	public List<Event> getAllEvents() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM Event", Event.class).list();
		}
	}

	// Method to update an existing event's details
	@Override
	public void updateEvent(Event event) {
		Transaction tx = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			tx = session.beginTransaction();
			session.update(event);
			tx.commit();
			System.out.println("Event updated successfully!");
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		}
	}

	// Method to delete an event using its ID
	@Override
	public void deleteEvent(String eventId) {
	    Transaction tx = null;
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        tx = session.beginTransaction();
	        
	        // First delete all dependent vendors
	        Query<Vendor> vendorQuery = session.createQuery("FROM Vendor WHERE event.E_id = :eventId", Vendor.class);
	        vendorQuery.setParameter("eventId", eventId);
	        List<Vendor> vendors = vendorQuery.getResultList();
	        for (Vendor vendor : vendors) {
	            session.delete(vendor);
	        }

	        // Then delete all dependent participants
	        Query<Participants> participantQuery = session.createQuery("FROM Participants WHERE event.E_id = :eventId", Participants.class);
	        participantQuery.setParameter("eventId", eventId);
	        List<Participants> participants = participantQuery.getResultList();
	        for (Participants participant : participants) {
	            session.delete(participant);
	        }

	        // Finally delete the event
	        Event event = session.get(Event.class, eventId);
	        if (event != null) {
	            session.delete(event);
	        }

	        tx.commit();
	    } catch (Exception e) {
	        if (tx != null) {
	            tx.rollback();
	        }
	        throw e;
	    }
	}
	// Get list of events by their type
	@Override
	public List<Event> getEventsByType(String eventType) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM Event WHERE E_Type = :eventType", Event.class)
					.setParameter("eventType", eventType)
					.list();
		}
	}

	// Get list of events by their date
	@Override
	public List<Event> getEventsByDate(String date) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM Event WHERE E_Date = :date", Event.class)
					.setParameter("date", date)
					.list();
		}
	}
}
