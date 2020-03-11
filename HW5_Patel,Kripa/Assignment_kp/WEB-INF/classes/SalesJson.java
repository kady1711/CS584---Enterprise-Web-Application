import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import org.json.JSONObject;
import com.google.gson.Gson;

public class SalesJson extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("application/json");
		PrintWriter out =response.getWriter();
		Gson gson=new Gson();
		MySqlDataStoreUtilities dbInstance = new MySqlDataStoreUtilities();
		dbInstance.connect();
		ArrayList<Order> items = dbInstance.getTotalSale();
		String result=gson.toJson(items);
		out.println(result);
	}	
}