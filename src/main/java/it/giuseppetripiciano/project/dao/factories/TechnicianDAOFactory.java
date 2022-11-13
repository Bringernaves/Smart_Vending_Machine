package it.giuseppetripiciano.project.dao.factories;

import javax.sql.DataSource;

import it.giuseppetripiciano.project.dao.implementations.TechnicianDAOImpl;

public class TechnicianDAOFactory {
	
	private DataSource ds;
	
	public TechnicianDAOFactory(DataSource ds) {
		this.ds = ds;
	}
	public TechnicianDAOImpl getTechnicianDAO() {
		return new TechnicianDAOImpl(ds);
	}
}