package it.giuseppetripiciano.project.service.implementations;

import it.giuseppetripiciano.project.service.SHA;
import it.giuseppetripiciano.project.service.interfaces.CustomerService;

import it.giuseppetripiciano.project.dao.interfaces.CustomerDAO;

import it.giuseppetripiciano.project.dao.factories.CustomerDAOFactory;

import it.giuseppetripiciano.project.model.User;

import java.util.List;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.regex.Pattern;

import javax.sql.DataSource;

public class CustomerServiceImpl implements CustomerService {
	
	private CustomerDAOFactory customerDAOFactory;
	
	private CustomerDAO customerDAO;
	
	public CustomerServiceImpl(DataSource ds) {
		this.customerDAOFactory = new CustomerDAOFactory(ds);
	}
	
    public byte connect(Object user, int idMachine) {
    	byte connectionStatus = -1;
    	
    	if (user instanceof User) {
    		
				Pattern id_ptrn = Pattern.compile("^[1-9][0-9]*$");
				
				boolean isIdPtrn = id_ptrn.matcher(Integer.toString(idMachine)).matches();
	    			
	    		if (!isIdPtrn)
	    			return connectionStatus;
	        	
	        	this.customerDAO = this.customerDAOFactory.getCustomerDAO();
		    	
		    	int idUser = ((User) user).getId();
		    	int userType = ((User) user).getType();
		    	int userStatus = ((User) user).getStatus();
		    	
		    	if (userStatus!=0 && userStatus!=-1) {
		    		byte disconnectionStatus = this.customerDAO.disconnect(idUser, userStatus);
		    		
		    		if (disconnectionStatus == 1 || disconnectionStatus == 0) {
		    			((User) user).setStatus(0);
		    			
		    			if (disconnectionStatus == 1) {
			    			if (userType == 0)
			    				System.out.println("Customer with ID " + idUser + " disconnected from machine with ID " + userStatus + ".");
			    			else if (userType == 1)
			    				System.out.println("Technician with ID " + idUser + " disconnected from machine with ID " + userStatus + ".");
			    			else
			    				System.out.println("Admin with ID " + idUser + " disconnected from machine with ID " + userStatus + ".");
		    			}
		    		}
		    		else
		    			return disconnectionStatus;
		    	}
		    	
		    	connectionStatus = this.customerDAO.connect(idUser, idMachine);
		    	
		    	if (connectionStatus == 1) {
		    		((User) user).setStatus(idMachine);
		    		
		    		if (userType == 0)
	    				System.out.println("Customer with ID " + idUser + " connected to machine with ID " + idMachine + ".");
	    			else if (userType == 1)
	    				System.out.println("Technician with ID " + idUser + " connected to machine with ID " + idMachine + ".");
	    			else
	    				System.out.println("Admin with ID " + idUser + " connected to machine with ID " + idMachine + ".");
		    	}
    	}
    	
    	return connectionStatus;
    }
    
    public byte disconnect(Object user) {
    	byte disconnectionStatus = -1;
    	
    	if (user instanceof User) {  		
    		int userStatus = ((User) user).getStatus();
    		
    		if (userStatus > 0) {
	        	this.customerDAO = this.customerDAOFactory.getCustomerDAO();
		    	
		    	int idUser = ((User) user).getId();
		    	
				disconnectionStatus = this.customerDAO.disconnect(idUser, userStatus);
				
				if (disconnectionStatus == 1 || disconnectionStatus == 0) {
					int userType = ((User) user).getType();
					
					((User) user).setStatus(0);
					
					if (disconnectionStatus == 1) {
		    			if (userType == 0)
		    				System.out.println("Customer with ID " + idUser + " disconnected from machine with ID " + userStatus + ".");
		    			else if (userType == 1)
		    				System.out.println("Technician with ID " + idUser + " disconnected from machine with ID " + userStatus + ".");
		    			else
		    				System.out.println("Admin with ID " + idUser + " disconnected from machine with ID " + userStatus + ".");
	    			}
				}
    		}
    	}
    	
    	return disconnectionStatus;
    }
    
    public byte recharge(Object user, String addedCredit) {
    	byte rechargeStatus = -1;
    	
    	if (user instanceof User) {
    		try {
				Pattern credit_ptrn = Pattern.compile("^[0-9]{1,15}([.][0-9]{1,2})?$");
	    		
	    		boolean isCreditPtrn = credit_ptrn.matcher(addedCredit).matches();
			
	    		if (!isCreditPtrn)
	    			return rechargeStatus;
	        	
	        	this.customerDAO = this.customerDAOFactory.getCustomerDAO();
	    		
	    		int idUser = ((User) user).getId();
	    		
	    		BigDecimal old_credit = new BigDecimal(((User) user).getCredit()).setScale(2);
	    		
	    		BigDecimal new_credit = old_credit.add(new BigDecimal(addedCredit).setScale(2), new MathContext (15, RoundingMode.UNNECESSARY));
	    		
	    		rechargeStatus = this.customerDAO.recharge(idUser, new_credit.toString());
	    		
	    		if (rechargeStatus == 1) {
	    			((User) user).setCredit(new_credit.toString());
	    		}
    		} catch (Exception e) {
    			// error
    			
    			System.out.println(e.getMessage());
    		}
    	}
    	
    	return rechargeStatus;
    }
    
    public void checkCredit(Object user) {
    	if (user instanceof User) {
        	this.customerDAO = this.customerDAOFactory.getCustomerDAO();
        	
        	int idUser = ((User) user).getId();
        	
        	List<List<String>> checkCreditResult = this.customerDAO.checkCredit(idUser);
        	
        	if (checkCreditResult!=null) {
        		if (checkCreditResult.size() > 1) {
        			String credit = checkCreditResult.get(1).get(0);
        			((User) user).setCredit(credit);
        			
        		}		
        	}
    	}
    }
    
    public byte changeEmail(Object user, String psw, String email) {
    	byte changeEmailStatus = -1;
    	
    	if (user instanceof User) {
    		try {
	        	this.customerDAO = this.customerDAOFactory.getCustomerDAO();
	        	
	        	int idUser = ((User) user).getId();
	        	String userEmail = ((User) user).getEmail();
	        	
	        	if (userEmail != email)
	        		changeEmailStatus = this.customerDAO.changeEmail(idUser, SHA.encrypt(psw), email);
	        	
	        	if (changeEmailStatus == 1)
	        		((User) user).setEmail(email);
    		} catch (Exception e) {
    			// error
    			
    			System.out.println(e.getMessage());
    		}
    	}
    	
    	return changeEmailStatus;
    }
    
    public byte changePassword(Object user, String oldPsw, String psw) {
    	byte changePasswordStatus = -1;
    	
    	if (user instanceof User) {
    		try {
	        	this.customerDAO = this.customerDAOFactory.getCustomerDAO();
	        	
	        	int idUser = ((User) user).getId();
	        	
	        	if (oldPsw != psw)
	        		changePasswordStatus = this.customerDAO.changePassword(idUser, SHA.encrypt(oldPsw), SHA.encrypt(psw));
	        	
    		} catch (Exception e) {
    			// error
    			
    			System.out.println(e.getMessage());
    		}
    	}
    	
    	return changePasswordStatus;
    }
}
