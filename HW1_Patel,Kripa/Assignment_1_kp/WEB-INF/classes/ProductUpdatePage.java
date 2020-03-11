import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;
import java.io.*;


public class ProductUpdatePage extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("text/html");
		PrintWriter out =response.getWriter();
		Utilities util=new Utilities(request,out);
		util.parseHtml("header1.html");
		if(request.getParameter("updateMsg")!=null)
		{
			out.println("<h2 style='color:white;background-color:green;'>"+(String)(request.getParameter("updateMsg"))+"</h2>");
		}
		HttpSession session=request.getSession();
		out.println("<div id='body' style=\"background-color:white;width:100%;\">");
		out.println("<section id='content' style=\"width:100%;background-color:white;\">");
		out.println("<article> <h1 align=\"center\"><span style='color:red;'>"+"Welcome Store Manager</span></h1> ");
		if(request.getAttribute("checkMsg") != null )
		{
			out.println("<h2 style='color:white;background-color:green;'>"+(String)(request.getAttribute("checkMsg"))+"</h2>");
			request.setAttribute("msg","");
		}
		
		String content="<form action='CheckUpdateProduct' style='border:2px #fff solid ;'>"
		+" <div style='padding:15px;'><h3 style='green' >Product Update</h3>"
		+"</br><label><b>Enter Product ID</b></label>"
		+"<input type='text' placeholder='Enter Id' name='pId' required></br></br>"
		+"<label><b>Select Product Category</b></label></br><select name='pCategory'></br></br>"
		+"<option value='speakers' selected>Sound System</option><option value='voiceassistant'>Voice Assistant</option><option value='phones'>Phones</option><option value='fwatches'>Fitness Watches</option><option value='laptops'>Laptops</option><option value='televisions'>Televisions</option><option value='watches'>Smart Watches</option><option value='headphones'>HeadPhones</option></select>"
		+"<button style='color:white;background-color:green;margin:6px;padding:12px 22px;width:100%;cursor:pointer;' type='submit'>Update Product</button></div></form>";
		
		out.println(content);
		out.println("</article></section>");
		//util.parseHtml("nav1.html");
		util.parseHtml("footer.html");
	}

}