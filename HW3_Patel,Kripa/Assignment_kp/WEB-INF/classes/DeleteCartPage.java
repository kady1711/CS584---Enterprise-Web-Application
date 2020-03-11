import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class DeleteCartPage extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		CartDetail cartdetail = (CartDetail) session.getAttribute("CartDetail");
		cartdetail.deleteItem(request.getParameter("name"));
		session.setAttribute("CartDetail",cartdetail);
		RequestDispatcher dis = request.getRequestDispatcher("CartPage");
		dis.forward(request,response);
		
	}
}