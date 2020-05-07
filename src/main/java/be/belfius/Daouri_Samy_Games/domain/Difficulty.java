package be.belfius.Daouri_Samy_Games.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Difficulty extends DataStructure{
	
	public Difficulty(){
		super();
		this.tableName = "Difficulty";
		this.nameColumn= "difficulty_name";
	}
	
	public Difficulty(int id , String name){
		super(id, name);
		this.tableName = "Difficulty";
		this.nameColumn= "difficulty_name";
	}

	@Override
	public boolean setStructure(ResultSet set) {
		try {
			if(set.next()) {
				this.id = set.getInt("id");
				this.name = set.getString("difficulty_name");
				return true;
			}	

		} catch (SQLException e) {
			return false;
		} catch(NullPointerException e) {
			return false;
		}
		return false;
	}
}
