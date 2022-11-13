package it.giuseppetripiciano.project.service.implementations;

import java.util.List;
import java.util.regex.Pattern;

import javax.sql.DataSource;

import it.giuseppetripiciano.project.service.interfaces.MachineService;

import it.giuseppetripiciano.project.dao.interfaces.MachineDAO;

import it.giuseppetripiciano.project.dao.factories.MachineDAOFactory;

import it.giuseppetripiciano.project.model.Machine;

public class MachineServiceImpl implements MachineService{

	private MachineDAOFactory machineDAOFactory;
	
	private MachineDAO machineDAO;
	
	public MachineServiceImpl(DataSource ds) {
		this.machineDAOFactory = new MachineDAOFactory(ds);
	}
	
    public byte checkStatus(Object machine) {
    	byte statusChange = -1;
    	
    	if (machine instanceof Machine) {
    		this.machineDAO = this.machineDAOFactory.getMachineDAO();
    		
    		List<List<String>> checkStatusResult = this.machineDAO.checkStatus(((Machine) machine).getId());
    		
    		if (checkStatusResult!=null) {
				try {
					int old_status = ((Machine) machine).getStatus();
					
		    		if (checkStatusResult.size() > 1) {
		    			int new_status = Integer.parseInt(checkStatusResult.get(1).get(0));
		    			
	    				if (old_status!=new_status) { 
    						// -> connected
    						
	    					statusChange = 1;
    					
	    					// update status
	    					
	    					((Machine) machine).setStatus(new_status);
	    				}
	    				else
	    					// unchanged
	    					
	    					statusChange = 0;	
		    		}
		    		else {
		    			if (old_status != 0) {
		    				// -> disconnected
		    				
		    				statusChange = 2;
		    				
		    				// update status
		    				
		    				((Machine) machine).setStatus(0);
		    			}
		    			else
		    				// unchanged
		    				
		    				statusChange = 0;	
		    		}
	    		} catch (NumberFormatException nfe) {
	    			// error
					// -> error
					
					statusChange = -1;
				
					System.out.println(nfe.getMessage());
				}	
    		}
    		
    		if (statusChange == 1) {
        		List<List<String>> checkStockResult = this.machineDAO.checkStock(((Machine) machine).getId());
        		
        		if (checkStockResult!=null)
        			// update stock
        			
        			((Machine) machine).setStock(checkStockResult);
        		else
        			// -> error
        			
        			statusChange = -1;
        	}
    		
    	}
    	
    	if (statusChange == -1)
    		// set error status
    		
    		((Machine) machine).setStatus(-2);
    	
    	return statusChange;
    }
    
    public void resetStatus(Object machine) {
    	if (machine instanceof Machine) {
    		if (((Machine) machine).getStatus()!=-1)
    			((Machine) machine).setStatus(-1);
    	}
    }
    
    public byte sellProduct(Object machine, int idProduct) {
    	byte sellProductStatus = -1;
    	
    	if (machine instanceof Machine) {
	    		Pattern id_ptrn = Pattern.compile("^[1-9][0-9]*$");
	    		
	    		boolean isIdPtrn = id_ptrn.matcher(Integer.toString(idProduct)).matches();
	    		
	    		if (!isIdPtrn)
	    			return sellProductStatus;
	    		
	    		this.machineDAO = this.machineDAOFactory.getMachineDAO();
	    		
	    		int idMachine = ((Machine) machine).getId();
	    		
	    		int idUser = ((Machine) machine).getStatus();
	    		
	    		sellProductStatus = this.machineDAO.sellProduct(idMachine, idUser, idProduct);
	    		
	    		if (sellProductStatus == 1)
	    			System.out.println("Machine with ID " + idMachine + " sold product with ID " + idProduct + ".");
    	}
    	
    	return sellProductStatus;
    }
}
