package com.event1.ServiceImpl;

import java.util.List;

import com.event1.Service.EventService;
import com.event1.dao.EventDao;
import com.event1.daoImpl.EventDaoImpl;
import com.event1.entity.Event;

public class EventServiceImpl implements EventService {

	// Create an instance of EventDao to interact with the database
	EventDao eventDao = new EventDaoImpl();

	// Method to create a new event
	@Override
	public void createEvent(Event event) {
		eventDao.createEvent(event);  // Call the DAO to save the event
	}

	// Method to retrieve an event by its ID
	@Override
	public Event getEvent(String eventId) {
		return eventDao.getEvent(eventId);  // Call the DAO to get the event
	}

	// Method to retrieve all events
	@Override
	public List<Event> getAllEvents() {
		return eventDao.getAllEvents();  // Call the DAO to get all events
	}

	// Method to update an existing event
	@Override
	public void updateEvent(Event event) {
		eventDao.updateEvent(event);  // Call the DAO to update the event
	}

	// Method to delete an event by its ID
	@Override
	public void deleteEvent(String eventId) {
		eventDao.deleteEvent(eventId);  // Call the DAO to delete the event
	}

	// Method to get events by type (e.g., conference, seminar)
	@Override
	public List<Event> getEventsByType(String eventType) {
		return eventDao.getEventsByType(eventType);  // Call the DAO to get events by type
	}

	// Method to get events by their date
	@Override
	public List<Event> getEventsByDate(String date) {
		return eventDao.getEventsByDate(date);  // Call the DAO to get events by date
	}
}
