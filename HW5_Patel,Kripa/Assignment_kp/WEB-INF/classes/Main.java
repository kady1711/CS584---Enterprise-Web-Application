import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;

public class Main extends HttpServlet
{
	MySqlDataStoreUtilities dbInstance = null;
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
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
		//String dir = System.getenv("ANT_HOME");
		SaxParser saxparser= new SaxParser("C:\\tomcat\\tomcat-7.0.34-preconfigured\\apache-tomcat-7.0.34\\webapps\\Assignment_kp\\WEB-INF\\classes\\ProductCatalog.xml");
		HashMap<String,Object> products = saxparser.getProducts();
		dbInstance = new MySqlDataStoreUtilities();
		dbInstance.connect();
		dbInstance.removeProducts();
		
		for(Map.Entry<String, Object> e : products.entrySet())
		{
			String category = e.getKey();
			if(category.equals("fwatches")){
				fwatches=(ArrayList<FitnessWatch>)products.get(category);
				for(FitnessWatch fwatch : fwatches){
					dbInstance.addNewProduct(fwatch.getId(),fwatch.getImage(),fwatch.getName(),fwatch.getPrice(),fwatch.getCondition(),fwatch.getCompany(),fwatch.getColor(),fwatch.getDescription(),fwatch.getRetailer(),"fwatches",fwatch.getAccessories(),fwatch.getQuantity(),fwatch.getRebate(),fwatch.getOnSale());
				}
			}
			else if(category.equals("watches")){
				watches=(ArrayList<SmartWatch>)products.get(category);
				for(SmartWatch watch : watches){
					dbInstance.addNewProduct(watch.getId(),watch.getImage(),watch.getName(),watch.getPrice(),watch.getCondition(),watch.getCompany(),watch.getColor(),watch.getDescription(),watch.getRetailer(),"watches",watch.getAccessories(),watch.getQuantity(),watch.getRebate(),watch.getOnSale());
				}
			}
			else if(category.equals("laptops")){
				laptops=(ArrayList<Laptop>)products.get(category);
				for(Laptop laptop : laptops){
					dbInstance.addNewProduct(laptop.getId(),laptop.getImage(),laptop.getName(),laptop.getPrice(),laptop.getCondition(),laptop.getCompany(),laptop.getColor(),laptop.getDescription(),laptop.getRetailer(),"laptops",laptop.getAccessories(),laptop.getQuantity(),laptop.getRebate(),laptop.getOnSale());
				}
			}
			else if(category.equals("speakers")){
				speakers=(ArrayList<SoundSystem>)products.get(category);
				for(SoundSystem speaker : speakers){
						dbInstance.addNewProduct(speaker.getId(),speaker.getImage(),speaker.getName(),speaker.getPrice(),speaker.getCondition(),
						speaker.getCompany(),speaker.getColor(),speaker.getDescription(),speaker.getRetailer(),"speakers",speaker.getAccessories(),speaker.getQuantity(),speaker.getRebate(),speaker.getOnSale());
					}
			}
			else if(category.equals("televisions")){
				televisions=(ArrayList<Television>)products.get(category);
				for(Television television : televisions){
					dbInstance.addNewProduct(television.getId(),television.getImage(),television.getName(),television.getPrice(),television.getCondition(),
						television.getCompany(),television.getColor(),television.getDescription(),television.getRetailer(),"televisions",television.getAccessories(),television.getQuantity(),television.getRebate(),television.getOnSale());
				}
			}
			else if(category.equals("voiceassistants")){
				voiceassistants=(ArrayList<VoiceAssistant>)products.get(category);
				for(VoiceAssistant voiceassistant : voiceassistants){
					dbInstance.addNewProduct(voiceassistant.getId(),voiceassistant.getImage(),voiceassistant.getName(),voiceassistant.getPrice(),voiceassistant.getCondition(),
					voiceassistant.getCompany(),voiceassistant.getColor(),voiceassistant.getDescription(),voiceassistant.getRetailer(),"voiceassistants",voiceassistant.getAccessories(),voiceassistant.getQuantity(),voiceassistant.getRebate(),voiceassistant.getOnSale());
				}
			}
			else if(category.equals("headphones")){
				headphones=(ArrayList<HeadPhone>)products.get(category);
				for(HeadPhone headphone : headphones){
					dbInstance.addNewProduct(headphone.getId(),headphone.getImage(),headphone.getName(),headphone.getPrice(),headphone.getCondition(),
					headphone.getCompany(),headphone.getColor(),headphone.getDescription(),headphone.getRetailer(),"headphones",headphone.getAccessories(),headphone.getQuantity(),headphone.getRebate(),headphone.getOnSale());
				}
			}
			else if(category.equals("phones")){
				phones=(ArrayList<Phone>)products.get(category);
				for(Phone phone : phones){
					dbInstance.addNewProduct(phone.getId(),phone.getImage(),phone.getName(),phone.getPrice(),phone.getCondition(),
					phone.getCompany(),phone.getColor(),phone.getDescription(),phone.getRetailer(),"phones",phone.getAccessories(),phone.getQuantity(),phone.getRebate(),phone.getOnSale());
				}
			}
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
	{
		response.setContentType("text/html");
		PrintWriter out =response.getWriter();
		DealMatches promos = new DealMatches();
		HashMap<String,Item> promoProducts = promos.getPromoProducts();
		ArrayList<String> fileContents = promos.getFileContents();
		if(request.getAttribute("checkMsg") != null)
		{
			out.println("<h3 style='color:black;background-color:blue;'>"+(String)(request.getAttribute("checkMsg"))+"</h3>");
			request.setAttribute("checkMsg","");
		}
		Utilities util=new Utilities(request,out);
		util.parseHtml("header.html");
		//util.parseHtml("body.html");
		HttpSession session= request.getSession();
		String firstname=null,usertype=null,username=null;
		if(session.getAttribute("UserDetail") != null)
		{
			firstname=((UserDetail)(session.getAttribute("UserDetail"))).getFirstName();
			username=((UserDetail)(session.getAttribute("UserDetail"))).getUserName();
			usertype=((UserDetail)(session.getAttribute("UserDetail"))).getUserType();
		} 
		out.println("<div id=\"body\"><section id=\"content\"><h2><font color=\"red\">WELCOME TO SMART PORTABLES!</font></h2><br><br>");
		out.println("<article><h2>We Beat Our Competitors in All Aspects</h2><h2>Price-Match Guaranteed!</h2><br>");	
		out.println("<table border='1'>");
		if(fileContents.isEmpty())
		{
			out.println("<h3 style=\"color:blue;\">No Offers Found!</h3><br>");
		}
		else{
			for(String fc : fileContents)
			{
				out.println("<h3 style=\"color:blue;\">"+fc+"</h3>");
			}
		}
		out.println("</table></article>");
		
		out.println("<article><table border='1'>");
		
		if(promoProducts.isEmpty())
		{
			out.println("<h3 style=\"color:blue;\">No Products Found!</h3><br>");
		}
		else{
			out.println("<h2 style=\"color:blue;\">Best Promos From Twitter</h2>");
			Iterator it = promoProducts.entrySet().iterator();
			out.println("<table>");
			while (it.hasNext()) {
					Map.Entry pair = (Map.Entry)it.next();
					Item item = (Item)pair.getValue();
					
					out.println("<tr>");
					out.println("<td width=\"30%\">");
					out.println("<img style='display:block;margin:12px;width:110px;height:210px;' src=\"images\\"+item.image+"\" >");
					out.println("</td>");
					out.println("<td width=\"40%\"><table>");
					out.println(" <tr> <td><b>Name: </b>"+item.name+"</td></tr>");
					out.println(" <tr><td><b> Price:</b> $"+item.price+"</td></tr>");	
					out.println("</table></td><td><table>");
					out.println("<form action = 'AddCartPage'>");
					out.println("<input type='hidden' name='pId' value='"+item.getId()+"'>");
					out.println("<input type='hidden' name='pCategory' value='"+item.getCategory()+"'>");
					out.println("<input type='hidden' name='pName' value='"+item.name+"'>");
					if(firstname!=null && !(firstname.isEmpty()))
					{	
						out.println("<tr><td width=\"28%\"><input name=\"action\" type=\"submit\" style='background-color:blue;color:white;padding:14px 20px;margin: 8px 0;width:100%;' class=\"button\"  value=\"Write New Reviews\"></td>");
						out.println("<td width=\"28%\"><input name=\"action\" class=\"button\" type=\"submit\" style='background-color:blue;color: white;padding:14px 20px;margin:8px;width:100%;' value=\"View Product Reviews\"></td></tr>");
						out.println("<tr><td  colspan='2' width=\"28%\"><input class=\"button\" name=\"action\" type=\"submit\" style='background-color:#4CAF50;color: white;padding:14px 20px;margin:8px;width:100%;'  value=\"Add To Cart\"></td></tr>");
					}
					out.println("<tr><td  colspan='2' width=\"30%\"><input class=\"button\" type=\"submit\" name=\"action\" style='cursor: pointer;color:white;background-color:red;padding:16px 22px;margin:8px;width:100%;' value=\"View Product\"></td></tr>");
					out.println("</form>");out.println("</table></td></tr>");
			}
			out.println("</table>");	
		}
		
		out.println("</table></article></section>");
		
		util.parseHtml("nav.html");
		util.parseHtml("footer.html");
	}
}