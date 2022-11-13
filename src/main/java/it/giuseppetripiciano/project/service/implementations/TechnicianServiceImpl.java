package it.giuseppetripiciano.project.service.implementations;

import it.giuseppetripiciano.project.service.interfaces.TechnicianService;

import it.giuseppetripiciano.project.dao.interfaces.TechnicianDAO;

import java.util.regex.Pattern;

import javax.sql.DataSource;

import it.giuseppetripiciano.project.dao.factories.TechnicianDAOFactory;

import it.giuseppetripiciano.project.model.User;

public class TechnicianServiceImpl implements TechnicianService {
	
	private TechnicianDAOFactory technicianDAOFactory;
	
	private TechnicianDAO technicianDAO;
	
	public TechnicianServiceImpl(DataSource ds) {
		this.technicianDAOFactory = new TechnicianDAOFactory(ds);
	}
	
    public byte refill(Object user, int idMachine) {
    	byte refillStatus = -1;
    	
    	if (user instanceof User) {
	    	if (((User) user).getType()!=0) {
	    		Pattern id_ptrn = Pattern.compile("^[1-9][0-9]*$");
	    		
	    		boolean isIdPtrn = id_ptrn.matcher(Integer.toString(idMachine)).matches();
	    		
	    		if (!isIdPtrn)
	    			return refillStatus;
	    		
	    		this.technicianDAO = this.technicianDAOFactory.getTechnicianDAO();
	    		
	    		refillStatus = this.technicianDAO.refill(idMachine);
	    		
	    		if (refillStatus == 1) {
	    			int idUser = ((User) user).getId();
	    			
	    			System.out.println("Technician with ID " + idUser + " refilled machine with ID " + idMachine + ".");
	    		}
	    	}
    	}
    	
    	return refillStatus;
    }
}
