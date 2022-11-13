package it.giuseppetripiciano.project.dao.implementations;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public abstract class SetupDAOImpl {
	
	public static final byte SUCCESS = 1;
	public static final byte FAILURE = 0;
	public static final byte ERROR = -1;
	
	private DataSource ds;
	
	private Connection connection;
    private PreparedStatement statement;
    private ResultSet rs;
    
	private int count = 0;
	private List<List<String>> res = null;
	
	public SetupDAOImpl(DataSource ds) {
		this.ds = ds;
	}
	
	public byte open(){
		byte openStatus = 1;
		
		try {
			this.connection = ds.getConnection();	
		} catch (Exception e) {
			System.out.println(e.getMessage());
			openStatus = ERROR;
		}
		
		return openStatus;
	}
	
	public void exec() throws Exception {	
		this.rs = statement.executeQuery();
		
		ResultSetMetaData rsmd = this.rs.getMetaData();
		int columnCount = rsmd.getColumnCount();
		this.res = new ArrayList<>();

		// header
		List<String> header = new ArrayList<>();
		for (int i = 0; i < columnCount; i++) {
			header.add(rsmd.getColumnLabel(i + 1));
		}
		this.res.add(header);
		
		// data
		while (this.rs.next()) {
			List<String> row = new ArrayList<>();
			for (int i = 0; i < columnCount; i++) {
				row.add(this.rs.getString(i + 1));
			}
			this.res.add(row);
		}	
	}
	public void upd() throws Exception {	
		this.count = this.statement.executeUpdate();
	}
	public void closeStatement(){
		try {
			if(this.rs != null)
				this.rs.close();
			this.statement.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void closeConnection() {
		try {
			this.connection.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void setStatement(PreparedStatement stmt) {
		this.statement = stmt;
	}
	public PreparedStatement getStatement() {
		return this.statement;
	}
	public Connection getConnection() {
		return this.connection;
	}
	public List<List<String>> getResult() {
		return this.res;
	}
	public int getCount() {
		return this.count;
	}
}
