package com.event1.Service;

import com.event1.entity.Venue;
import java.util.List;

public interface VenueService {

	// Create a new venue
	void createVenue(Venue venue);

	// Retrieve a venue by its ID
	Venue getVenue(String venueId);

	// Retrieve a list of all venues
	List<Venue> getAllVenues();

	// Update an existing venue's information
	void updateVenue(Venue venue);

	// Delete a venue by its ID
	void deleteVenue(String venueId);

	// Retrieve a list of venues by the owner's name
	List<Venue> getVenuesByOwner(String owner);

	// Retrieve a list of venues by their address
	List<Venue> getVenuesByAddress(String address);
	
	
}
