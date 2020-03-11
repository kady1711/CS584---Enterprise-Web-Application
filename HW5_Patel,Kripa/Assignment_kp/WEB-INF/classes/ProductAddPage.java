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
		String pcategory1=null;
		if(pcategory.equals("SmartWatches"))
		{
			pcategory="watches";
			pcategory1="SmartWatches";
		}
		else if(pcategory.equals("Laptops"))
		{
			pcategory="laptops";
			pcategory1="Laptops";
		}
		else if(pcategory.equals("Headphones"))
		{
			pcategory="headphones";
			pcategory1="Headphones";
		}
		else if(pcategory.equals("Phones"))
		{
			pcategory="phones";
			pcategory1="Phones";
		}
		else if(pcategory.equals("VoiceAssistant"))
		{
			pcategory="voiceassistants";
			pcategory1="VoiceAssistant";
		}
		else if(pcategory.equals("FitnessWatches"))
		{
			pcategory="fwatches";
			pcategory1="FitnessWatches";
		}
		else if(pcategory.equals("Television"))
		{
			pcategory="televisions";
			pcategory1="Television";
		}
		
		else if(pcategory.equals("SoundSystem"))
		{
			pcategory="speakers";
			pcategory1="SoundSystem";
		}
		//String dir = System.getenv("ANT_HOME");
		SaxParser s1= new SaxParser("C:\\tomcat\\tomcat-7.0.34-preconfigured\\apache-tomcat-7.0.34\\webapps\\Assignment_kp\\WEB-INF\\classes\\ProductCatalog.xml");
		int pSize = s1.getProductsSize();
		MySqlDataStoreUtilities dbInstance = new MySqlDataStoreUtilities();
		dbInstance.connect();
		dbInstance.addNewProduct1(pSize+1+"",pimage,pname,Double.parseDouble(pprice),pcondition,pcompany,pcolor,pdescription,"bestdeal",pcategory);
		SaxParserForAdd s= new SaxParserForAdd("C:\\tomcat\\tomcat-7.0.34-preconfigured\\apache-tomcat-7.0.34\\webapps\\Assignment_kp\\WEB-INF\\classes\\ProductCatalog.xml",pname,pprice,pcolor,pcondition,pdescription,pcompany,pimage,pcategory1,pSize);
		request.setAttribute("checkMsg","Product Added");
		RequestDispatcher dis = request.getRequestDispatcher("/HomeSM");
		dis.forward(request,response);
	}
}