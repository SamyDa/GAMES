package be.belfius.Daouri_Samy_Games.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import be.belfius.Daouri_Samy_Games.App;
import be.belfius.Daouri_Samy_Games.domain.Borrow;
import be.belfius.Daouri_Samy_Games.domain.DataStructure;

public class GameRepository  {
	private Connection con;
	private Statement state;
	private ResultSet result;
	private String user;
	private String password;
		
	public boolean openConnection() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/games?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",user,password);
			state = con.createStatement(); 
			App.logger.info("Connection to DB games");
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
				App.logger.info("Connection to DB closed");
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
			App.logger.info("Query to DB : " + query);
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
			App.logger.info("Query to DB : " + query);
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
			App.logger.info("Query to DB : " + query);
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
	public void setDBConf(String user, String password) {
		
		this.user = user;
		this.password = password;
		App.logger.info("Setting configuration for "  + user);
	}

	public boolean setBorrow(Borrow borrow) {
	
		try {
			con.setAutoCommit(false);
			PreparedStatement pstmt = con.prepareStatement("insert into borrow (game_id, borrower_id, borrow_date) values ( ? , ? , ?)");
			pstmt.setInt(1, borrow.getGameId());
			pstmt.setInt(2, borrow.getBorrowerId());
			pstmt.setDate(3, Date.valueOf(borrow.getBorrowDate()));
			pstmt.execute();		
			con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
}
