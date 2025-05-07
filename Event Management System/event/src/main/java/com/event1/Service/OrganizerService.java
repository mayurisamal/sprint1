package com.event1.Service;

import com.event1.entity.Organizer;
import java.util.List;

public interface OrganizerService {

	// Method to create a new organizer
	void createOrganizer(Organizer organizer);

	// Method to retrieve an organizer by their ID
	Organizer getOrganizer(String organizerId);

	// Method to retrieve all organizers
	List<Organizer> getAllOrganizers();

	// Method to update an existing organizer's information
	void updateOrganizer(Organizer organizer);

	// Method to delete an organizer by their ID
	void deleteOrganizer(String organizerId);

	// Method to retrieve organizers by their role 
	List<Organizer> getOrganizersByRole(String role);

	// Method to retrieve organizers by their email address
	List<Organizer> getOrganizersByEmail(String email);
}
