import com.mongodb.*;
import java.util.*;
import java.io.*;
import com.mongodb.client.MongoDatabase; 

public class MongoDbDataStoreUtilities {
	
	MongoClient mongo= null;
	DB db= null;
	DBCollection reviews = null;
	
	public void connect()
	{
		try{
			mongo= new MongoClient("localhost",27017);
			db= mongo.getDB("webappreviews");
			reviews = db.getCollection("reviews");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void addReview(ReviewDetail reviewDetail)
	{
		BasicDBObject doc = new BasicDBObject("title", "reviews").
		
		append("productRebate",reviewDetail.getProductRebate()).
		append("productOnSale",reviewDetail.getOnSale()).
		append("userId",reviewDetail.getUserId()).
		append("userAge",reviewDetail.getUserAge()).
		append("userGender",reviewDetail.getUserGender()).
		append("reviewRating", reviewDetail.getReviewRating()).
		append("reviewDate", reviewDetail.getReviewDate()).
		append("reviewText", reviewDetail.getReviewText()).
		append("retailerName",reviewDetail.getRetailerName()).
		append("retailerCity",reviewDetail.getRetailerCity()).
		append("retailerState",reviewDetail.getRetailerState()).
		append("retailerZip",reviewDetail.getRetailerZip()).
		append("productName", reviewDetail.getProductName()).
		append("productCategory", reviewDetail.getProductCategory()).
		append("productPrice", reviewDetail.getProductPrice()).
		append("productCompany", reviewDetail.getProductCompany()).
		append("userOccupation",reviewDetail.getUserOccupation());	
		reviews.insert(doc);
	}
	
	public HashMap getAllReviews()
	{
		HashMap<String,ArrayList<ReviewDetail>> reviewDetails = new HashMap<String,ArrayList<ReviewDetail>>();
		DBCursor cursor = reviews.find();
		
		while(cursor.hasNext())
		{
			BasicDBObject obj = (BasicDBObject)cursor.next();
			if(!reviewDetails.containsKey(obj.getString("productName")))
			{
				ArrayList<ReviewDetail> listReview = new ArrayList<ReviewDetail>();
				reviewDetails.put(obj.getString("productName"),listReview);
			}
			ArrayList<ReviewDetail> reviewlist = reviewDetails.get(obj.getString("productName"));
			
			ReviewDetail reviewDetail = new ReviewDetail();
			
			reviewDetail.setOnSale(obj.getString("productOnSale"));
			reviewDetail.setUserId(obj.getString("userId"));
			reviewDetail.setUserAge(obj.getString("userAge"));
			reviewDetail.setUserGender(obj.getString("userGender"));
			reviewDetail.setUserOccupation(obj.getString("userOccupation"));
			reviewDetail.setRetailerName(obj.getString("retailerName"));
			reviewDetail.setProductRebate(obj.getString("productRebate"));
			reviewDetail.setRetailerZip(obj.getString("retailerZip"));
			reviewDetail.setRetailerCity(obj.getString("retailerCity"));
			reviewDetail.setRetailerState(obj.getString("retailerState"));
			reviewDetail.setReviewRating(obj.getString("reviewRating"));
			reviewDetail.setReviewText(obj.getString("reviewText"));
			reviewDetail.setReviewDate(obj.getString("reviewDate"));
			reviewDetail.setProductName(obj.getString("productName"));
			reviewDetail.setProductCategory(obj.getString("productCategory"));
			reviewDetail.setProductPrice(obj.getString("productPrice"));
			reviewDetail.setProductCompany(obj.getString("productCompany"));
			reviewlist.add(reviewDetail);
			
			reviewDetails.put(obj.getString("productName"),reviewlist);
		}
		return reviewDetails;
	}
	
}