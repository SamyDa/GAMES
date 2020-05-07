package be.belfius.Daouri_Samy_Games.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Category extends DataStructure{
	
	public Category(){
		super();
		this.tableName = "Category";
		this.nameColumn= "category_name";
	}
	
	public Category(int id , String name){
		super(id, name);
		this.tableName = "Category";
		this.nameColumn= "category_name";
	}
	
	@Override
	public boolean setStructure(ResultSet set) {
		try {
			if(set.next()) {
				this.id = set.getInt("id");
				this.name = set.getString("category_name");
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
