package be.belfius.Daouri_Samy_Games.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import be.belfius.Daouri_Samy_Games.domain.DataStructure;




public class GameRepository  {
	private Connection con;
	private Statement state;
	private ResultSet result;
		
	public boolean openConnection() {
		/*try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} */
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/games?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","Sa1_Sony_4");
			state = con.createStatement();
			return true;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		
	}	
	
	public boolean closeConnection() {
		
		try {
			if(!con.isClosed()) {
				con.close();
				return true;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		return false;
		
	}	
	
	public ResultSet getList(String tableName) {
		try {
			result = state.executeQuery("SELECT * FROM "+ tableName);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
		return result;
	}

	public ResultSet getList(DataStructure selectCriteria) {
		String query = "SELECT * FROM "+ selectCriteria.getTableName();
		
		if(selectCriteria.getId() > 0)
		{
			query += " WHERE ID  = " + selectCriteria.getId() ;
		}
		if(selectCriteria.getName() != null) {
			query +=" WHERE Upper(" +selectCriteria.getNameColumn() + ") like ('%" +selectCriteria.getName().toUpperCase()+"%')" ;
		}
		
		try{
			result = state.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
		return result;
	}
	
	public int getMaxId(DataStructure selectCriteria) {
		int id = 0;
		String query = "SELECT Max(ID) FROM "+ selectCriteria.getTableName();
		try {
			result = state.executeQuery(query);
			if(result.next()) {
				for (int i=1; i<= result.getMetaData().getColumnCount(); i++) {
					id = Integer.parseInt(result.getObject(i).toString());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	
		return id;
		
	}
	public int getMaxId(String tableName) {
		
		int id = 0;
		String query = "SELECT Max(ID) FROM "+ tableName;
		try {
			result = state.executeQuery(query);
			if(result.next()) {
				for (int i=1; i<= result.getMetaData().getColumnCount(); i++) {
					id = Integer.parseInt(result.getObject(i).toString());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return id;
	}
	
	public void finalize() {
		try {
			con.close();
		} catch (SQLException e) {
			System.out.println("Closing connection");
			e.printStackTrace();
		}
	}
}
