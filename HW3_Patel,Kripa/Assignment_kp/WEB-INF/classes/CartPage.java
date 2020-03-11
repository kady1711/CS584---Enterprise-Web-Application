import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class CartPage extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("text/html");
		PrintWriter out =response.getWriter();
		HttpSession session= request.getSession();
		int j=1;
		String firstname=null,usertype=null;
		if(session.getAttribute("UserDetail") != null)
		{
			firstname=((UserDetail)(session.getAttribute("UserDetail"))).getFirstName();
			usertype=((UserDetail)(session.getAttribute("UserDetail"))).getUserType();
		} 
		
		Utilities util=new Utilities(request,out);
		util.parseHtml("header.html");
		if(firstname!=null && !(firstname.isEmpty()))
		{
			CartDetail cartdetail=(CartDetail)session.getAttribute("CartDetail");
			if(cartdetail == null)
			{
				out.println("<div id='body'><section id='content'><article><table><h2><span style='green;'>Empty Cart</span></h2>");
				out.println("</table></article></section>");
			}
			else
			{
				HashMap<String, List<Object>> objs = cartdetail.getAllItems();
				if(objs.size() == 0)
				{
					out.println("<div id='body'>");
					out.println("<section id='content'>");
					out.println("<article>");
					out.println("<table>");
					out.println("<h2><span style='red;'>No Items in Cart</span></h2>");
					out.println("</table>");
					out.println("</article>");
					out.println("</section>");
				}
				else{
					out.println("<div id='body'><section id='content'><article><table>");
					out.println("<tr><td>Index</td><td>Product Image</td><td>Product Name</td><td>Product Price</td><td>Quantity</td><td></td></tr>");
					for(Map.Entry<String, List<Object>> e : objs.entrySet()){
						String obj = e.getKey();
						List<Object> objects = e.getValue();
						out.println("<form method='get' action='FinalCheckoutPage'><input type='hidden' name='pname' value='"+obj+"'>");
						out.println("<tr><td>"+j+"</td><td><img src ='images//"+objects.get(3)+"' width = '100' height = '200'></td><td>"+objects.get(0)+" </td><td>"+"$"+objects.get(1)+"</td>");
						out.println("<td><select name='pqty'><option value='1' selected>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option></select></td>");
						out.println("<td><a href='DeleteCartPage?name="+obj+"'><button type='button' style='padding:15px 21px;cursor:pointer;background-color:red;color:white;margin:7px;width:100%;}'>Remove Item</button></a></td></tr>");
						j++;
					}
					out.println("<td colspan='6' align='center'><input type='submit' name='action' value='Checkout Time' style='padding:14px 20px;cursor:pointer;background-color:green;color:white;margin:8px;width:100%;}'></td></tr></form></table></article></section>");
				}
			}
		}
		else{
				out.println("<div id='body'><section id='content'><article><table>");
				out.println("<h2><span style='green;'>Login First to View Cart</span></h2>");
				out.println("</table></article></section>");
		}
		if(usertype!= null && usertype.equals("customer"))
		{
			util.parseHtml("nav.html");
		}
		else if(usertype!= null && usertype.equals("salesmanager")){
			util.parseHtml("nav1.html");
		}
		else{
			util.parseHtml("nav.html");
		}	
		util.parseHtml("footer.html");
	}
}