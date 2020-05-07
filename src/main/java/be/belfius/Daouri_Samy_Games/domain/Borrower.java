package be.belfius.Daouri_Samy_Games.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Borrower extends DataStructure{
	
	private String street;
	private String houseNumber;
	private String busNumber;
	private int postCode;
	private String city;
	private String telephone;
	private String email;
	
	
	public Borrower() {
		this.tableName = "Borrower";
		this.nameColumn= "borrower_name";
	}
	
	public Borrower(int id, String name, String street, String houseNumber, String busNumber, int postCode, String city,
			String telephone, String email) {
		this.nameColumn= "borrower_name";
		this.tableName = "Borrower";
		this.id = id;
		this.name = name;
		this.street = street;
		this.houseNumber = houseNumber;
		this.busNumber = busNumber;
		this.postCode = postCode;
		this.city = city;
		this.telephone = telephone;
		this.email = email;
	}
	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	public String getBusNumber() {
		return busNumber;
	}
	public void setBusNumber(String busNumber) {
		this.busNumber = busNumber;
	}
	public int getPostCode() {
		return postCode;
	}
	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	@Override
	public boolean setStructure(ResultSet set) {
		try {
			if(set.next()) {
				
				this.id = set.getInt("id");
				this.name = set.getString("borrower_name");
				this.street = set.getString("street");;
				this.houseNumber = set.getString("house_number");;
				this.busNumber = set.getString("bus_number");;
				this.postCode = set.getInt("postCode");
				this.city = set.getString("city");;
				this.telephone = set.getString("telephone");;
				this.email = set.getString("email");;
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
