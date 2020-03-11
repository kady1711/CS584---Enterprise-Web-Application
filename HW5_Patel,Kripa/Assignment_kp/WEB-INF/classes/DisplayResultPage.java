import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class DisplayResultPage extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		Utilities util=new Utilities(request,out);
		if(request.getAttribute("checkMsg") != null )
		{
			out.println("<h2 style='background-color:green;color:white;'>"+(String)(request.getAttribute("checkMsg"))+"</h2>");
			request.setAttribute("checkMsg","");
		}
		
		String firstName=null;
		if(session.getAttribute("UserDetail") != null)
		{
			firstName=((UserDetail)(session.getAttribute("UserDetail"))).getFirstName();
		}
		
		Item item = (Item)request.getAttribute("itemObj");
	
		util.parseHtml("header.html");
		out.println("<div id='body' style=\"background-color:white;\">");
		out.println("<section id='content'>");
		out.println("<h1 align=\"center\"><span style='color:blue;'>Product Details</span></h1><article><table><tr><td width=\"23%\">");
		out.println("<img style=';margin-right:14px;width:99px;height:199px;display:block;margin-left:14px' src=\"images\\"+item.getImage()+"\" ></td>");
		out.println("<td width=\"50%\"><table>");
		out.println(" <tr> <td>Name: "+item.getName()+"</td></tr>");
		out.println(" <tr><td> Color: "+item.getColor()+"</td></tr>");
		out.println(" <tr><td> Price: $"+item.getPrice()+"</td></tr>");	
		out.println("</table></td><td>");
		
		out.println("<table>");
		out.println("<form action ='AddCartPage'>");
		out.println("<input type='hidden' name='pId' value='"+item.getId()+"'>");
		out.println("<input type='hidden' name='pCategory' value='"+item.getCategory()+"'>");
		out.println("<input type='hidden' name='pName' value='"+item.name+"'>");
		if(firstName!=null && !(firstName.isEmpty()))
		{	
			out.println("<tr><td width=\"28%\"><input name=\"action\" type=\"submit\" style='background-color:blue;color:white;padding:14px 20px;margin: 8px 0;width:100%;' class=\"button\"  value=\"Write New Reviews\"></td>");
			out.println("<td width=\"28%\"><input name=\"action\" class=\"button\" type=\"submit\" style='background-color:blue;color: white;padding:14px 20px;margin:8px;width:100%;' value=\"View Product Reviews\"></td></tr>");
			out.println("<tr><td  colspan='2' width=\"28%\"><input class=\"button\" name=\"action\" type=\"submit\" style='background-color:#4CAF50;color: white;padding:14px 20px;margin:8px;width:100%;'  value=\"Add To Cart\"></td></tr>");
		}
		out.println("<tr><td  colspan='2' width=\"30%\"><input class=\"button\" type=\"submit\" name=\"action\" style='cursor: pointer;color:white;background-color:red;padding:16px 22px;margin:8px;width:100%;' value=\"View Product\"></td></tr>");
		out.println("</form>");
		out.println("</table></td></tr></table></article></section>");
		util.parseHtml("nav.html");
		util.parseHtml("footer.html");
		
	}
}