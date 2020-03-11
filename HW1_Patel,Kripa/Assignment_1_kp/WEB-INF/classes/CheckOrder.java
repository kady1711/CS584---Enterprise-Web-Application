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
			File f=new File(dir+"\\webapps\\Assignment_1_kp\\PaymentDetails.txt");
			FileInputStream ifi= new FileInputStream(f);
			ObjectInputStream ioi = new ObjectInputStream(ifi);
			orders=(HashMap)ioi.readObject();
			if(orders.containsKey(orderid) && !(orders.isEmpty()))
			{
				Order order= (Order)orders.get(orderid);
				HashMap<String, List<Object>> c = order.getItems() ;
				for(int i=0; i<n.length;i++)
				{
					List<Object> ci= c.get(n[i]);
					int qu=Integer.parseInt(q[i]);
					if(qu!= 0)
					{
						float price= (float) ci.get(1);
						ci.set(2,(Integer.parseInt(q[i])));
						a=a+( price*(Integer.parseInt(q[i])));
					}
					else{
						c.remove(n[i]);
					}
				}
				order.setAmount(a+"");
				order.setItems(c);
				orders.put(orderid,order);
				FileOutputStream ofo= new FileOutputStream(new File(dir+"\\webapps\\Assignment_1_kp\\PaymentDetails.txt"));
				ObjectOutputStream foo = new ObjectOutputStream(ofo);
				foo.writeObject(orders);
				foo.flush();
				foo.close();
				ofo.close();	
				
				String eMsg="Order "+orderid +" Updated!";
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