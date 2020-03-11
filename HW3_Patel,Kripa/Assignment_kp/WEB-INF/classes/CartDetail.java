import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;	
public class CartDetail {
	public HashMap<String, List<Object>> cartItems;
	int j=0;
	public CartDetail(){
		cartItems = new HashMap<String, List<Object>>();
	}
	
	public HashMap getAllItems(){
		return cartItems;
	}
	
	public void addItem(String id,String pName,float pprice,String pimage,int pquantity){
		List<Object> item = new ArrayList<Object>();
		item.add(pName);
		item.add(pprice);
		item.add(pquantity);
		item.add(pimage);
		j++;
		cartItems.put(pName+j, item);
	}
	 
	public void deleteItem(String name){
		cartItems.remove(name);
	}
}


