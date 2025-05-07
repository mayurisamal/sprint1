package com.event1.dao;

import com.event1.entity.Event;
import java.util.List;

public interface EventDao {

	// Method to save a new event to the database
	void createEvent(Event event);

	// Method to get one event by its ID
	Event getEvent(String eventId);

	// Method to get all events from the database
	List<Event> getAllEvents();

	// Method to update an existing event
	void updateEvent(Event event);

	// Method to delete an event by its ID
	void deleteEvent(String eventId);

	// Get list of events that match a specific type
	List<Event> getEventsByType(String eventType);

	// Get list of events happening on a specific date
	List<Event> getEventsByDate(String date);
}
