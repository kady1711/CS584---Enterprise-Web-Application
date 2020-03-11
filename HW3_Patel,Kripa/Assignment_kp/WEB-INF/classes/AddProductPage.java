import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class AddProductPage extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("text/html");
		PrintWriter out =response.getWriter();
		HttpSession session= request.getSession();
		Utilities util=new Utilities(request,out);
		
		util.parseHtml("header1.html");
		out.println("<div id='body' style=\"background-color:white;width:100%;\">");
		out.println("<section id='content' style=\"width:100%;background-color:white;\">");
		out.println("<article> <h1 align=\"center\"><span style='color:blue;'>"+"Add Product</span></h1> ");
		out.println("<form  style='border:2px #f1f1f1 solid;' action=\"ProductAddPage\" ><div style='padding:12px;'>");
		out.println("<label><b>Product Name</b></label><input style='width:100%' type='text' placeholder='Enter Product Name' name='pname' required \"></br></br>");
		out.println("<label><b>Product Description</b></label></br><textarea style='width:100%;' placeholder='Enter Product Description' name='pdescription' required></textarea></br></br>");
		out.println("<label><b>Product Color</b></label></br><input style='width:100%;' type='text' placeholder='Enter Product Color' name='pcolor' required \"></br></br>");
		out.println("<label><b>Product Price</b></label><input style='width:100%' type='text' placeholder='Enter Product Price' name='pprice' \"></br></br>");
		out.println("<label><b>Enter Product Image Name</b></label></br><input style='width:100%;' type='text'  placeholder='Enter Product Image Name(with Extension)' name='pimage' required ></br></br>");
		out.println("<label><b>Product Condition</b></label></br><input style='width:100%;' type='text' placeholder='Enter Product Condition' name='pcondition' required \"></br></br>");
		out.println("<label><b>Product Company</b></label></br><input style='width:100%;' type='text' placeholder='Enter Product Company' name='pcompany' required \"></br></br>");
		out.println("<label><b>Select Category</b></label></br><select name='pcategory'><option value='speakers' selected>Sound System</option><option value='voiceassistant'>Voice Assistant</option><option value='phones'>Phones</option><option value='fwatches'>Fitness Watches</option><option value='laptops'>Laptops</option><option value='televisions'>Televisions</option><option value='watches'>Smart Watches</option><option value='headphones'>HeadPhones</option></select>");
		//out.println("<input type='hidden' name='updateMsg' value='Product Updated'>");
		out.println("<button type='submit' style='color:white;background-color:green;margin:6px;padding:12px 22px;width:100%;cursor:pointer;'> Add Product </button>");
		out.println("</div></form>");
		out.println("</article></section>");
		util.parseHtml("footer.html");
		
	}
}