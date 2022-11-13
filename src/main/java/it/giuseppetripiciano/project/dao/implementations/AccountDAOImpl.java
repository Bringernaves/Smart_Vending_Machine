package it.giuseppetripiciano.project.dao.implementations;

import java.util.List;

import javax.sql.DataSource;

import it.giuseppetripiciano.project.dao.interfaces.AccountDAO;

public class AccountDAOImpl extends SetupDAOImpl implements AccountDAO {
	
	public AccountDAOImpl (DataSource ds) {
		super(ds);
	}
	
	public byte registerCustomer(String firstname, String lastname, String email, String psw){
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
			this.getStatement().setByte(5, (byte) 0);
			
			this.upd();
		} catch (Exception e) {
			// error
			
			System.out.println(e.getMessage());
			query2Status = ERROR;
		} finally {
			this.closeStatement();
		}
		
		if (query2Status == ERROR)
			return query2Status;
		
		if (this.getCount() > 0)
			// success
			
			query2Status = SUCCESS;
		else
			// error
			
			query2Status = ERROR;
		
		this.closeConnection();
		
		return query2Status;
	}
	
	public List<List<String>> userLogin(String email, String psw) {
		if (this.open() == ERROR)
			// error
			
			return null;
		
		String query = "SELECT idUser, firstname, lastname, email, credit, type FROM user WHERE email=? AND password=?";
		
		try {
			this.setStatement(this.getConnection().prepareStatement(query));
			
			this.getStatement().setString(1, email);
			this.getStatement().setString(2, psw);
			
			this.exec();
		} catch (Exception e) {
			// error
			
			System.out.println(e.getMessage());
		} finally {
			this.closeStatement();
		}
		
		this.closeConnection();
		
		return this.getResult();
	}
	
	public List<List<String>> machineLogin(int id, String psw) {
		if (this.open() == ERROR)
			// error
			
			
			return null;
		
		String query = "SELECT idMachine, status FROM machine WHERE idMachine=? AND password=?";
		
		try {
			this.setStatement(this.getConnection().prepareStatement(query));
			
			this.getStatement().setInt(1, id);
			this.getStatement().setString(2, psw);
			
			this.exec();
		} catch (Exception e) {
			// error
			
			System.out.println(e.getMessage());
		} finally {
			this.closeStatement();
		}
		
		this.closeConnection();
		
		return this.getResult();
	}
}
