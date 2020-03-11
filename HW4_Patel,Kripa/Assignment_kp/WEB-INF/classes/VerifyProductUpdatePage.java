import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class VerifyProductUpdatePage extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("text/html");
		PrintWriter out =response.getWriter();
		String productid=request.getParameter("productid");
		String productcolor=request.getParameter("productcolor");
		String productcondition=request.getParameter("productcondition");
		String productprice=request.getParameter("productprice");
		String productcompany=request.getParameter("productcompany");
		String productname=request.getParameter("productname");
		String productimage=request.getParameter("productimage");
		String productcategory=request.getParameter("productcategory");
		String productdescription=request.getParameter("productdescription");
		MySqlDataStoreUtilities dbInstance = new MySqlDataStoreUtilities();
		dbInstance.connect();
		dbInstance.modifyProduct(productid,productimage,productname,Double.parseDouble(productprice),productcondition,productcompany,productcolor,productdescription,"smartportables",productcategory);
		
		request.setAttribute("checkMsg","Product Updated Succesfully!");
		RequestDispatcher dis = request.getRequestDispatcher("/ProductUpdatePage");	
		dis.forward(request,response);
		
	}
	
	
}