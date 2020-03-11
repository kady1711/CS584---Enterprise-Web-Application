import javax.servlet.http.*;
import javax.servlet.*;
import java.util.*;
import java.io.*;

public class DealMatches implements Serializable {

	ArrayList<String> fileContents ;
	
	public HashMap getPromoProducts()
	{	
		String p = null;
		MySqlDataStoreUtilities dbInstance = new MySqlDataStoreUtilities();
		dbInstance.connect();
		HashMap<String, Item> promoProducts = new HashMap<String, Item>();
		fileContents = new ArrayList<String>();
		HashMap<String, Item> items = dbInstance.getAllItems();
		
		String dir = System.getenv("ANT_HOME");
		try
		{
			for(Map.Entry<String, Item> pair: items.entrySet())
			{
				//Map.Entry pair = (Map.Entry)it.next();
				if(!promoProducts.containsKey(pair.getKey()) && promoProducts.size()<=1 )
				{
					BufferedReader br = new BufferedReader(new FileReader(new File("C:\\tomcat\\tomcat-7.0.34-preconfigured\\apache-tomcat-7.0.34\\webapps\\Assignment_kp\\DealMatches.txt")));
					p = br.readLine();
					
					if(p!=null){
						do{
							if(p.contains(pair.getKey()))
							{
								fileContents.add(p);
								promoProducts.put(pair.getKey(), pair.getValue());
								break;
							}
						}while ((p=br.readLine()) != null);
					}
					else{
						return null;
					}
				}
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		return promoProducts;
	}
	
	public ArrayList getFileContents()
	{
		return fileContents;
	}
}