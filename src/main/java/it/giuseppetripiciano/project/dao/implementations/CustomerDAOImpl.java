package it.giuseppetripiciano.project.dao.implementations;

import java.util.List;

import javax.sql.DataSource;

import java.sql.Types;

import it.giuseppetripiciano.project.dao.interfaces.CustomerDAO;

public class CustomerDAOImpl extends SetupDAOImpl implements CustomerDAO{
	
	public CustomerDAOImpl (DataSource ds) {
		super(ds);
	}
	
	public byte connect(int idUser, int idMachine){
		if (this.open() == ERROR)
			// error
			
			return ERROR;
		
		String query1 = "SELECT idMachine FROM machine WHERE idMachine=?";
		
		byte query1Status = 0;
		
		try {
			this.setStatement(this.getConnection().prepareStatement(query1));
			
			this.getStatement().setInt(1, idMachine);
			
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
			String query2 = "UPDATE machine SET status=? WHERE idMachine=? AND status IS NULL";
			
			byte query2Status = 0;
			
			try {
				this.setStatement(this.getConnection().prepareStatement(query2));
				
				this.getStatement().setLong(1, idUser);
				this.getStatement().setInt(2, idMachine);
				
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
					
			if (this.getCount() > 0) {
				// success
				
				query2Status = SUCCESS;
			}
			else
				// failure
				
				query2Status = FAILURE;
			
			this.closeConnection();
			
			return query2Status;
		}
		else
			// failure 2
			
			query1Status = FAILURE + 2;
		
		this.closeConnection();
		
		return query1Status;
	}
	
	public byte disconnect(int idUser, int idMachine){
		if (this.open() == ERROR)
			// error
			
			return ERROR;
		
		String query = "UPDATE machine SET status=? WHERE idMachine=? AND status=?";
		
		byte queryStatus = 0;
		
		try {
			this.setStatement(this.getConnection().prepareStatement(query));
			
			this.getStatement().setNull(1, Types.NULL);
			this.getStatement().setInt(2, idMachine);
			this.getStatement().setLong(3, idUser);
			
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
			// failure
			
			queryStatus = FAILURE;
		
		this.closeConnection();
		
		return queryStatus;
	}
	
	public byte recharge(int idUser, String credit) {
		if (this.open() == ERROR)
			// error
			
			return ERROR;
		
		String query = "UPDATE user SET credit=? WHERE idUser=?";
		
		byte queryStatus = 0;
		
		try {
			this.setStatement(this.getConnection().prepareStatement(query));
			
			this.getStatement().setString(1, credit);
			this.getStatement().setInt(2, idUser);
			
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
	
	public List<List<String>> checkCredit(int idUser){
		if (this.open() == ERROR)
			// error
			
			return null;
		
		String query = "SELECT credit FROM user WHERE idUser=?";
		
		byte queryStatus = 0;
		
		try {
			this.setStatement(this.getConnection().prepareStatement(query));
			
			this.getStatement().setInt(1, idUser);
			
			this.exec();	
		} catch (Exception e) {
			// error
			
			System.out.println(e.getMessage());
			queryStatus = ERROR;
		} finally {
			this.closeStatement();
		}
		
		if (queryStatus == ERROR) {
			this.closeConnection();
			return null;
		}
		
		this.closeConnection();
		
		return this.getResult();
	} 
	
	public byte changeEmail(int idUser, String psw, String email) {
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
		
		String query2 = "UPDATE user SET email=? WHERE idUser=? AND password=?";
		
		byte query2Status = 0;
		
		try {
			this.setStatement(this.getConnection().prepareStatement(query2));
			
			this.getStatement().setString(1, email);
			this.getStatement().setInt(2, idUser);
			this.getStatement().setString(3, psw);
			
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
			// failure 2
			
			query2Status = FAILURE + 2;
		
		this.closeConnection();
		
		return query2Status;
	}
	
	public byte changePassword(int idUser, String oldPsw, String psw) {
		if (this.open() == ERROR)
			// error
			
			return ERROR;
		
		String query = "UPDATE user SET password=? WHERE idUser=? AND password=?";
		
		byte queryStatus = 0;
		
		try {
			this.setStatement(this.getConnection().prepareStatement(query));
			
			this.getStatement().setString(1, psw);
			this.getStatement().setInt(2, idUser);
			this.getStatement().setString(3, oldPsw);
			
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
			// failure
			
			queryStatus = FAILURE;
		
		this.closeConnection();
		
		return queryStatus;
	}
}
