import javax.servlet.http.*;
import javax.servlet.*;
import java.util.*;
import java.io.*;

public class AutoCompleteServlet extends HttpServlet {

String query = null,searchValue =null;
public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		StringBuffer sb = new StringBuffer();
		boolean found = false;
		AjaxUtility ajaxUtil = new AjaxUtility();
		ajaxUtil.connect();
		HashMap<String,Events> events = ajaxUtil.getAllEvents(); 
		PrintWriter out =response.getWriter();
		query = request.getParameter("query");
        searchValue = request.getParameter("search_query");
	
		if(!searchValue.equals(""))
		{
			if(query.equals("display"))
			{
				Events event = (Events)events.get(searchValue);
				request.setAttribute("itemObj", events.get(searchValue));
				RequestDispatcher dis = request.getRequestDispatcher("/DisplayResultPage");  
				dis.forward(request, response);  
			}
			if(query.equals("find"))
			{
				searchValue=searchValue.trim().toLowerCase();
				for(Map.Entry<String,Events> it : events.entrySet())
				{
					Events event=(Events)it.getValue();
					if (event.getName().toLowerCase().startsWith(searchValue))
					{
						sb.append("<event>");
                        sb.append("<eventId>" + event.getId() + "</eventId>");
                        sb.append("<eventName>" + event.getName() + "</eventName>");
                        sb.append("</event>");
						System.out.println(sb);
						found=true;
					}
				}	
				
				if(!found)
				{
					response.setStatus(HttpServletResponse.SC_NO_CONTENT);
				}		
				else 
				{
					response.setContentType("text/xml");
					response.setHeader("Cache-Control", "no-cache");
					out.write("<events>");
					out.write(sb.toString());
					out.write("</events>");
				}
			}
			
		}
	}
}