import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;

public class CheckRegister extends HttpServlet{	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("text/html");
		PrintWriter out =response.getWriter();
		String dir = System.getenv("ANT_HOME");
		String email = request.getParameter("email");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		//String username = request.getParameter("username");
		String password = request.getParameter("password");
		String city = request.getParameter("city");
		String preferences =request.getParameter("preferences").toString();
		String eMsg="";
		//HashMap<String,UserDetail> ud = new HashMap<String,UserDetail>();

		try{
			/*File f=new File(dir+"\\webapps\\Assignment_kp\\UserDetails.txt");
			if (!f.exists()) {
                f.createNewFile();
            }
			FileInputStream fi= new FileInputStream(f);
			if(f.length() != 0 )
			{
				ObjectInputStream obj = new ObjectInputStream(fi);
				ud=(HashMap)obj.readObject();
			}*/
			
			//System.out.println(email);
			MySqlDataStoreUtilities dbInstance = new MySqlDataStoreUtilities();
			//if(dbInstance.connect())
			//{
				String s=dbInstance.register(email,password,firstname,lastname,city,preferences);
				//System.out.println(s);
				// if(ud)
				// {
					// eMsg="Used Email!!";
					// request.setAttribute("checkMsg",eMsg);
					request.setAttribute("firstname",firstname);
					request.setAttribute("lastname",lastname);
					// request.setAttribute("email",email);
					
					
					// if(request.getParameter("email") == null)
					// {
						// RequestDispatcher dis = request.getRequestDispatcher("/RegisterPage");
						// dis.forward(request,response);
					// }
					// /*else{
						// RequestDispatcher dis = request.getRequestDispatcher("/HomeSAM");
						// dis.forward(request,response);
					// }*/
				// }
				// else
				// {
					// String usertype="user";
					//UserDetail userDetail= new UserDetail(firstname,lastname,email,password,usertype);
					//ud.put(email,userDetail);
					//dbInstance.Resgister(firstname,lastname,email,password,pincode,gender,preferences,usertype);
					// /*FileOutputStream fout= new FileOutputStream(new File(dir+"\\webapps\\Assignment_kp\\UserDetails.txt"));
					// ObjectOutputStream oout = new ObjectOutputStream(fout);
					// oout.writeObject(ud);
					// oout.flush();
					// oout.close();
					// fout.close();*/
				
					// if(request.getParameter("user") == null)
					// {
						// eMsg="Success in Registration";
						// request.setAttribute("checkMsg",eMsg);
						// RequestDispatcher dis = request.getRequestDispatcher("/Main");
						// dis.forward(request,response);
					// }
					// /*else{
						// eMsg="Added 1 User!";
						// request.setAttribute("checkMsg",eMsg);
						// RequestDispatcher dis = request.getRequestDispatcher("/HomeSAM");
						// dis.forward(request,response);
					// }*/
				// }
			
		//	}
		// /*	else
			// {
						// if(request.getParameter("user") == null)
						// {
							// eMsg="SQL SERVER IS NOT UP AND RUNNING!";
							// request.setAttribute("checkMsg",eMsg);
							// RequestDispatcher rd = request.getRequestDispatcher("/Register");
								
							// rd.forward(request,response);
						// }
						// else{
							// eMsg="SQL SERVER IS NOT UP AND RUNNING!";
							// request.setAttribute("checkMsg",eMsg);
							// RequestDispatcher rd = request.getRequestDispatcher("/HomeSAM");
							// rd.forward(request,response);
						// }
			// } */
			
			
			//if(ud){
				 //eMsg="Success in Registration";
				 //request.setAttribute("checkMsg",eMsg);
				 HttpSession session = request.getSession();
				 session.setAttribute("email", email);
				RequestDispatcher dis = request.getRequestDispatcher("/Main");
				dis.forward(request,response);
			//}
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}