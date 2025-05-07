package com.event1.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.event1.entity.Event;
import com.event1.entity.Venue;
import com.event1.entity.Organizer;
import com.event1.entity.Participants;
import com.event1.entity.Vendor;

public class HibernateUtil {

	// Static SessionFactory to be used throughout the application
	private final static SessionFactory sessionFactory = buildSessionFactory();

	// Method to build and configure the SessionFactory
	private static SessionFactory buildSessionFactory() {
		try {
			// Configuring Hibernate with the hibernate.cfg.xml file and adding annotated entity classes
			return new Configuration().configure("hibernate.cfg.xml")
					.addAnnotatedClass(Event.class)       // Add Event entity
					.addAnnotatedClass(Venue.class)       // Add Venue entity
					.addAnnotatedClass(Organizer.class)   // Add Organizer entity
					.addAnnotatedClass(Participants.class) // Add Participants entity
					.addAnnotatedClass(Vendor.class)      // Add Vendor entity
					.buildSessionFactory();               // Build the SessionFactory
		} catch (Throwable e) {
			// Print error message if SessionFactory creation fails
			System.err.println("Initial SessionFactory creation failed: " + e);
			throw new ExceptionInInitializerError(e);  // Throw exception to halt initialization
		}
	}

	// Method to retrieve the SessionFactory
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	// Method to open a new session from the SessionFactory
	public static Session getSession() {
		return getSessionFactory().openSession(); // Open a new session
	}

	// Method to close the SessionFactory, shutting down the Hibernate utility
	public static void shutdown() {
		getSessionFactory().close(); // Close the SessionFactory
	}
}
