import java.io.*;
import java.sql.*;
import java.io.IOException;
import java.util.*;

public class ProductRecommenderUtility{
	
	Connection con = null;
    static String message;
	
	public boolean connect()
	{
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/WebApp","root","Amb!vert6");
			if(con.isClosed() || con==null){
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

	
	public Item getProductByName(String pName)
	{
		Item item = null;
		try
		{	
			PreparedStatement ps =  con.prepareStatement("select * from products where pname=?");
			ps.setString(1,pName);
			ResultSet rs = ps.executeQuery();

			while(rs.next())
			{
				item = new Item();
				item.setPrice((float)rs.getDouble("pprice"));
				item.setName(rs.getString("pname"));
				item.setRetailer(rs.getString("rname"));
				item.setCompany(rs.getString("pcompany"));
				item.setColor(rs.getString("pcolor"));
				item.setCondition(rs.getString("pcondition"));
				item.setId(rs.getInt("pid")+"");
				item.setDescription(rs.getString("pdescription"));
				item.setImage(rs.getString("pimage"));
				item.setCategory(rs.getString("pcategory"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return item;
	}
	
	public HashMap readCSVFile(){

		String f = "";
		String dir = System.getenv("ANT_HOME");
        BufferedReader reader = null;
        String seperator = ",";
		HashMap<String,String> itemMap = new HashMap<String,String>();
		try {

            reader = new BufferedReader(new FileReader(new File("C:\\tomcat\\tomcat-7.0.34-preconfigured\\apache-tomcat-7.0.34\\webapps\\Assignment_kp\\matrixFactorizationBasedRecommendations.csv")));
            while ((f = reader.readLine()) != null) {
                String[] recommendItem = f.split(seperator,2);
				itemMap.put(recommendItem[0],recommendItem[1]);
            }
			
		} catch (Exception e) {
            e.printStackTrace();
        }  finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
		}
		
		return itemMap;
	}
}