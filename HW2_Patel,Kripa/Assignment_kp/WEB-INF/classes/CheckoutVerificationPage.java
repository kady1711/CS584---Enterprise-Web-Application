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
		HashMap<String,Order> orderDetails = new HashMap<String,Order>();
		int irandNo=new Random().nextInt(10000)+10;
		String oId = "HM#1112"+(irandNo+"");
		util.parseHtml("header.html");
		String dir = System.getenv("ANT_HOME");
		
		try{
			/*	File f=new File(dir+"\\webapps\\Assignment_kp\\PaymentDetails.txt");
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
				}  */
				
				
				MySqlDataStoreUtilities dbInstance = new MySqlDataStoreUtilities();
				dbInstance.connect();
				orderDetails =(HashMap)dbInstance.checkOidExists();
				while(orderDetails.containsKey(oId) && !(orderDetails.isEmpty()))
				{
					irandNo=new Random().nextInt(10000)+10;
					oId = "HM#1112"+(irandNo+"");
				}
				Order odr=new Order(oId,oemail,oamount,oaddress,d1);	
				CartDetail c = (CartDetail) session.getAttribute("CartDetail");
				HashMap<String,List<Object>> i= c.getAllItems();
				odr.setItems(i);
				orderDetails.put(oId,odr);
				
			
		/*		FileOutputStream fo= new FileOutputStream(new File(dir+"\\webapps\\Assignment_kp\\PaymentDetails.txt"));
				ObjectOutputStream oo = new ObjectOutputStream(fo);
				oo.writeObject(orders);
				oo.flush();
				oo.close();
				fo.close();	*/
				
				int newOrderNo = dbInstance.addNewOrder(oId,oemail,oamount,oaddress,d1);
				
				for(Map.Entry<String, List<Object>> entry : i.entrySet()){
					List<Object> ci = entry.getValue();
					int productqty = (int)ci.get(2);
					String productname = (String)ci.get(0);
					String productimage = (String)ci.get(3);
					String productprice = Float.toString((Float)ci.get(1));
					dbInstance.addNewOrderProduct(newOrderNo,productname,productprice,productqty,productimage);
				}
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
	