import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;
import java.net.*;

public class CheckLogin extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("text/html");
				
		HashMap<String,UserDetail> ud = new HashMap<String,UserDetail>();
		PrintWriter out =response.getWriter();
		String dir = System.getenv("ANT_HOME");
		//String usertype= request.getParameter("usertype");
		//String firstname = request.getParameter("firstname");
		String password = request.getParameter("password");
		String email = request.getParameter("email");

		try{
			/*File f=new File(dir+"\\webapps\\Assignment_kp\\UserDetails.txt");
			FileInputStream fileinput= new FileInputStream(f);
			ObjectInputStream objIn = new ObjectInputStream(fileinput);
			ud=(HashMap)objIn.readObject();  */
			
			MySqlDataStoreUtilities dbInstance = new MySqlDataStoreUtilities();
			//dbInstance.connect();
			//ud =(HashMap)dbInstance.checkMail();
			if(dbInstance.verifyLogin(email,password).equals("Success"))
			{
				UserDetail userDetail=(UserDetail)ud.get(email);
				
				HttpSession session = request.getSession();
				//session.setAttribute("UserDetail", userDetail);
				//session.setAttribute("firstname", firstname);
				session.setAttribute("email", email);
				RequestDispatcher rd=request.getRequestDispatcher("/Main");  
				rd.forward(request, response);
				
			}	
		
		// else
		// {
			
			// RequestDispatcher ris=request.getRequestDispatcher("/LoginPage");  
			// request.setAttribute("checkMsg","Invalid Credentials");
			// ris.include(request, response);  
		// }
		
		// if(!ud.containsKey(email) || ud.size() == 0)
		// {
			
			// RequestDispatcher ris=request.getRequestDispatcher("/LoginPage");  
			// request.setAttribute("checkMsg","Invalid Credentials");
			// ris.include(request, response);  
		// }	
		// else{
			// UserDetail userDetail=(UserDetail)ud.get(email);
			// if(userDetail.getEmail().equals(email) && userDetail.getPassword().equals(password) )
			// {
				// if(usertype.equals("storeManager"))
				// {
					// HttpSession session = request.getSession();
					// session.setAttribute("UserDetail", userDetail);
					// RequestDispatcher rd=request.getRequestDispatcher("/Main");  
					// rd.forward(request, response);  
				// }
				
			// /*	if(usertype.equals("salesManager"))
				// {
					// HttpSession session = request.getSession();
					// session.setAttribute("UserDetail", userDetail);
					// RequestDispatcher rd=request.getRequestDispatcher("/HomeSAM");  
					// rd.forward(request, response);  
				// }
				
				// if(usertype.equals("customer"))
				// {
					// HttpSession session = request.getSession();
					// session.setAttribute("UserDetail", userDetail);
					// RequestDispatcher rd=request.getRequestDispatcher("/Main");  
					// rd.forward(request, response);  
				// } */
				
			//}
			// else
			// {
				// request.setAttribute("checkMsg","Invalid");
				// RequestDispatcher rd=request.getRequestDispatcher("/LoginPage");  
				// rd.include(request, response);  
			// }
		//} 
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}