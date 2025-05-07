package com.event1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.event1.entity.*;
import com.event1.util.HibernateUtil;

public class App {

	public static void main(String[] args) {

		// Get SessionFactory
		SessionFactory factory = HibernateUtil.getSessionFactory();

		// Create a session (connection)
		Session session = factory.openSession();

		// Begin a transaction
		Transaction tx = session.beginTransaction();



		// Commit the transaction
		tx.commit();

		// Close session and factory
		session.close();
		factory.close();

		System.out.println("✅ Hibernate setup complete! Ready to perform operations.");
	}
}
