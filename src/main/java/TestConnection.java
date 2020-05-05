

import java.sql.*;
import java.util.Scanner;  

public class TestConnection {

	public static void main(String[] args) {
		
		System.out.println("Enter password");
		
		//Scanner scanner = new Scanner(System.in);
		
		//String password  = scanner.nextLine();
		
		String url = "jdbc:mysql://localhost:3306/games";
		//url+= "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"; 
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/games?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","Sa1_Sony_4");
			System.out.println("Test");
			//Cr�ation d'un objet Statement
			Statement state = con.createStatement();
			//L'objet ResultSet contient le r�sultat de la requ�te SQL
			ResultSet result = state.executeQuery("SELECT * FROM Category");
			//On r�cup�re les MetaData
			ResultSetMetaData resultMeta = result.getMetaData();
			
			System.out.println("\n**********************************");
			//On affiche le nom des colonnes
			for(int i = 1; i <= resultMeta.getColumnCount(); i++)
			System.out.print("\t" +resultMeta.getColumnName(i).toUpperCase() + "\t *");
			
			System.out.println("\n**********************************");
			while(result.next()){
				for(int i = 1; i <= resultMeta.getColumnCount(); i++)
				System.out.print("\t" + result.getObject(i).toString() +
				"\t |");
				System.out.println("\n---------------------------------");
				}
			result.close();
			state.close();
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  

	}

}
