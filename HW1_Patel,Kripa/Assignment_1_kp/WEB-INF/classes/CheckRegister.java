import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;

public class CheckRegister extends HttpServlet{	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("text/html");
		PrintWriter out =response.getWriter();
		String dir = System.getenv("ANT_HOME");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String eMsg="";
		HashMap<String,UserDetail> ud = new HashMap<String,UserDetail>();

		try{
			File f=new File(dir+"\\webapps\\Assignment_1_kp\\UserDetails.txt");
			if (!f.exists()) {
                f.createNewFile();
            }
			FileInputStream fi= new FileInputStream(f);
			if(f.length() != 0 )
			{
				ObjectInputStream obj = new ObjectInputStream(fi);
				ud=(HashMap)obj.readObject();
			}
			if(ud.containsKey(username) && !(ud.isEmpty()))
			{
				eMsg="Used Username!";
				request.setAttribute("checkMsg",eMsg);
				request.setAttribute("firstname",firstname);
				request.setAttribute("lastname",lastname);
				request.setAttribute("username",username);
				
				
				if(request.getParameter("customer") == null)
				{
					RequestDispatcher dis = request.getRequestDispatcher("/RegisterPage");
					dis.forward(request,response);
				}
				else{
					RequestDispatcher dis = request.getRequestDispatcher("/HomeSAM");
					dis.forward(request,response);
				}
			}
			else
			{
				String usertype="customer";
				UserDetail userDetail= new UserDetail(firstname,lastname,username,password,usertype);
				ud.put(username,userDetail);
				FileOutputStream fout= new FileOutputStream(new File(dir+"\\webapps\\Assignment_1_kp\\UserDetails.txt"));
				ObjectOutputStream oout = new ObjectOutputStream(fout);
				oout.writeObject(ud);
				oout.flush();
				oout.close();
				fout.close();
			
				if(request.getParameter("customer") == null)
				{
					eMsg="Success in Registration";
					request.setAttribute("checkMsg",eMsg);
					RequestDispatcher dis = request.getRequestDispatcher("/Main");
					dis.forward(request,response);
				}
				else{
					eMsg="Added 1 Customer!";
					request.setAttribute("checkMsg",eMsg);
					RequestDispatcher dis = request.getRequestDispatcher("/HomeSAM");
					dis.forward(request,response);
				}
			}
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}