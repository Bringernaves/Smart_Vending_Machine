package it.giuseppetripiciano.project.dao.factories;

import javax.sql.DataSource;

import it.giuseppetripiciano.project.dao.implementations.CustomerDAOImpl;

public class CustomerDAOFactory {
	
	private DataSource ds;
	
	public CustomerDAOFactory(DataSource ds) {
		this.ds = ds;
	}
	public CustomerDAOImpl getCustomerDAO() {
		return new CustomerDAOImpl(ds);
	}
}