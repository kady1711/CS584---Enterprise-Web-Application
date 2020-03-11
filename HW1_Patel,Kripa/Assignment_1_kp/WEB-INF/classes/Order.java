import java.io.*;
import java.util.*;

public class Order implements Serializable{
   
	public HashMap<String, List<Object>> items;
	private String oId;
    private String emailaddress;
    private String oAmount;
    private String address;
	private String oDate;
	public Order(String oId,String emailaddress, String oAmount, String address,String oDate)
	{
		this.oDate=oDate;
		this.oId=oId;
		this.emailaddress=emailaddress;
		this.oAmount=oAmount;
		this.address=address;
		items = new HashMap<String, List<Object>>();
	}
	
    public String getEmail() {
        return emailaddress;
    }

	public String getOrderId() {
        return oId;
    }
	
	public void setOrderId(String oId) {
        this.oId = oId;
    }

    public void setAmount(String oAmount) {
        this.oAmount = oAmount;
    }
	
	public void setAddress(String address) {
        this.address = address;
    }
	
	public String getAmount() {
        return oAmount;
    }
	
	public String getAddress() {
        return address;
    }
	
	public String getOrderDate() {
        return oDate;
    }

    public void setEmail(String emailaddress) {
        this.emailaddress = emailaddress;
    }

  
	public HashMap getItems(){
	        return items;
	 }
	public void setItems(HashMap<String, List<Object>> items){
	        this.items = items;
	}
}