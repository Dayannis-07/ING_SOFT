package main.models;

public class User {
     private String name;
	private String lastName;
	private String email;
	private String password;
	private String userType;
	private boolean isAdmin;	
	
	public User(String name, String lastName, String email, String password, String userType, boolean isAdmin){
	this.name = name;
	this.lastName = lastName;
	this.email = email;
	this.password = password;
	this.userType = userType;
	this.isAdmin = isAdmin;
	}
	
	public void setPassword(String password){
		this.password = password;

	}
	
	public void setIsAdmin(boolean isAdmin){
		this.isAdmin = isAdmin;
	}
	
	public void setEmail(String email){
		this.email = email;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	public String getPassword(){
		return password;
	}
	
	public boolean getIsAdmin(){
		return isAdmin;
	}

	public String getEmail(){
		return email;
	}

	public String getName() {
		return name;
	}

	public String getLastName() {
		return lastName;
	}

	public String getUserType() {
		return userType;
	}
}