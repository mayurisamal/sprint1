package com.event1.ServiceImpl;

import java.util.List;

import com.event1.Service.VenueService;
import com.event1.dao.VenueDao;
import com.event1.daoImpl.VenueDaoImpl;
import com.event1.entity.Venue;

public class VenueServiceImpl implements VenueService {

	// Create an instance of VenueDao to interact with the database
	VenueDao venueDao = new VenueDaoImpl();

	// Method to create a new venue
	@Override
	public void createVenue(Venue venue) {
		venueDao.createVenue(venue);  // Call the DAO to save the venue in the database
	}

	// Method to retrieve a venue by its ID
	@Override
	public Venue getVenue(String venueId) {
		return venueDao.getVenue(venueId);  // Call the DAO to get the venue by its ID
	}

	// Method to retrieve all venues
	@Override
	public List<Venue> getAllVenues() {
		return venueDao.getAllVenues();  // Call the DAO to get a list of all venues
	}

	// Method to update an existing venue's information
	@Override
	public void updateVenue(Venue venue) {
		venueDao.updateVenue(venue);  // Call the DAO to update the venue's details in the database
	}

	// Method to delete a venue by its ID
	@Override
	public void deleteVenue(String venueId) {
		venueDao.deleteVenue(venueId);  // Call the DAO to delete the venue from the database
	}

	// Method to retrieve venues by their owner
	@Override
	public List<Venue> getVenuesByOwner(String owner) {
		return venueDao.getVenuesByOwner(owner);  // Call the DAO to get venues by their owner's name
	}

	// Method to retrieve venues by their address
	@Override
	public List<Venue> getVenuesByAddress(String address) {
		return venueDao.getVenuesByAddress(address);  // Call the DAO to get venues by their address
	}
}
