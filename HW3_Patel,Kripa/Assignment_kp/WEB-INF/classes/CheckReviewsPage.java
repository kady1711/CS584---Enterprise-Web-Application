import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;

public class CheckReviewsPage extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("text/html");
		
		String firstname=null,usertype=null,userage=null,usergender=null,username=null;
		HttpSession session= request.getSession();
		
		if(session.getAttribute("UserDetail") != null)
		{
			firstname=((UserDetail)(session.getAttribute("UserDetail"))).getFirstName();
			usergender=((UserDetail)(session.getAttribute("UserDetail"))).getGender();
			username=((UserDetail)(session.getAttribute("UserDetail"))).getUserName();
			userage=((UserDetail)(session.getAttribute("UserDetail"))).getAge();
			usertype=((UserDetail)(session.getAttribute("UserDetail"))).getUserType();
		}
		String rname= request.getParameter("rname");
		String rdate = request.getParameter("rdate");
		String rrating = request.getParameter("rrating");
		String rtext= request.getParameter("rtext");
		MySqlDataStoreUtilities dbInstance = new MySqlDataStoreUtilities();
		dbInstance.connect();
		String pid= request.getParameter("pId");
		
		
		
		String pcategory=request.getParameter("pCategory");
		String pprice=request.getParameter("pprice");
		String pname= request.getParameter("pname");
		//String rname=request.getParameter("rname");
		String rcity=request.getParameter("rcity");
		String rstate=request.getParameter("rstate");
		String rzip=request.getParameter("rzip");
		String uage= request.getParameter("userage");
		String uname= request.getParameter("username");
		String pcompany= request.getParameter("pcompany");
		String ugender= request.getParameter("usergender");
		
		
		
		
		ReviewDetail reviewDetail = new ReviewDetail();
		reviewDetail.setReviewDate(rdate);
		reviewDetail.setReviewRating(rrating);
		reviewDetail.setReviewText(rtext);
		reviewDetail.setUserId(uname);
		reviewDetail.setUserOccupation("accountant");
		reviewDetail.setUserGender(ugender);
		reviewDetail.setUserAge(uage);
		reviewDetail.setRetailerName(rname);
		reviewDetail.setRetailerState(rstate);
		reviewDetail.setRetailerZip(rzip);
		reviewDetail.setRetailerCity(rcity);
		reviewDetail.setProductRebate("yes");
		
		reviewDetail.setOnSale("yes");
		
		reviewDetail.setProductCompany(pcompany);
		reviewDetail.setProductName(pname);
		reviewDetail.setProductPrice(pprice);
		reviewDetail.setProductCategory(pcategory);
		
		MongoDbDataStoreUtilities mdb = new MongoDbDataStoreUtilities();
		mdb.connect();
		mdb.addReview(reviewDetail);
		request.setAttribute("checkMsg","New Review Added" );
		RequestDispatcher dis = request.getRequestDispatcher("NewReviewsPage");
		dis.forward(request,response);
	}
}