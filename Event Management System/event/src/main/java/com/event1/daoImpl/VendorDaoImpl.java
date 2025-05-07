package com.event1.daoImpl;

import com.event1.dao.VendorDao;
import com.event1.entity.Vendor;
import com.event1.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Scanner;

public class VendorDaoImpl implements VendorDao {

	// Method to save a new vendor to the database
	@Override
	public void createVendor(Vendor vendor) {
		Transaction tx = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			tx = session.beginTransaction();
			session.save(vendor);
			tx.commit();
			System.out.println("Vendor created successfully!");
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		}
	}

	// Method to get a vendor by their ID
	@Override
	public Vendor getVendor(String vendorId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Vendor vendor = session.get(Vendor.class, vendorId);
			if (vendor != null) {
				return vendor;
			} else {
				System.out.println("Vendor not found!");
				return null;
			}
		}
	}

	// Method to get all vendors from the database
	@Override
	public List<Vendor> getAllVendors() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM Vendor", Vendor.class).list();
		}
	}

	// Method to update an existing vendor's details
	@Override
	public void updateVendor(Vendor vendor) {
		Transaction tx = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			tx = session.beginTransaction();
			session.update(vendor);
			tx.commit();
			System.out.println("Vendor updated successfully!");
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		}
	}

	// Method to delete a vendor using their ID
	@Override
	public void deleteVendor(String vendorId) {
		Transaction tx = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Vendor vendor = session.get(Vendor.class, vendorId);
			if (vendor != null) {
				System.out.println("Are you sure you want to delete this vendor? (yes/no)");
				Scanner sc = new Scanner(System.in);
				String confirmation = sc.nextLine();
				if ("yes".equalsIgnoreCase(confirmation)) {
					tx = session.beginTransaction();
					session.delete(vendor);
					tx.commit();
					System.out.println("Vendor deleted successfully!");
				} else {
					System.out.println("Deletion cancelled.");
				}
			} else {
				System.out.println("Vendor not found!");
			}
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		}
	}

	// Method to get a list of vendors based on a fee range
	@Override
	public List<Vendor> getVendorsByFeeRange(double minFee, double maxFee) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM Vendor WHERE Vend_Fee BETWEEN :minFee AND :maxFee", Vendor.class)
					.setParameter("minFee", minFee)
					.setParameter("maxFee", maxFee)
					.list();
		}
	}
}
