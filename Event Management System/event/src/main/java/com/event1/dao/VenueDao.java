package com.event1.dao;

import com.event1.entity.Venue;
import java.util.List;

public interface VenueDao {

	// Method to save a new venue to the database
	void createVenue(Venue venue);

	// Method to get one venue by its ID
	Venue getVenue(String venueId);

	// Method to get all venues from the database
	List<Venue> getAllVenues();

	// Method to update an existing venue's details
	void updateVenue(Venue venue);

	// Method to delete a venue using its ID
	void deleteVenue(String venueId);

	// Get list of venues based on their owner
	List<Venue> getVenuesByOwner(String owner);

	// Get list of venues based on their address
	List<Venue> getVenuesByAddress(String address);
}
