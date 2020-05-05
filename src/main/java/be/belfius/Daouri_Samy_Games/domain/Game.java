package be.belfius.Daouri_Samy_Games.domain;

public class Game {

	private int id ; 
	private String name; 
	private String editor ; 
	private String author; 
	private int editionYear ; 
	private int age ; 
	private int minPlayers ; 
	private int maxPlayers ; 
	private int categoryId ; 
	private int playDuration ; 
	private int difficultyId ; 
	private double price; 
	private String image;
	
	public Game() {
		
	}
	
	public Game(int id, String name, String editor, String author, int editionYear, int age, int minPlayers,
			int maxPlayers, int categoryId, int playDuration, int difficultyId, double price, String image) {
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
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

	public int getPlayDuration() {
		return playDuration;
	}

	public void setPlayDuration(int playDuration) {
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
	
	
	
}
