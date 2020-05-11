package be.belfius.Daouri_Samy_Games.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;


public class Borrow extends DataStructure {
	
	private int gameId;
	private int borrowerId;
	private LocalDate borrowDate;
	private LocalDate returnDate;
	private Game game;
	private Borrower borrower;
	
	public Borrow() {
		super();
		this.tableName = "Borrow";
	}
	public Borrow(int id, int gameId, int borrowerId, LocalDate borrowDate, LocalDate returnDate) {
		this.id = id;
		this.gameId = gameId;
		this.borrowerId = borrowerId;
		this.borrowDate = borrowDate;
		this.returnDate = returnDate;
		this.tableName = "Borrow";
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
		LocalDate date =  LocalDate.MIN;
		if (returnDate!= null) {
			date = returnDate;
		}
		
		return date;
	}
	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public Borrower getBorrower() {
		return borrower;
	}
	public void setBorrower(Borrower borrower) {
		this.borrower = borrower;
	}
	@Override
	public boolean setStructure(ResultSet set) {
		try {
			if(set.next()) {
				this.id = set.getInt("id");
				this.gameId = set.getInt("game_id");
				this.borrowerId = set.getInt("borrower_id");
				this.borrowDate = set.getDate("borrow_date").toLocalDate();
				if (set.getDate("return_date") != null)
					this.returnDate = set.getDate("return_date").toLocalDate();
				else
					this.returnDate = LocalDate.of(0001, 01, 01);
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
