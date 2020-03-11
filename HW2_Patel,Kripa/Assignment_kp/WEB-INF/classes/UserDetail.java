import java.io.*;

public class UserDetail implements Serializable{ 
    private String username;
	private String password;
	private String usertype;
    private String firstname;
    private String lastname;
	private String age;
	private String gender;
	public UserDetail(String firstname,String lastname, String username, String password,String usertype)
	{
		this.firstname=firstname;
		this.lastname=lastname;
		this.username=username;
		this.password=password;
		this.usertype=usertype;
	}
	public String getAge() {
        return age;
    }

	public void setAge(String age) {
        this.age=age;
    }
	
	public String getGender() {
        return gender;
    }

	public void setGender(String gender) {
        this.gender=gender;
    }
    public String getLastName() {
        return lastname;
    }

	public String getFirstName() {
        return firstname;
    }
	
	public String getUserType() {
        return usertype;
    }

	public void setUserType() {
        this.usertype=usertype;
    }
	
	public String getUserName() {
        return username;
    }
	
	public String getPassword() {
        return password;
    }

    public void setLastName(String lastname) {
        this.lastname = lastname;
    }

    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }

    
    public void setUserName(String username) {
        this.username = username;
    }
	
	
    public void setPassword(String password) {
        this.password = password;
    }
}