import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;
import java.net.*;

public class TopZipCodesPage extends HttpServlet
{	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("text/html");
		PrintWriter out =response.getWriter();
		HttpSession session = request.getSession();
		Utilities util=new Utilities(request,out);
		if(request.getAttribute("checkMsg") != null)
		{
			out.println("<h3 style='color:black;background-color:blue;'>"+(String)(request.getAttribute("checkMsg"))+"</h3>");
			request.setAttribute("checkMsg","");
		}	
		MySqlDataStoreUtilities dbInstance = new MySqlDataStoreUtilities();
		dbInstance.connect();
		List<String> r = dbInstance.getTopZipCodes();
		String firstname=null,usertype=null;
		if(session.getAttribute("UserDetail") != null)
		{
			firstname=((UserDetail)(session.getAttribute("UserDetail"))).getFirstName();
			usertype=((UserDetail)(session.getAttribute("UserDetail"))).getUserType();
		} 
		
		util.parseHtml("header.html");
		out.println("<div id='body'>");
		out.println("<section id='content'>");
		
		int i=1;
		try{
			if(r.isEmpty())
			{
				out.println(" <h2 align=\"center\"><span style='color:red;'>No Top Retailers</span></h2>");
				
			}
			else
			{
				for(String rName : r)
				{	
					if(i<6)
					{	
						
						RetailerDetail retailerDetail= (RetailerDetail) dbInstance.getRetailerDetailByName(rName);
						
						out.println("<article>");
						out.println("<h2 style='color:blue'>ZipCode #"+i+" Details</h2>");
						int zip=Integer.parseInt(retailerDetail.getRetailerZip());
						out.println("ZipCode : "+ zip+" </br></article>");
					}	
					i++;				
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		out.println("</section>");		
		util.parseHtml("nav.html");
		util.parseHtml("footer.html");
	}
}