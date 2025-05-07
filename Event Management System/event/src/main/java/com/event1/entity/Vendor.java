package com.event1.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity		// This class will be stored as a table in the database
public class Vendor {

	@Id		// Primary key for Vendor table
	private String Vend_Id;

	// Vendor details
	private String Vend_Name;
	private String Vend_Contact;
	private Double Vend_Fee;
	private String Vend_Details;

	@ManyToOne		// Many vendors can be linked to one event
	private Event event;


	// Getters and Setters
	public String getVend_Id() {
		return Vend_Id;
	}

	public void setVend_Id(String vend_Id) {
		Vend_Id = vend_Id;
	}

	public String getVend_Name() {
		return Vend_Name;
	}

	public void setVend_Name(String vend_Name) {
		Vend_Name = vend_Name;
	}

	public String getVend_Contact() {
		return Vend_Contact;
	}

	public void setVend_Contact(String vend_Contact) {
		Vend_Contact = vend_Contact;
	}

	public Double getVend_Fee() {
		return Vend_Fee;
	}

	public void setVend_Fee(double d) {
		Vend_Fee = d;
	}

	public String getVend_Details() {
		return Vend_Details;
	}

	public void setVend_Details(String vend_Details) {
		Vend_Details = vend_Details;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}


	// Shows vendor information in a readable format
	@Override
	public String toString() {
		return "Vendor [Vend_Id=" + Vend_Id + ", Vend_Name=" + Vend_Name + ", Vend_Contact=" + Vend_Contact 
				+ ", Vend_Fee=" + Vend_Fee + ", Vend_Details=" + Vend_Details + ", Event=" + event.getE_title() + "]";
	}
}
