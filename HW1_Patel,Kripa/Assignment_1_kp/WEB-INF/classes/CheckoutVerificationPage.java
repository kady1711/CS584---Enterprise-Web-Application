import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;

public class CheckoutVerificationPage extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("text/html");
		DateFormat dff = new SimpleDateFormat("yyyy/MM/dd");
		Date d = new Date();
		String d1=dff.format(d);
		PrintWriter out =response.getWriter();
		Utilities util=new Utilities(request,out);
		HttpSession session= request.getSession();
		String oamount=request.getParameter("amount");
		String oaddress=request.getParameter("address");
		String oemail= request.getParameter("username");
		HashMap<String,Order> orders = new HashMap<String,Order>();
		int n=new Random().nextInt(10000)+10;
		String oId = "HM#1112"+(n+"");
		util.parseHtml("header.html");
		String dir = System.getenv("ANT_HOME");
		
		try{
				File f=new File(dir+"\\webapps\\Assignment_1_kp\\PaymentDetails.txt");
				if (!f.exists()) {
					f.createNewFile();
				}
				FileInputStream fi= new FileInputStream(f);
				
				if(f.length() != 0 )
				{
					ObjectInputStream oi = new ObjectInputStream(fi);
					orders=(HashMap)oi.readObject();
				}
				
				while(orders.containsKey(oId) && !(orders.isEmpty()))
				{
					n=new Random().nextInt(10000)+10;
					oId = "HM#1112"+(n+"");
				}
				
				Order order=new Order(oId,oemail,oamount,oaddress,d1);	
				CartDetail c = (CartDetail) session.getAttribute("CartDetail");
				HashMap<String,List<Object>> i= c.getAllItems();
				order.setItems(i);
				orders.put(oId,order);
				FileOutputStream fo= new FileOutputStream(new File(dir+"\\webapps\\Assignment_1_kp\\PaymentDetails.txt"));
				ObjectOutputStream oo = new ObjectOutputStream(fo);
				oo.writeObject(orders);
				oo.flush();
				oo.close();
				fo.close();	
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			session.removeAttribute("CartDetail");
			out.println("<div id='body'><section id='content'><article>");
			out.println("<h1 style='color:blue;'>Order with "+oId+" has been placed!</br></h1></article></section>");		
			util.parseHtml("nav.html");
			util.parseHtml("footer.html");
	}	
}
	