import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class ProductsPage extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("text/html");
		String category=request.getParameter("category");
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
		
		SaxParser saxparser= new SaxParser("C:\\apache-tomcat-7.0.34\\webapps\\Assignment_1_kp\\WEB-INF\\classes\\ProductCatalog.xml");
		HashMap<String,Object> products = saxparser.getProducts();
		
		out.println("<div id='body'>");
		out.println("<section id='content'>");
		
		if(category.equals("fwatches"))
		{
			fwatches=(ArrayList<FitnessWatch>)products.get(category);
		}
		else if(category.equals("watches"))
		{
			watches=(ArrayList<SmartWatch>)products.get(category);
		}
		else if(category.equals("speakers"))
		{
			speakers=(ArrayList<SoundSystem>)products.get(category);
		}
		else if(category.equals("laptops"))
		{
			laptops=(ArrayList<Laptop>)products.get(category);
		}
		else if(category.equals("voiceassistants"))
		{
			voiceassistants=(ArrayList<VoiceAssistant>)products.get(category);
		}
		
		else if(category.equals("headphones"))
		{
			headphones=(ArrayList<HeadPhone>)products.get(category);
		}
		
		else if(category.equals("televisions"))
		{
			televisions=(ArrayList<Television>)products.get(category);
		}
		
		else if(category.equals("phones"))
		{
			phones=(ArrayList<Phone>)products.get(category);
		}	
		
		if(products.size()!=0)
		{	
			if(category.equals("speakers"))
			{
				out.println("<article> <h2><span style='color:green;'>"+"Sound Systems </span></h2> ");
				out.println("<table>");
				for (SoundSystem speaker: speakers) {
					out.println("<tr>");
					out.println("<td width=\"30%\">");
					out.println("<img style='display:block;margin:12px;width:210px;height:210px;' src=\"images\\"+speaker.image+"\" >");
					out.println("</td>");
					out.println("<td width=\"40%\"><table>");
					out.println(" <tr> <td><b>Name: </b>"+speaker.name+"</td></tr>");
					out.println(" <tr><td><b> Price:</b> $"+speaker.price+"</td></tr>");	
					out.println("</table></td><td><table>");
					out.println("<form action = 'AddCartPage'>");
					out.println("<input type='hidden' name='pId' value='"+speaker.getId()+"'>");
					out.println("<input type='hidden' name='pCategory' value='"+category+"'>");
					
					if(firstname!=null && !(firstname.isEmpty()))
					{	
						out.println("<tr><td width=\"28%\"><input class=\"button\" name=\"action\" type=\"submit\" style=' background-color: #4CAF50;color: white;padding: 14px 20px;margin: 8px 0;border:none;cursor:pointer;width:100%;'  value=\"Add To Cart\"></td></tr>");
						
					}
					out.println("<tr><td width=\"30%\"><input class=\"button\" type=\"submit\" name=\"action\" style='cursor: pointer;color:white;background-color:red;border:none;padding: 16px 22px;margin: 8px;width: 100%;' value=\"View Product\"></td></tr>");
					out.println("</form>");out.println("</table></td></tr>");
				}
			}
			
			if(category.equals("watches"))
			{
				out.println("<article> <h2><span style='color:green;'>"+"Watches </span></h2> ");
				out.println("<table>");
				for (SmartWatch watch: watches) {
					out.println("<tr>");
					out.println("<td width=\"30%\">");
					out.println("<img style='display:block;margin:12px;width:210px;height:210px;' src=\"images\\"+watch.image+"\" >");
					out.println("</td>");
					out.println("<td width=\"40%\"><table>");
					out.println(" <tr> <td><b>Name: </b>"+watch.name+"</td></tr>");
					out.println(" <tr><td><b> Price:</b> $"+watch.price+"</td></tr>");	
					out.println("</table></td><td><table>");
					out.println("<form action ='AddCartPage'>");
					out.println("<input type='hidden' name='pId' value='"+watch.getId()+"'>");
					out.println("<input type='hidden' name='pCategory' value='"+category+"'>");
					
					if(firstname!=null && !(firstname.isEmpty()))
					{	
						out.println("<tr><td width=\"28%\"><input class=\"button\" name=\"action\" type=\"submit\" style='background-color: #4CAF50;color: white;padding: 14px 20px;margin: 8px 0;border: none;cursor: pointer;width:100%;'  value=\"Add To Cart\"></td></tr>");
						
					}
					out.println("<tr><td width=\"30%\"><input class=\"button\" type=\"submit\" name=\"action\" style='cursor: pointer; color: white;background-color: red;border: none;padding: 16px 22px;margin: 8px;width:100%;' value=\"View Product\"></td></tr>");
					out.println("</form>");
					out.println("</table></td></tr>");
				}
			}
			
			
			if(category.equals("fwatches"))
			{
				out.println("<article> <h2><span style='color:green;'>"+"Fitness Watches </span></h2> ");
				out.println("<table>");
				for (FitnessWatch fwatch: fwatches) {
					out.println("<tr>");
					out.println("<td width=\"30%\">");
					out.println("<img style='display:block;margin:12px;width:210px;height:210px;' src=\"images\\"+fwatch.image+"\" >");
					out.println("</td>");
					out.println("<td width=\"40%\"><table>");
					out.println(" <tr> <td><b>Name: </b>"+fwatch.name+"</td></tr>");
					out.println(" <tr><td><b> Price:</b> $"+fwatch.price+"</td></tr>");	
					out.println("</table></td><td><table>");
					out.println("<form action = 'AddCartPage'>");
					out.println("<input type='hidden' name='pId' value='"+fwatch.getId()+"'>");
					out.println("<input type='hidden' name='pCategory' value='"+category+"'>");
					
					if(firstname!=null && !(firstname.isEmpty()))
					{	
						out.println("<tr><td width=\"28%\"><input class=\"button\" name=\"action\" type=\"submit\" style=' background-color: #4CAF50;color: white;padding: 14px 20px;margin: 8px 0;border: none;cursor: pointer;width: 100%;}'  value=\"Add To Cart\"></td></tr>");
						
					}
					
					out.println("<tr><td width=\"30%\"><input class=\"button\" type=\"submit\" name=\"action\" style='cursor: pointer; color: white;background-color: red;border: none;padding: 16px 22px;margin: 8px;width: 100%;}' value=\"View Product\"></td></tr>");
					out.println("</form>");
					out.println("</table></td></tr>");
				}
			}
			
			if(category.equals("phones"))
			{
				out.println("<article> <h2><span style='color:green;'>"+"Phones </span></h2> ");
				out.println("<table>");
				for (Phone phone: phones) {
					out.println("<tr>");
					out.println("<td width=\"30%\">");
					out.println("<img style='display:block;margin:12px;width:210px;height:210px;' src=\"images\\"+phone.image+"\" >");
					out.println("</td>");
					out.println("<td width=\"40%\"><table>");
					out.println(" <tr> <td><b>Name: </b>"+phone.name+"</td></tr>");
					out.println(" <tr><td><b> Price:</b> $"+phone.price+"</td></tr>");	
					out.println("</table></td><td><table>");
					out.println("<form action = 'AddCartPage'>");
					out.println("<input type='hidden' name='pId' value='"+phone.getId()+"'>");
					out.println("<input type='hidden' name='pCategory' value='"+category+"'>");
					
					if(firstname!=null && !(firstname.isEmpty()))
					{	
						out.println("<tr><td width=\"28%\"><input class=\"button\" name=\"action\" type=\"submit\" style=' background-color: #4CAF50;color: white;padding: 14px 20px;margin: 8px 0;border: none;cursor: pointer;width: 100%;}'  value=\"Add To Cart\"></td></tr>");
						
					}
					out.println("<tr><td width=\"30%\"><input class=\"button\" type=\"submit\" name=\"action\" style=' color: white;background-color: red;border: none;padding: 16px 22px;margin: 8px;width: 100%;cursor: pointer;}' value=\"View Product\"></td></tr>");
					out.println("</form>");out.println("</table></td></tr>");
				}
			}
			
			if(category.equals("headphones"))
			{
				out.println("<article> <h2><span style='color:green;'>"+"HeadPhones </span></h2> ");
				out.println("<table>");
				for (HeadPhone headphone: headphones) {
					out.println("<tr>");
					out.println("<td width=\"30%\">");
					out.println("<img style='display:block;margin:12px;width:210px;height:210px;' src=\"images\\"+headphone.image+"\" >");
					out.println("</td>");
					out.println("<td width=\"40%\"><table>");
					out.println(" <tr> <td><b>Name: </b>"+headphone.name+"</td></tr>");
					out.println(" <tr><td><b> Price:</b> $"+headphone.price+"</td></tr>");	
					out.println("</table></td><td><table>");
					out.println("<form action = 'AddCartPage'>");
					out.println("<input type='hidden' name='pId' value='"+headphone.getId()+"'>");
					out.println("<input type='hidden' name='pCategory' value='"+category+"'>");
					
					if(firstname!=null && !(firstname.isEmpty()))
					{	
						out.println("<tr><td width=\"28%\"><input class=\"button\" name=\"action\" type=\"submit\" style=' background-color: #4CAF50;color: white;padding: 14px 20px;margin: 8px 0;border: none;cursor: pointer;width: 100%;}'  value=\"Add To Cart\"></td></tr>");
					
					}
					out.println("<tr><td width=\"30%\"><input class=\"button\" type=\"submit\" name=\"action\" style=' color: white;background-color: red;border: none;padding: 16px 22px;margin: 8px;width: 100%;}' value=\"View Product\"></td></tr>");
						out.println("</form>");out.println("</table></td></tr>");
				}
			}
			
			if(category.equals("televisions"))
			{
				out.println("<article> <h2><span style='color:green;'>"+"Televisions </span></h2> ");
				out.println("<table>");
				for (Television television: televisions) {
					out.println("<tr>");
					out.println("<td width=\"30%\">");
					out.println("<img style='display:block;margin:12px;width:210px;height:210px;' src=\"images\\"+television.image+"\" >");
					out.println("</td>");
					out.println("<td width=\"40%\"><table>");
					out.println(" <tr> <td><b>Name: </b>"+television.name+"</td></tr>");
					out.println(" <tr><td><b> Price:</b> $"+television.price+"</td></tr>");	
					out.println("</table></td><td><table>");
					out.println("<form action = 'AddCartPage'>");
					out.println("<input type='hidden' name='pId' value='"+television.getId()+"'>");
					out.println("<input type='hidden' name='pCategory' value='"+category+"'>");
					
					
					if(firstname!=null && !(firstname.isEmpty()))
					{	
						out.println("<tr><td width=\"28%\"><input class=\"button\" name=\"action\" type=\"submit\" style=' background-color: #4CAF50;color: white;padding: 14px 20px;margin: 8px 0;border: none;cursor: pointer;width: 100%;}'  value=\"Add To Cart\"></td></tr>");
					
					}
					out.println("<tr><td width=\"30%\"><input class=\"button\" type=\"submit\" name=\"action\" style=' color: white;background-color: red;border: none;padding: 16px 22px;margin: 8px;width: 100%;}' value=\"View Product\"></td></tr>");
						out.println("</form>");out.println("</table></td></tr>");
				}
			}
			
			if(category.equals("voiceassistants"))
			{
				out.println("<article> <h2><span style='color:green;'>"+"Voice Assistants</span></h2> ");
				out.println("<table>");
				for (VoiceAssistant voiceassistant: voiceassistants) {
					out.println("<tr>");
					out.println("<td width=\"30%\">");
					out.println("<img style='display:block;margin:12px;width:210px;height:210px;' src=\"images\\"+voiceassistant.image+"\" >");
					out.println("</td>");
					out.println("<td width=\"40%\"><table>");
					out.println(" <tr> <td><b>Name: </b>"+voiceassistant.name+"</td></tr>");
					out.println(" <tr><td><b> Price:</b> $"+voiceassistant.price+"</td></tr>");	
					out.println("</table></td><td><table>");
					out.println("<form action = 'AddCartPage'>");
					out.println("<input type='hidden' name='pId' value='"+voiceassistant.getId()+"'>");
					out.println("<input type='hidden' name='pCategory' value='"+category+"'>");
					
					if(firstname!=null && !(firstname.isEmpty()))
					{	
						out.println("<tr><td width=\"28%\"><input class=\"button\" name=\"action\" type=\"submit\" style=' background-color: #4CAF50;color: white;padding: 14px 20px;margin: 8px 0;border: none;cursor: pointer;width: 100%;}'  value=\"Add To Cart\"></td></tr>");
						
					}
					out.println("<tr><td width=\"30%\"><input class=\"button\" type=\"submit\" name=\"action\" style=' color: white;background-color: red;border: none;padding: 16px 22px;margin: 8px;width: 100%;}' value=\"View Product\"></td></tr>");
					out.println("</form>");out.println("</table></td></tr>");
				}
			}
				
			if(category.equals("laptops"))
			{
				out.println("<article> <h2><span style='color:green;'>"+"Laptops</span></h2> ");
				out.println("<table>");
				for (Laptop laptop: laptops) {
					out.println("<tr>");
					out.println("<td width=\"30%\">");
					out.println("<img style='display:block;margin:12px;width:210px;height:210px;' src=\"images\\"+laptop.image+"\" >");
					out.println("</td>");
					out.println("<td width=\"40%\"><table>");
					out.println(" <tr> <td><b>Name: </b>"+laptop.name+"</td></tr>");
					out.println(" <tr><td><b> Price:</b> $"+laptop.price+"</td></tr>");	
					out.println("</table></td><td><table>");
					out.println("<form action = 'AddCartPage'>");
					out.println("<input type='hidden' name='pId' value='"+laptop.getId()+"'>");
					out.println("<input type='hidden' name='pCategory' value='"+category+"'>");
					
					if(firstname!=null && !(firstname.isEmpty()))
					{	
						out.println("<tr><td width=\"28%\"><input class=\"button\" name=\"action\" type=\"submit\" style=' background-color: #4CAF50;color: white;padding: 14px 20px;margin: 8px 0;border: none;cursor: pointer;width: 100%;}'  value=\"Add To Cart\"></td></tr>");
						
					}
					out.println("<tr><td width=\"30%\"><input class=\"button\" type=\"submit\" name=\"action\" style=' color: white;background-color: red;border: none;padding: 16px 22px;margin: 8px;width: 100%;}' value=\"View Product\"></td></tr>");
					out.println("</form>");out.println("</table></td></tr>");
				}
			}	
			
		}
		else
		{
			out.println("Sorry!! No Products!");
		}
		
		out.println("</table>");
		out.println("</article>");
		out.println("</section>");
		if(usertype!= null && usertype.equals("customer"))
		{
			util.parseHtml("nav.html");
		}
		else if(usertype!= null && usertype.equals("salesmanager") ){
			util.parseHtml("nav1.html");
		}
		else{
				util.parseHtml("nav.html");
		}
		util.parseHtml("footer.html");
		
		
	}
}