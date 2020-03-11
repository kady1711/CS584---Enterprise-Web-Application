import java.io.*;
import java.sql.*;
import java.io.IOException;
import java.util.*;

public class EventRecommenderUtility{
	
	static Connection conn = null;
    static String message;
	
	public static String getConnection()
	{

		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SportsStalk","root","Amb!vert6");							
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
			 message="unsuccessful";
		     return message;
		}
	}

	public HashMap<String,String> readOutputFile(){

		//String TOMCAT_HOME = System.getProperty("catalina.home");
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
		HashMap<String,String> prodRecmMap = new HashMap<String,String>();
		try {

            br = new BufferedReader(new FileReader(new File("C:\\tomcat\\tomcat-7.0.34-preconfigured\\apache-tomcat-7.0.34\\webapps\\SportsStalk\\matrixFactorizationBasedRecommendations.csv")));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] prod_recm = line.split(cvsSplitBy,2);
				prodRecmMap.put(prod_recm[0],prod_recm[1]);
            }
			
		} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
		}
		
		return prodRecmMap;
	}
	
	public static Events getProduct(String event){
		Events prodObj = new Events();
		try 
		{
			String msg = getConnection();
			String selectProd="select * from events where name=?";
			PreparedStatement pst = conn.prepareStatement(selectProd);
			pst.setString(1,event);
			ResultSet rs = pst.executeQuery();
		
			while(rs.next())
			{	
				prodObj = new Events(rs.getString("id"),rs.getString("status"),rs.getString("locale"),rs.getString("name"),rs.getString("date"),rs.getString("venue"),rs.getString("city"),rs.getString("state"),rs.getString("postalCode"),rs.getString("country"),rs.getString("catName"));
			}
			rs.close();
			pst.close();
			conn.close();
		}
		catch(Exception e)
		{
		}
		return prodObj;	
	}
}