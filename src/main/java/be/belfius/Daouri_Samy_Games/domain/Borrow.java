package be.belfius.Daouri_Samy_Games.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Borrow extends DataStructure {
	
	private int gameId;
	private int borrowerId;
	private Date borrowDate;
	private Date returnDate;
	
	public Borrow() {
		super();
		this.tableName = "Borrow";
	}
	public Borrow(int id, int gameId, int borrowerId, Date borrowDate, Date returnDate) {
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
	public Date getBorrowDate() {
		return borrowDate;
	}
	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	
	@Override
	public boolean setStructure(ResultSet set) {
		try {
			if(set.next()) {
				this.id = set.getInt("id");
				this.gameId = set.getInt("game_id");
				this.borrowerId = set.getInt("borrower_id");
				this.borrowDate = set.getDate("borrow_date");
				this.returnDate = set.getDate("return_date");
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
