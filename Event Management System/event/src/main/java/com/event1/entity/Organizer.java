package com.event1.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity				// Marks this class as a table in the database
public class Organizer {

	@Id				// Primary key for Organizer table
	private String O_id;

	// Organizer details
	private String O_Name;
	private String O_Address;
	private String O_Email;
	private String O_Role;

	// Getters and Setters
	public String getO_id() {
		return O_id;
	}

	public void setO_id(String o_id) {
		O_id = o_id;
	}

	public String getO_Name() {
		return O_Name;
	}

	public void setO_Name(String o_Name) {
		O_Name = o_Name;
	}

	public String getO_Address() {
		return O_Address;
	}

	public void setO_Address(String o_Address) {
		O_Address = o_Address;
	}

	public String getO_Email() {
		return O_Email;
	}

	public void setO_Email(String o_Email) {
		O_Email = o_Email;
	}

	public String getO_Role() {
		return O_Role;
	}

	public void setO_Role(String o_Role) {
		O_Role = o_Role;
	}

	// Shows organizer information in a readable format
	@Override
	public String toString() {
		return "Organizer [O_id=" + O_id + ", O_Name=" + O_Name + ", O_Address=" + O_Address + ", O_Email=" 
				+ O_Email + ", O_Role=" + O_Role + "]";
	}
}
