

import java.time.LocalDateTime;
import java.util.Scanner;

public class GameApp {

    private static Scanner scanner = new Scanner(System.in);
    private static LocalDateTime currentDate = LocalDateTime.now();
    
    
    public static void main(String[] args) {
                    
        int choice = 0;
        System.out.println("GameApp from Samy Daouri  -  Version 0.1   - " +  currentDate);
        System.out.println();  
        displayMenu();
        do{         
            System.out.println("\nWhich option do you want to execute (99 to display the menu again)?");                     
            choice = scanner.nextInt();
            processChoice(choice);
            System.out.println();
        }while(choice !=0 && choice != 11);
                    
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
            	showGame();
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
                break;
            default :
                if(choice !=0 )
                    System.out.println("This option is not allowed");
                break;
        }
    }

	private static void searchComplex() {
		// TODO Auto-generated method stub
		
	}

	private static void showGame() {
		// TODO Auto-generated method stub
		
	}

	private static void searchAdvanced() {
		// TODO Auto-generated method stub
		
	}

	private static void showBorrowedGame() {
		// TODO Auto-generated method stub
		
	}

	private static void showAndPickGame() {
		// TODO Auto-generated method stub
		
	}

	private static void showAllGames() {
		// TODO Auto-generated method stub
		
	}

	private static void showFirstBorrower() {
		// TODO Auto-generated method stub
		
	}

	private static void showFifthGame() {
		// TODO Auto-generated method stub
		
	}

	private static void showFirstGameCategory() {
		// TODO Auto-generated method stub
		
	}

}
