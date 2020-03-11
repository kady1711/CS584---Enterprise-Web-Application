import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;
import java.net.*;
import java.util.Map.Entry;

public class FilterByPreferencePage extends HttpServlet
{	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("text/html");
		PrintWriter out =response.getWriter();
		HttpSession session = request.getSession();
		Utilities util=new Utilities(request,out);
		/* if(request.getAttribute("checkMsg") != null)
		{
			out.println("<h3 style='color:black;background-color:blue;'>"+(String)(request.getAttribute("checkMsg"))+"</h3>");
			request.setAttribute("checkMsg","");
		} */	
		MySqlDataStoreUtilities dbInstance = new MySqlDataStoreUtilities();
		
		//List<String> r = dbInstance.getTopZipCodes();
		String email=null;
		util.parseHtml("header.html");
		out.println("<div id='body'>");
		//out.println("<section id='content'>");
		out.println(" <h1 align=\"center\"><span style='color:red;'>Recommendation By Preference</span></h1> <table>");
		out.println("<article>");
		
		if(session.getAttribute("email") != null)
		{
			List<Events> events = new ArrayList<Events>();
			email=(session.getAttribute("email").toString());
			events = dbInstance.getRecommendationByPreference(email);
			
			for(Events event:events){
				//System.out.println(event.getName());
				out.println("<div id='body' >");
				//out.println("<section id='content'>");
				//out.println("<h1 align=\"center\"><span style='color:blue;'>Product Details</span></h1><article><table><tr><td width=\"23%\">");
				//out.println("<img style=';margin-right:14px;width:99px;height:199px;display:block;margin-left:14px' src=\"images\\"+item.getImage()+"\" ></td>");
				out.println("<tr class='card'><div id='shop_item'>");
				out.println("<td width='40%'><h3>"+event.getName()+"</h3></td>");
				out.println("<td width='20%'>"+event.getDate()+"</td>");
				out.println("<td width='20%'>"+event.getVenue()+"</td>");
				out.println("<td width='10%'>"+event.getCity()+"</td>");
				out.println("<td width='5%'>"+event.getState()+"</td>");
				out.println("<form class = 'submit-button' method = 'get' action = ''>"+
					"<td width='25%'><input class = 'submit-button' type = 'submit' value='Interested Event'></td>" +
					"</form></tr>");
				//out.println(" <tr><td> Color: "+event.getColor()+"</td></tr>");
				//out.println(" <tr><td> Price: $"+event.getPrice()+"</td></tr>");	
				out.println("</div></tr>");
		
			}
			//firstname=((UserDetail)(session.getAttribute("UserDetail"))).getFirstName();
			
			//usertype=((UserDetail)(session.getAttribute("UserDetail"))).getUserType();
		} 
		else
		{
			out.println("User Must Login to view!!!");
		}
		
		
		//out.println("</table>");
		
		out.println("</article>");
		//out.println("</section>");
		out.println("</div></table>");
		util.parseHtml("nav.html");
		util.parseHtml("footer.html");
	}
}