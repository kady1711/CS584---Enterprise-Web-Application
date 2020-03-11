import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class CheckOrder extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("text/html");
		String orderid= request.getParameter("orderid");
		String dir = System.getenv("ANT_HOME");
		String n[]= request.getParameterValues("pname");
		String q[]= request.getParameterValues("qty");
		HashMap<String,Order> orders = new HashMap<String,Order>();
		float a=0;
	
		try{
			/*File f=new File(dir+"\\webapps\\Assignment_kp\\PaymentDetails.txt");
			FileInputStream ifi= new FileInputStream(f);
			ObjectInputStream ioi = new ObjectInputStream(ifi);
			orders=(HashMap)ioi.readObject(); */
			
			MySqlDataStoreUtilities dbInstance = new MySqlDataStoreUtilities();
			dbInstance.connect();
			orders =(HashMap)dbInstance.checkOidExists();
			
			if(orders.containsKey(orderid) && !(orders.isEmpty()))
			{
				Order order= (Order)orders.get(orderid);
				HashMap<String, List<Object>> c = order.getItems() ;
				for(int i=0; i<n.length;i++)
				{
					List<Object> ci= c.get(n[i]);
					float price= Float.parseFloat((String)ci.get(1));
					int qu=Integer.parseInt(q[i]);
					a=a+( price*(Integer.parseInt(q[i])));
					if(qu!= 0)
					{
						ci.set(2,(Integer.parseInt(q[i])));
						dbInstance.modifyProductsForOrder((int)ci.get(4),qu);
					}
					else{
						dbInstance.removeProductsForOrder((int)ci.get(4));
						c.remove(n[i]);
						
					}
				}
				
				String eMsg="";
				
				if(a == 0)
				{
					orders.remove(orderid);
					dbInstance.removeWholeOrder(orderid);
					eMsg="Order "+orderid +" Deleted!";
					
				}
				else{
					dbInstance.modifyOrderAmount(a,orderid);
					order.setAmount(a+"");
					order.setItems(c);
					orders.put(orderid,order);
					eMsg="Order "+orderid +" Updated!";
				}
				
				/*
				order.setAmount(a+"");
				order.setItems(c);
				orders.put(orderid,order);
				FileOutputStream ofo= new FileOutputStream(new File(dir+"\\webapps\\Assignment_kp\\PaymentDetails.txt"));
				ObjectOutputStream foo = new ObjectOutputStream(ofo);
				foo.writeObject(orders);
				foo.flush();
				foo.close();
				ofo.close();	*/
				
				
				request.setAttribute("checkMsg",eMsg);
				RequestDispatcher dis = request.getRequestDispatcher("/OrderUpdatePage");	
				dis.forward(request,response);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}
}