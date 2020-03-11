import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;
import java.net.*;

public class OrderUpdatePage extends HttpServlet
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
		+"<article> <h2 style='color:green' >Update Order</h2></br> "
		+"<form action='CheckUpdateOrder' style='border: 2px solid #fff;'>"
		+"<div style='padding:15px;'>"
		+"<label><b>OrderID</b></label>"
		+"<input type='text' placeholder='Enter Order ID' name='orderid' required>"
		
		+"<button style='color: white;background-color: red;margin: 7px;padding: 15px 21px;width:100%;}' type='submit'>Update</button></div> </form>"
		+"</article></section>";		
		out.println(body);
		util.parseHtml("nav1.html");
		util.parseHtml("footer.html");
	}
}