package it.giuseppetripiciano.project.dao.implementations;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sql.DataSource;

import it.giuseppetripiciano.project.dao.interfaces.TechnicianDAO;

public class TechnicianDAOImpl extends SetupDAOImpl implements TechnicianDAO {
	
	public TechnicianDAOImpl(DataSource ds) {
		super(ds);
	}

	public byte refill (int idMachine) {
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
			String query2 = "SELECT idProduct, name FROM product";
			
			byte query2Status = 0;
			
			try {
				this.setStatement(this.getConnection().prepareStatement(query2));
				
				this.exec();
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
			
			List<List<String>> result2 = this.getResult();
			
			if (result2.size() > 1) {
				String query3 = "INSERT INTO stock (idMachine_Ref, idProduct_Ref, qty) VALUES (?,?,?)"
						+ "ON DUPLICATE KEY UPDATE qty=?";
				
				byte query3Status;
				
				int idProduct = -1;
				String productName = null;
				Pattern pattern = Pattern.compile("caffè", Pattern.CASE_INSENSITIVE);
				Matcher matcher = null;
				
				for (int i=1;i<=result2.size()-1;i++) {
					try {
						idProduct = Integer.parseInt(result2.get(i).get(0));
						productName = result2.get(i).get(1);
						
						query3Status = 0;
					
						this.setStatement(this.getConnection().prepareStatement(query3));
						
						this.getStatement().setInt(1, idMachine);
						this.getStatement().setInt(2, idProduct);
						
						matcher = pattern.matcher(productName);
						
						if (matcher.find()) {
							this.getStatement().setInt(3, 50);
							this.getStatement().setInt(4, 50);
						}
						else {
							this.getStatement().setInt(3, 10);
							this.getStatement().setInt(4, 10);
						}
						
						this.upd();
					} catch (Exception e) {
						// error
						
						System.out.println(e.getMessage());
						query3Status = ERROR;
					} finally {
						this.closeStatement();
					}
					
					if (query3Status == ERROR) {
						this.closeConnection();
						return query3Status;
					}
				}
				//success
				
				query3Status = SUCCESS;
				
				this.closeConnection();
				
				return query3Status;
			}
			else {
				// failure
				
				query2Status = FAILURE;
				
				this.closeConnection();
				
				return query2Status;
			}
		}
		else {
			// failure 2
			
			query1Status = (FAILURE + 2);
			
			this.closeConnection();
			
			return query1Status;
		}
	}
}
