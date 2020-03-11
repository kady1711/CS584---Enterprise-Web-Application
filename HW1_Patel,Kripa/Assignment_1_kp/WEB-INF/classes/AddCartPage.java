import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class AddCartPage extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("text/html");
		String pageAction=request.getParameter("action");
		if(pageAction!= null && pageAction.equals("View Product"))
		{
			RequestDispatcher dis = request.getRequestDispatcher("ProductDetailsPage");
			dis.forward(request,response);
		}
		else
		{
			String pId=request.getParameter("pId");
			String category=request.getParameter("pCategory");
			String aName=request.getParameter("aName");
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
			
			SaxParser sax= new SaxParser("C:\\apache-tomcat-7.0.34\\webapps\\Assignment_1_kp\\WEB-INF\\classes\\ProductCatalog.xml");
			HashMap<String,Object> products = sax.getProducts();
			
			if(category.equals("watches"))
			{
				watches=(ArrayList<SmartWatch>)products.get(category);
				for(SmartWatch watch : watches)
				{
					if(watch.getId().equals(pId))
					{
						if(aName != null )
						{
							CartDetail cartdetail=(CartDetail)session.getAttribute("CartDetail");	
							if(cartdetail == null)
							{
								cartdetail=new CartDetail();
								session.setAttribute("CartDetail",cartdetail);
							}
							for(Accessory accessory : watch.accessories)
							{
								if(accessory.getName().equals(aName))
								{	
									cartdetail.addItem(watch.getId(),accessory.getName(),accessory.getPrice(),accessory.getImage(),1);
									RequestDispatcher dis = request.getRequestDispatcher("CartPage");
									dis.forward(request,response);
								}
							}
						}
						else{
							CartDetail cartdetail=(CartDetail)session.getAttribute("CartDetail");
						
							if(cartdetail == null){
								cartdetail=new CartDetail();
								session.setAttribute("CartDetail",cartdetail);
							}
							cartdetail.addItem(watch.getId(),watch.getName(),watch.getPrice(),watch.getImage(),1);
							RequestDispatcher dis = request.getRequestDispatcher("CartPage");
							dis.forward(request,response);
						}
					}
				}
			}
			else if(category.equals("fwatches"))
			{
				fwatches=(ArrayList<FitnessWatch>)products.get(category);
				for(FitnessWatch fwatch : fwatches)
				{
					if(fwatch.getId().equals(pId))
					{
						if(aName != null )
						{
							CartDetail cartdetail=(CartDetail)session.getAttribute("CartDetail");	
							if(cartdetail == null)
							{
								cartdetail=new CartDetail();
								session.setAttribute("CartDetail",cartdetail);
							}
							for(Accessory accessory : fwatch.accessories)
							{
								if(accessory.getName().equals(aName))
								{	
									cartdetail.addItem(fwatch.getId(),accessory.getName(),accessory.getPrice(),accessory.getImage(),1);
									RequestDispatcher dis = request.getRequestDispatcher("CartPage");
									dis.forward(request,response);
								}
							}
						}
						else{
							CartDetail cartdetail=(CartDetail)session.getAttribute("CartDetail");
						
							if(cartdetail == null){
								cartdetail=new CartDetail();
								session.setAttribute("CartDetail",cartdetail);
							}
							cartdetail.addItem(fwatch.getId(),fwatch.getName(),fwatch.getPrice(),fwatch.getImage(),1);
							RequestDispatcher dis = request.getRequestDispatcher("CartPage");
							dis.forward(request,response);
						}
					}
				}
			}
			else if(category.equals("speakers"))
			{
				speakers=(ArrayList<SoundSystem>)products.get(category);
				for(SoundSystem speaker : speakers)
				{
					if(speaker.getId().equals(pId))
					{
						if(aName != null )
						{
							CartDetail cartdetail=(CartDetail)session.getAttribute("CartDetail");	
							if(cartdetail == null)
							{
								cartdetail=new CartDetail();
								session.setAttribute("CartDetail",cartdetail);
							}
							for(Accessory accessory : speaker.accessories)
							{
								if(accessory.getName().equals(aName))
								{	
									cartdetail.addItem(speaker.getId(),accessory.getName(),accessory.getPrice(),accessory.getImage(),1);
									RequestDispatcher dis = request.getRequestDispatcher("CartPage");
									dis.forward(request,response);
								}
							}
						}
						else{
							CartDetail cartdetail=(CartDetail)session.getAttribute("CartDetail");
						
							if(cartdetail == null){
								cartdetail=new CartDetail();
								session.setAttribute("CartDetail",cartdetail);
							}
							cartdetail.addItem(speaker.getId(),speaker.getName(),speaker.getPrice(),speaker.getImage(),1);
							RequestDispatcher dis = request.getRequestDispatcher("CartPage");
							dis.forward(request,response);
						}
					}
				}
			}
			else if(category.equals("televisions"))
			{
				televisions=(ArrayList<Television>)products.get(category);
				for(Television television : televisions)
				{
					if(television.getId().equals(pId))
					{
						if(aName != null )
						{
							CartDetail cartdetail=(CartDetail)session.getAttribute("CartDetail");	
							if(cartdetail == null)
							{
								cartdetail=new CartDetail();
								session.setAttribute("CartDetail",cartdetail);
							}
							for(Accessory accessory : television.accessories)
							{
								if(accessory.getName().equals(aName))
								{	
									cartdetail.addItem(television.getId(),accessory.getName(),accessory.getPrice(),accessory.getImage(),1);
									RequestDispatcher dis = request.getRequestDispatcher("CartPage");
									dis.forward(request,response);
								}
							}
						}
						else{
							CartDetail cartdetail=(CartDetail)session.getAttribute("CartDetail");
						
							if(cartdetail == null){
								cartdetail=new CartDetail();
								session.setAttribute("CartDetail",cartdetail);
							}
							cartdetail.addItem(television.getId(),television.getName(),television.getPrice(),television.getImage(),1);
							RequestDispatcher dis = request.getRequestDispatcher("CartPage");
							dis.forward(request,response);
						}
					}
				}
			}
			else if(category.equals("phones"))
			{
				phones=(ArrayList<Phone>)products.get(category);
				for(Phone phone : phones)
				{
					if(phone.getId().equals(pId))
					{
						if(aName != null )
						{
							CartDetail cartdetail=(CartDetail)session.getAttribute("CartDetail");	
							if(cartdetail == null)
							{
								cartdetail=new CartDetail();
								session.setAttribute("CartDetail",cartdetail);
							}
							for(Accessory accessory : phone.accessories)
							{
								if(accessory.getName().equals(aName))
								{	
									cartdetail.addItem(phone.getId(),accessory.getName(),accessory.getPrice(),accessory.getImage(),1);
									RequestDispatcher dis = request.getRequestDispatcher("CartPage");
									dis.forward(request,response);
								}
							}
						}
						else{
							CartDetail cartdetail=(CartDetail)session.getAttribute("CartDetail");
						
							if(cartdetail == null){
								cartdetail=new CartDetail();
								session.setAttribute("CartDetail",cartdetail);
							}
							cartdetail.addItem(phone.getId(),phone.getName(),phone.getPrice(),phone.getImage(),1);
							RequestDispatcher dis = request.getRequestDispatcher("CartPage");
							dis.forward(request,response);
						}
					}
				}
			}
			
			else if(category.equals("headphones"))
			{
				headphones=(ArrayList<HeadPhone>)products.get(category);
				for(HeadPhone headphone : headphones)
				{
					if(headphone.getId().equals(pId))
					{
						if(aName != null )
						{
							CartDetail cartdetail=(CartDetail)session.getAttribute("CartDetail");	
							if(cartdetail == null)
							{
								cartdetail=new CartDetail();
								session.setAttribute("CartDetail",cartdetail);
							}
							for(Accessory accessory : headphone.accessories)
							{
								if(accessory.getName().equals(aName))
								{	
									cartdetail.addItem(headphone.getId(),accessory.getName(),accessory.getPrice(),accessory.getImage(),1);
									RequestDispatcher dis = request.getRequestDispatcher("CartPage");
									dis.forward(request,response);
								}
							}
						}
						else{
							CartDetail cartdetail=(CartDetail)session.getAttribute("CartDetail");
						
							if(cartdetail == null){
								cartdetail=new CartDetail();
								session.setAttribute("CartDetail",cartdetail);
							}
							cartdetail.addItem(headphone.getId(),headphone.getName(),headphone.getPrice(),headphone.getImage(),1);
							RequestDispatcher dis = request.getRequestDispatcher("CartPage");
							dis.forward(request,response);
						}
					}
				}
			}
			
			else if(category.equals("voiceassistants"))
			{
				voiceassistants=(ArrayList<VoiceAssistant>)products.get(category);
				for(VoiceAssistant voiceassistant : voiceassistants)
				{
					if(voiceassistant.getId().equals(pId))
					{
						if(aName != null )
						{
							CartDetail cartdetail=(CartDetail)session.getAttribute("CartDetail");	
							if(cartdetail == null)
							{
								cartdetail=new CartDetail();
								session.setAttribute("CartDetail",cartdetail);
							}
							for(Accessory accessory : voiceassistant.accessories)
							{
								if(accessory.getName().equals(aName))
								{	
									cartdetail.addItem(voiceassistant.getId(),accessory.getName(),accessory.getPrice(),accessory.getImage(),1);
									RequestDispatcher dis = request.getRequestDispatcher("CartPage");
									dis.forward(request,response);
								}
							}
						}
						else{
							CartDetail cartdetail=(CartDetail)session.getAttribute("CartDetail");
						
							if(cartdetail == null){
								cartdetail=new CartDetail();
								session.setAttribute("CartDetail",cartdetail);
							}
							cartdetail.addItem(voiceassistant.getId(),voiceassistant.getName(),voiceassistant.getPrice(),voiceassistant.getImage(),1);
							RequestDispatcher dis = request.getRequestDispatcher("CartPage");
							dis.forward(request,response);
						}
					}
				}
			}
			
			else if(category.equals("laptops"))
			{
				laptops=(ArrayList<Laptop>)products.get(category);
				for(Laptop laptop : laptops)
				{
					if(laptop.getId().equals(pId))
					{
						if(aName != null )
						{
							CartDetail cartdetail=(CartDetail)session.getAttribute("CartDetail");	
							if(cartdetail == null)
							{
								cartdetail=new CartDetail();
								session.setAttribute("CartDetail",cartdetail);
							}
							for(Accessory accessory : laptop.accessories)
							{
								if(accessory.getName().equals(aName))
								{	
									cartdetail.addItem(laptop.getId(),accessory.getName(),accessory.getPrice(),accessory.getImage(),1);
									RequestDispatcher dis = request.getRequestDispatcher("CartPage");
									dis.forward(request,response);
								}
							}
						}
						else{
							CartDetail cartdetail=(CartDetail)session.getAttribute("CartDetail");
						
							if(cartdetail == null){
								cartdetail=new CartDetail();
								session.setAttribute("CartDetail",cartdetail);
							}
							cartdetail.addItem(laptop.getId(),laptop.getName(),laptop.getPrice(),laptop.getImage(),1);
							RequestDispatcher dis = request.getRequestDispatcher("CartPage");
							dis.forward(request,response);
						}
					}
				}
			}
			
		}
	}
}