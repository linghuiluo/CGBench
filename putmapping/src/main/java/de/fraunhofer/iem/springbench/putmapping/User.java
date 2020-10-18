package de.fraunhofer.iem.springbench.putmapping;

public class User {

	private String name;
	private String email;
	private String password;
	
	User(String n, String e, String p){
		name=n;
		email=e;
		password = p;
	}
	
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getPass() {
		return password;
	}
	
	public void setPass(String p) {
		password = p;
	}
	public void setName(String n) {
		name = n;
	}
	
	public void setEmail(String e) {
		email = e;
	}
}
