import java.io.*;

public class UserDetail implements Serializable{ 
	private String userId;
    private String email;
	private String password;
	private String firstname;
    private String lastname;
    private String city;
	private String preferences;
	
	public UserDetail(String userId, String email,String password,String firstname,String lastname,String city, String preferences)
	{
		this.userId=userId;
		this.email=email;
		//this.password=password;
		this.firstname=firstname;
		//this.lastname=lastname;
		this.city=city;
		this.preferences=preferences;
		
		//this.usertype=usertype;
	}
	public String getCity(){
		return city;
	}
	
    public String getLastName() {
        return lastname;
    }

	public String getFirstName() {
        return firstname;
    }
	
	public String getEmail() {
        return email;
    }
	
	public String getUserId() {
        return userId;
    }
	
	public String getPassword(){
		return password;
	}

	

    public void setLastName(String lastname) {
        this.lastname = lastname;
    }

    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }

    
    public void setUserId(String userId) {
        this.userId = userId;
    }
	
	public void setEmail(String email){
		this.email=email;
	}
	
    public void setPassword(String password) {
        this.password = password;
    }
	
	public void setCity(String city){
		this.city=city;
	}
}