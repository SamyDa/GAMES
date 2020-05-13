package be.belfius.Daouri_Samy_Games.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import be.belfius.Daouri_Samy_Games.domain.Borrow;
import be.belfius.Daouri_Samy_Games.domain.Borrower;
import be.belfius.Daouri_Samy_Games.domain.DataStructure;
import be.belfius.Daouri_Samy_Games.domain.Game;
import be.belfius.Daouri_Samy_Games.repository.GameRepository;

public class GameService extends Throwable{
	
	private GameRepository gameRepository = new GameRepository();
	
	//getDataByPosition is used to retrieve data from the DB based. The T structure is used to find the table to search
	// the position is used to retrieve a specific id in the table
	public <T> boolean getDataByPosition(T structure , int position) throws Exception{
		DataStructure element = (DataStructure) structure;
		ResultSet result ;
		if(gameRepository.openConnection()) {
			if (position > 0 && position <= gameRepository.getMaxId(element)) {
				element.setId(position);
				element.setName(null);
				
					result = gameRepository.getList(element);
					if (!element.setStructure(result)) {
						element.setId(0);
						element.setName(null);
						throw new Exception("Cannot read result set from DB");
					}
				}
			else {
				element.setId(0);
				element.setName(null);
				throw new Exception("Invalid input for Category selection");
			}
			gameRepository.closeConnection();
		}
		
		return true;
	}


	//Get the data with a name as filter
	public boolean getDataByName(DataStructure element)  throws Exception{
		if (element instanceof Borrow)
			return false;
		
		ResultSet result ;
		element.setId(-1);
		if(gameRepository.openConnection()) {
			result = gameRepository.getList(element);
			if (!element.setStructure(result)) {
				element.setId(0);
				element.setName(null);
				throw new Exception("Cannot read result set from DB");
			}
			gameRepository.closeConnection();
			return true;
		}
		return false;
	}

	//retrieve a list of elements using generics and factory pattern to create a new object without knowing the class
	@SuppressWarnings({ "deprecation", "unchecked" })
	public <T> List<T> fillList(T element ) {
		List<T> list = new ArrayList<T>();
		
		try {
			Class cl = Class.forName(element.getClass().getName());
			if(((DataStructure) element).getId() == 0)
				((DataStructure) element).setId(-1);
			if(((DataStructure) element).getName().isEmpty() || "".equals(((DataStructure) element).getName()))
				((DataStructure) element).setName(null);
			if(gameRepository.openConnection()) {
				ResultSet result = gameRepository.getList((DataStructure)element);
				
				while(true) {
					Object newObject =  cl.newInstance();
					if (!((DataStructure) newObject).setStructure(result))
						break;
					list.add((T) newObject);
				}
				gameRepository.closeConnection();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	//Register user and password
	public boolean setDBConf(String user, String password) {
		gameRepository.setDBConf(user, password);
		if (gameRepository.openConnection()) {
			gameRepository.closeConnection();
			return true;
		}
		else{
			return false;
		}
	}

	///Insert a new borrowing based on the game and the borrower
	public boolean registerBorrowing(Game game, Borrower borrower) {
		boolean swSuccess = false;;
		Borrow borrow = new Borrow(-1, game.getId(), borrower.getId(), LocalDate.now(), null);
		if(gameRepository.openConnection()) {
			swSuccess = gameRepository.setBorrow(borrow);
			gameRepository.closeConnection();
			return swSuccess;
		}
		else 
			return false;
	}

	//update a specific row (based on the id ) in the DB
	public boolean updateRow(DataStructure update) {
		boolean swSuccess = false;;
		if(gameRepository.openConnection()) {
			swSuccess = gameRepository.updateRow(update);
			gameRepository.closeConnection();
			return swSuccess;
		}
		else 
			return false;
	}
	
}
