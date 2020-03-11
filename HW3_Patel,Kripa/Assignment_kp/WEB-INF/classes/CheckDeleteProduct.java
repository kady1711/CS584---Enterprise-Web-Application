import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class CheckDeleteProduct extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("text/html");
		String category=request.getParameter("pCategory");
		String id=request.getParameter("pId");
		//PrintWriter out =response.getWriter();
		HttpSession session= request.getSession();
		
		
			MySqlDataStoreUtilities dbInstance = new MySqlDataStoreUtilities();
			dbInstance.connect();
			dbInstance.removeProduct(id);
			String e="Product Deleted!";
			request.setAttribute("checkMsg",e);
			RequestDispatcher rd = request.getRequestDispatcher("/ProductDeletePage");	
			rd.forward(request,response);
			
			
		
	}
}