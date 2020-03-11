import java.sql.*;
import java.util.*;
import java.io.*;

public class AjaxUtility
{
	Connection conn = null;
	
	Connection con = null;
	public boolean connect()
	{
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SportsStalk?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","Amb!vert6");
			if(conn.isClosed() || conn==null){
				return false;
			}
			else
			{
				return true;
			}
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
			return false;
		}
	}
	
	public HashMap getAllEvents()
	{
		Events event = null;
		HashMap<String,Events> events = new HashMap<>();
		try
		{	
			PreparedStatement pst = conn.prepareStatement("select * from events");
			ResultSet rs = pst.executeQuery();
		
			//PreparedStatement ps =  con.prepareStatement("select * from products");
			//ResultSet rs = ps.executeQuery();

			while(rs.next())
			{
				event = new Events();
				//event.setPrice((float)rs.getDouble("pprice"));
				event.setId(rs.getString("id"));
				event.setName(rs.getString("name"));
				event.setDate(rs.getString("date"));
				event.setVenue(rs.getString("venue"));
				event.setCity(rs.getString("city"));
				event.setState(rs.getString("state"));
				event.setPostalCode(rs.getString("postalCode"));
				//event.setDescription(rs.getString("pdescription"));
				//event.setImage(rs.getString("pimage"));
				//event.setCategory(rs.getString("pcategory"));
				events.put(rs.getString("name"),event);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return events;
	}
	
}	