package com.event1.dao;

import com.event1.entity.Organizer;
import java.util.List;

public interface OrganizerDao {

	// Method to save a new organizer to the database
	void createOrganizer(Organizer organizer);

	// Method to get one organizer by their ID
	Organizer getOrganizer(String organizerId);

	// Method to get all organizers from the database
	List<Organizer> getAllOrganizers();

	// Method to update an existing organizer's details
	void updateOrganizer(Organizer organizer);

	// Method to delete an organizer using their ID
	void deleteOrganizer(String organizerId);

	// Get list of organizers who have a specific role 
	List<Organizer> getOrganizersByRole(String role);

	// Get list of organizers using their email address
	List<Organizer> getOrganizersByEmail(String email);
}
