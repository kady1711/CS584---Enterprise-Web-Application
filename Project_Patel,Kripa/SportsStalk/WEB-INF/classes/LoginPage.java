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
		+"<article> <h2 style='color:#B86363;margin-top:14px;'>LogIn</h2>"
		+"<form action='CheckLogin' style='border: 2px solid #fff;margin-top:14px;'>"
		+"<div style='padding:8px;'>"
		+"<input type='text' placeholder='Enter Email' name='email' required>"
		+"<br><br>"
		+"<input type='password' placeholder='Enter Password' name='password' required>"
		+"</br><button style='color: white;background-color: #B86363;margin-left:14px;margin-top:14px;padding: 5px 5px;width:16%;}' type='submit'>LogIn</button></div> </form>"
		+"Don't have an account?  <a href='RegisterPage'>Register</a>"
		+"</article></section>";		
		out.println(body);
		util.parseHtml("footer.html");
	}
}