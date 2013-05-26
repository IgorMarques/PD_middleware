package library;

public class Librarian {
	private String username;
	private String password;
	
	public Librarian(String name, String password){
		this.username=name;
		this.password=password;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
