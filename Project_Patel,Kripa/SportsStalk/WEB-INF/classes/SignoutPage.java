import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;

public class SignoutPage extends HttpServlet
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
	{
		response.setContentType("text/html");
		PrintWriter out =response.getWriter();
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("/SportsStalk/Main");
	}
}