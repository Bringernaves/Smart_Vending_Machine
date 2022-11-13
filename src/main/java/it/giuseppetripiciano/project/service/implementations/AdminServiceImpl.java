package it.giuseppetripiciano.project.service.implementations;

import it.giuseppetripiciano.project.service.interfaces.AdminService;

import it.giuseppetripiciano.project.dao.interfaces.AdminDAO;

import java.util.regex.Pattern;

import javax.sql.DataSource;

import it.giuseppetripiciano.project.dao.factories.AdminDAOFactory;

import it.giuseppetripiciano.project.model.User;

import it.giuseppetripiciano.project.service.SHA;

public class AdminServiceImpl implements AdminService {
	
	private AdminDAOFactory adminDAOFactory;
	
	private AdminDAO adminDAO;
	
	public AdminServiceImpl(DataSource ds) {
		this.adminDAOFactory = new AdminDAOFactory(ds);
	}
	
    public byte registerTechnician(Object user, String firstname, String lastname, String email, String psw) {
    	byte registerStatus = -1;
    	
    	if (user instanceof User) {
	    	if (((User) user).getType() == 2) {
	    		try {
		    		Pattern email_ptrn = Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$");
		        	Pattern psw_ptrn = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$");
		        	
		        	boolean isEmailPtrn = email_ptrn.matcher(email).matches();
		        	boolean isPswPtrn = psw_ptrn.matcher(psw).matches();
		        	
		        	if (!isEmailPtrn || !isPswPtrn)
		        		return registerStatus;
		    		
		    		this.adminDAO = this.adminDAOFactory.getAdminDAO();
	    		
	    			registerStatus = this.adminDAO.registerTechnician(firstname, lastname, email, SHA.encrypt(psw));
	    			
	    			if (registerStatus == 1)
	    	    		System.out.println("Technician " + firstname + " " + lastname + " registered.");
	    		} catch (Exception e) {
	    			// error
	    			
	    			System.out.println(e.getMessage());
	    		}
	    	}
    	}
    	
    	return registerStatus;
    }
    
    public byte registerMachine(Object user, String psw) {
    	byte registerStatus = -1;
    	
    	if (user instanceof User) {
	    	if (((User) user).getType() == 2) {
	    		try {
		    		Pattern psw_ptrn = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$");
		    		
		    		boolean isPswPtrn = psw_ptrn.matcher(psw).matches();
		    		
		    		if (!isPswPtrn)
		    			return registerStatus;
		    		
		    		this.adminDAO = this.adminDAOFactory.getAdminDAO();
	    		
	    			registerStatus = this.adminDAO.registerMachine(SHA.encrypt(psw));
	    			
	    			if (registerStatus == 1)
	    	    		System.out.println("Machine registered.");
	    		} catch (Exception e) {
	    			// error
	    			
	    			System.out.println(e.getMessage());
	    		}
	    	}
    	}
    	
    	return registerStatus;
    }
}
