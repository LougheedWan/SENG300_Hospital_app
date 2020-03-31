
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
	public User(String name, String actor, String username) {
	this.firstName = name;
	this.actor = actor;
	this.username = username;
	
	}
	
	public String getType() {
		System.out.println(actor);
		return this.actor;
	}
	
	public String getUsername() {
		return this.username;
	}
	public String getName() {
		return this.firstName;
	}
	
	
	
}
