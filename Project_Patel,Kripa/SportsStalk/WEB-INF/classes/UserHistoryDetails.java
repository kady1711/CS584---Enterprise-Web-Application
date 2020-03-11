
public class UserHistoryDetails implements java.io.Serializable{

	
	private String  userEmail;
	//private String  userName;
	private String eventId;
	private String eventName;
	
	
	
	
	public String getUseremail() {
		return userEmail;
	}
	public void setUseremail(String userEmail) {
		this.userEmail = userEmail;
	}
	/* public String getUsername() {
		return userName;
	}
	public void setUsername(String userName) {
		this.userName = userName;
	} */
	public String getEventid() {
		return eventId;
	}
	public void setEventid(String eventId) {
		this.eventId = eventId;
	}
	public String getEventname() {
		return eventName;
	}
	public void setEventname(String eventName) {
		this.eventName = eventName;
	}
	
	
}
