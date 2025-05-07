package com.event1.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity		// Marks this class as a database entity 
public class Participants {

	@Id		// Primary key for the Participants table
	private String P_Id;

	// Participant details
	private String P_Name;
	private String P_Contact;
	private String P_Status;
	private String P_Performance;

	@ManyToOne		// Many participants can be linked to one event
	private Event event;

	// Getters and Setters
	public String getP_Id() {
		return P_Id;
	}

	public void setP_Id(String p_Id) {
		P_Id = p_Id;
	}

	public String getP_Name() {
		return P_Name;
	}

	public void setP_Name(String p_Name) {
		P_Name = p_Name;
	}

	public String getP_Contact() {
		return P_Contact;
	}

	public void setP_Contact(String p_Contact) {
		P_Contact = p_Contact;
	}

	public String getP_Status() {
		return P_Status;
	}

	public void setP_Status(String p_Status) {
		P_Status = p_Status;
	}

	public String getP_Performance() {
		return P_Performance;
	}

	public void setP_Performance(String p_Performance) {
		P_Performance = p_Performance;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	// Shows participants information in a readable format
	@Override
	public String toString() {
		return "Participants [P_Id=" + P_Id + ", P_Name=" + P_Name + ", P_Contact=" + P_Contact + ", P_Status=" 
				+ P_Status + ", P_Performance=" + P_Performance + ", Event=" + event.getE_title() + "]";
	}
}
