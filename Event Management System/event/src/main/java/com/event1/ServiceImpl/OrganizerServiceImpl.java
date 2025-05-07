package com.event1.ServiceImpl;

import java.util.List;

import com.event1.Service.OrganizerService;
import com.event1.dao.OrganizerDao;
import com.event1.daoImpl.OrganizerDaoImpl;
import com.event1.entity.Organizer;

public class OrganizerServiceImpl implements OrganizerService {

	// Create an instance of OrganizerDao to interact with the database
	OrganizerDao organizerDao = new OrganizerDaoImpl();

	// Method to create a new organizer
	@Override
	public void createOrganizer(Organizer organizer) {
		organizerDao.createOrganizer(organizer);  // Call the DAO to save the organizer
	}

	// Method to retrieve an organizer by their ID
	@Override
	public Organizer getOrganizer(String organizerId) {
		return organizerDao.getOrganizer(organizerId);  // Call the DAO to get the organizer
	}

	// Method to retrieve all organizers
	@Override
	public List<Organizer> getAllOrganizers() {
		return organizerDao.getAllOrganizers();  // Call the DAO to get all organizers
	}

	// Method to update an existing organizer's information
	@Override
	public void updateOrganizer(Organizer organizer) {
		organizerDao.updateOrganizer(organizer);  // Call the DAO to update the organizer
	}

	// Method to delete an organizer by their ID
	@Override
	public void deleteOrganizer(String organizerId) {
		organizerDao.deleteOrganizer(organizerId);  // Call the DAO to delete the organizer
	}

	// Method to retrieve organizers by their role (e.g., Manager, Event Planner)
	@Override
	public List<Organizer> getOrganizersByRole(String role) {
		return organizerDao.getOrganizersByRole(role);  // Call the DAO to get organizers by role
	}

	// Method to retrieve organizers by their email address
	@Override
	public List<Organizer> getOrganizersByEmail(String email) {
		return organizerDao.getOrganizersByEmail(email);  // Call the DAO to get organizers by email
	}
}
