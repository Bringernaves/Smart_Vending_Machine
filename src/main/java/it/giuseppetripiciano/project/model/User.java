package it.giuseppetripiciano.project.model;

import java.io.Serializable;

public class User extends Account implements Serializable {
	private static final long serialVersionUID = 80L;
	
	// status >0 connected, =0 disconnected, =-1 undefined
	
	private byte type; // =0 customer, =1 technician, =2 admin
	private String firstname;
	private String lastname;
	private String email;
	private String credit;
	
	public User() {
		super();
		this.type = -1;
	}
	
	public void setType(byte type) {
		this.type = type;
	}
	
	public byte getType() {
		return this.type;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getFirstname() {
		return this.firstname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getLastname() {
		return this.lastname;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setCredit(String credit) {
		this.credit = credit;
	}
	
	public String getCredit() {
		return this.credit;
	}
}
