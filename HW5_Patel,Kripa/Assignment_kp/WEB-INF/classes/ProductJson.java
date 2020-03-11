import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import org.json.JSONObject;
import com.google.gson.Gson;

public class ProductJson extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("application/json");
		PrintWriter out =response.getWriter();
		Gson gson=new Gson();
		MySqlDataStoreUtilities dbInstance = new MySqlDataStoreUtilities();
		dbInstance.connect();
		ArrayList<Item> items = dbInstance.getProductList();
		String result=gson.toJson(items);
		out.println(result);
	}
}