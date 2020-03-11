import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;

public class Main extends HttpServlet
{
	MySqlDataStoreUtilities dbInstance = null;
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
	{
		response.setContentType("text/html");
		PrintWriter out =response.getWriter();
		
		if(request.getAttribute("checkMsg") != null)
		{
			out.println("<h3 style='color:black;background-color:blue;'>"+(String)(request.getAttribute("checkMsg"))+"</h3>");
			request.setAttribute("checkMsg","");
		}
		Utilities util=new Utilities(request,out);
		out.println("<link rel='shortcut icon' href='images/SS.png'/><div class='parallax'>");
		util.parseHtml("header.html");
		
		//util.parseHtml("body.html");
		HttpSession session= request.getSession();
		String firstname=null,lastname=null,email=null;
		if(session.getAttribute("UserDetail") != null)
		{
			//firstname=((UserDetail)(session.getAttribute("UserDetail"))).getFirstName();
			//email=((UserDetail)(session.getAttribute("UserDetail"))).getEmail();
			email=(session.getAttribute("email")).toString();
			//lastname=((UserDetail)(session.getAttribute("UserDetail"))).getLastName();
			//System.out.println(email);
		} 
		//Utilities util=new Utilities(request,out);
		//util.parseHtml("header.html");
		//util.parseHtml("body.html");
		util.parseHtml("nav.html");
		out.println("</div>");
		util.parseHtml("body.html");
		util.parseHtml("footer.html");
	}
}