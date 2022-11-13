package it.giuseppetripiciano.project.dao.implementations;

import java.util.List;

import javax.sql.DataSource;

import it.giuseppetripiciano.project.dao.interfaces.MachineDAO;

public class MachineDAOImpl extends SetupDAOImpl implements MachineDAO {

	public MachineDAOImpl(DataSource ds) {
		super(ds);
	}
	
	public List<List<String>> checkStatus (int idMachine) {
		if (this.open() == ERROR) {
			// error
			
			return null;
		}
		
		String query = "SELECT status FROM machine WHERE idMachine=? AND status IS NOT NULL";
		
		try {
			this.setStatement(this.getConnection().prepareStatement(query));
			
			this.getStatement().setInt(1, idMachine);
			
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
	
	public List<List<String>> checkStock (int idMachine){
		if (this.open() == ERROR)
			// error
			
			return null;
		
		String query = "SELECT product.idProduct, product.name, product.price, stock.qty"
				+ " FROM product INNER JOIN stock ON product.idProduct=stock.idProduct_Ref"
				+ " WHERE stock.idMachine_Ref=? AND stock.qty!=0";
		
		try {
			this.setStatement(this.getConnection().prepareStatement(query));
			
			this.getStatement().setInt(1, idMachine);
			
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
	
	public byte sellProduct(int idMachine, int idUser, int idProduct) {
		if (this.open() == ERROR) {
			// error
			
			return ERROR;
		}
		
		String query1 = "SELECT price FROM product WHERE idProduct=?";
		
		byte query1Status = 0;
		
		try {
			this.setStatement(this.getConnection().prepareStatement(query1));
			
			this.getStatement().setInt(1, idProduct);
			
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
		
		String query2 = "UPDATE user INNER JOIN machine ON user.idUser=machine.status "
				+ "INNER JOIN stock ON machine.idMachine=stock.idMachine_Ref "
				+ "SET stock.qty=stock.qty-1, user.credit=user.credit-? "
				+ "WHERE machine.idMachine=? AND user.idUser=? AND stock.idProduct_Ref=? AND stock.qty>0 AND user.credit>=?";
		
		byte query2Status = 0;
		
		try {
			float productPrice = Float.parseFloat(this.getResult().get(1).get(0));
			
			this.setStatement(this.getConnection().prepareStatement(query2));
			
			this.getStatement().setFloat(1, productPrice);
			this.getStatement().setInt(2, idMachine);
			this.getStatement().setInt(3, idUser);
			this.getStatement().setInt(4, idProduct);
			this.getStatement().setFloat(5, productPrice);
			
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
			// failure
			
			query2Status = FAILURE;
		
		this.closeConnection();
		
		return query2Status;
	}
}
