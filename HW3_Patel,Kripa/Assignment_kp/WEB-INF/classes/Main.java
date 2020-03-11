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
		String dir = System.getenv("ANT_HOME");
		SaxParser saxparser= new SaxParser(dir+"\\webapps\\Assignment_kp\\WEB-INF\\classes\\ProductCatalog.xml");
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
		
		if(request.getAttribute("checkMsg") != null)
		{
			out.println("<h3 style='color:black;background-color:blue;'>"+(String)(request.getAttribute("checkMsg"))+"</h3>");
			request.setAttribute("checkMsg","");
		}
		Utilities util=new Utilities(request,out);
		util.parseHtml("header.html");
		util.parseHtml("body.html");
		util.parseHtml("nav.html");
		util.parseHtml("footer.html");
	}
}