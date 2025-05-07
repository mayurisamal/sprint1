package com.event1.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity		// This tells Hibernate to treat this class as a table in the database
public class Event {

	@Id    // Primary key of the Event table
	private String E_id;

	// Event details
	private String E_title;
	private String E_Type;
	private String E_Date;
	private String E_Time;

	@ManyToOne		// Many events can be held at the same venue
	private Venue venue;

	@ManyToOne		// Many events can have the same organizer
	private Organizer organizer;

	// Getters and Setters
	public String getE_id() {
		return E_id;
	}

	public void setE_id(String e_id) {
		E_id = e_id;
	}

	public String getE_title() {
		return E_title;
	}

	public void setE_title(String e_title) {
		E_title = e_title;
	}

	public String getE_Type() {
		return E_Type;
	}

	public void setE_Type(String e_Type) {
		E_Type = e_Type;
	}

	public String getE_Date() {
		return E_Date;
	}

	public void setE_Date(String e_Date) {
		E_Date = e_Date;
	}

	public String getE_Time() {
		return E_Time;
	}

	public void setE_Time(String e_Time) {
		E_Time = e_Time;
	}

	public Venue getVenue() {
		return venue;
	}

	public void setVenue(Venue venue) {
		this.venue = venue;
	}

	public Organizer getOrganizer() {
		return organizer;
	}

	public void setOrganizer(Organizer organizer) {
		this.organizer = organizer;
	}
	// Shows event information in a readable format
	@Override
	public String toString() {
		return "Event [E_id=" + E_id + ", E_title=" + E_title + ", E_Type=" + E_Type + ", E_Date=" + E_Date 
				+ ", E_Time=" + E_Time + ", Venue=" + venue.getV_Name() + ", Organizer=" + organizer.getO_Name() + "]";
	}
}

