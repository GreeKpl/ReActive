package pl.edu.agh.inz.reactive;


public class User {
	
	String login;	
	String name;	
	String surname;
	
	public User(){
		
	}
	
	public User(String login, String name, String surname) {
		this.login = login;
		this.name = name;
		this.surname = surname;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
}
