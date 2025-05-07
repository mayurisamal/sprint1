package com.event1.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity				// This annotation marks the class as a database entity
public class Venue {
	
	@Id				// This is the primary key for the Venue table
	private String V_id;

	// Venue details
	private String V_Name;
	private String V_Address;
	private String V_Contact;
	private String V_Owner;

	// Getters and Setters
	public String getV_id() {
		return V_id;
	}

	public void setV_id(String v_id) {
		V_id = v_id;
	}

	public String getV_Name() {
		return V_Name;
	}

	public void setV_Name(String v_Name) {
		V_Name = v_Name;
	}

	public String getV_Address() {
		return V_Address;
	}

	public void setV_Address(String v_Address) {
		V_Address = v_Address;
	}

	public String getV_Contact() {
		return V_Contact;
	}

	public void setV_Contact(String v_Contact) {
		V_Contact = v_Contact;
	}

	public String getV_Owner() {
		return V_Owner;
	}

	public void setV_Owner(String v_Owner) {
		V_Owner = v_Owner;
	}

	// Shows venue information in a readable format
	@Override
	public String toString() {
		return "Venue [V_id=" + V_id + ", V_Name=" + V_Name + ", V_Address=" + V_Address + ", V_Contact=" + V_Contact 
				+ ", V_Owner=" + V_Owner + "]";
	}
}
