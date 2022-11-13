package it.giuseppetripiciano.project.dao.factories;

import javax.sql.DataSource;

import it.giuseppetripiciano.project.dao.implementations.AdminDAOImpl;;

public class AdminDAOFactory {
	
	private DataSource ds;
	
	public AdminDAOFactory(DataSource ds) {
		this.ds = ds;
	}
	
	public AdminDAOImpl getAdminDAO() {
		return new AdminDAOImpl(ds);
	}
}