package be.belfius.Daouri_Samy_Games.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import be.belfius.Daouri_Samy_Games.domain.Game;



public class GameRepository  {
	private Connection con;
	private Statement state;
	private ResultSet result;
	
	
	public GameRepository() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/games?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","Sa1_Sony_4");
			System.out.println("Connected to Database");
			state = con.createStatement();
			System.out.println("Statement created");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	
	public ResultSet getList(String tableName) {
	
		try {
			result = state.executeQuery("SELECT * FROM "+ tableName);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
		return result;
	}

}
