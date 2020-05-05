package be.belfius.Daouri_Samy_Games.domain;

import java.time.LocalDate;

public class Borrow {
	private int id ;
	private int gameId;
	private int borrowerId;
	private LocalDate borrowDate;
	private LocalDate returnDate;
	
	public Borrow() {
		
	}
	public Borrow(int id, int gameId, int borrowerId, LocalDate borrowDate, LocalDate returnDate) {
		this.id = id;
		this.gameId = gameId;
		this.borrowerId = borrowerId;
		this.borrowDate = borrowDate;
		this.returnDate = returnDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getGameId() {
		return gameId;
	}
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	public int getBorrowerId() {
		return borrowerId;
	}
	public void setBorrowerId(int borrowerId) {
		this.borrowerId = borrowerId;
	}
	public LocalDate getBorrowDate() {
		return borrowDate;
	}
	public void setBorrowDate(LocalDate borrowDate) {
		this.borrowDate = borrowDate;
	}
	public LocalDate getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

}
