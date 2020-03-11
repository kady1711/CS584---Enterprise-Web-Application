import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;

public class Main extends HttpServlet
{
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
		util.parseHtml("header.html");
		util.parseHtml("body.html");
		util.parseHtml("nav.html");
		util.parseHtml("footer.html");
	}
}