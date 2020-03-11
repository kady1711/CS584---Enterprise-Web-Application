import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class ProductDetailsPage extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("text/html");
		String category=request.getParameter("pCategory");
		String id=request.getParameter("pId");
		PrintWriter out =response.getWriter();
		HttpSession session= request.getSession();
		UserDetail userdetail= (UserDetail) session.getAttribute("UserDetail");
		Utilities util=new Utilities(request,out);
		String firstname=null,usertype=null;
		if(session.getAttribute("UserDetail") != null)
		{
			firstname=((UserDetail)(session.getAttribute("UserDetail"))).getFirstName();
			usertype=((UserDetail)(session.getAttribute("UserDetail"))).getUserType();
		} 
		
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
		
		
		util.parseHtml("header.html");
		
		SaxParser saxparser= new SaxParser("C:\\apache-tomcat-7.0.34\\webapps\\Assignment_kp\\WEB-INF\\classes\\ProductCatalog.xml");
		HashMap<String,Object> products = saxparser.getProducts();
		
		out.println("<div id='body' style=\"background-color:white;width:100%;\"><section style=\"background-color:white;width:100%;\" id='content'>");
		out.println("<article><h1><span style='color:green;'>"+"Product Details</span></h1> ");
		
		
		if(category.equals("fwatches")){
			fwatches=(ArrayList<FitnessWatch>)products.get(category);
			for(FitnessWatch fwatch : fwatches){
				if(fwatch.getId().equals(id)){
					out.println("<table style=\"border=\"1\" width:100%\" style=\"height:100%\">");
					out.println("<tr><td width=\"35%\"><img style=\"width:420px;height:470px;\"  src=\"images\\"+fwatch.getImage()+"\" style=\"display:block;\" /></td><td width=\"65%\"><table>");
					out.println("<tr><td colspan=2><h2 align='center' style='color:green;'> Name :"+fwatch.getName()+"</h2></td></tr>");
					out.println("<tr><td colspan=2><b>Description: </b>"+fwatch.getDescription()+"</td></tr>");
					out.println("<tr><td colspan=2><b>Price: $</b>"+fwatch.getPrice()+"</td></tr>");
					out.println("<tr><td colspan=2><b>Company: </b>"+fwatch.getCompany()+"</td></tr>");
					//out.println("<tr><td colspan=2><b>Retailer: </b>"+fwatch.getRetailer()+"</td></tr>");
					out.println("<tr><td colspan=2><b>Color: </b>"+fwatch.getColor()+"</td></tr>");
					out.println("<tr><td colspan=2><b>Condition: </b>"+fwatch.getCondition()+"</td></tr><tr><td>");
					if(firstname != null && !(firstname.isEmpty())){	
						out.println("<form method = 'get' action = 'AddCartPage'>");
						out.println("<input type='hidden' name='pId' value='"+fwatch.getId()+"'>"+"<input type='hidden' name='pCategory' value='"+category+"'>");
						out.println("<tr><td  align='center' width='75%'><input style='color:white; background-color:green;padding:16px 22px;margin:7px;width:100%;cursor:pointer; }' type=\"submit\" class=\"button\"  name=\"action\" value=\"Add To Cart\"></td></form>");
					}
					out.println("<td width='25%' align='center'><a href=\"ProductsPage?category="+category+"\"><button style='color:white; background-color:green;padding:16px 22px;margin:7px;width:100%;cursor:pointer; }'>Back</button></a></td>");
					out.println("</tr></tr></td></table></td></tr></table><div class=\"clear\"></div><div class=\"clear\"></div><article align='center'>");
					if(!(fwatch.accessories.isEmpty())){
						out.println("<h1 align=\"center\"><span style='color:green;'>"+"Product Accessories</span></h1><div id=\"myCarousel\" class=\"carousel slide\" data-ride=\"carousel\" style=\"margin-left:11%;width:81%;\"><div class=\"carousel-inner\" align='center' role=\"listbox\" >");
						int i=1;	
						for (Accessory accessory: fwatch.accessories) {
							if(i!=1)
							{
								out.println("<div class=\"item\">");
							}
							else{
								out.println("<div class=\"item active\">");
							}
							out.println("<table style='color:black;'>");
							out.println("<tr><td align='center'><img class=\"img-responsive\" style='width:200px;height:300px;' src=\"images\\"+accessory.getImage()+"\"></td></tr>");
							out.println("<tr><td align='center'>Name : "+accessory.getName()+"</td></tr>");
							out.println("<tr><td align='center'>Condition : "+accessory.getCondition()+"</td></tr>");
							out.println("<tr><td align='center'>Price : $"+accessory.getPrice()+"</td></tr>");
							if(firstname != null && !(firstname.isEmpty()))
							{	
								out.println("<form method = 'get' action = 'AddCartPage'>");
								out.println("<input type='hidden' name='pId' value='"+fwatch.getId()+"'>");
								out.println("<input type='hidden' name='pCategory' value='"+category+"'>");
								out.println("<input type='hidden' name='aName' value='"+accessory.getName()+"'>");
								out.println("<tr><td width='70%' align='center'><input class=\"button\" type=\"submit\" style=' width:100%;background-color:green;padding:16px 21px;margin:8px;color: white;cursor: pointer;}' name=\"action\" value=\"Add To Cart\"></td></tr></form>");
								
							
							}
							i++;
							out.println("</table></div>");
							
						}
						out.println("</div><a class=\"left carousel-control\" href=\"#myCarousel\" role=\"button\" data-slide=\"prev\"><span class=\"glyphicon glyphicon-chevron-left\" aria-hidden=\"true\"></span><span class=\"sr-only\">Previous</span></a>");	
						out.println("<a class=\"right carousel-control\" href=\"#myCarousel\" role=\"button\" data-slide=\"next\"><span class=\"glyphicon glyphicon-chevron-right\" aria-hidden=\"true\"></span><span class=\"sr-only\">Next</span></a></div>");
					}
					else
					{
						out.println("<article><h1 align=\"center\"><span style='color:green;'>"+"No Product Accessory </span></h1> ");
					}						
					out.println("</article>");
				}
			}
		}
		else if(category.equals("watches")){
			watches=(ArrayList<SmartWatch>)products.get(category);
			for(SmartWatch watch : watches){
				if(watch.getId().equals(id)){
					out.println("<table style=\"border=\"1\" width:100%\" style=\"height:100%\">");
					out.println("<tr><td width=\"35%\"><img style=\"width:420px;height:470px;\"  src=\"images\\"+watch.getImage()+"\" style=\"display:block;\" /></td><td width=\"65%\"><table>");
					out.println("<tr><td colspan=2><h2 align='center' style='color:green;'> Name :"+watch.getName()+"</h2></td></tr>");
					out.println("<tr><td colspan=2><b>Description: </b>"+watch.getDescription()+"</td></tr>");
					out.println("<tr><td colspan=2><b>Price: $</b>"+watch.getPrice()+"</td></tr>");
					out.println("<tr><td colspan=2><b>Company: </b>"+watch.getCompany()+"</td></tr>");
					//out.println("<tr><td colspan=2><b>Retailer: </b>"+fwatch.getRetailer()+"</td></tr>");
					out.println("<tr><td colspan=2><b>Color: </b>"+watch.getColor()+"</td></tr>");
					out.println("<tr><td colspan=2><b>Condition: </b>"+watch.getCondition()+"</td></tr><tr><td>");
					if(firstname != null && !(firstname.isEmpty())){	
						out.println("<form method = 'get' action = 'AddCartPage'>");
						out.println("<input type='hidden' name='pId' value='"+watch.getId()+"'>"+"<input type='hidden' name='pCategory' value='"+category+"'>");
						out.println("<tr><td  align='center' width='75%'><input style='color:white; background-color:green;padding:16px 22px;margin:7px;width:100%;cursor:pointer; }' type=\"submit\" class=\"button\"  name=\"action\" value=\"Add To Cart\"></td></form>");
					}
					out.println("<td width='25%' align='center'><a href=\"ProductsPage?category="+category+"\"><button style='color:white; background-color:green;padding:16px 22px;margin:7px;width:100%;cursor:pointer; }'>Back</button></a></td>");
					out.println("</tr></tr></td></table></td></tr></table><div class=\"clear\"></div><div class=\"clear\"></div><article align='center'>");
					if(!(watch.accessories.isEmpty())){
						out.println("<h1 align=\"center\"><span style='color:green;'>"+"Product Accessories</span></h1><div id=\"myCarousel\" class=\"carousel slide\" data-ride=\"carousel\" style=\"margin-left:11%;width:81%;\"><div class=\"carousel-inner\" align='center' role=\"listbox\" >");
						int i=1;	
						for (Accessory accessory: watch.accessories) {
							if(i!=1)
							{
								out.println("<div class=\"item\">");
							}
							else{
								out.println("<div class=\"item active\">");
							}
							out.println("<table style='color:black;'>");
							out.println("<tr><td align='center'><img class=\"img-responsive\" style='width:200px;height:300px;' src=\"images\\"+accessory.getImage()+"\"></td></tr>");
							out.println("<tr><td align='center'>Name : "+accessory.getName()+"</td></tr>");
							out.println("<tr><td align='center'>Condition : "+accessory.getCondition()+"</td></tr>");
							out.println("<tr><td align='center'>Price : $"+accessory.getPrice()+"</td></tr>");
							if(firstname != null && !(firstname.isEmpty()))
							{	
								out.println("<form method = 'get' action = 'AddCartPage'>");
								out.println("<input type='hidden' name='pId' value='"+watch.getId()+"'>");
								out.println("<input type='hidden' name='pCategory' value='"+category+"'>");
								out.println("<input type='hidden' name='aName' value='"+accessory.getName()+"'>");
								out.println("<tr><td width='70%' align='center'><input class=\"button\" type=\"submit\" style=' width:100%;background-color:green;padding:16px 21px;margin:8px;color: white;cursor: pointer;}' name=\"action\" value=\"Add To Cart\"></td></tr></form>");
								
							}
							i++;
							out.println("</table></div>");
							
						}
						out.println("</div><a class=\"left carousel-control\" href=\"#myCarousel\" role=\"button\" data-slide=\"prev\"><span class=\"glyphicon glyphicon-chevron-left\" aria-hidden=\"true\"></span><span class=\"sr-only\">Previous</span></a>");	
						out.println("<a class=\"right carousel-control\" href=\"#myCarousel\" role=\"button\" data-slide=\"next\"><span class=\"glyphicon glyphicon-chevron-right\" aria-hidden=\"true\"></span><span class=\"sr-only\">Next</span></a></div>");
					}
					else
					{
						out.println("<article><h1 align=\"center\"><span style='color:green;'>"+"No Product Accessory </span></h1> ");
					}						
					out.println("</article>");
				}
			}
		}
		else if(category.equals("laptops")){
			laptops=(ArrayList<Laptop>)products.get(category);
			for(Laptop laptop : laptops){
				if(laptop.getId().equals(id)){
					out.println("<table style=\"border=\"1\" width:100%\" style=\"height:100%\">");
					out.println("<tr><td width=\"35%\"><img style=\"width:420px;height:470px;\"  src=\"images\\"+laptop.getImage()+"\" style=\"display:block;\" /></td><td width=\"65%\"><table>");
					out.println("<tr><td colspan=2><h2 align='center' style='color:green;'> Name :"+laptop.getName()+"</h2></td></tr>");
					out.println("<tr><td colspan=2><b>Description: </b>"+laptop.getDescription()+"</td></tr>");
					out.println("<tr><td colspan=2><b>Price: $</b>"+laptop.getPrice()+"</td></tr>");
					out.println("<tr><td colspan=2><b>Company: </b>"+laptop.getCompany()+"</td></tr>");
					//out.println("<tr><td colspan=2><b>Retailer: </b>"+fwatch.getRetailer()+"</td></tr>");
					out.println("<tr><td colspan=2><b>Color: </b>"+laptop.getColor()+"</td></tr>");
					out.println("<tr><td colspan=2><b>Condition: </b>"+laptop.getCondition()+"</td></tr><tr><td>");
					if(firstname != null && !(firstname.isEmpty())){	
						out.println("<form method = 'get' action = 'AddCartPage'>");
						out.println("<input type='hidden' name='pId' value='"+laptop.getId()+"'>"+"<input type='hidden' name='pCategory' value='"+category+"'>");
						out.println("<tr><td  align='center' width='75%'><input style='color:white; background-color:green;padding:16px 22px;margin:7px;width:100%;cursor:pointer; }' type=\"submit\" class=\"button\"  name=\"action\" value=\"Add To Cart\"></td></form>");
					}
					out.println("<td width='25%' align='center'><a href=\"ProductsPage?category="+category+"\"><button style='color:white; background-color:green;padding:16px 22px;margin:7px;width:100%;cursor:pointer; }'>Back</button></a></td>");
					out.println("</tr></tr></td></table></td></tr></table><div class=\"clear\"></div><div class=\"clear\"></div><article align='center'>");
					if(!(laptop.accessories.isEmpty())){
						out.println("<h1 align=\"center\"><span style='color:green;'>"+"Product Accessories</span></h1><div id=\"myCarousel\" class=\"carousel slide\" data-ride=\"carousel\" style=\"margin-left:11%;width:81%;\"><div class=\"carousel-inner\" align='center' role=\"listbox\" >");
						int i=1;	
						for (Accessory accessory: laptop.accessories) {
							if(i!=1)
							{
								out.println("<div class=\"item\">");
							}
							else{
								out.println("<div class=\"item active\">");
							}
							out.println("<table style='color:black;'>");
							out.println("<tr><td align='center'><img class=\"img-responsive\" style='width:200px;height:300px;' src=\"images\\"+accessory.getImage()+"\"></td></tr>");
							out.println("<tr><td align='center'>Name : "+accessory.getName()+"</td></tr>");
							out.println("<tr><td align='center'>Condition : "+accessory.getCondition()+"</td></tr>");
							out.println("<tr><td align='center'>Price : $"+accessory.getPrice()+"</td></tr>");
							if(firstname != null && !(firstname.isEmpty()))
							{	
								out.println("<form method = 'get' action = 'AddCartPage'>");
								out.println("<input type='hidden' name='pId' value='"+laptop.getId()+"'>");
								out.println("<input type='hidden' name='pCategory' value='"+category+"'>");
								out.println("<input type='hidden' name='aName' value='"+accessory.getName()+"'>");
								out.println("<tr><td width='70%' align='center'><input class=\"button\" type=\"submit\" style=' width:100%;background-color:green;padding:16px 21px;margin:8px;color: white;cursor: pointer;}' name=\"action\" value=\"Add To Cart\"></td></tr></form>");
								
							}
							i++;
							out.println("</table></div>");
							
						}
						out.println("</div><a class=\"left carousel-control\" href=\"#myCarousel\" role=\"button\" data-slide=\"prev\"><span class=\"glyphicon glyphicon-chevron-left\" aria-hidden=\"true\"></span><span class=\"sr-only\">Previous</span></a>");	
						out.println("<a class=\"right carousel-control\" href=\"#myCarousel\" role=\"button\" data-slide=\"next\"><span class=\"glyphicon glyphicon-chevron-right\" aria-hidden=\"true\"></span><span class=\"sr-only\">Next</span></a></div>");
					}
					else
					{
						out.println("<article><h1 align=\"center\"><span style='color:green;'>"+"No Product Accessory </span></h1> ");
					}						
					out.println("</article>");
				}
			}
		}
		else if(category.equals("speakers")){
			speakers=(ArrayList<SoundSystem>)products.get(category);
			for(SoundSystem speaker : speakers){
				if(speaker.getId().equals(id)){
					out.println("<table style=\"border=\"1\" width:100%\" style=\"height:100%\">");
					out.println("<tr><td width=\"35%\"><img style=\"width:420px;height:470px;\"  src=\"images\\"+speaker.getImage()+"\" style=\"display:block;\" /></td><td width=\"65%\"><table>");
					out.println("<tr><td colspan=2><h2 align='center' style='color:green;'> Name :"+speaker.getName()+"</h2></td></tr>");
					out.println("<tr><td colspan=2><b>Description: </b>"+speaker.getDescription()+"</td></tr>");
					out.println("<tr><td colspan=2><b>Price: $</b>"+speaker.getPrice()+"</td></tr>");
					out.println("<tr><td colspan=2><b>Company: </b>"+speaker.getCompany()+"</td></tr>");
					//out.println("<tr><td colspan=2><b>Retailer: </b>"+fwatch.getRetailer()+"</td></tr>");
					out.println("<tr><td colspan=2><b>Color: </b>"+speaker.getColor()+"</td></tr>");
					out.println("<tr><td colspan=2><b>Condition: </b>"+speaker.getCondition()+"</td></tr><tr><td>");
					if(firstname != null && !(firstname.isEmpty())){	
						out.println("<form method = 'get' action = 'AddCartPage'>");
						out.println("<input type='hidden' name='pId' value='"+speaker.getId()+"'>"+"<input type='hidden' name='pCategory' value='"+category+"'>");
						out.println("<tr><td  align='center' width='75%'><input style='color:white; background-color:green;padding:16px 22px;margin:7px;width:100%;cursor:pointer; }' type=\"submit\" class=\"button\"  name=\"action\" value=\"Add To Cart\"></td></form>");
					}
					out.println("<td width='25%' align='center'><a href=\"ProductsPage?category="+category+"\"><button style='color:white; background-color:green;padding:16px 22px;margin:7px;width:100%;cursor:pointer; }'>Back</button></a></td>");
					out.println("</tr></tr></td></table></td></tr></table><div class=\"clear\"></div><div class=\"clear\"></div><article align='center'>");
					if(!(speaker.accessories.isEmpty())){
						out.println("<h1 align=\"center\"><span style='color:green;'>"+"Product Accessories</span></h1><div id=\"myCarousel\" class=\"carousel slide\" data-ride=\"carousel\" style=\"margin-left:11%;width:81%;\"><div class=\"carousel-inner\" align='center' role=\"listbox\" >");
						int i=1;	
						for (Accessory accessory: speaker.accessories) {
							if(i!=1)
							{
								out.println("<div class=\"item\">");
							}
							else{
								out.println("<div class=\"item active\">");
							}
							out.println("<table style='color:black;'>");
							out.println("<tr><td align='center'><img class=\"img-responsive\" style='width:200px;height:300px;' src=\"images\\"+accessory.getImage()+"\"></td></tr>");
							out.println("<tr><td align='center'>Name : "+accessory.getName()+"</td></tr>");
							out.println("<tr><td align='center'>Condition : "+accessory.getCondition()+"</td></tr>");
							out.println("<tr><td align='center'>Price : $"+accessory.getPrice()+"</td></tr>");
							if(firstname != null && !(firstname.isEmpty()))
							{	
								out.println("<form method = 'get' action = 'AddCartPage'>");
								out.println("<input type='hidden' name='pId' value='"+speaker.getId()+"'>");
								out.println("<input type='hidden' name='pCategory' value='"+category+"'>");
								out.println("<input type='hidden' name='aName' value='"+accessory.getName()+"'>");
								out.println("<tr><td width='70%' align='center'><input class=\"button\" type=\"submit\" style=' width:100%;background-color:green;padding:16px 21px;margin:8px;color: white;cursor: pointer;}' name=\"action\" value=\"Add To Cart\"></td></tr></form>");
								
							}
							i++;
							out.println("</table></div>");
							
						}
						out.println("</div><a class=\"left carousel-control\" href=\"#myCarousel\" role=\"button\" data-slide=\"prev\"><span class=\"glyphicon glyphicon-chevron-left\" aria-hidden=\"true\"></span><span class=\"sr-only\">Previous</span></a>");	
						out.println("<a class=\"right carousel-control\" href=\"#myCarousel\" role=\"button\" data-slide=\"next\"><span class=\"glyphicon glyphicon-chevron-right\" aria-hidden=\"true\"></span><span class=\"sr-only\">Next</span></a></div>");
					}
					else
					{
						out.println("<article><h1 align=\"center\"><span style='color:green;'>"+"No Product Accessory </span></h1> ");
					}						
					out.println("</article>");
				}
			}
		}
		else if(category.equals("televisions")){
			televisions=(ArrayList<Television>)products.get(category);
			for(Television television : televisions){
				if(television.getId().equals(id)){
					out.println("<table style=\"border=\"1\" width:100%\" style=\"height:100%\">");
					out.println("<tr><td width=\"35%\"><img style=\"width:420px;height:470px;\"  src=\"images\\"+television.getImage()+"\" style=\"display:block;\" /></td><td width=\"65%\"><table>");
					out.println("<tr><td colspan=2><h2 align='center' style='color:green;'> Name :"+television.getName()+"</h2></td></tr>");
					out.println("<tr><td colspan=2><b>Description: </b>"+television.getDescription()+"</td></tr>");
					out.println("<tr><td colspan=2><b>Price: $</b>"+television.getPrice()+"</td></tr>");
					out.println("<tr><td colspan=2><b>Company: </b>"+television.getCompany()+"</td></tr>");
					//out.println("<tr><td colspan=2><b>Retailer: </b>"+fwatch.getRetailer()+"</td></tr>");
					out.println("<tr><td colspan=2><b>Color: </b>"+television.getColor()+"</td></tr>");
					out.println("<tr><td colspan=2><b>Condition: </b>"+television.getCondition()+"</td></tr><tr><td>");
					if(firstname != null && !(firstname.isEmpty())){	
						out.println("<form method = 'get' action = 'AddCartPage'>");
						out.println("<input type='hidden' name='pId' value='"+television.getId()+"'>"+"<input type='hidden' name='pCategory' value='"+category+"'>");
						out.println("<tr><td  align='center' width='75%'><input style='color:white; background-color:green;padding:16px 22px;margin:7px;width:100%;cursor:pointer; }' type=\"submit\" class=\"button\"  name=\"action\" value=\"Add To Cart\"></td></form>");
					}
					out.println("<td width='25%' align='center'><a href=\"ProductsPage?category="+category+"\"><button style='color:white; background-color:green;padding:16px 22px;margin:7px;width:100%;cursor:pointer; }'>Back</button></a></td>");
					out.println("</tr></tr></td></table></td></tr></table><div class=\"clear\"></div><div class=\"clear\"></div><article align='center'>");
					if(!(television.accessories.isEmpty())){
						out.println("<h1 align=\"center\"><span style='color:green;'>"+"Product Accessories</span></h1><div id=\"myCarousel\" class=\"carousel slide\" data-ride=\"carousel\" style=\"margin-left:11%;width:81%;\"><div class=\"carousel-inner\" align='center' role=\"listbox\" >");
						int i=1;	
						for (Accessory accessory: television.accessories) {
							if(i!=1)
							{
								out.println("<div class=\"item\">");
							}
							else{
								out.println("<div class=\"item active\">");
							}
							out.println("<table style='color:black;'>");
							out.println("<tr><td align='center'><img class=\"img-responsive\" style='width:200px;height:300px;' src=\"images\\"+accessory.getImage()+"\"></td></tr>");
							out.println("<tr><td align='center'>Name : "+accessory.getName()+"</td></tr>");
							out.println("<tr><td align='center'>Condition : "+accessory.getCondition()+"</td></tr>");
							out.println("<tr><td align='center'>Price : $"+accessory.getPrice()+"</td></tr>");
							if(firstname != null && !(firstname.isEmpty()))
							{	
								out.println("<form method = 'get' action = 'AddCartPage'>");
								out.println("<input type='hidden' name='pId' value='"+television.getId()+"'>");
								out.println("<input type='hidden' name='pCategory' value='"+category+"'>");
								out.println("<input type='hidden' name='aName' value='"+accessory.getName()+"'>");
								out.println("<tr><td width='70%' align='center'><input class=\"button\" type=\"submit\" style=' width:100%;background-color:green;padding:16px 21px;margin:8px;color: white;cursor: pointer;}' name=\"action\" value=\"Add To Cart\"></td></tr></form>");
								
							}
							i++;
							out.println("</table></div>");
							
						}
						out.println("</div><a class=\"left carousel-control\" href=\"#myCarousel\" role=\"button\" data-slide=\"prev\"><span class=\"glyphicon glyphicon-chevron-left\" aria-hidden=\"true\"></span><span class=\"sr-only\">Previous</span></a>");	
						out.println("<a class=\"right carousel-control\" href=\"#myCarousel\" role=\"button\" data-slide=\"next\"><span class=\"glyphicon glyphicon-chevron-right\" aria-hidden=\"true\"></span><span class=\"sr-only\">Next</span></a></div>");
					}
					else
					{
						out.println("<article><h1 align=\"center\"><span style='color:green;'>"+"No Product Accessory </span></h1> ");
					}						
					out.println("</article>");
				}
			}
		}
		else if(category.equals("voiceassistants")){
			voiceassistants=(ArrayList<VoiceAssistant>)products.get(category);
			for(VoiceAssistant voiceassistant : voiceassistants){
				if(voiceassistant.getId().equals(id)){
					out.println("<table style=\"border=\"1\" width:100%\" style=\"height:100%\">");
					out.println("<tr><td width=\"35%\"><img style=\"width:420px;height:470px;\"  src=\"images\\"+voiceassistant.getImage()+"\" style=\"display:block;\" /></td><td width=\"65%\"><table>");
					out.println("<tr><td colspan=2><h2 align='center' style='color:green;'> Name :"+voiceassistant.getName()+"</h2></td></tr>");
					out.println("<tr><td colspan=2><b>Description: </b>"+voiceassistant.getDescription()+"</td></tr>");
					out.println("<tr><td colspan=2><b>Price: $</b>"+voiceassistant.getPrice()+"</td></tr>");
					out.println("<tr><td colspan=2><b>Company: </b>"+voiceassistant.getCompany()+"</td></tr>");
					//out.println("<tr><td colspan=2><b>Retailer: </b>"+fwatch.getRetailer()+"</td></tr>");
					out.println("<tr><td colspan=2><b>Color: </b>"+voiceassistant.getColor()+"</td></tr>");
					out.println("<tr><td colspan=2><b>Condition: </b>"+voiceassistant.getCondition()+"</td></tr><tr><td>");
					if(firstname != null && !(firstname.isEmpty())){	
						out.println("<form method = 'get' action = 'AddCartPage'>");
						out.println("<input type='hidden' name='pId' value='"+voiceassistant.getId()+"'>"+"<input type='hidden' name='pCategory' value='"+category+"'>");
						out.println("<tr><td  align='center' width='75%'><input style='color:white; background-color:green;padding:16px 22px;margin:7px;width:100%;cursor:pointer; }' type=\"submit\" class=\"button\"  name=\"action\" value=\"Add To Cart\"></td></form>");
					}
					out.println("<td width='25%' align='center'><a href=\"ProductsPage?category="+category+"\"><button style='color:white; background-color:green;padding:16px 22px;margin:7px;width:100%;cursor:pointer; }'>Back</button></a></td>");
					out.println("</tr></tr></td></table></td></tr></table><div class=\"clear\"></div><div class=\"clear\"></div><article align='center'>");
					if(!(voiceassistant.accessories.isEmpty())){
						out.println("<h1 align=\"center\"><span style='color:green;'>"+"Product Accessories</span></h1><div id=\"myCarousel\" class=\"carousel slide\" data-ride=\"carousel\" style=\"margin-left:11%;width:81%;\"><div class=\"carousel-inner\" align='center' role=\"listbox\" >");
						int i=1;	
						for (Accessory accessory: voiceassistant.accessories) {
							if(i!=1)
							{
								out.println("<div class=\"item\">");
							}
							else{
								out.println("<div class=\"item active\">");
							}
							out.println("<table style='color:black;'>");
							out.println("<tr><td align='center'><img class=\"img-responsive\" style='width:200px;height:300px;' src=\"images\\"+accessory.getImage()+"\"></td></tr>");
							out.println("<tr><td align='center'>Name : "+accessory.getName()+"</td></tr>");
							out.println("<tr><td align='center'>Condition : "+accessory.getCondition()+"</td></tr>");
							out.println("<tr><td align='center'>Price : $"+accessory.getPrice()+"</td></tr>");
							if(firstname != null && !(firstname.isEmpty()))
							{	
								out.println("<form method = 'get' action = 'AddCartPage'>");
								out.println("<input type='hidden' name='pId' value='"+voiceassistant.getId()+"'>");
								out.println("<input type='hidden' name='pCategory' value='"+category+"'>");
								out.println("<input type='hidden' name='aName' value='"+accessory.getName()+"'>");
								out.println("<tr><td width='70%' align='center'><input class=\"button\" type=\"submit\" style=' width:100%;background-color:green;padding:16px 21px;margin:8px;color: white;cursor: pointer;}' name=\"action\" value=\"Add To Cart\"></td></tr></form>");
								
							}
							i++;
							out.println("</table></div>");
							
						}
						out.println("</div><a class=\"left carousel-control\" href=\"#myCarousel\" role=\"button\" data-slide=\"prev\"><span class=\"glyphicon glyphicon-chevron-left\" aria-hidden=\"true\"></span><span class=\"sr-only\">Previous</span></a>");	
						out.println("<a class=\"right carousel-control\" href=\"#myCarousel\" role=\"button\" data-slide=\"next\"><span class=\"glyphicon glyphicon-chevron-right\" aria-hidden=\"true\"></span><span class=\"sr-only\">Next</span></a></div>");
					}
					else
					{
						out.println("<article><h1 align=\"center\"><span style='color:green;'>"+"No Product Accessory </span></h1> ");
					}						
					out.println("</article>");
				}
			}
		}
		else if(category.equals("headphones")){
			headphones=(ArrayList<HeadPhone>)products.get(category);
			for(HeadPhone headphone : headphones){
				if(headphone.getId().equals(id)){
					out.println("<table style=\"border=\"1\" width:100%\" style=\"height:100%\">");
					out.println("<tr><td width=\"35%\"><img style=\"width:420px;height:470px;\"  src=\"images\\"+headphone.getImage()+"\" style=\"display:block;\" /></td><td width=\"65%\"><table>");
					out.println("<tr><td colspan=2><h2 align='center' style='color:green;'> Name :"+headphone.getName()+"</h2></td></tr>");
					out.println("<tr><td colspan=2><b>Description: </b>"+headphone.getDescription()+"</td></tr>");
					out.println("<tr><td colspan=2><b>Price: $</b>"+headphone.getPrice()+"</td></tr>");
					out.println("<tr><td colspan=2><b>Company: </b>"+headphone.getCompany()+"</td></tr>");
					//out.println("<tr><td colspan=2><b>Retailer: </b>"+fwatch.getRetailer()+"</td></tr>");
					out.println("<tr><td colspan=2><b>Color: </b>"+headphone.getColor()+"</td></tr>");
					out.println("<tr><td colspan=2><b>Condition: </b>"+headphone.getCondition()+"</td></tr><tr><td>");
					if(firstname != null && !(firstname.isEmpty())){	
						out.println("<form method = 'get' action = 'AddCartPage'>");
						out.println("<input type='hidden' name='pId' value='"+headphone.getId()+"'>"+"<input type='hidden' name='pCategory' value='"+category+"'>");
						out.println("<tr><td  align='center' width='75%'><input style='color:white; background-color:green;padding:16px 22px;margin:7px;width:100%;cursor:pointer; }' type=\"submit\" class=\"button\"  name=\"action\" value=\"Add To Cart\"></td></form>");
					}
					out.println("<td width='25%' align='center'><a href=\"ProductsPage?category="+category+"\"><button style='color:white; background-color:green;padding:16px 22px;margin:7px;width:100%;cursor:pointer; }'>Back</button></a></td>");
					out.println("</tr></tr></td></table></td></tr></table><div class=\"clear\"></div><div class=\"clear\"></div><article align='center'>");
					if(!(headphone.accessories.isEmpty())){
						out.println("<h1 align=\"center\"><span style='color:green;'>"+"Product Accessories</span></h1><div id=\"myCarousel\" class=\"carousel slide\" data-ride=\"carousel\" style=\"margin-left:11%;width:81%;\"><div class=\"carousel-inner\" align='center' role=\"listbox\" >");
						int i=1;	
						for (Accessory accessory: headphone.accessories) {
							if(i!=1)
							{
								out.println("<div class=\"item\">");
							}
							else{
								out.println("<div class=\"item active\">");
							}
							out.println("<table style='color:black;'>");
							out.println("<tr><td align='center'><img class=\"img-responsive\" style='width:200px;height:300px;' src=\"images\\"+accessory.getImage()+"\"></td></tr>");
							out.println("<tr><td align='center'>Name : "+accessory.getName()+"</td></tr>");
							out.println("<tr><td align='center'>Condition : "+accessory.getCondition()+"</td></tr>");
							out.println("<tr><td align='center'>Price : $"+accessory.getPrice()+"</td></tr>");
							if(firstname != null && !(firstname.isEmpty()))
							{	
								out.println("<form method = 'get' action = 'AddCartPage'>");
								out.println("<input type='hidden' name='pId' value='"+headphone.getId()+"'>");
								out.println("<input type='hidden' name='pCategory' value='"+category+"'>");
								out.println("<input type='hidden' name='aName' value='"+accessory.getName()+"'>");
								out.println("<tr><td width='70%' align='center'><input class=\"button\" type=\"submit\" style=' width:100%;background-color:green;padding:16px 21px;margin:8px;color: white;cursor: pointer;}' name=\"action\" value=\"Add To Cart\"></td></tr></form>");
								
							}
							i++;
							out.println("</table></div>");
							
						}
						out.println("</div><a class=\"left carousel-control\" href=\"#myCarousel\" role=\"button\" data-slide=\"prev\"><span class=\"glyphicon glyphicon-chevron-left\" aria-hidden=\"true\"></span><span class=\"sr-only\">Previous</span></a>");	
						out.println("<a class=\"right carousel-control\" href=\"#myCarousel\" role=\"button\" data-slide=\"next\"><span class=\"glyphicon glyphicon-chevron-right\" aria-hidden=\"true\"></span><span class=\"sr-only\">Next</span></a></div>");
					}
					else
					{
						out.println("<article><h1 align=\"center\"><span style='color:green;'>"+"No Product Accessory </span></h1> ");
					}						
					out.println("</article>");
				}
			}
		}
		else if(category.equals("phones")){
			phones=(ArrayList<Phone>)products.get(category);
			for(Phone phone : phones){
				if(phone.getId().equals(id)){
					out.println("<table style=\"border=\"1\" width:100%\" style=\"height:100%\">");
					out.println("<tr><td width=\"35%\"><img style=\"width:420px;height:470px;\"  src=\"images\\"+phone.getImage()+"\" style=\"display:block;\" /></td><td width=\"65%\"><table>");
					out.println("<tr><td colspan=2><h2 align='center' style='color:green;'> Name :"+phone.getName()+"</h2></td></tr>");
					out.println("<tr><td colspan=2><b>Description: </b>"+phone.getDescription()+"</td></tr>");
					out.println("<tr><td colspan=2><b>Price: $</b>"+phone.getPrice()+"</td></tr>");
					out.println("<tr><td colspan=2><b>Company: </b>"+phone.getCompany()+"</td></tr>");
					//out.println("<tr><td colspan=2><b>Retailer: </b>"+fwatch.getRetailer()+"</td></tr>");
					out.println("<tr><td colspan=2><b>Color: </b>"+phone.getColor()+"</td></tr>");
					out.println("<tr><td colspan=2><b>Condition: </b>"+phone.getCondition()+"</td></tr><tr><td>");
					if(firstname != null && !(firstname.isEmpty())){	
						out.println("<form method = 'get' action = 'AddCartPage'>");
						out.println("<input type='hidden' name='pId' value='"+phone.getId()+"'>"+"<input type='hidden' name='pCategory' value='"+category+"'>");
						out.println("<tr><td  align='center' width='75%'><input style='color:white; background-color:green;padding:16px 22px;margin:7px;width:100%;cursor:pointer; }' type=\"submit\" class=\"button\"  name=\"action\" value=\"Add To Cart\"></td></form>");
					}
					out.println("<td width='25%' align='center'><a href=\"ProductsPage?category="+category+"\"><button style='color:white; background-color:green;padding:16px 22px;margin:7px;width:100%;cursor:pointer; }'>Back</button></a></td>");
					out.println("</tr></tr></td></table></td></tr></table><div class=\"clear\"></div><div class=\"clear\"></div><article align='center'>");
					if(!(phone.accessories.isEmpty())){
						out.println("<h1 align=\"center\"><span style='color:green;'>"+"Product Accessories</span></h1><div id=\"myCarousel\" class=\"carousel slide\" data-ride=\"carousel\" style=\"margin-left:11%;width:81%;\"><div class=\"carousel-inner\" align='center' role=\"listbox\" >");
						int i=1;	
						for (Accessory accessory: phone.accessories) {
							if(i!=1)
							{
								out.println("<div class=\"item\">");
							}
							else{
								out.println("<div class=\"item active\">");
							}
							out.println("<table style='color:black;'>");
							out.println("<tr><td align='center'><img class=\"img-responsive\" style='width:200px;height:300px;' src=\"images\\"+accessory.getImage()+"\"></td></tr>");
							out.println("<tr><td align='center'>Name : "+accessory.getName()+"</td></tr>");
							out.println("<tr><td align='center'>Condition : "+accessory.getCondition()+"</td></tr>");
							out.println("<tr><td align='center'>Price : $"+accessory.getPrice()+"</td></tr>");
							if(firstname != null && !(firstname.isEmpty()))
							{	
								out.println("<form method = 'get' action = 'AddCartPage'>");
								out.println("<input type='hidden' name='pId' value='"+phone.getId()+"'>");
								out.println("<input type='hidden' name='pCategory' value='"+category+"'>");
								out.println("<input type='hidden' name='aName' value='"+accessory.getName()+"'>");
								out.println("<tr><td width='70%' align='center'><input class=\"button\" type=\"submit\" style=' width:100%;background-color:green;padding:16px 21px;margin:8px;color: white;cursor: pointer;}' name=\"action\" value=\"Add To Cart\"></td></tr></form>");
								
							}
							i++;
							out.println("</table></div>");
							
						}
						out.println("</div><a class=\"left carousel-control\" href=\"#myCarousel\" role=\"button\" data-slide=\"prev\"><span class=\"glyphicon glyphicon-chevron-left\" aria-hidden=\"true\"></span><span class=\"sr-only\">Previous</span></a>");	
						out.println("<a class=\"right carousel-control\" href=\"#myCarousel\" role=\"button\" data-slide=\"next\"><span class=\"glyphicon glyphicon-chevron-right\" aria-hidden=\"true\"></span><span class=\"sr-only\">Next</span></a></div>");
					}
					else
					{
						out.println("<article><h1 align=\"center\"><span style='color:green;'>"+"No Product Accessory </span></h1> ");
					}						
					out.println("</article>");
				}
			}
		}
		
		
		
		
		
		out.println("</article></section></div><div class=\"clear\"></div>");
		util.parseHtml("footer.html");
		}
}