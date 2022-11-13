package it.giuseppetripiciano.project.service.implementations;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.sql.DataSource;

import it.giuseppetripiciano.project.service.interfaces.AccountService;

import it.giuseppetripiciano.project.dao.interfaces.AccountDAO;

import it.giuseppetripiciano.project.dao.factories.AccountDAOFactory;

import it.giuseppetripiciano.project.model.*;

import it.giuseppetripiciano.project.service.SHA;

public class AccountServiceImpl implements AccountService {
	
	private AccountDAOFactory accountDAOFactory;
	
	private AccountDAO accountDAO;
	
	public AccountServiceImpl(DataSource ds) {
		this.accountDAOFactory = new AccountDAOFactory(ds);
	}

    public byte registerCustomer(String firstname, String lastname, String email, String psw) {
    	byte registerStatus = -1;
    	
    	try {
	    	Pattern email_ptrn = Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$");
	    	Pattern psw_ptrn = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$");
	    	
	    	boolean isEmailPtrn = email_ptrn.matcher(email).matches();
	    	boolean isPswPtrn = psw_ptrn.matcher(psw).matches();
	    	
	    	if (!isEmailPtrn || !isPswPtrn)
	    		return registerStatus;
	    	
	    	this.accountDAO = this.accountDAOFactory.getAccountDAO();
	    	
			registerStatus = this.accountDAO.registerCustomer(firstname, lastname, email, SHA.encrypt(psw));
			
			if (registerStatus == 1)
	    		System.out.println("Customer " + firstname + " " + lastname + " registered.");
    	} catch (Exception e) {
    		// error
    		
    		System.out.println(e.getMessage());
    	}
    	
    	return registerStatus;
    }
    
    public List<Object> userLogin(String email, String psw) {
    	byte loginStatus = -1;
    	
    	List<Object> result = new ArrayList<Object>();
    	
    	try {
	    	Pattern email_ptrn = Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$");
	    	Pattern psw_ptrn = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$");
	    	
	    	boolean isEmailPtrn = email_ptrn.matcher(email).matches();
	    	boolean isPswPtrn = psw_ptrn.matcher(psw).matches();
	    	
	    	if (!isEmailPtrn || !isPswPtrn) {
	    		result.add(null);
	    		result.add(loginStatus);
	    		return result;
	    	}
	    	
	    	this.accountDAO = this.accountDAOFactory.getAccountDAO();
    	
	    	List<List<String>> loginResult = this.accountDAO.userLogin(email, SHA.encrypt(psw));
			
	    	if (loginResult!=null) {
				if (loginResult.size() > 1) {
					// success
					
					// User account
					
					int id = Integer.parseInt(loginResult.get(1).get(0));
					
					String firstname = loginResult.get(1).get(1);
					String lastname = loginResult.get(1).get(2);
					String credit = loginResult.get(1).get(4);
					
					byte type = Byte.parseByte(loginResult.get(1).get(5)); // admin=2, technician=1, customer=0
					
					User userAccount = new User();
					
					userAccount.setId(id);
					userAccount.setType(type);
					userAccount.setFirstname(firstname);
					userAccount.setLastname(lastname);
					userAccount.setEmail(email);
					userAccount.setCredit(credit);
					
					if (type == 0)
						System.out.println("Customer with ID " + id + " logged in.");
					else if (type == 1)
						System.out.println("Technician with ID " + id + " logged in.");
					else
						System.out.println("Admin with ID " + id + " logged in.");
					
					loginStatus = 1;
					
					result.add(userAccount);
				}
				else {
					// failure 
					
					loginStatus = 0;
					
					result.add(null);
				}
	    	}
	    	
	    	if (loginStatus == -1)
	    		result.add(null);
	    	
	    	result.add(Byte.valueOf(loginStatus));
	    	
    	} catch (Exception e) {
    		// error
    		
    		System.out.println(e.getMessage());	
    	}
    	
    	return result;
    }
    
    public List<Object> machineLogin(int id, String psw) {
    	byte loginStatus = -1;
    	
    	List<Object> result = new ArrayList<Object>();
    	
    	try {
	    	Pattern id_ptrn = Pattern.compile("^[1-9][0-9]*$");
	    	Pattern psw_ptrn = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$");
	    	
	    	boolean isIdPtrn = id_ptrn.matcher(Integer.toString(id)).matches();
	    	boolean isPswPtrn = psw_ptrn.matcher(psw).matches();
	    	
	    	if (!isIdPtrn || !isPswPtrn) {
	    		result.add(null);
	    		result.add(loginStatus);
	    		return result;
	    	}
	    	
	    	this.accountDAO = this.accountDAOFactory.getAccountDAO();
    	
	    	List<List<String>> loginResult = this.accountDAO.machineLogin(id, SHA.encrypt(psw));
	    	
	    	if (loginResult!=null) {
		    	if (loginResult.size() > 1) {
					// success
					
					// Machine account
					
					Machine machineAccount = new Machine();
					
					machineAccount.setId(id);
					
					System.out.println("Machine with ID " + id + " logged in.");
					
					loginStatus = 2;
					
					result.add(machineAccount);
				}
				else {
					// failure
					
					loginStatus = 0;
					
					result.add(null);
				}
	    	}
    	} catch (Exception e) {
    		// error
    		
    		System.out.println(e.getMessage());
    	}
    	
    	if (loginStatus == -1)
    		result.add(null);
    	
    	result.add(Byte.valueOf(loginStatus));
    	
    	return result;
    }
    
    public void logout(Object account) {
    	if (account instanceof Account) {
			if (account instanceof User) {
				long id = ((User) account).getId();
				byte type = ((User) account).getType();
				
				if (type == 0) {
					System.out.println("Customer with ID " + id + " logged out.");
				}
				else if (type == 1) {
					System.out.println("Technician with ID " + id + " logged out.");
				}
				else 
					System.out.println("Admin with ID " + id + " logged out.");
			}
			else {
				int id = ((Machine) account).getId();
				
				System.out.println("Machine with ID " + id + " logged out.");
			}
    	}
    }
}

