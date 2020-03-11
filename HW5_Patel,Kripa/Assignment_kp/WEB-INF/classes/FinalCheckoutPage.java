import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class FinalCheckoutPage extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("text/html");
		PrintWriter out =response.getWriter();
		HttpSession session= request.getSession();
		int j=1;
		String firstname=null,usertype=null,username=null,lastname=null;
		
		if(session.getAttribute("UserDetail") != null)
		{
			firstname=((UserDetail)(session.getAttribute("UserDetail"))).getFirstName();
			usertype=((UserDetail)(session.getAttribute("UserDetail"))).getUserType();
			lastname=((UserDetail)(session.getAttribute("UserDetail"))).getLastName();
			username=((UserDetail)(session.getAttribute("UserDetail"))).getUserName();
		} 
		CartDetail cartDetail = (CartDetail) session.getAttribute("CartDetail");
		UserDetail userDetail= (UserDetail) session.getAttribute("UserDetail");
		Utilities util=new Utilities(request,out);
		util.parseHtml("header.html");
		String q[]= request.getParameterValues("pqty");
		String n[]= request.getParameterValues("pname");

		HashMap<String,List<Object>> cartitems= cartDetail.getAllItems();
		float a1=0;
		for(int i=0;i<n.length;i++)
		{
			if(cartitems.containsKey(n[i]))
			{
				List<Object> item= cartitems.get(n[i]);
				float p= (float) item.get(1);
				item.set(2,(Integer.parseInt(q[i])));
				a1=a1+( p * (Integer.parseInt(q[i])));
			}
		}		
		session.setAttribute("CartDetail",cartDetail);
		String a=a1+"";
		
		out.println("<div id='body' style=\"width:100%;background-color:white;\"><section id='content' style=\"width:100%;background-color:white;\">");
		out.println("<article> <h1 align=\"center\"><span style='color:green;'>"+"Personal Details </span></h1> ");
		out.println("<form id='checkout' action='CheckoutVerificationPage' style='border:2px #fff type='email';'><div style='padding:18px;'>");
		
		out.println("<label><b>First Name</b></label><input style='width:100%' type='text' placeholder='Enter First Name' name='firstname' required value=\""+firstname+"\"></br></br>");
		out.println("<label><b>Last Name</b></label><input style='width:100%' type='text' placeholder='Enter Last Name' name='lastname' required value=\""+lastname+"\"></br></br>");
		
		if(usertype.equals("customer"))
		{
			out.println("<label><b>Email Address</b></label></br><input style='width:100%;'  placeholder='Enter Email' type='email' name='username' required value=\""+username+"\" ></br></br>");
			out.println("<label><b>Shipping Address</b></label></br><textarea style='width:100%;' placeholder='Enter Shipping Address' name='address' required ></textarea>");
			out.println("</article>");
			out.println("<input type='hidden' name='amount' value='"+a+"'/>");
			out.println("<article><h1 align=\"center\"><span style='color:green;'>"+"Card Details </span></h1> ");
			out.println("<label><b>Card Owner Name</b></label><input style='width:100%' type='text' name='cname'  placeholder='Enter Card Owner Name' required ></br></br>");
			out.println("<label><b>Card Number</b></label></br><input type='text' placeholder='Enter Card Number' required style='width:100%;'></br></br>");
			out.println("<label><b>Expiration Date</b></label></br><input type='month' placeholder='YYYY/MM'  required ></br></br>");
			out.println("</br><label><b>CVV</b></label><input type='number'  max='999' min='100' placeholder='CVV'  required ></br></br>");
		}
		else if(usertype.equals("salesManager"))
		{
			out.println("<input type='hidden' name='amount' value='"+a+"'/></article>");
			out.println("<label><b>Enter Username of Customer</b></label></br><input style='width:100%;' placeholder='Enter Email Address of Customer' type='email' name='username' required >");
			out.println("</br></br><label><b>Address</b></label></br><textarea style='width:100%;' placeholder='Enter shipping address' name='address' required ></textarea>");
		
		}
		out.println("<button style='color:white;background-color:green;padding:16px 22px;cursor:pointer;margin:6px;width: 100%;}' type='submit'>Pay Amount : $"+a+"</button>");
		out.println("</form></article></section></div><div class=\"clear\"></div>");
		util.parseHtml("footer.html");
		
		
	}
}