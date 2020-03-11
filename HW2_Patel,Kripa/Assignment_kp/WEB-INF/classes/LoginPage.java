import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;
import java.net.*;

public class LoginPage extends HttpServlet
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
		+"<article> <h2 style='color:green' >Login</h2></br> "
		+"<form action='CheckLogin' style='border: 2px solid #fff;'>"
		+" <div style='padding:15px;'>"
		+"<br><label><b>Username </b></label>"
		+"<input type='text' placeholder='Enter Username' name='username' required>"
		+"</br><br><label><b>Password </b></label>"
		+"<input type='password' placeholder='Enter Password' name='password' required width:100%;>"
		+" </br><br><input type=\"radio\" name=\"usertype\" value=\"customer\" checked> Customer  <input type=\"radio\" name=\"usertype\" value=\"salesManager\"> Sales Manager  <input type=\"radio\" name=\"usertype\" value=\"storeManager\"> Store Manager  "
		+"</br><button style='color: white;background-color: red;margin: 7px;padding: 10px 21px;width:100%;}' type='submit'>Login</button></div> </form>"
		+"<a href='RegisterPage'>Register</a>"
		+"</article></section></div>";		
		out.println(body);
		util.parseHtml("nav.html");
		util.parseHtml("footer.html");
	}
}