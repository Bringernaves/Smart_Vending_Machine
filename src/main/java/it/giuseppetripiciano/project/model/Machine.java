package it.giuseppetripiciano.project.model;

import java.io.Serializable;
import java.util.List;

public class Machine extends Account implements Serializable{
	private static final long serialVersionUID = 90L;
	
	// status >0 connected, =0 disconnected, =-1 undefined, =-2 error
	
	private List<List<String>> stock;
	
	public Machine() {
		super();
	}
	
	public void setStock(List<List<String>> stock) {
		this.stock = stock;
	}
	
	public List<List<String>> getStock(){
		return this.stock;
	}
}
