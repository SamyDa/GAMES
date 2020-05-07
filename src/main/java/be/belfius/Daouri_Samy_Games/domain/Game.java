package be.belfius.Daouri_Samy_Games.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Game extends DataStructure{

	private String editor ; 
	private String author; 
	private int editionYear ; 
	private String age; 
	private int minPlayers ; 
	private int maxPlayers ; 
	private int categoryId ; 
	private String playDuration ; 
	private int difficultyId ; 
	private double price; 
	private String image;
	private Category category;
	private Difficulty difficulty;
	
	public Game() {
		this.tableName = "Game";
		this.nameColumn= "game_name";
	}
	
	public Game(int id, String name, String editor, String author, int editionYear, String age, int minPlayers,
			int maxPlayers, int categoryId, String playDuration, int difficultyId, double price, String image) {
		this.tableName = "Game";
		this.nameColumn= "game_name";
		this.id = id;
		this.name = name;
		this.editor = editor;
		this.author = author;
		this.editionYear = editionYear;
		this.age = age;
		this.minPlayers = minPlayers;
		this.maxPlayers = maxPlayers;
		this.categoryId = categoryId;
		this.playDuration = playDuration;
		this.difficultyId = difficultyId;
		this.price = price;
		this.image = image;
	}


	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getEditionYear() {
		return editionYear;
	}

	public void setEditionYear(int editionYear) {
		this.editionYear = editionYear;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public int getMinPlayers() {
		return minPlayers;
	}

	public void setMinPlayers(int minPlayers) {
		this.minPlayers = minPlayers;
	}

	public int getMaxPlayers() {
		return maxPlayers;
	}

	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getPlayDuration() {
		return playDuration;
	}

	public void setPlayDuration(String playDuration) {
		this.playDuration = playDuration;
	}

	public int getDifficultyId() {
		return difficultyId;
	}

	public void setDifficultyId(int difficultyId) {
		this.difficultyId = difficultyId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	@Override
	public boolean setStructure(ResultSet set) {
		try {
			if(set.next()) {
				this.id = set.getInt("id");
				this.name = set.getString("game_name");
				this.editor = set.getString("editor");
				this.author =   set.getString("author");
				this.editionYear = set.getInt("year_edition");
				this.age = set.getString("age");
				this.minPlayers = set.getInt("min_players");
				this.maxPlayers = set.getInt("max_players");
				this.categoryId = set.getInt("category_id");
				this.playDuration = set.getString("play_duration");
				this.difficultyId = set.getInt("difficulty_id");
				this.price = set.getDouble("price");
				this.image = set.getString("image");
				return true;
			}	

		} catch (SQLException e) {
			return false;
		} catch(NullPointerException e) {
			return false;
		}
		return false;
	}

	@Override
	public String toString() {
		return    "\tName : " + this.name + " \n "+ "\tEditor : " + editor + " \n "+ "\tAuthor : " +author + " \n "+  "\tEdition year : " +editionYear+ " \n "
	            + "\tAge : " +age + " \n "+  "\tMin Players : " +minPlayers + " \n "+  "\tMax Players : " +maxPlayers + " \n "+   "\tDuration : " + playDuration + " \n "
				+ "\tPrice : " +price + " \n "+  "\tImage : " +image ; 
	}
	
	public String toString(String category , String difficulty) {
		return  "\tName : " + this.name + " \n "+ "\tEditor : " + editor + " \n "+ "\tAuthor : " +author + " \n "+  "\tEdition year : " +editionYear+ " \n "+  "\tAge : " +age + " \n "+  "\tMin Players : " +minPlayers + " \n "+  "\tMax Players : " +maxPlayers + " \n "+  "\tCategory : " + category + " \n "+   "\tDuration : " +playDuration + " \n "+  "\tDifficulty : " +difficulty + " \n "+  "\tPrice : " +price + " \n "+  "\tImage : " +image ; 
	}
	
	
	
	
	
}
