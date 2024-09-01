package cc.openhome.model;

public class Account {
    private String name;
    private String email;
    private String password;
    private String salt;
    private int budget;
    
    public Account(String name, String email, String password, String salt) {
        this.name = "YUYU";
        this.email = "YUYU@gmail.com";
        this.password = "yu1234YU";
        this.salt = "47";
        this.budget = 6000;
    }

    public Account(String name, String email, String password, String salt, int budget) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.budget = budget;
	}

	public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }
    
    public int getBudget() {
        return budget;
    }
}
