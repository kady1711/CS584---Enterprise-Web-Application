import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;

public class DisplayReviews extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("text/html");
		
		PrintWriter out =response.getWriter();
		HttpSession session = request.getSession();
		MongoDbDataStoreUtilities mdb = new MongoDbDataStoreUtilities();
		mdb.connect();
		
		if(request.getAttribute("checkMsg") != null)
		{
			out.println("<h2 style=color:white;background-color:red;'>"+(String)(request.getAttribute("checkMsg"))+"</h2>");
			request.setAttribute("checkMsg","");
		}
		
		String firstname=null,usertype=null,userage=null,usergender=null,username=null;
		if(session.getAttribute("UserDetail") != null)
		{
			firstname=((UserDetail)(session.getAttribute("UserDetail"))).getFirstName();
			usergender=((UserDetail)(session.getAttribute("UserDetail"))).getGender();
			username=((UserDetail)(session.getAttribute("UserDetail"))).getUserName();
			userage=((UserDetail)(session.getAttribute("UserDetail"))).getAge();
			usertype=((UserDetail)(session.getAttribute("UserDetail"))).getUserType();
		}
		
		String pId=request.getParameter("pId");
		String pType=request.getParameter("pCategory");
		String pName =request.getParameter("pName");
		
		Utilities util=new Utilities(request,out);
		util.parseHtml("header.html");
		out.println("<div id='body' style=\"background-color:white;width:100%;\">");
		out.println("<section id='content' style=\"width:100%;background-color:white;\">");
		
		HashMap<String,ArrayList<ReviewDetail>> reviews = mdb.getAllReviews();
		int i=0;
		if(!reviews.containsKey(pName))
		{
			out.println(" <h1 align=\"center\"><span style='color:red;'>"+"No Reviews Found for "+pName+"</span></h1> ");
		}
		else
		{
			out.println(" <h1 align=\"center\"><span style='color:red;'>"+pName+" Reviews</span></h1> ");
			ArrayList<ReviewDetail> r = (ArrayList<ReviewDetail>)reviews.get(pName);
		
			for(ReviewDetail reviewDetail : r)
			{
					i++;
					out.println("<article>");
					out.println("<h2 style='color:blue'>Review #"+i+" Details</h2>");
					out.println("<b>User:</b> "+ reviewDetail.getUserId()+"</br>");
					out.println("<b>Review Rating:</b> "+ reviewDetail.getReviewRating()+"</br>");
					out.println("<b>Review Text: </b>"+ reviewDetail.getReviewText()+"</br>");
					out.println("<b>Review Date:</b> "+ reviewDetail.getReviewDate()+"</br>");
					
					out.println("</article>");	
			}
		}
		out.println("<div width='50%' align='center'><a href=\"ProductServlet?productType="+pType+"\"><button style='background-color:green;color: white;padding:14px 20px;margin:8px;width:100%;'>Back</button></a></div>");
		out.println("</section></div><div class=\"clear\"></div>");
		
		util.parseHtml("footer.html");
	
	}
}