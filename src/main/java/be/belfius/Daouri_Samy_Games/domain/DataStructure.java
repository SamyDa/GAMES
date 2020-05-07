package be.belfius.Daouri_Samy_Games.domain;

import java.sql.ResultSet;

public abstract class DataStructure {

	protected int id;
	protected String name;
	protected String tableName;
	protected String nameColumn;
 
	public DataStructure() {
		this.id= 0;
		this.name= "";
		this.tableName =""; 
		this.nameColumn =""; 
	}
	
	public DataStructure(int id, String name) {
		this.id = id;
		this.name = name;
		this.tableName =""; 
		this.nameColumn =""; 
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getTableName() {
		return tableName;
	}
	
	
	
	public String getNameColumn() {
		return nameColumn;
	}

	public abstract boolean setStructure(ResultSet set) ;

	
	
}
