package it.giuseppetripiciano.project.model;

import java.io.Serializable;

public abstract class Account implements Serializable{
	private static final long serialVersionUID = 70L;
	
	private int id;
	private int status;
	
	public Account() {
		this.id = -1;
		this.status = -1;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getStatus() {
		return this.status;
	}
}
