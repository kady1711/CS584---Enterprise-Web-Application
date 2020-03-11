import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;


public class TotalSalesChart extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("text/html");
		PrintWriter out =response.getWriter();
		Utilities util=new Utilities(request,out);
		util.parseHtml("header1.html");
		
		HttpSession session=request.getSession();
		out.println("<div id='body' style=\"background-color:white;width:100%;\">");
		out.println("<section id='content' style=\"width:100%;background-color:white;\">");
		out.println("<article> <h1 align=\"center\"><span style='color:red;'>"+"Sales Chart</span></h1> ");
		if(request.getAttribute("checkMsg") != null )
		{
			out.println("<h2 style='color:white;background-color:green;'>"+(String)(request.getAttribute("checkMsg"))+"</h2>");
			request.setAttribute("checkMsg","");
		}
		out.println("<div id=\"sales_barchart\" ></div>");
		out.println("</article></section>");
		
		util.parseHtml("footer.html");
	}

}