import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;

public class NewReviewsPage extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("text/html");
		String category=request.getParameter("pCategory");
		String id=request.getParameter("pId");
		PrintWriter out =response.getWriter();
		HttpSession session= request.getSession();
		Utilities util=new Utilities(request,out);
		List<Television> televisions;
		List<Laptop> laptops;
		List<VoiceAssistant> voiceassistants;
		List<Phone> phones;
		List<SmartWatch> watches;
		List<FitnessWatch> fwatches;
		List<HeadPhone> headphones;
		List<SoundSystem> speakers;
		
		
		speakers = new ArrayList<SoundSystem>();
		laptops=new ArrayList<Laptop>();
		phones = new ArrayList<Phone>();
		voiceassistants=new ArrayList<VoiceAssistant>();
        watches = new ArrayList<SmartWatch>();
		fwatches = new ArrayList<FitnessWatch>();
		headphones=new ArrayList<HeadPhone>();
		televisions=new ArrayList<Television>();
		String dir = System.getenv("ANT_HOME");
		//SaxParser saxparser= new SaxParser(dir+"\\webapps\\Assignment_kp\\WEB-INF\\classes\\ProductCatalog.xml");
		MySqlDataStoreUtilities dbInstance = new MySqlDataStoreUtilities();
		dbInstance.connect();
		HashMap<String,Object> products = dbInstance.getProducts();
		util.parseHtml("header.html");
		out.println("<div id='body' style=\"background-color:white;width:100%;\"><section style=\"background-color:white;width:100%;\" id='content'>");
		out.println("<article><h1><span style='color:green;'>"+"Product Reviews</span></h1> ");
		if(request.getAttribute("checkMsg") != null )
		{
			out.println("<h2 style='color:white;background-color:blue;'>"+(String)(request.getAttribute("checkMsg"))+"</h2>");
			request.setAttribute("checkMsg","");
		}
		String firstname=null,usertype=null,userage=null,usergender=null,username=null;
		if(session.getAttribute("UserDetail") != null)
		{
			firstname=((UserDetail)(session.getAttribute("UserDetail"))).getFirstName();
			usergender=((UserDetail)(session.getAttribute("UserDetail"))).getGender();
			username=((UserDetail)(session.getAttribute("UserDetail"))).getUserName();
			userage=((UserDetail)(session.getAttribute("UserDetail"))).getAge();
			usertype=((UserDetail)(session.getAttribute("UserDetail"))).getUserType();
		}
		int flag=0;
		
		if(category.equals("fwatches")){
			fwatches=(ArrayList<FitnessWatch>)products.get(category);
			for(FitnessWatch fwatch : fwatches){
				if(fwatch.getId().equals(id)){
					out.println("<form action = 'CheckReviewsPage'>");
					out.println("<table border=\"1\" style=\"width:100%\" style=\"height:100%\">");
					out.println("<tr><td width=\"35%\"><img style=\"width:420px;height:470px;\"  src=\"images\\"+fwatch.getImage()+"\" style=\"display:block;\" /></td><td width=\"65%\"><table>");
					out.println("<tr><td colspan=2><h2 align='center' style='color:green;'> Name :"+fwatch.getName()+"</h2></td></tr>");
					out.println("<tr><td colspan=2><b>Retailer Name : </b>"+fwatch.getRetailer()+"</td></tr>");
					out.println("<tr><td colspan=2><b>Price : $</b>"+fwatch.getPrice()+"</td></tr>");
					out.println("<tr><td colspan=2><b>Manufacturer Name : </b>"+fwatch.getCompany()+"</td></tr>");
					
					RetailerDetail retailer=dbInstance.getRetailerByName(fwatch.getRetailer());
					
					Date todaydate = new Date();
					DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
					String d=df.format(todaydate);
					out.println("<input type='hidden' name='username' value='"+username+"'>");
					out.println("<input type='hidden' name='usergender' value='"+usergender+"'>");
					out.println("<input type='hidden' name='pname' value='"+fwatch.getName()+"'>");
					out.println("<input type='hidden' name='userage' value='"+userage+"'>");
					out.println("<input type='hidden' name='pcompany' value='"+fwatch.getCompany()+"'>");
					out.println("<input type='hidden' name='pprice' value='"+fwatch.getPrice()+"'>");
					out.println("<input type='hidden' name='rname' value='"+fwatch.getRetailer()+"'>");
					out.println("<input type='hidden' name='rcity' value='"+retailer.getRetailerCity()+"'>");
					out.println("<input type='hidden' name='rstate' value='"+retailer.getRetailerState()+"'>");
					out.println("<input type='hidden' name='rzip' value='"+retailer.getRetailerZip()+"'>");
					out.println("<input type='hidden' name='rdate' value='"+d+"'>");
					out.println("<tr><td colspan=2><label><b>Write Review</b></label></br><textarea style='width:100%;' placeholder='Enter Review Text' name='rtext' required ></textarea></td></tr>");
					out.println("<tr><td colspan=2><b>Today's Date :</b>"+d+"</td></tr>");
					
					out.println("<tr><td colspan=2><label><b>Rate This Product</b></label></br>");
					out.println("<select style='width:100%;' name='rrating'><option value='1' selected>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option></select></td></tr><tr><td>");	
					out.println("<input type='hidden' name='pId' value='"+fwatch.getId()+"'>");
					out.println("<input type='hidden' name='pCategory' value='"+category+"'>");
					out.println("<tr><td width='100%'><input class=\"button\" type=\"submit\"style=' width:100%;background-color:green;padding:16px 21px;margin:8px;color: white;cursor: pointer;' name=\"action\" value=\"Submit Review\"></td>");
					out.println("</tr></tr></td></table></td></tr></table></form>");
				}
			}
		}
		else if(category.equals("watches")){
			watches=(ArrayList<SmartWatch>)products.get(category);
			for(SmartWatch watch : watches){
				if(watch.getId().equals(id)){
					out.println("<form action = 'CheckReviewsPage'>");
					out.println("<table border=\"1\" style=\"width:100%\" style=\"height:100%\">");
					out.println("<tr><td width=\"35%\"><img style=\"width:420px;height:470px;\"  src=\"images\\"+watch.getImage()+"\" style=\"display:block;\" /></td><td width=\"65%\"><table>");
					out.println("<tr><td colspan=2><h2 align='center' style='color:green;'> Name :"+watch.getName()+"</h2></td></tr>");
					out.println("<tr><td colspan=2><b>Retailer Name : </b>"+watch.getRetailer()+"</td></tr>");
					out.println("<tr><td colspan=2><b>Price : $</b>"+watch.getPrice()+"</td></tr>");
					out.println("<tr><td colspan=2><b>Manufacturer Name : </b>"+watch.getCompany()+"</td></tr>");
				
					Date todaydate = new Date();
					DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
					String d=df.format(todaydate);
					out.println("<input type='hidden' name='username' value='"+username+"'>");
					out.println("<input type='hidden' name='usergender' value='"+usergender+"'>");
					out.println("<input type='hidden' name='pname' value='"+watch.getName()+"'>");
					out.println("<input type='hidden' name='userage' value='"+userage+"'>");
					out.println("<input type='hidden' name='pcompany' value='"+watch.getCompany()+"'>");
					out.println("<input type='hidden' name='pprice' value='"+watch.getPrice()+"'>");
					out.println("<input type='hidden' name='rname' value='"+watch.getRetailer()+"'>");
					out.println("<input type='hidden' name='rdate' value='"+d+"'>");
					out.println("<tr><td colspan=2><label><b>Write Review</b></label></br><textarea style='width:100%;' placeholder='Enter Review Text' name='rtext' required ></textarea></td></tr>");
					out.println("<tr><td colspan=2><b>Today's Date :</b>"+d+"</td></tr>");
					
					out.println("<tr><td colspan=2><label><b>Rate This Product</b></label></br>");
					out.println("<select style='width:100%;' name='rrating'><option value='1' selected>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option></select></td></tr><tr><td>");	
					out.println("<input type='hidden' name='pId' value='"+watch.getId()+"'>");
					out.println("<input type='hidden' name='pCategory' value='"+category+"'>");
					out.println("<tr><td width='100%'><input class=\"button\" type=\"submit\"style=' width:100%;background-color:green;padding:16px 21px;margin:8px;color: white;cursor: pointer;' name=\"action\" value=\"Submit Review\"></td>");
					out.println("</tr></tr></td></table></td></tr></table></form>");
				}
			}
		}
		else if(category.equals("laptops")){
			laptops=(ArrayList<Laptop>)products.get(category);
			for(Laptop laptop : laptops){
				if(laptop.getId().equals(id)){
					out.println("<form action = 'CheckReviewsPage'>");
					out.println("<table border=\"1\" style=\"width:100%\" style=\"height:100%\">");
					out.println("<tr><td width=\"35%\"><img style=\"width:420px;height:470px;\"  src=\"images\\"+laptop.getImage()+"\" style=\"display:block;\" /></td><td width=\"65%\"><table>");
					out.println("<tr><td colspan=2><h2 align='center' style='color:green;'> Name :"+laptop.getName()+"</h2></td></tr>");
					out.println("<tr><td colspan=2><b>Retailer Name : </b>"+laptop.getRetailer()+"</td></tr>");
					out.println("<tr><td colspan=2><b>Price : $</b>"+laptop.getPrice()+"</td></tr>");
					out.println("<tr><td colspan=2><b>Manufacturer Name : </b>"+laptop.getCompany()+"</td></tr>");
				
					Date todaydate = new Date();
					DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
					String d=df.format(todaydate);
					out.println("<input type='hidden' name='username' value='"+username+"'>");
					out.println("<input type='hidden' name='usergender' value='"+usergender+"'>");
					out.println("<input type='hidden' name='pname' value='"+laptop.getName()+"'>");
					out.println("<input type='hidden' name='userage' value='"+userage+"'>");
					out.println("<input type='hidden' name='pcompany' value='"+laptop.getCompany()+"'>");
					out.println("<input type='hidden' name='pprice' value='"+laptop.getPrice()+"'>");
					out.println("<input type='hidden' name='rname' value='"+laptop.getRetailer()+"'>");
					out.println("<input type='hidden' name='rdate' value='"+d+"'>");
					out.println("<tr><td colspan=2><label><b>Write Review</b></label></br><textarea style='width:100%;' placeholder='Enter Review Text' name='rtext' required ></textarea></td></tr>");
					out.println("<tr><td colspan=2><b>Today's Date :</b>"+d+"</td></tr>");
					
					out.println("<tr><td colspan=2><label><b>Rate This Product</b></label></br>");
					out.println("<select style='width:100%;' name='rrating'><option value='1' selected>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option></select></td></tr><tr><td>");	
					out.println("<input type='hidden' name='pId' value='"+laptop.getId()+"'>");
					out.println("<input type='hidden' name='pCategory' value='"+category+"'>");
					out.println("<tr><td width='100%'><input class=\"button\" type=\"submit\"style=' width:100%;background-color:green;padding:16px 21px;margin:8px;color: white;cursor: pointer;' name=\"action\" value=\"Submit Review\"></td>");
					out.println("</tr></tr></td></table></td></tr></table></form>");
				}
			}
		}
		else if(category.equals("speakers")){
			speakers=(ArrayList<SoundSystem>)products.get(category);
			for(SoundSystem speaker : speakers){
				if(speaker.getId().equals(id)){
					out.println("<form action = 'CheckReviewsPage'>");
					out.println("<table border=\"1\" style=\"width:100%\" style=\"height:100%\">");
					out.println("<tr><td width=\"35%\"><img style=\"width:420px;height:470px;\"  src=\"images\\"+speaker.getImage()+"\" style=\"display:block;\" /></td><td width=\"65%\"><table>");
					out.println("<tr><td colspan=2><h2 align='center' style='color:green;'> Name :"+speaker.getName()+"</h2></td></tr>");
					out.println("<tr><td colspan=2><b>Retailer Name : </b>"+speaker.getRetailer()+"</td></tr>");
					out.println("<tr><td colspan=2><b>Price : $</b>"+speaker.getPrice()+"</td></tr>");
					out.println("<tr><td colspan=2><b>Manufacturer Name : </b>"+speaker.getCompany()+"</td></tr>");
				
					Date todaydate = new Date();
					DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
					String d=df.format(todaydate);
					out.println("<input type='hidden' name='username' value='"+username+"'>");
					out.println("<input type='hidden' name='usergender' value='"+usergender+"'>");
					out.println("<input type='hidden' name='pname' value='"+speaker.getName()+"'>");
					out.println("<input type='hidden' name='userage' value='"+userage+"'>");
					out.println("<input type='hidden' name='pcompany' value='"+speaker.getCompany()+"'>");
					out.println("<input type='hidden' name='pprice' value='"+speaker.getPrice()+"'>");
					out.println("<input type='hidden' name='rname' value='"+speaker.getRetailer()+"'>");
					out.println("<input type='hidden' name='rdate' value='"+d+"'>");
					out.println("<tr><td colspan=2><label><b>Write Review</b></label></br><textarea style='width:100%;' placeholder='Enter Review Text' name='rtext' required ></textarea></td></tr>");
					out.println("<tr><td colspan=2><b>Today's Date :</b>"+d+"</td></tr>");
					
					out.println("<tr><td colspan=2><label><b>Rate This Product</b></label></br>");
					out.println("<select style='width:100%;' name='rrating'><option value='1' selected>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option></select></td></tr><tr><td>");	
					out.println("<input type='hidden' name='pId' value='"+speaker.getId()+"'>");
					out.println("<input type='hidden' name='pCategory' value='"+category+"'>");
					out.println("<tr><td width='100%'><input class=\"button\" type=\"submit\"style=' width:100%;background-color:green;padding:16px 21px;margin:8px;color: white;cursor: pointer;' name=\"action\" value=\"Submit Review\"></td>");
					out.println("</tr></tr></td></table></td></tr></table></form>");
				}
			}
		}
		else if(category.equals("televisions")){
			televisions=(ArrayList<Television>)products.get(category);
			for(Television television : televisions){
				if(television.getId().equals(id)){
					out.println("<form action = 'CheckReviewsPage'>");
					out.println("<table border=\"1\" style=\"width:100%\" style=\"height:100%\">");
					out.println("<tr><td width=\"35%\"><img style=\"width:420px;height:470px;\"  src=\"images\\"+television.getImage()+"\" style=\"display:block;\" /></td><td width=\"65%\"><table>");
					out.println("<tr><td colspan=2><h2 align='center' style='color:green;'> Name :"+television.getName()+"</h2></td></tr>");
					out.println("<tr><td colspan=2><b>Retailer Name : </b>"+television.getRetailer()+"</td></tr>");
					out.println("<tr><td colspan=2><b>Price : $</b>"+television.getPrice()+"</td></tr>");
					out.println("<tr><td colspan=2><b>Manufacturer Name : </b>"+television.getCompany()+"</td></tr>");
				
					Date todaydate = new Date();
					DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
					String d=df.format(todaydate);
					out.println("<input type='hidden' name='username' value='"+username+"'>");
					out.println("<input type='hidden' name='usergender' value='"+usergender+"'>");
					out.println("<input type='hidden' name='pname' value='"+television.getName()+"'>");
					out.println("<input type='hidden' name='userage' value='"+userage+"'>");
					out.println("<input type='hidden' name='pcompany' value='"+television.getCompany()+"'>");
					out.println("<input type='hidden' name='pprice' value='"+television.getPrice()+"'>");
					out.println("<input type='hidden' name='rname' value='"+television.getRetailer()+"'>");
					out.println("<input type='hidden' name='rdate' value='"+d+"'>");
					out.println("<tr><td colspan=2><label><b>Write Review</b></label></br><textarea style='width:100%;' placeholder='Enter Review Text' name='rtext' required ></textarea></td></tr>");
					out.println("<tr><td colspan=2><b>Today's Date :</b>"+d+"</td></tr>");
					
					out.println("<tr><td colspan=2><label><b>Rate This Product</b></label></br>");
					out.println("<select style='width:100%;' name='rrating'><option value='1' selected>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option></select></td></tr><tr><td>");	
					out.println("<input type='hidden' name='pId' value='"+television.getId()+"'>");
					out.println("<input type='hidden' name='pCategory' value='"+category+"'>");
					out.println("<tr><td width='100%'><input class=\"button\" type=\"submit\"style=' width:100%;background-color:green;padding:16px 21px;margin:8px;color: white;cursor: pointer;' name=\"action\" value=\"Submit Review\"></td>");
					out.println("</tr></tr></td></table></td></tr></table></form>");
				}
			}
		}
		else if(category.equals("voiceassistants")){
			voiceassistants=(ArrayList<VoiceAssistant>)products.get(category);
			for(VoiceAssistant voiceassistant : voiceassistants){
				if(voiceassistant.getId().equals(id)){
					out.println("<form action = 'CheckReviewsPage'>");
					out.println("<table border=\"1\" style=\"width:100%\" style=\"height:100%\">");
					out.println("<tr><td width=\"35%\"><img style=\"width:420px;height:470px;\"  src=\"images\\"+voiceassistant.getImage()+"\" style=\"display:block;\" /></td><td width=\"65%\"><table>");
					out.println("<tr><td colspan=2><h2 align='center' style='color:green;'> Name :"+voiceassistant.getName()+"</h2></td></tr>");
					out.println("<tr><td colspan=2><b>Retailer Name : </b>"+voiceassistant.getRetailer()+"</td></tr>");
					out.println("<tr><td colspan=2><b>Price : $</b>"+voiceassistant.getPrice()+"</td></tr>");
					out.println("<tr><td colspan=2><b>Manufacturer Name : </b>"+voiceassistant.getCompany()+"</td></tr>");
				
					Date todaydate = new Date();
					DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
					String d=df.format(todaydate);
					out.println("<input type='hidden' name='username' value='"+username+"'>");
					out.println("<input type='hidden' name='usergender' value='"+usergender+"'>");
					out.println("<input type='hidden' name='pname' value='"+voiceassistant.getName()+"'>");
					out.println("<input type='hidden' name='userage' value='"+userage+"'>");
					out.println("<input type='hidden' name='pcompany' value='"+voiceassistant.getCompany()+"'>");
					out.println("<input type='hidden' name='pprice' value='"+voiceassistant.getPrice()+"'>");
					out.println("<input type='hidden' name='rname' value='"+voiceassistant.getRetailer()+"'>");
					out.println("<input type='hidden' name='rdate' value='"+d+"'>");
					out.println("<tr><td colspan=2><label><b>Write Review</b></label></br><textarea style='width:100%;' placeholder='Enter Review Text' name='rtext' required ></textarea></td></tr>");
					out.println("<tr><td colspan=2><b>Today's Date :</b>"+d+"</td></tr>");
					
					out.println("<tr><td colspan=2><label><b>Rate This Product</b></label></br>");
					out.println("<select style='width:100%;' name='rrating'><option value='1' selected>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option></select></td></tr><tr><td>");	
					out.println("<input type='hidden' name='pId' value='"+voiceassistant.getId()+"'>");
					out.println("<input type='hidden' name='pCategory' value='"+category+"'>");
					out.println("<tr><td width='100%'><input class=\"button\" type=\"submit\"style=' width:100%;background-color:green;padding:16px 21px;margin:8px;color: white;cursor: pointer;' name=\"action\" value=\"Submit Review\"></td>");
					out.println("</tr></tr></td></table></td></tr></table></form>");
				}
			}
		}
		else if(category.equals("headphones")){
			headphones=(ArrayList<HeadPhone>)products.get(category);
			for(HeadPhone headphone : headphones){
				if(headphone.getId().equals(id)){
					out.println("<form action = 'CheckReviewsPage'>");
					out.println("<table border=\"1\" style=\"width:100%\" style=\"height:100%\">");
					out.println("<tr><td width=\"35%\"><img style=\"width:420px;height:470px;\"  src=\"images\\"+headphone.getImage()+"\" style=\"display:block;\" /></td><td width=\"65%\"><table>");
					out.println("<tr><td colspan=2><h2 align='center' style='color:green;'> Name :"+headphone.getName()+"</h2></td></tr>");
					out.println("<tr><td colspan=2><b>Retailer Name : </b>"+headphone.getRetailer()+"</td></tr>");
					out.println("<tr><td colspan=2><b>Price : $</b>"+headphone.getPrice()+"</td></tr>");
					out.println("<tr><td colspan=2><b>Manufacturer Name : </b>"+headphone.getCompany()+"</td></tr>");
				
					Date todaydate = new Date();
					DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
					String d=df.format(todaydate);
					out.println("<input type='hidden' name='username' value='"+username+"'>");
					out.println("<input type='hidden' name='usergender' value='"+usergender+"'>");
					out.println("<input type='hidden' name='pname' value='"+headphone.getName()+"'>");
					out.println("<input type='hidden' name='userage' value='"+userage+"'>");
					out.println("<input type='hidden' name='pcompany' value='"+headphone.getCompany()+"'>");
					out.println("<input type='hidden' name='pprice' value='"+headphone.getPrice()+"'>");
					out.println("<input type='hidden' name='rname' value='"+headphone.getRetailer()+"'>");
					out.println("<input type='hidden' name='rdate' value='"+d+"'>");
					out.println("<tr><td colspan=2><label><b>Write Review</b></label></br><textarea style='width:100%;' placeholder='Enter Review Text' name='rtext' required ></textarea></td></tr>");
					out.println("<tr><td colspan=2><b>Today's Date :</b>"+d+"</td></tr>");
					
					out.println("<tr><td colspan=2><label><b>Rate This Product</b></label></br>");
					out.println("<select style='width:100%;' name='rrating'><option value='1' selected>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option></select></td></tr><tr><td>");	
					out.println("<input type='hidden' name='pId' value='"+headphone.getId()+"'>");
					out.println("<input type='hidden' name='pCategory' value='"+category+"'>");
					out.println("<tr><td width='100%'><input class=\"button\" type=\"submit\"style=' width:100%;background-color:green;padding:16px 21px;margin:8px;color: white;cursor: pointer;' name=\"action\" value=\"Submit Review\"></td>");
					out.println("</tr></tr></td></table></td></tr></table></form>");
				}
			}
		}
		else if(category.equals("phones")){
			phones=(ArrayList<Phone>)products.get(category);
			for(Phone phone : phones){
				if(phone.getId().equals(id)){
					out.println("<form action = 'CheckReviewsPage'>");
					out.println("<table border=\"1\" style=\"width:100%\" style=\"height:100%\">");
					out.println("<tr><td width=\"35%\"><img style=\"width:420px;height:470px;\"  src=\"images\\"+phone.getImage()+"\" style=\"display:block;\" /></td><td width=\"65%\"><table>");
					out.println("<tr><td colspan=2><h2 align='center' style='color:green;'> Name :"+phone.getName()+"</h2></td></tr>");
					out.println("<tr><td colspan=2><b>Retailer Name : </b>"+phone.getRetailer()+"</td></tr>");
					out.println("<tr><td colspan=2><b>Price : $</b>"+phone.getPrice()+"</td></tr>");
					out.println("<tr><td colspan=2><b>Manufacturer Name : </b>"+phone.getCompany()+"</td></tr>");
				
					Date todaydate = new Date();
					DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
					String d=df.format(todaydate);
					out.println("<input type='hidden' name='username' value='"+username+"'>");
					out.println("<input type='hidden' name='usergender' value='"+usergender+"'>");
					out.println("<input type='hidden' name='pname' value='"+phone.getName()+"'>");
					out.println("<input type='hidden' name='userage' value='"+userage+"'>");
					out.println("<input type='hidden' name='pcompany' value='"+phone.getCompany()+"'>");
					out.println("<input type='hidden' name='pprice' value='"+phone.getPrice()+"'>");
					out.println("<input type='hidden' name='rname' value='"+phone.getRetailer()+"'>");
					out.println("<input type='hidden' name='rdate' value='"+d+"'>");
					out.println("<tr><td colspan=2><label><b>Write Review</b></label></br><textarea style='width:100%;' placeholder='Enter Review Text' name='rtext' required ></textarea></td></tr>");
					out.println("<tr><td colspan=2><b>Today's Date :</b>"+d+"</td></tr>");
					
					out.println("<tr><td colspan=2><label><b>Rate This Product</b></label></br>");
					out.println("<select style='width:100%;' name='rrating'><option value='1' selected>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option></select></td></tr><tr><td>");	
					out.println("<input type='hidden' name='pId' value='"+phone.getId()+"'>");
					out.println("<input type='hidden' name='pCategory' value='"+category+"'>");
					out.println("<tr><td width='100%'><input class=\"button\" type=\"submit\"style=' width:100%;background-color:green;padding:16px 21px;margin:8px;color: white;cursor: pointer;' name=\"action\" value=\"Submit Review\"></td>");
					out.println("</tr></tr></td></table></td></tr></table></form>");
				}
			}
		}
		out.println("</article></section></div><div class=\"clear\"></div>");
		util.parseHtml("footer.html");
		
	}
}