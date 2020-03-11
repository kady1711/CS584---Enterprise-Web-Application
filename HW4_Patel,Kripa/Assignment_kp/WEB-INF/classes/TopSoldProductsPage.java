import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;
import java.net.*;

public class TopSoldProductsPage extends HttpServlet
{	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("text/html");
		PrintWriter out =response.getWriter();
		HttpSession session = request.getSession();
		Utilities util=new Utilities(request,out);
		if(request.getAttribute("checkMsg") != null)
		{
			out.println("<h3 style='color:black;background-color:blue;'>"+(String)(request.getAttribute("checkMsg"))+"</h3>");
			request.setAttribute("checkMsg","");
		}	
		util.parseHtml("header.html");
		out.println("<div id='body'>");
		out.println("<section id='content'>");
		out.println("<article>");
		MySqlDataStoreUtilities dbInstance = new MySqlDataStoreUtilities();
		dbInstance.connect();
		String firstname=null,usertype=null;
		if(session.getAttribute("UserDetail") != null)
		{
			firstname=((UserDetail)(session.getAttribute("UserDetail"))).getFirstName();
			usertype=((UserDetail)(session.getAttribute("UserDetail"))).getUserType();
		} 
		ArrayList<String> pNames = dbInstance.getTopSoldProducts();
		out.println("<table>");
		if(pNames.isEmpty())
		{
			out.println("<tr><h2 align=\"center\"><span style='color:red;'>No Products Present</span></h2></tr>");
		}
		else
		{
			for(String pName : pNames)
			{
					Item item= (Item) dbInstance.getProduct(pName);
					
					out.println("<tr>");
					out.println("<td width=\"30%\">");
					out.println("<img style='display:block;margin:12px;width:110px;height:210px;' src=\"images\\"+item.image+"\" >");
					out.println("</td>");
					out.println("<td width=\"40%\"><table>");
					out.println(" <tr> <td><b>Name: </b>"+item.name+"</td></tr>");
					out.println(" <tr><td><b> Price:</b> $"+item.price+"</td></tr>");	
					out.println("</table></td><td><table>");
					out.println("<form action = 'AddCartPage'>");
					out.println("<input type='hidden' name='pId' value='"+item.getId()+"'>");
					out.println("<input type='hidden' name='pCategory' value='"+item.getCategory()+"'>");
					out.println("<input type='hidden' name='pName' value='"+item.name+"'>");
					if(firstname!=null && !(firstname.isEmpty()))
					{	
						out.println("<tr><td width=\"28%\"><input name=\"action\" type=\"submit\" style='background-color:blue;color:white;padding:14px 20px;margin: 8px 0;width:100%;' class=\"button\"  value=\"Write New Reviews\"></td>");
						out.println("<td width=\"28%\"><input name=\"action\" class=\"button\" type=\"submit\" style='background-color:blue;color: white;padding:14px 20px;margin:8px;width:100%;' value=\"View Product Reviews\"></td></tr>");
						out.println("<tr><td  colspan='2' width=\"28%\"><input class=\"button\" name=\"action\" type=\"submit\" style='background-color:#4CAF50;color: white;padding:14px 20px;margin:8px;width:100%;'  value=\"Add To Cart\"></td></tr>");
					}
					out.println("<tr><td  colspan='2' width=\"30%\"><input class=\"button\" type=\"submit\" name=\"action\" style='cursor: pointer;color:white;background-color:red;padding:16px 22px;margin:8px;width:100%;' value=\"View Product\"></td></tr>");
					out.println("</form>");out.println("</table></td></tr>");
			}
		}
		out.println("</table></article></section>");		
		util.parseHtml("nav.html");
		util.parseHtml("footer.html");
	}
}