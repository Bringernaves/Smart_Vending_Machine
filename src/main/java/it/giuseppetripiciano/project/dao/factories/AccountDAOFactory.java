package it.giuseppetripiciano.project.dao.factories;

import javax.sql.DataSource;

import it.giuseppetripiciano.project.dao.implementations.AccountDAOImpl;

public class AccountDAOFactory {
	
	private DataSource ds;
	
	public AccountDAOFactory(DataSource ds) {
		this.ds = ds;
	}
	
	public AccountDAOImpl getAccountDAO() {
		return new AccountDAOImpl(ds);
	}
}
