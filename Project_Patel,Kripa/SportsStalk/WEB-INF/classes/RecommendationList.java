import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class RecommendationList extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("text/html");
		PrintWriter out =response.getWriter();
		HttpSession session= request.getSession();
		int j=1;
		String email=null;
		email=(session.getAttribute("email").toString());
		Utilities util=new Utilities(request,out);
		util.parseHtml("header.html");
		out.println("<div id='body'>");
		out.println("<section id='content'>");
		out.println(" <h1 align=\"center\"><span style='color:red;'>Recommendation For You</span></h1> ");
		out.println("<article>");	
				
				/*Carousel for Recommended Products on basis of review ratings*/
				
				EventRecommenderUtility eveRecUtil = new EventRecommenderUtility();
				//eveRecUtil.connect();
				HashMap<String,String> itemRecMap = new HashMap<String,String>();
				itemRecMap = eveRecUtil.readOutputFile();
				if(session.getAttribute("email") != null)
				{
				for(Map.Entry<String, String> m : itemRecMap.entrySet()){
					String fileUserName = m.getKey();
					if(email.equals(fileUserName))
					{
						String itemsRecList = m.getValue();
						itemsRecList = itemsRecList.replace("[","");
						itemsRecList = itemsRecList.replace("]","");
						itemsRecList = itemsRecList.replace("\""," ");
						ArrayList<String> itemsList = new ArrayList<String>(Arrays.asList(itemsRecList.split(",")));
						out.println("<h1 align=\"center\">"+"</h1><div id=\"myCarousel\" class=\"carousel slide\" data-ride=\"carousel\" style=\"margin-left:11%;width:81%;\"><div class=\"carousel-inner\" align='center' role=\"listbox\" >");
						int i=1;
						for(String product : itemsList)
						{
							product = product.replace("'","");
							Events event = eveRecUtil.getProduct(product.trim());
							System.out.println(product.trim());
							System.out.println(product.trim().length());
							if(i!=1)
							{
								out.println("<div class=\"item\">");
							}
							else{
								out.println("<div class=\"item active\">");
							}
							//System.out.println(event.getImage());
							out.println("<table style='color:black;'>");
							out.println("<tr><td align='center'>Event : "+event.getName()+"</td></tr>");
							out.println("<tr><td align='center'>Venue : "+event.getVenue()+"</td></tr>");
							out.println("<tr><td align='center'>Event Date : "+event.getDate()+"</td></tr>");
							out.println("<tr><td align='center'>City : "+event.getCity()+"</td></tr>");
							out.println("<tr><td align='center'>State : "+event.getState()+"</td></tr>");
							out.println("<tr><td align='center'>Category : "+event.getType()+"</td></tr>");
							
							i++;
							out.println("</table></div>");
							
						}
						out.println("</div><a class=\"left carousel-control\" href=\"#myCarousel\" role=\"button\" data-slide=\"prev\"><span class=\"glyphicon glyphicon-chevron-left\" aria-hidden=\"true\"></span><span class=\"sr-only\">Previous</span></a>");	
						out.println("<a class=\"right carousel-control\" href=\"#myCarousel\" role=\"button\" data-slide=\"next\"><span class=\"glyphicon glyphicon-chevron-right\" aria-hidden=\"true\"></span><span class=\"sr-only\">Next</span></a></div>");
						out.println("</section>");
					}
				}
				}
					else{
							out.println("<div id='body'><section id='content'><article><table>");
							out.println("<h2><span style='green;'>Login First to View Recommendation</span></h2>");
							out.println("</table></article></section>");
					}
				
	}
}