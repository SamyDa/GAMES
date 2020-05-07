package be.belfius.Daouri_Samy_Games;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.Stream;

import be.belfius.Daouri_Samy_Games.domain.Borrow;
import be.belfius.Daouri_Samy_Games.domain.Borrower;
import be.belfius.Daouri_Samy_Games.domain.Category;
import be.belfius.Daouri_Samy_Games.domain.Difficulty;
import be.belfius.Daouri_Samy_Games.domain.DifficultyLevel;
import be.belfius.Daouri_Samy_Games.domain.Game;
import be.belfius.Daouri_Samy_Games.repository.GameRepository;
import be.belfius.Daouri_Samy_Games.service.GameService;

/**
 * Hello world!
 *
 */
public class App 
{
	private static Scanner scanner = new Scanner(System.in);
    private static LocalDateTime currentDate = LocalDateTime.now();
    private static GameService  gameService = new GameService();
    
    public static void main(String[] args) {
        int choice = 0;
        System.out.println("GameApp from Samy Daouri  -  Version 0.1   - " +  currentDate);
        System.out.println();
        displayMenu();
        do{         
            System.out.println("\nWhich option do you want to execute (99 to display the menu again)?");      
            try {
	            choice = scanner.nextInt();
	            
            }catch(InputMismatchException e) {
            	choice = 99;
            	System.out.println("Only integers are allowed, please try again");
            	scanner.next();
            }
            processChoice(choice);
            System.out.println();
        }while(choice !=0 );
                    
    }

    private static void displayMenu() {
        System.out.println("-------------------------------------------------------------");
        System.out.println("----                     Menu                            ----");
        System.out.println("-------------------------------------------------------------");
        System.out.println("Available options :");
        System.out.println("\t 0. Close the application");
        System.out.println("\t 1. Show the first game category");
        System.out.println("\t 2. Show the fifth game ");
        System.out.println("\t 3. Show the first borrower ");
        System.out.println("\t 4. Show a game of your choice");
        System.out.println("\t 5. Show all games");
        System.out.println("\t 6. Show a list of games and choose a game");
        System.out.println("\t 7. Show borrowed games");
        System.out.println("\t 8. Advanced search: difficulty");
        System.out.println("\t 9. Complex search: borrowers");
        System.out.println("\n------------------------------------------------------------");
    }
    
    private static void processChoice(int choice) {
            
            switch(choice) {
            case 1 :
            	showFirstGameCategory();
                break;
            case 2 :
            	showFifthGame();
                break;
            case 3 :
            	showFirstBorrower();
                break;
            case 4 :
            	showGameFromName();
            	break;
            case 5 :
            	showAllGames();
                break;
            case 6 :
            	showAndPickGame();
                break;
            case 7 :
            	showBorrowedGame();
                break;
            case 8 :
            	searchAdvanced();
                break;
            case 9 :
            	searchComplex();
            	break;
            case 99:
            	displayMenu();
                break;
            default :
                if(choice !=0 )
                    System.out.println("This option is not allowed");
                break;
        }
    }

	private static void searchComplex() {
		
		
	}

	private static void showGameFromName() {
		//After entering, the first game with these letters is shown
		//Adjust this in such a way that clearly displays the name, publisher, age, price to the user.
		//First test this by entering a part of a game name with the correct uppercase and lowercase letters. Then adjust
		//your code so that you also get a correct result without having to worry about case sensitivity.
		//If no game is found, display an error message 	
		System.out.println("Please enter the name of the game you that want to display  : ");
		String gameName = scanner.next();
		Game game = new Game();
		game.setName(gameName);
		try {
			if(gameService.getDataByName(game)) {
				System.out.println("The first game matching your selection criterium is : ");
				System.out.println("Name : "+ game.getName() + ", Publisher : "+game.getEditor() + ", Recommended age : " + game.getAge() + ", Price : " + game.getPrice());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("The game has not been found");
		}
		
	}

	private static void showGame(int position) {
		try {
			Game game = new Game();
			gameService.getDataByPosition(game, position);
			System.out.println("Game information : ");
			System.out.println(game.toString());
			
		}catch (SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void searchAdvanced() {
		int choice = 0;
		System.out.println("List of difficulties : ");
		System.out.println("-----------------------");
		for(DifficultyLevel lvl : DifficultyLevel.values()) {
			System.out.println(lvl.getDifficultyOrder() + ". "+lvl.getDifficultyLevel());
		}
		while(choice < 1 || choice > 5) {
			System.out.println("Please select a difficulty in the following list (1 to 5) : ");
			try {
		            choice = scanner.nextInt();
	        }catch(InputMismatchException e) {
	         	choice = 0;
	         	System.out.println("Only integers between 1 and 5 are allowed, please try again");
	         	scanner.next();
	        }
		}
		
		DifficultyLevel chosenLvl = null;
		for(DifficultyLevel lvl :  DifficultyLevel.values()) {
			if(choice == lvl.getDifficultyOrder())
				  chosenLvl  = lvl;
		}
		
		DifficultyLevel lvl =chosenLvl;
		System.out.println("The list of games corresponding to the difficulty " + chosenLvl.getDifficultyLevel() + " : ");
		List<Game> gameList = gameService.fillList(new Game());
		gameList.stream().filter(n -> n.getDifficultyId()== lvl.getDifficultyOrder()).forEach(n -> System.out.println("\t" + n.getName()) );
		
	}

	private static void showBorrowedGame() {
		//-When the user chooses this, he receives the borrowed games where you have an overview of all games that have
		// been (or have been) borrowed.
		//-Display this information in 4 columns: the name of the game, the name of the borrower, the borrow date and the
		// return date (you can choose the order of the columns yourself).
		//-You order this on the name of the borrower (alphabetically) and on loan date.
		//-Add the possibiliy to search the name of the borrower. Do this by giving him the opportunity to enter the name of
		// the borrower (provide a list of all possible borrowers)
		//- He must have the choice between choosing a name or exiting.
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		List<Borrow> borrowList = new ArrayList<Borrow>();
		borrowList = gameService.fillList(new Borrow());
		//find and fill Game and Borrower for each borrow
		
		System.out.println(String.format("%-10s","Borrow") + "\t"  + String.format("%-10s", "Return") + "\t"  + String.format("%-35s", "Game Name")  + "\t"  + "Borrower Name" );
		System.out.println(String.format("%-10s","-------") + "\t"  + String.format("%-10s", "-------") + "\t"  + String.format("%-35s", "----------")  + "\t"  + "--------------" );
		System.out.println("(Note : if the return date has not been specified, it is displayed as"+dateFormat.format(new Date(0L))+")");
		System.out.println();
		borrowList.forEach((n) -> {
			Game game = new Game();
			Borrower borrower = new Borrower() ;
			n.setGame(game); 
			n.setBorrower(borrower); 
			try {
				gameService.getDataByPosition(game, n.getGameId());
				gameService.getDataByPosition(borrower, n.getBorrowerId());
			} catch (Exception e) {
				e.printStackTrace();
			}});
		Comparator<Borrow> compareByName = Comparator.comparing(Borrow::getBorrower, (s1,s2)-> {return s2.getName().compareTo(s1.getName());}).thenComparing(Borrow::getBorrowDate, (d1,d2) -> {return d2.compareTo(d1);});
		borrowList.stream().sorted((b1,b2) -> b1.getBorrower().getName().compareTo(b2.getBorrower().getName())).forEach(n -> {
			System.out.println(String.format("%1$10s",dateFormat.format(n.getBorrowDate())) +"\t"+ String.format("%1$10s",dateFormat.format(n.getReturnDate())) + "\t" +String.format("%1$-35s",n.getGame().getName()) + "\t"+n.getBorrower().getName());
			});
		while(true) {
			System.out.println("If you want to filter on a specific borrower, please enter a name (\"*\" to quit): ");
			String filterName = scanner.next();
			if(!filterName.equals("*")) {
				System.out.println(String.format("%-10s","Borrow") + "\t"  + String.format("%-10s", "Return") + "\t"  + String.format("%-35s", "Game Name")  + "\t"  + "Borrower Name" );
				System.out.println(String.format("%-10s","-------") + "\t"  + String.format("%-10s", "-------") + "\t"  + String.format("%-35s", "----------")  + "\t"  + "--------------" );
				System.out.println("(Note : if the return date has not been specified, it is displayed as"+dateFormat.format(new Date(0L))+")");
				borrowList.stream().filter((n) -> n.getBorrower().getName().toUpperCase().contains(filterName.toUpperCase())).forEach(n -> {
					System.out.println(String.format("%1$10s",dateFormat.format(n.getBorrowDate())) +"\t"+ String.format("%1$10s",dateFormat.format(n.getReturnDate())) + "\t" +String.format("%1$-35s",n.getGame().getName()) + "\t"+n.getBorrower().getName());});
			}
			else 
				return;
		}
	}

	private static void showAndPickGame() {
		//Show the name of the game and the category of game (not the category id)
		//When the user enters part of a name of a game, he gets all the details (from table game, category and difficulty) of
		//the selected game.
		
//		List<Game> gameList = new ArrayList<Game>();
		List<Game> gameList = gameService.fillList(new Game());
		gameList.forEach((n) ->{Category category = new Category(); n.setCategory(category); try {
			gameService.getDataByPosition(category, n.getCategoryId());
			System.out.println("\t" + n.getName() + " - " + category.getName() );
		} catch (Exception e) {
			e.printStackTrace();
		}});
		
		
		System.out.println("Please pick a game in the previous list and it will be described : ");
		String gameName =  scanner.next();
		
		Optional<Game> filterGame = gameList.stream().filter((n) -> n.getName().toUpperCase().contains(gameName.toUpperCase())).findFirst();
		
		if(filterGame.isPresent()) {
			Difficulty difficulty = new Difficulty();
			filterGame.get().setDifficulty(difficulty);
			try {
				gameService.getDataByPosition(difficulty, filterGame.get().getDifficultyId());
				System.out.println(filterGame.get().toString(filterGame.get().getCategory().getName(), filterGame.get().getDifficulty().getName()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("The game has not been found !");
		}
		
	}

	private static void showAllGames() {
		//When the user selects 5, he sees a list of games
		//This list of all games is sorted by name.
		//In this overview you only show the name of the game, the editor and the price
		List<Game> gameList = new ArrayList<Game>();
		gameList = gameService.fillList(new Game());
		gameList.stream().sorted((g1,g2) ->g1.getName().compareTo(g2.getName())).forEach((n)-> {System.out.println(n.getName() + " from "+ n.getAuthor() + " for the price of "+ n.getPrice());});
		
	}

	private static void showFirstBorrower() {
		//This retrieves the information from one borrower (with id 1) from the borrower table
		//Just show the name and the city
		try {
			Borrower borrower = new Borrower();
			gameService.getDataByPosition(borrower, 1);
			System.out.println("First game Borrower is " + borrower.getName()+ "(From "+borrower.getCity()+")");
		}catch (SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void showFifthGame() {
		//Get the information from the game with id 5 from the table ‘game’. Show all the information of that game (as a
		//check for later parts of the project).
		showGame(5);
	}

	private static void showFirstGameCategory() {
		//This will display all the information of one category (with nr = 1) from the table ‘Category’: the id (/!\ should not be allowed o_o) and category
		//name.
		try {
			Category category =new Category();
			gameService.getDataByPosition(category, 1);
			System.out.println("First game category is " + category.getName());
		}catch (SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
