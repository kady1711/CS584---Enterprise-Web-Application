import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class CheckDeleteProduct extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("text/html");
		String category=request.getParameter("pCategory");
		String id=request.getParameter("pId");
		//PrintWriter out =response.getWriter();
		HttpSession session= request.getSession();
		
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
		
		SaxParser saxparser= new SaxParser("C:\\apache-tomcat-7.0.34\\webapps\\Assignment_1_kp\\WEB-INF\\classes\\ProductCatalog.xml");
		HashMap<String,Object> products = saxparser.getProducts();
		
		int flag=0;
		
		if(category.equals("fwatches")){
			fwatches=(ArrayList<FitnessWatch>)products.get(category);
			for(FitnessWatch fwatch : fwatches){
				if(fwatch.getId().equals(id)){
					flag=1;
					break;
				}
			}
		}
		else if(category.equals("watches")){
			watches=(ArrayList<SmartWatch>)products.get(category);
			for(SmartWatch watch : watches){
				if(watch.getId().equals(id)){
					flag=1;break;
				}
			}
		}
		else if(category.equals("laptops")){
			laptops=(ArrayList<Laptop>)products.get(category);
			for(Laptop laptop : laptops){
				if(laptop.getId().equals(id)){
					flag=1;break;
				}
			}
		}
		else if(category.equals("speakers")){
			speakers=(ArrayList<SoundSystem>)products.get(category);
			for(SoundSystem speaker : speakers){
				if(speaker.getId().equals(id)){
					flag=1;break;
				}
			}
		}
		else if(category.equals("televisions")){
			televisions=(ArrayList<Television>)products.get(category);
			for(Television television : televisions){
				if(television.getId().equals(id)){
					flag=1;break;
				}
			}
		}
		else if(category.equals("voiceassistants")){
			voiceassistants=(ArrayList<VoiceAssistant>)products.get(category);
			for(VoiceAssistant voiceassistant : voiceassistants){
				if(voiceassistant.getId().equals(id)){
					flag=1;break;
				}
			}
		}
		else if(category.equals("headphones")){
			headphones=(ArrayList<HeadPhone>)products.get(category);
			for(HeadPhone headphone : headphones){
				if(headphone.getId().equals(id)){
					flag=1;break;
				}
			}
		}
		else if(category.equals("phones")){
			phones=(ArrayList<Phone>)products.get(category);
			for(Phone phone : phones){
				if(phone.getId().equals(id)){
					flag=1;break;
				}
			}
		}
		if(flag==0)
		{
			String e="No Product Found!";
			request.setAttribute("checkMsg",e);
			RequestDispatcher rd = request.getRequestDispatcher("/ProductDeletePage");	
			rd.forward(request,response);
		}
		else{
			String e="Product Deleted!";
			request.setAttribute("checkMsg",e);
			RequestDispatcher rd = request.getRequestDispatcher("/ProductDeletePage");	
			rd.forward(request,response);
		}
		
	}
}