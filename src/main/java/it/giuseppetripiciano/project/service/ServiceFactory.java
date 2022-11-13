package it.giuseppetripiciano.project.service;

import javax.sql.DataSource;

import it.giuseppetripiciano.project.service.implementations.*;

public class ServiceFactory {
	
	private DataSource ds;
	
	public ServiceFactory (DataSource ds) {
		this.ds = ds;
	}
	
	public AccountServiceImpl getAccountService() {
		return new AccountServiceImpl(ds);
	}
	
	public CustomerServiceImpl getCustomerService() {
		return new CustomerServiceImpl(ds);
	}
	
	public TechnicianServiceImpl getTechnicianService() {
		return new TechnicianServiceImpl(ds);
	}
	
	public AdminServiceImpl getAdminService() {
		return new AdminServiceImpl(ds);
	}
	
	public MachineServiceImpl getMachineService() {
		return new MachineServiceImpl(ds);
	}
}
