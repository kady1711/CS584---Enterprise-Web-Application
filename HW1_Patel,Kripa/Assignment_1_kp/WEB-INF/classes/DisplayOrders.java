import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;;

public class DisplayOrders extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("text/html");
		PrintWriter out =response.getWriter();
		Utilities util=new Utilities(request,out);
		HttpSession session = request.getSession();
		util.parseHtml("header.html");
		int i=0;
		String dir = System.getenv("ANT_HOME");
		UserDetail userDetail= (UserDetail) session.getAttribute("UserDetail");
		String username= userDetail.getUserName();
		
		HashMap<String,Order> orders = new HashMap<String,Order>();
		try{
			File f=new File(dir+"\\webapps\\Assignment_1_kp\\PaymentDetails.txt");
			FileInputStream fii= new FileInputStream(f);
			ObjectInputStream ioi = new ObjectInputStream(fii);
			orders=(HashMap)ioi.readObject();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			if(!(orders.isEmpty()))
			{
				for(Map.Entry<String,Order> e : orders.entrySet())
				{
					String oId = e.getKey();
					Order order = e.getValue();
					
						if(order.getEmail().equals(username))
						{
							out.println("<div id='body'><section id='content'><article><table><tr><td>Order Number</td><td>Email Address</td><td>Order Amount</td><td>OrderDate</td><td>Delivery Date</td><td>Shipping Address</td><td></td></tr>");
							break;
						}
				}
				for(Map.Entry<String, Order> e : orders.entrySet())
				{
					String oId = e.getKey();
					Order order = e.getValue();
						if(order.getEmail().equals(username))
						{
							String newDate="",newto="";
							try{
								String old=order.getOrderDate();   
								Calendar calender = Calendar.getInstance();    
								SimpleDateFormat df = new SimpleDateFormat( "yyyy/MM/dd" ); 
								calender.setTime( df.parse(old));    
								calender.add( Calendar.DATE, 14 );    
								newDate=df.format(calender.getTime());    
							}
							catch(Exception ex)
							{
								ex.printStackTrace();
							}
							
							out.println("<form action='RemoveOrderPage'><input type='hidden' name='orderid' value='"+oId+"'>");
							out.println("<tr><td>"+order.getOrderId()+"</td><td>"+order.getEmail()+"</td><td>"+"$"+order.getAmount()+"  </td><td>"+order.getOrderDate()+"</td><td>"+newDate+"</td><td>"+order.getAddress()+"</td><td>");
							try{
								String old=order.getOrderDate();   
								Calendar calender = Calendar.getInstance();    
								SimpleDateFormat df = new SimpleDateFormat( "yyyy/MM/dd" ); 
								calender.setTime( df.parse(old));    
								calender.add( Calendar.DATE, 14 );    
								newto=df.format(calender.getTime());    
							}
							catch(Exception ex)
							{
								ex.printStackTrace();
							}
							Date nowDate= new Date();
							Date new1= new Date(newto);
							
							if(newto != null && new1.after(nowDate))
							{
								out.println("<a href='RemoveOrderPage'><button type='submit' style=' background-color:green;padding:12px 22px;color: white;width:100%;margin:6px;cursor: pointer;}'>Cancel Order</button></a></td></tr>");
							}
							i++;
							out.println("</form>");
						}
				}
				if(i==0)
				{
					out.println("<div id='body'><section id='content'><article><table>");
					out.println("<h2><span style='red;'>Oops!! No Orders</span></h2></table></article></section>");
				}
				else{
					out.println("</table></article></section>");
				}
			}
			else{
				out.println("<div id='body'><section id='content'><article><table>");
				out.println("<h2><span style='red;'>Oops!! No Orders</span></h2></table></article></section>");
			}
		util.parseHtml("nav.html");
		util.parseHtml("footer.html");
	}
}