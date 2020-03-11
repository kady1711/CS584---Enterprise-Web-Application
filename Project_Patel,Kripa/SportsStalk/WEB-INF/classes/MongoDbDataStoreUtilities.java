import com.mongodb.*;
import java.util.*;
import java.io.*;
import com.mongodb.client.MongoDatabase; 

public class MongoDbDataStoreUtilities {
	
	MongoClient mongo= null;
	DB db= null;
	DBCollection userhistory = null;
	
	public void connect()
	{
		try{
			mongo= new MongoClient("localhost",27017);
			db= mongo.getDB("sportsstalkhistory");
			userhistory = db.getCollection("userhistory");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void addHistory(UserHistoryDetails userhistorydetails)
	{
		BasicDBObject doc = new BasicDBObject("title", "userhistory").
		
		append("userEmail",userhistorydetails.getUseremail()).
		//append("userName",userhistorydetails.getUsername()).
		append("eventId",userhistorydetails.getEventid()).
		append("eventName",userhistorydetails.getEventname());
		userhistory.insert(doc);
	}
	
	public HashMap getAllHistory()
	{
		HashMap<String,ArrayList<UserHistoryDetails>> userhistorydetails = new HashMap<String,ArrayList<UserHistoryDetails>>();
		DBCursor cursor = userhistory.find();
		
		while(cursor.hasNext())
		{
			BasicDBObject obj = (BasicDBObject)cursor.next();
			if(!userhistorydetails.containsKey(obj.getString("userEmail")))
			{
				ArrayList<UserHistoryDetails> listReview = new ArrayList<UserHistoryDetails>();
				userhistorydetails.put(obj.getString("userEmail"),listReview);
			}
			ArrayList<UserHistoryDetails> userhistorylist = userhistorydetails.get(obj.getString("userEmail"));
			
			UserHistoryDetails userhistorydetail = new UserHistoryDetails();
			
			userhistorydetail.setUseremail(obj.getString("userEmail"));
			//userhistorydetail.setUsername(obj.getString("userName"));
			userhistorydetail.setEventid(obj.getString("eventId"));
			userhistorydetail.setEventname(obj.getString("eventName"));
			userhistorylist.add(userhistorydetail);
			
			userhistorydetails.put(obj.getString("eventName"),userhistorylist);
		}
		return userhistorydetails;
	}
	
}