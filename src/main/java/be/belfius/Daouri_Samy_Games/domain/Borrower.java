package be.belfius.Daouri_Samy_Games.domain;

public class Borrower {
	
	private int id ;
	private String name;
	private String street;
	private int houseNumber;
	private int busNumber;
	private int postCode;
	private String city;
	private String telephone;
	private String email;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public int getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}
	public int getBusNumber() {
		return busNumber;
	}
	public void setBusNumber(int busNumber) {
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
	public Borrower() {
		
	}
	
	public Borrower(int id, String name, String street, int houseNumber, int busNumber, int postCode, String city,
			String telephone, String email) {
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
	
	
	
	
	
}
