import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class RemoveOrderPage extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("text/html");
		PrintWriter out =response.getWriter();
		HashMap<String,Order> orders = new HashMap<String,Order>();
		String dir = System.getenv("ANT_HOME");
		String orderid= request.getParameter("orderid");
		try{
			/*File f=new File(dir+"\\webapps\\Assignment_1_kp\\PaymentDetails.txt");
			FileInputStream sf= new FileInputStream(f);
			ObjectInputStream io = new ObjectInputStream(sf);
			orders=(HashMap)io.readObject();  */
		
			MySqlDataStoreUtilities dbInstance = new MySqlDataStoreUtilities();
			dbInstance.connect();
			dbInstance.removeWholeOrder(orderid);
			orders.remove(orderid);
			/*
			FileOutputStream of= new FileOutputStream(new File(dir+"\\webapps\\Assignment_kp\\PaymentDetails.txt"));
			ObjectOutputStream oo = new ObjectOutputStream(of);
			oo.writeObject(orders);
			oo.flush();
			oo.close();
			of.close();	  */
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		RequestDispatcher dis = request.getRequestDispatcher("DisplayOrders");
		dis.forward(request,response);
		
	}
}