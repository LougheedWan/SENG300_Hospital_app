
public abstract class User {
	// Instance Variables
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String middleName;
	private String actor;
	private int age;
	
	
	
	// Constructor
	public User(String name, String actor) {
	this.firstName = name;
	this.actor = actor;
	
	}
	
	public String getType() {
		return actor;
	}
	
	
}
