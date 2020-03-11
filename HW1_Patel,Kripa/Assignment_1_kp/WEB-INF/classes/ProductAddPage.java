import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class ProductAddPage extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("text/html");
		PrintWriter out =response.getWriter();
		HttpSession session= request.getSession();
		Utilities util=new Utilities(request,out);
		
		String pname=request.getParameter("pname");
		String pcolor=request.getParameter("pcolor");
		String pcondition=request.getParameter("pcondition");
		String pcategory=request.getParameter("pcategory");
		String pdescription=request.getParameter("pdescription");
		String pprice=request.getParameter("pprice");
		String pcompany=request.getParameter("pcompany");
		String pimage=request.getParameter("pimage");
		String dir = System.getenv("ANT_HOME");
		SaxParser s1= new SaxParser(dir+"\\webapps\\Assignment_1_kp\\WEB-INF\\classes\\ProductCatalog.xml");
		int pSize = s1.getProductsSize();
		
		SaxParserForAdd s= new SaxParserForAdd(dir+"\\webapps\\Assignment_1_kp\\WEB-INF\\classes\\ProductCatalog.xml",pname,pprice,pcolor,pcondition,pdescription,pcompany,pimage,pcategory,pSize);
		request.setAttribute("checkMsg","Product Added");
		RequestDispatcher dis = request.getRequestDispatcher("/HomeSM");
		dis.forward(request,response);
	}
}