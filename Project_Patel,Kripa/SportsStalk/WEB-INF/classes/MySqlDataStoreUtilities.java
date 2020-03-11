import java.sql.*;
import java.util.*;
                	
public class MySqlDataStoreUtilities
{
static Connection conn = null;
static String message;
public static String getConnection()
{

	try
	{
	Class.forName("com.mysql.jdbc.Driver").newInstance();
	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SportsStalk?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","Amb!vert6");							
	message="Successfull";
	return message;
	}
	catch(SQLException e)
	{
		message="unsuccessful";
		     return message;
	}
	catch(Exception e)
	{
		message=e.getMessage();
		return message;
	}
}

public static HashMap<String,Basketball> getBasketballs()
{	
	HashMap<String,Basketball> hm=new HashMap<String,Basketball>();
	try 
	{
		getConnection();
		
		String selectBasketball="select * from  events where catName=?";
		PreparedStatement pst = conn.prepareStatement(selectBasketball);
		pst.setString(1,"Basketball");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	Basketball tab = new Basketball(rs.getString("status"),rs.getString("locale"),rs.getString("name"),rs.getString("date"),rs.getString("venue"),rs.getString("city"),rs.getString("state"),rs.getString("postalCode"),rs.getString("country"));
				hm.put(rs.getString("id"), tab);
				tab.setId(rs.getString("id"));

		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

public static HashMap<String,Baseball> getBaseballs()
{	
	HashMap<String,Baseball> hm=new HashMap<String,Baseball>();
	try 
	{
		getConnection();
		
		String selectBaseball="select * from  events where catName=?";
		PreparedStatement pst = conn.prepareStatement(selectBaseball);
		pst.setString(1,"Baseball");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	Baseball tab = new Baseball(rs.getString("status"),rs.getString("locale"),rs.getString("name"),rs.getString("date"),rs.getString("venue"),rs.getString("city"),rs.getString("state"),rs.getString("postalCode"),rs.getString("country"));
				hm.put(rs.getString("id"), tab);
				tab.setId(rs.getString("id"));

		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

public static HashMap<String,Icehockey> getIcehockeys()
{	
	HashMap<String,Icehockey> hm=new HashMap<String,Icehockey>();
	try 
	{
		getConnection();
		
		String selectIcehockey="select * from  events where catName=?";
		PreparedStatement pst = conn.prepareStatement(selectIcehockey);
		pst.setString(1,"Hockey");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	Icehockey tab = new Icehockey(rs.getString("status"),rs.getString("locale"),rs.getString("name"),rs.getString("date"),rs.getString("venue"),rs.getString("city"),rs.getString("state"),rs.getString("postalCode"),rs.getString("country"));
				hm.put(rs.getString("id"), tab);
				tab.setId(rs.getString("id"));

		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

public static HashMap<String,Football> getFootballs()
{	
	HashMap<String,Football> hm=new HashMap<String,Football>();
	try 
	{
		getConnection();
		
		String selectFootball="select * from  events where catName=?";
		PreparedStatement pst = conn.prepareStatement(selectFootball);
		pst.setString(1,"Football");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	Football tab = new Football(rs.getString("status"),rs.getString("locale"),rs.getString("name"),rs.getString("date"),rs.getString("venue"),rs.getString("city"),rs.getString("state"),rs.getString("postalCode"),rs.getString("country"));
				hm.put(rs.getString("id"), tab);
				tab.setId(rs.getString("id"));

		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

public static HashMap<String,Golf> getGolfs()
{	
	HashMap<String,Golf> hm=new HashMap<String,Golf>();
	try 
	{
		getConnection();
		
		String selectGolf="select * from  events where catName=?";
		PreparedStatement pst = conn.prepareStatement(selectGolf);
		pst.setString(1,"Golf");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	Golf tab = new Golf(rs.getString("status"),rs.getString("locale"),rs.getString("name"),rs.getString("date"),rs.getString("venue"),rs.getString("city"),rs.getString("state"),rs.getString("postalCode"),rs.getString("country"));
				hm.put(rs.getString("id"), tab);
				tab.setId(rs.getString("id"));

		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

public static HashMap<String,Tennis> getTenniss()
{	
	HashMap<String,Tennis> hm=new HashMap<String,Tennis>();
	try 
	{
		getConnection();
		
		String selectTennis="select * from  events where catName=?";
		PreparedStatement pst = conn.prepareStatement(selectTennis);
		pst.setString(1,"Tennis");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	Tennis tab = new Tennis(rs.getString("status"),rs.getString("locale"),rs.getString("name"),rs.getString("date"),rs.getString("venue"),rs.getString("city"),rs.getString("state"),rs.getString("postalCode"),rs.getString("country"));
				hm.put(rs.getString("id"), tab);
				tab.setId(rs.getString("id"));

		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

	public static void setFavoriteItems(String userName, String eventId) {
		
		try {
			getConnection();
		if (conn == null) {
			return;
		}
		String query = "INSERT INTO history values(?, ?)";
		
			PreparedStatement statement = conn.prepareStatement(query);
			
				statement.setString(1, userName);
				statement.setString(2, eventId);
				statement.execute();
			
		} catch (Exception e) {
			
		}

	}

	
	public String verifyLogin(String email, String password) {
		
		getConnection();
		try {
			String sql = "SELECT email from userDetails WHERE email = ? and password = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, email);
			statement.setString(2, password);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				return "Success";
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "Unsuccessfll";
	}
	
	
	public String register(String email, String password, String firstname, String lastname, String city, String preferences) {
		try{
			getConnection();
		String query = "INSERT INTO userDetails(firstname, lastname, email, password, city, preferences) VALUES (?, ?, ?, ?, ?, ?)";
	
			// check if user_id has been registered
			System.out.println(email);
			/*String sql = "SELECT email from users WHERE email = ? ";
			PreparedStatement statement1 = conn.prepareStatement(sql);
			statement1.setString(1, email);
			ResultSet rs = statement1.executeQuery();
			String username = null;
			while (rs.next()) {
				username = rs.getString("email");
				if (username.equals(email)) {
					return false;
				}
			}*/
			// not registered
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, firstname);
			statement.setString(2, lastname);
			statement.setString(3, email);
			statement.setString(4, password);
			statement.setString(5, city);
			statement.setString(6, preferences);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Success";
	}
	
	public ArrayList getRecommendationByCity(String cityused)
	{
		
		getConnection();
		
		//String cityused = getCity(em);
		ArrayList<Events> events = new ArrayList<Events>();	
		try
		{	
			PreparedStatement ps =  conn.prepareStatement("select DISTINCT * from events where city = ?");
			ps.setString(1,cityused);
			ResultSet rs = ps.executeQuery();
			System.out.println(ps);
			while(rs.next())
			{
				Events event = new Events();
				event.setId(rs.getString("id"));
				event.setStatus(rs.getString("status"));
				event.setLocale(rs.getString("locale"));
				event.setName(rs.getString("name"));
				event.setDate(rs.getString("date"));
				event.setVenue(rs.getString("venue"));
				event.setCity(rs.getString("city"));
				event.setState(rs.getString("state"));
				event.setPostalCode(rs.getString("postalCode"));
				event.setCountry(rs.getString("country"));
				event.setType(rs.getString("catName"));
				events.add(event);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return events;
	}
	
	public String getCity(String em){
		String usercity = null;
		
		getConnection();
		try
		{
		
		PreparedStatement ps = conn.prepareStatement("select city from userDetails where email = ?");
		ps.setString(1,em);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()){
			usercity = rs.getString("city");
		}
		
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return usercity;
	}
	
	public ArrayList getRecommendationByPreference(String pr)
	{
		
		getConnection();
		
		String preferenceused = getPreference(pr);
		ArrayList<Events> events = new ArrayList<Events>();	
		try
		{	
			PreparedStatement ps =  conn.prepareStatement("select DISTINCT * from events where catName = ?");
			ps.setString(1,preferenceused);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				Events event = new Events();
				event.setId(rs.getString("id"));
				event.setStatus(rs.getString("status"));
				event.setLocale(rs.getString("locale"));
				event.setName(rs.getString("name"));
				event.setDate(rs.getString("date"));
				event.setVenue(rs.getString("venue"));
				event.setCity(rs.getString("city"));
				event.setState(rs.getString("state"));
				event.setPostalCode(rs.getString("postalCode"));
				event.setCountry(rs.getString("country"));
				event.setType(rs.getString("catName"));
				events.add(event);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return events;
	}
	
	public String getPreference(String pr){
		String userpreference = null;
		
		getConnection();
		try
		{
		
			PreparedStatement ps = conn.prepareStatement("select preferences from userDetails where email = ?");
			ps.setString(1,pr);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				userpreference = rs.getString("preferences");
			}
		
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return userpreference;
	}


}