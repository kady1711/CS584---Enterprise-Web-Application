import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class DailySalesPage extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("text/html");
		PrintWriter out =response.getWriter();
		Utilities util=new Utilities(request,out);
		util.parseHtml("header1.html");
		
		HttpSession session=request.getSession();
		out.println("<div id='body' style=\"background-color:white;width:100%;\">");
		out.println("<section id='content' style=\"background-color:white;\">");
		out.println("<article> <h1 align=\"center\">");
		out.println("<span style='color:blue;'>"+"Inventory Report</span></h1> ");
		if(request.getAttribute("checkMsg") != null )
		{
			out.println("<h2 style='color:white;background-color:blue;'>"+(String)(request.getAttribute("checkMsg"))+"</h2>");
			request.setAttribute("checkMsg","");
		}
		out.println("<table style='border:2px solid #000;'>");
		MySqlDataStoreUtilities dbInstance = new MySqlDataStoreUtilities();
		dbInstance.connect();
		ArrayList<Order> items = dbInstance.getDailySales();
		int j=1;
		if(items.isEmpty())
		{
			out.println("<h1 style='background-color:green;color:white;'>Sorry! No Sale Found!</h1>");
		}
		else
		{
			out.println("<tr style='background-color:green;color:white;border:2px solid #000'>");
			out.println("<td>Index</td>");
			out.println("<td>Date</td>");
			out.println("<td>Total Sale ($)</td>");
			out.println("</tr>");
			for(Order item : items)
			{
				out.println("<tr><td>"+j+"</td>");
				j++;
				out.println("<td>"+item.getOrderDate()+"</td>");
				out.println("<td>"+item.getAmount()+"</td>");
				out.println("</tr>");
			}
		}
		out.println("</table>");
		out.println("</article></section>");
		util.parseHtml("IRnav.html");
		util.parseHtml("footer.html");
	}

}