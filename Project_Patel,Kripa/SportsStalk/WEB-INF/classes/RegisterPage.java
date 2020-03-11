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
		+"<article> <h2 style='color:#B86363;margin-top:14px;' >SignUp</h2>"
		+"<form action='CheckRegister' style='border: 2px solid #fff;margin-top:14px;'>"
		+"<div style='padding:8px;'>"
		+""
		+"<input type='text' placeholder='Enter First Name' name='firstname' required >"
		+"<br><br>"
		+"<input type='text' placeholder='Enter Last Name' name='lastname' required>"
		+"<br><br>"
		+"<input type='text' placeholder='Enter Email' name='email' required>"
		+"<br><br>"
		+"<input type='password' placeholder='Enter Password' name='password' required>"
		+"<br><br>"
		+"<input type='text' placeholder='Enter City' name='city' required>"
		+"<br><br>"
		
		+"<input type=\"radio\" name='preferences' value='football' style='margin-right:4px;margin-left:12px;'> Football&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
		+"<input type=\"radio\" name='preferences' value='baseball' style='margin-right:4px;'> Baseball&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
		+"<input type=\"radio\" name='preferences' value='basketball' style='margin-right:4px;margin-left:12px;'> Basketball&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
		+"<input type=\"radio\" name='preferences' value='icehockey' style='margin-right:4px;'> Ice-Hockey&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
		+"<input type=\"radio\" name='preferences' value='tennis' style='margin-right:4px;margin-left:12px;'> Tennis&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
		+"<input type=\"radio\" name='preferences' value='golf' style='margin-right:4px;'> Golf<br>"
		+"<input type=\"hidden\" name='usertype' value='user' style='margin-right:4px;'> "
		
	/* 	+"<label><b>Age</b></label>"
		+"<input  placeholder='Enter Age' type='number' name='userage' required>" */
		
		+"<button style='color: white;background-color: #B86363;margin-left:14px;margin-top:14px;padding: 5px 5px;width:16%;}' type='submit'>Register</button></div> </form>"
		+"Already have an account?  <a href='LoginPage'>LogIn</a>"
		+"</article></section>";		
		out.println(body);
		util.parseHtml("footer.html");
	}
}