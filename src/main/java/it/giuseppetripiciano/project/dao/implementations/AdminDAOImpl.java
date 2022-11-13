package it.giuseppetripiciano.project.dao.implementations;

import javax.sql.DataSource;

import it.giuseppetripiciano.project.dao.interfaces.AdminDAO;

public class AdminDAOImpl extends SetupDAOImpl implements AdminDAO {
	
	public AdminDAOImpl (DataSource ds) {
		super(ds);
	}
	
	public byte registerTechnician(String firstname, String lastname, String email, String psw){
		if (this.open() == ERROR)
			// error
			
			return ERROR;
		
		String query1 = "SELECT email FROM user WHERE email=?";
		
		byte query1Status = 0;
		
		try {
			this.setStatement(this.getConnection().prepareStatement(query1));
			
			this.getStatement().setString(1, email);
			
			this.exec();
		} catch (Exception e) {
			// error
			
			System.out.println(e.getMessage());
			query1Status = ERROR;
		} finally {
			this.closeStatement();
		}
		
		if (query1Status == ERROR) {
			this.closeConnection();
			return query1Status;
		}
		
		if (this.getResult().size() > 1) {
			// failure
			
			query1Status = FAILURE;
			this.closeConnection();
			return query1Status;
		}
		
		String query2 = "INSERT INTO user (firstname, lastname, email, password, type) VALUES (?,?,?,?,?)";
		
		byte query2Status = 0;
		
		try {
			this.setStatement(this.getConnection().prepareStatement(query2));
			
			this.getStatement().setString(1, firstname);
			this.getStatement().setString(2, lastname);
			this.getStatement().setString(3, email);
			this.getStatement().setString(4, psw);
			this.getStatement().setByte(5, (byte) 1);
			
			this.upd();
		} catch (Exception e) {
			// error
			
			System.out.println(e.getMessage());
			query2Status = ERROR;
		} finally {
			this.closeStatement();
		}
		
		if (query2Status == ERROR) {
			this.closeConnection();
			return query2Status;
		}
	
		if (this.getCount() > 0)
			// success
			
			query2Status = SUCCESS;
		else
			// error
			
			query2Status = ERROR;
		
		this.closeConnection();
		
		return query2Status;
	}
	
	public byte registerMachine(String psw){
		if (this.open() == ERROR) {
			// error
			
			return ERROR;
		}
		
		String query = "INSERT INTO machine (password) VALUES (?)";
		
		byte queryStatus = 0;
		
		try {
			this.setStatement(this.getConnection().prepareStatement(query));
			
			this.getStatement().setString(1, psw);
			
			this.upd();
		} catch (Exception e) {
			// error
			
			System.out.println(e.getMessage());
			queryStatus = ERROR;
		} finally {
			this.closeStatement();
		}
		
		if (queryStatus == ERROR) {
			this.closeConnection();
			return queryStatus;
		}
		
		if (this.getCount() > 0)
			// success
			
			queryStatus = SUCCESS;
		else
			// error
			
			queryStatus = ERROR;
		
		this.closeConnection();
		
		return queryStatus;
	}
}
