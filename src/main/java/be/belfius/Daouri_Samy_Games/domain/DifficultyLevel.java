package be.belfius.Daouri_Samy_Games.domain;

public enum DifficultyLevel {

	VERUEASY(1,"Very easy"),EASY(2,"Easy"),AVERAGE(3,"Average"),DIFFICULT(4,"Difficult"), VERYDIFFICULT(5,"Very difficult") ;
	
	private String level;
	private int order;
	
	private DifficultyLevel(int order, String string) {
		this.order = order;
		this.level = string;
	}
	
	public String getDifficultyLevel() {
		return this.level;
	}
	public int getDifficultyOrder() {
		return this.order;
	}
	
	
}
