package be.belfius.Daouri_Samy_Games;


import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.belfius.Daouri_Samy_Games.domain.Borrow;
import be.belfius.Daouri_Samy_Games.domain.Borrower;
import be.belfius.Daouri_Samy_Games.domain.Category;
import be.belfius.Daouri_Samy_Games.domain.Difficulty;
import be.belfius.Daouri_Samy_Games.domain.DifficultyLevel;
import be.belfius.Daouri_Samy_Games.domain.Game;
import be.belfius.Daouri_Samy_Games.service.GameService;


public class App 
{
	private static Scanner scanner = new Scanner(System.in);
    private static LocalDateTime currentDate = LocalDateTime.now();
    private static GameService  gameService = new GameService();
    public static Logger logger = LoggerFactory.getLogger(App.class);
    
    
    public static void main(String[] args) {
    	System.setProperty("org.slf4j.simpleLogger.showDateTime", "true");
		System.setProperty("org.slf4j.simpleLogger.dateTimeFormat", "yyyy-MM-dd HH:mm:ss");
		
        int choice = 0;
        System.out.println("GameApp from Samy Daouri  -  Version 0.1   - " +  currentDate);
        System.out.println();
        getConnectionConf();
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

    private static void getConnectionConf() {
    	while(true) {
	    	System.out.println("Enter your DB user  : ");
	    	String user = scanner.next();
	    	
	    	System.out.println("Enter your DB password");
			String password = scanner.next();
			
			if(gameService.setDBConf(user, password))
				break;
    	}
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
        System.out.println("\t10. Search the borrowing on dates  ");
        System.out.println("\t11. Register a new borrowing ");
        System.out.println("\t12. Return a game");
        System.out.println("\n------------------------------------------------------------");
    }
    
    private static void processChoice(int choice) {
            logger.trace("User chose option : " + choice);
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
            case 10: 
            	searchBorrowing();
            	break;
            case 11 : 
            	registerBorrowing();
            	break;
            case 12 : 
            	returnGame();
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

	private static void returnGame() {
		System.out.println("Who is the Borrower returning the game ? ");
		String name = scanner.next();
		Borrower borrower = new Borrower();
		borrower.setId(-1);
		borrower.setName(name);
		int choice  = 0;
		List<Borrower> borrowerList = gameService.fillList(borrower);
		if (borrowerList.size() == 0 ) {
			System.out.println("Nobody was found with a name like "+ name + ". Please try again ") ;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return;
		}
		else if(borrowerList.size() > 1 ) {
			System.out.println("Please select the correct borrower in the below list (0 to leave) :");
			int i = 0;
			for(Borrower elBor : borrowerList) {
				i++;
				System.out.println("\t" + i +".\t" + elBor.getName());
			}
			while(choice< 1 || choice > borrowerList.size()) {
				try {
					choice = scanner.nextInt();
					if (choice ==0)
						return;
				}catch(InputMismatchException e) {
					choice = 0 ;
					scanner.next();
					System.out.println("The choice is incorrect. Only Integers (1 -> "+borrowerList.size()+" ) are allowed");
				}
			}
			borrower = borrowerList.get(choice-1); 
		}
		else if(borrowerList.size() == 1) {
			borrower = borrowerList.get(0); 
		}
		
		List<Borrow> borrowList = gameService.fillList(new Borrow());
		borrowList.forEach((n) -> {
			Game game = new Game();
			Borrower borr = new Borrower() ;
			n.setGame(game); 
			n.setBorrower(borr); 
			try {
				gameService.getDataByPosition(game, n.getGameId());
				gameService.getDataByPosition(borr, n.getBorrowerId());
			} catch (Exception e) {
				e.printStackTrace();
			}});
		//Compilation purpose
		Borrower bor = borrower;
		//List <Borrow> filteredList = borrowList.stream().filter(n -> n.getBorrower().getName().equals(bor.getName()) && (n.getReturnDate()==null)).collect(Collectors.toList());
		List <Borrow> filteredList = borrowList.stream().filter(n -> n.getBorrower().getName().equals(bor.getName()) && LocalDate.of(1,1,1).equals(n.getReturnDate())).collect(Collectors.toList());
		
		if(filteredList.size() == 0 ) {
			System.out.println("No pending borrowing has been found");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return;
		}
		System.out.println("The borrowing list for this person is : ");
		int i= 0;
		for(Borrow element : filteredList) {
			
			System.out.println("\t" + ++i + ".\t "+element.getGame().getName() + " (borrowed on "+ element.getBorrowDate() +")\t" + element.getReturnDate() );
		}
		choice = 0;
		while(choice< 1 || choice > filteredList.size()) {
			try {
				System.out.println("Please, select the game (index) to return (0 to leave):");
				choice = scanner.nextInt();
				if (choice==0)
					return;
			}catch(InputMismatchException e) {
				choice = 0 ;
				scanner.next();
				System.out.println("The choice is incorrect. Only Integers (1 -> "+filteredList.size()+" ) are allowed");
			}
		}
		filteredList.get(choice-1).setReturnDate(LocalDate.now());
		if(gameService.updateRow(filteredList.get(choice-1))) {
			System.out.println("The game has been returned");
		}
		else 
			System.out.println("Something went wrong, cannot be returned");;
		
		
	}

	private static void registerBorrowing() {
		System.out.println("Please enter (a part of ) the game name that is borrowed : ");
		List<Game> gameList = new ArrayList<Game>(); 
		while(gameList.isEmpty()) {
			System.out.println("( *** to leave)");
			String gameName = scanner.next();
			if("***".equals(gameName))
				return;
			
			Game game = new Game();
			game.setName(gameName);
			
			//get all the games that contain the game name
			gameList = gameService.fillList(game);
			
			if(gameList.isEmpty())
				System.out.println("Please try again, tha game has not been found ");
		}
		int i = 0;
		for(Game game : gameList) {
			i++;
			System.out.println("\t" + i + ".\t" +  game.getName() );
		}
		int choiceGame = 0;
		
		while(choiceGame > gameList.size() || choiceGame < 1 ) {
			
			System.out.println("Please select the index of the game you want to select ( *** to leave ): ");
			try {
				
				choiceGame = scanner.nextInt();
				
				if(choiceGame > gameList.size() || choiceGame < 1)
					System.out.println("This choice is invalid, please try again");
				
	        }catch(InputMismatchException e) {
	        	choiceGame = 0;
	        	System.out.println("Only integers are allowed, please try again");
	        	scanner.next();
	        	
	        }
			
			
		}
		
		System.out.println("Please enter (a part of ) the borrower : ");
		List<Borrower> borrowerList = new ArrayList<Borrower>(); 
		while(borrowerList.isEmpty()) {
			System.out.println("( *** to leave)");
			String borrowerName = scanner.next();
			if("***".equals(borrowerName))
				return;
			
			Borrower borrower = new Borrower();
			borrower.setName(borrowerName);
			
			//get all the games that contain the game name
			borrowerList = gameService.fillList(borrower);
			
			if(borrowerList.isEmpty())
				System.out.println("Please try again, tha borrower has not been found ");
		}
		
		i=0;
		for(Borrower borrower : borrowerList) {
			i++;
			System.out.println("\t" + i + ".\t" +  borrower.getName() );
		}
		
		int choiceBorrower = 0;
		
		while(choiceBorrower > borrowerList.size() || choiceBorrower < 1 ) {
			
			System.out.println("Please select the index of the game you want to select ( *** to leave ): ");
			try {
				
				choiceBorrower = scanner.nextInt();
				
				if(choiceBorrower > borrowerList.size() || choiceBorrower < 1)
					System.out.println("This choice is invalid, please try again");
				
	        }catch(InputMismatchException e) {
	        	choiceBorrower = 0;
	        	System.out.println("Only integers are allowed, please try again");
	        	scanner.next();
	        	
	        }
			
		}
		
		gameService.registerBorrowing( gameList.get(choiceGame-1) , borrowerList.get(choiceBorrower-1));
		
		
	}

	private static void searchBorrowing() {
		// Search a borrowing after a certain date
		//First, all the games are listed
		List<Borrow> borrowList = gameService.fillList(new Borrow());
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
		//Then, it is asked to the user on which date he wants to filter 
		System.out.println("What date do you want to search ? ");
		System.out.println("\t1.\tBorrowing date");
		System.out.println("\t2.\tReturning date");
		System.out.println("\t3.\tBorrowing & Returning date");
		int choice = 0;
		while(choice < 1 || choice > 3) {
			try {
				System.out.println("Enter the option number : ");
				choice = scanner.nextInt();
				if(choice < 1 || choice > 3)
					System.out.println("The selected option is not correct, please try again.");
			}catch(InputMismatchException e) {
				choice = 0;
				scanner.next();
				System.out.println("Only Integers are allowed");
			}
		}
		LocalDate borrowDate = null;
		LocalDate returnDate = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		
		if(choice == 1 || choice == 3 ) {
			while(borrowDate == null) {
				System.out.println("Please enter the borrowing date from which you want to filter : (DD/MM/YYYY)");
				String date = scanner.next();
				try {
					borrowDate = LocalDate.parse(date, formatter);
				}catch(DateTimeParseException e) {
					System.out.println("Error during the parsing of the date, please verify your input and try again ");
				}
			}
		}
		
		if (choice ==2 || choice ==3) {
			
			while(returnDate == null) {
				System.out.println("Please enter the returning date from which you want to filter : (DD/MM/YYYY)");
				String date = scanner.next();
				try {
				returnDate = LocalDate.parse(date, formatter);
				}catch(DateTimeParseException e) {
					System.out.println("Error during the parsing of the date, please verify your input and try again ");
				}
			}
		}
		
		//compilation purpose in labmda expression
		LocalDate dateFrom = borrowDate;
		LocalDate dateTo = returnDate;
		
		System.out.println(String.format("%-10s","Borrow") + "\t"  + String.format("%-10s", "Return") + "\t"  + String.format("%-35s", "Game Name")  + "\t"  + "Borrower Name" );
		System.out.println(String.format("%-10s","-------") + "\t"  + String.format("%-10s", "-------") + "\t"  + String.format("%-35s", "----------")  + "\t"  + "--------------" );
		switch(choice) {
		
		case 1 :
			
			borrowList.stream().filter(n -> n.getBorrowDate().isAfter(dateFrom)).forEach(n -> {
				System.out.println(String.format("%1$10s",n.getBorrowDate()) +"\t"+ String.format("%1$10s",n.getReturnDate()) + "\t" +String.format("%1$-35s",n.getGame().getName()) + "\t"+n.getBorrower().getName());});;
			break;
		case 2 : 
			//compilation purpose in labmda expression
			borrowList.stream().filter(n -> dateTo.isBefore(n.getReturnDate())).forEach(n -> {
				System.out.println(String.format("%1$10s",n.getBorrowDate()) +"\t"+ String.format("%1$10s",n.getReturnDate()) + "\t" +String.format("%1$-35s",n.getGame().getName()) + "\t"+n.getBorrower().getName());});;
			break;
		case 3 :
			//compilation purpose in labmda expression
			borrowList.stream().filter(n -> (dateFrom.isBefore(n.getReturnDate()) && dateTo.isAfter(n.getReturnDate()))).forEach(n -> {
				System.out.println(String.format("%1$10s",n.getBorrowDate()) +"\t"+ String.format("%1$10s",n.getReturnDate()) + "\t" +String.format("%1$-35s",n.getGame().getName()) + "\t"+n.getBorrower().getName());});;
			break;
		
		}
		
		
		
		
	}

	private static void searchComplex() {
		//- The user can search on a (part of a) name of a borrower, this is not always at the beginning of the name.
		//- He will receive an alphabetical list of borrowers, that have the search in their name.
		//- Show 4 columns: name, city, telephone, e-mail
		System.out.println("Please, enter a part of the name of the borrower you want to display : ");
		String chosenName = scanner.next();
		
		Borrower borrower = new Borrower();
		borrower.setId(-1);
		borrower.setName(chosenName);
		
		List<Borrower> borrowerList = gameService.fillList(borrower);
		System.out.println(String.format("%-35s","Name  ") + "\t"  + String.format("%-20s", "City  ") + "\t"  + String.format("%-20s", "Phone ")  + "\t"  + "Email" );
		System.out.println(String.format("%-35s","-------") + "\t"  + String.format("%-20s", "-----") + "\t"  + String.format("%-20s", "------")  + "\t"  + "------" );
		System.out.println();
		borrowerList.stream().sorted((b1,b2) -> b1.getName().compareTo(b2.getName())).forEach(n -> System.out.println(String.format("%1$-35s",n.getName()) +"\t"+ String.format("%1$-20s",n.getCity()) + "\t" +String.format("%1$-20s", n.getTelephone()) + "\t"+n.getEmail()));
		
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
		List<Borrow> borrowList = new ArrayList<Borrow>();
		borrowList = gameService.fillList(new Borrow());
		//find and fill Game and Borrower for each borrow
		
		System.out.println(String.format("%-10s","Borrow") + "\t"  + String.format("%-10s", "Return") + "\t"  + String.format("%-35s", "Game Name")  + "\t"  + "Borrower Name" );
		System.out.println(String.format("%-10s","-------") + "\t"  + String.format("%-10s", "-------") + "\t"  + String.format("%-35s", "----------")  + "\t"  + "--------------" );
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
		borrowList.stream().sorted(compareByName).forEach(n -> {
			System.out.println(String.format("%1$10s",n.getBorrowDate()) +"\t"+ String.format("%1$10s",n.getReturnDate()) + "\t" +String.format("%1$-35s",n.getGame().getName()) + "\t"+n.getBorrower().getName());
			});
		while(true) {
			System.out.println("If you want to filter on a specific borrower, please enter a name (\"*\" to quit): ");
			String filterName = scanner.next();
			if(!filterName.equals("*")) {
				System.out.println(String.format("%-10s","Borrow") + "\t"  + String.format("%-10s", "Return") + "\t"  + String.format("%-35s", "Game Name")  + "\t"  + "Borrower Name" );
				System.out.println(String.format("%-10s","-------") + "\t"  + String.format("%-10s", "-------") + "\t"  + String.format("%-35s", "----------")  + "\t"  + "--------------" );
				borrowList.stream().filter((n) -> n.getBorrower().getName().toUpperCase().contains(filterName.toUpperCase())).forEach(n -> {
					System.out.println(String.format("%1$10s",n.getBorrowDate()) +"\t"+ String.format("%1$10s",n.getReturnDate()) + "\t" +String.format("%1$-35s",n.getGame().getName()) + "\t"+n.getBorrower().getName());});
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
