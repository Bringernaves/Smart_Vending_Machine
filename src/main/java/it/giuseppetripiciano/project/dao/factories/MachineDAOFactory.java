package it.giuseppetripiciano.project.dao.factories;

import javax.sql.DataSource;

import it.giuseppetripiciano.project.dao.implementations.MachineDAOImpl;

public class MachineDAOFactory {
	
	private DataSource ds;
	
	public MachineDAOFactory(DataSource ds) {
		this.ds = ds;
	}
	
	public MachineDAOImpl getMachineDAO() {
		return new MachineDAOImpl(ds);
	}
}