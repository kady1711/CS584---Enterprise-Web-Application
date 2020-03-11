import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class CheckUpdateOrder extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("text/html");
		PrintWriter out =response.getWriter();
		Utilities util=new Utilities(request,out);
		HttpSession session=request.getSession();
		
		HashMap<String,Order> orders = new HashMap<String,Order>();
		String dir = System.getenv("ANT_HOME");
		String orderid= request.getParameter("orderid");
		MySqlDataStoreUtilities dbInstance = new MySqlDataStoreUtilities();
		dbInstance.connect();
		orders = (HashMap)dbInstance.checkOidExists();
		String eMsg="";
		util.parseHtml("header.html");
		out.println("<div id='body' style=\"background-color:white;width:100%;\"><section id='content' style=\"background-color:white;width:100%;\">");
		out.println("<article> <h1 align=\"center\"><span style='color:green;'>"+"Order Details </span></h1> ");
	/*	try{
			File f=new File(dir+"\\webapps\\Assignment_kp\\PaymentDetails.txt");
			FileInputStream ifi= new FileInputStream(f);
			ObjectInputStream ioi = new ObjectInputStream(ifi);
			orders=(HashMap)ioi.readObject();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}  */
		
		if(orders.containsKey(orderid) && !(orders.isEmpty())){
			Order order= (Order)orders.get(orderid);
			String orderdate=order.getOrderDate();
			out.println("Order Posted Date: "+orderdate+"</br>");
			String cDate="",tD1="";
			try{
				String uDate=orderdate; 
				Calendar calender = Calendar.getInstance();    
				SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");    
				calender.setTime( df.parse(uDate));    
				calender.add( Calendar.DATE,14);    
				cDate=df.format(calender.getTime());    
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		out.println("Expected Delivery Date: "+cDate+"</br>");
		try{
				String uDate=orderdate;    
				Calendar calender = Calendar.getInstance();    
				SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");   	
				calender.setTime( df.parse(uDate));    
				calender.add( Calendar.DATE,9);    
				tD1=df.format(calender.getTime());    
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			Date nowToday= new Date();	
			Date toDate1= new Date(tD1);
			if(tD1 != null && toDate1.after(nowToday))
			{
				HashMap<String, List<Object>> Citems = dbInstance.getProductsForOrder(orderid) ;
				out.println("<form action='CheckOrder'>");
				out.println("<input type='hidden' name='orderid' value='"+orderid+"'></br>");
				int j=1;
				for(Map.Entry<String, List<Object>> en : Citems.entrySet())
				{
					String n = en.getKey();
					List<Object> ps = en.getValue();
					out.println("<article><h2 style='color:red'>Product#"+j+" Details</h2>");
					out.println("Order Product Name: "+ ps.get(0)+"</br>");
					out.println("Order Product Price: "+ ps.get(1)+"</br>");
					out.println("<input type='hidden' name='pname' value='"+n+"'>");
					out.println("Order Product Quantity: <input type='text' name='qty' value='"+ps.get(2)+"' ></article>");
					j++;
				}
				out.println("Order Amount: $"+order.getAmount());
				out.println("<button style='color:white;background-color:red;padding:15px 22px;cursor:pointer;margin:6px;width: 100%;' type='submit' >Update</button></form>");
			}
			else{
				out.println("Sorry Already Dispatched!");
				out.println("Order Amount: $"+order.getAmount());
			}	
		}
		else
		{
			eMsg="Not Found!";
			request.setAttribute("checkMsg",eMsg);
			RequestDispatcher dis = request.getRequestDispatcher("/HomeSAM");			
			dis.forward(request,response);
		}
		out.println("</article></section>");
		util.parseHtml("footer.html");
	}
	
	
}
		
	