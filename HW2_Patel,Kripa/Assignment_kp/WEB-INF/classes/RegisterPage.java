import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;
import java.net.*;

public class RegisterPage extends HttpServlet
{	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("text/html");
		PrintWriter out =response.getWriter();
		Utilities util=new Utilities(request,out);
		if(request.getAttribute("checkMsg") != null)
		{
			out.println("<h3 style='color:black;background-color:blue;'>"+(String)(request.getAttribute("checkMsg"))+"</h3>");
			request.setAttribute("checkMsg","");
		}	
		util.parseHtml("header.html");
		String body="<div id='body'>"
		+"<section id='content'>"
		+"<article> <h2 style='color:green' >Register</h2></br> "
		+"<form action='CheckRegister' style='border: 2px solid #fff;'>"
		+"<div style='padding:15px;'>"
		+"<label><b>Username</b></label>"
		+"<input type='text' placeholder='Enter Username' name='username' required>"
		+"<br><br><label><b>Password</b></label>"
		+"<input type='password' placeholder='Enter Password' name='password' required>"
		+"<br><br><label><b>FirstName</b></label>"
		+"<input type='text' placeholder='Enter First Name' name='firstname' required >"
		+"<br><br><label><b>LastName</b></label>"
		+"<input type='text' placeholder='Enter Last Name' name='lastname' required>"
		+"<label><b>Gender</b></label></br>"
		+"<input type=\"radio\" name=\"usergender\" value=\"female\"> Female<br>"
		+"<input type=\"radio\" name=\"usergender\" value=\"male\" checked> Male<br>"
		
		+"<label><b>Age</b></label>"
		+"<input  placeholder='Enter Age' type='number' name='userage' required>"
		
		+"<button style='color: white;background-color: red;margin: 7px;padding: 15px 21px;width:100%;}' type='submit'>Register</button></div> </form>"
		+"</article></section>";		
		out.println(body);
		util.parseHtml("nav.html");
		util.parseHtml("footer.html");
	}
}