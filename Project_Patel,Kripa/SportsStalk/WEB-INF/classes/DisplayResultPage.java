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
		String email=null;
		/* if(session.getAttribute("UserDetail") != null)
		{
			firstName=((UserDetail)(session.getAttribute("UserDetail"))).getFirstName();
			email=((UserDetail)(session.getAttribute("UserDetail"))).getEmail();
		} */ 
		
		email=session.getAttribute("email").toString();
		//firstName=session.getAttribute("firstname").toString();
		
		Events event = (Events)request.getAttribute("itemObj");
		UserHistoryDetails userhistory = new UserHistoryDetails();
		userhistory.setUseremail(email);
		//userhistory.setUsername(firstName);
		userhistory.setEventname(event.getName());
		userhistory.setEventid(event.getId());
		
		MongoDbDataStoreUtilities mdb = new MongoDbDataStoreUtilities();
		mdb.connect();
		mdb.addHistory(userhistory);
	
		util.parseHtml("header.html");
		out.println("<div id='body' style=\"background-color:white;\">");
		out.println("<section id='content'>");
		//out.println("<h1 align=\"center\"><span style='color:blue;'>Product Details</span></h1><article><table><tr><td width=\"23%\">");
		//out.println("<img style=';margin-right:14px;width:99px;height:199px;display:block;margin-left:14px' src=\"images\\"+item.getImage()+"\" ></td>");
		out.println("<td width=\"50%\"><table>");
		out.println(" <tr> <td>Name: "+event.getName()+"</td></tr>");
		out.println(" <tr> <td>Name: "+event.getDate()+"</td></tr>");
		out.println(" <tr> <td>Name: "+event.getVenue()+"</td></tr>");
		out.println(" <tr> <td>Name: "+event.getCity()+"</td></tr>");
		out.println(" <tr> <td>Name: "+event.getState()+"</td></tr>");
		//out.println(" <tr><td> Color: "+event.getColor()+"</td></tr>");
		//out.println(" <tr><td> Price: $"+event.getPrice()+"</td></tr>");	
		out.println("</table></td><td>");
		
		out.println("<table>");
		//out.println("<form action ='AddCartPage'>");
		out.println("<input type='hidden' name='name' value='"+event.getName()+"'>");
		out.println("<input type='hidden' name='date' value='"+event.getDate()+"'>");
		out.println("<input type='hidden' name='venue' value='"+event.getVenue()+"'>");
		//if(firstName!=null && !(firstName.isEmpty()))
		//{	
			//out.println("<tr><td width=\"28%\"><input name=\"action\" type=\"submit\" style='background-color:blue;color:white;padding:14px 20px;margin: 8px 0;width:100%;' class=\"button\"  value=\"Write New Reviews\"></td>");
			//out.println("<td width=\"28%\"><input name=\"action\" class=\"button\" type=\"submit\" style='background-color:blue;color: white;padding:14px 20px;margin:8px;width:100%;' value=\"View Product Reviews\"></td></tr>");
			out.println("<tr><td  colspan='2' width=\"28%\"><input class=\"button\" name=\"action\" type=\"submit\" style='background-color:#4CAF50;color: white;padding:14px 20px;margin:8px;width:100%;'  value=\"Intersted\"></td></tr>");
		//}
		//out.println("<tr><td  colspan='2' width=\"30%\"><input class=\"button\" type=\"submit\" name=\"action\" style='cursor: pointer;color:white;background-color:red;padding:16px 22px;margin:8px;width:100%;' value=\"View Product\"></td></tr>");
		//out.println("</form>");
		out.println("</table></td></tr></table></article></section>");
		util.parseHtml("nav.html");
		util.parseHtml("footer.html");
		
	}
}