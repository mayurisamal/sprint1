package com.event1.Service;

import com.event1.entity.Event;
import java.util.List;

public interface EventService {

	// Method to create a new event
	void createEvent(Event event);

	// Method to retrieve an event by its ID
	Event getEvent(String eventId);

	// Method to retrieve all events
	List<Event> getAllEvents();

	// Method to update an existing event
	void updateEvent(Event event);

	// Method to delete an event by its ID
	void deleteEvent(String eventId);

	// Method to retrieve events by their type 
	List<Event> getEventsByType(String eventType);

	// Method to retrieve events by a specific date
	List<Event> getEventsByDate(String date);
	
	
}
