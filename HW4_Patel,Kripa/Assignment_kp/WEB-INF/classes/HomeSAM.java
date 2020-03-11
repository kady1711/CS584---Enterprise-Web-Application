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


public class HomeSAM extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("text/html");
		PrintWriter out =response.getWriter();
		Utilities util=new Utilities(request,out);
		util.parseHtml("header.html");
		
		HttpSession session=request.getSession();
		out.println("<div id='body' style=\"background-color:white;width:100%;\"><section id='content' background-color:white;\">");
		out.println("<article> <h1 align=\"center\"><span style='color:green;'>"+"Welcome Sales Manager </span></h1> ");
		if(request.getAttribute("checkMsg") != null )
		{
			out.println("<h2 style='color:white;background-color:green;'>"+(String)(request.getAttribute("checkMsg"))+"</h2>");
			request.setAttribute("msg","");
		}
		out.println("</article></section>");
		util.parseHtml("nav1.html");
		util.parseHtml("footer.html");
	}

}