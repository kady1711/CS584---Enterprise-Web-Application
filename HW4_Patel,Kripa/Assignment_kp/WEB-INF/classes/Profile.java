import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class Profile extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("text/html");
		PrintWriter out =response.getWriter();
		HttpSession session= request.getSession();
		String usertype=null,lastname=null,username=null,firstname=null;
		Utilities util=new Utilities(request,out);
		util.parseHtml("header.html");
		if(session.getAttribute("UserDetail") != null)
		{
			username=((UserDetail)(session.getAttribute("UserDetail"))).getUserName();
			lastname=((UserDetail)(session.getAttribute("UserDetail"))).getLastName();
			firstname=((UserDetail)(session.getAttribute("UserDetail"))).getFirstName();
			usertype=((UserDetail)(session.getAttribute("UserDetail"))).getUserType();
		} 

		out.println("<div id='body'><section id='content'><article> <h2><span style='color:Green;'>"+"Profile<br><br> </span></h2>");
		out.println("UserName: "+username);
		out.println("<br>Name: "+firstname+" "+lastname+"<br>");
		out.println("UserType: "+usertype);
		out.println("</article></section>");
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