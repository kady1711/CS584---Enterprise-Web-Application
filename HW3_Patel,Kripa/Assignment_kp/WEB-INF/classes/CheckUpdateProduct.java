import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class CheckUpdateProduct extends HttpServlet{
	
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
		MySqlDataStoreUtilities dbInstance = new MySqlDataStoreUtilities();
		dbInstance.connect();
		HashMap<String,Object> products = dbInstance.getProducts();
		/*SaxParser saxparser= new SaxParser(dir+"\\webapps\\Assignment_kp\\WEB-INF\\classes\\ProductCatalog.xml");
		HashMap<String,Object> products = saxparser.getProducts();  */
		String pname=null,pimage=null,pcolor=null,pdescription=null,pcompany=null,pcondition=null;
		float pprice=0;
		int flag=0;
		
		if(category.equals("fwatches")){
			fwatches=(ArrayList<FitnessWatch>)products.get(category);
			for(FitnessWatch fwatch : fwatches){
				if(fwatch.getId().equals(id)){
					pname=fwatch.getName();
					pimage=fwatch.getImage();
					pcondition=fwatch.getCondition();
					pdescription=fwatch.getDescription();
					pcompany=fwatch.getCompany();
					pcolor=fwatch.getColor();
					pprice=fwatch.getPrice();
					//dbInstance.modifyProduct(id,pimage,pname,pprice,pcondition,pcompany,pcolor,pdescription,"bestdeal",category);
					flag=1;break;
				}
			}
		}
		else if(category.equals("watches")){
			watches=(ArrayList<SmartWatch>)products.get(category);
			for(SmartWatch watch : watches){
				if(watch.getId().equals(id)){
					pname=watch.getName();
					pimage=watch.getImage();
					pcondition=watch.getCondition();
					pdescription=watch.getDescription();
					pcompany=watch.getCompany();
					pcolor=watch.getColor();
					pprice=watch.getPrice();flag=1;break;
				}
			}
		}
		else if(category.equals("laptops")){
			laptops=(ArrayList<Laptop>)products.get(category);
			for(Laptop laptop : laptops){
				if(laptop.getId().equals(id)){
					pname=laptop.getName();
					pimage=laptop.getImage();
					pcondition=laptop.getCondition();
					pdescription=laptop.getDescription();
					pcompany=laptop.getCompany();
					pcolor=laptop.getColor();
					pprice=laptop.getPrice();flag=1;break;
				}
			}
		}
		else if(category.equals("speakers")){
			speakers=(ArrayList<SoundSystem>)products.get(category);
			for(SoundSystem speaker : speakers){
				if(speaker.getId().equals(id)){
					pname=speaker.getName();
					pimage=speaker.getImage();
					pcondition=speaker.getCondition();
					pdescription=speaker.getDescription();
					pcompany=speaker.getCompany();
					pcolor=speaker.getColor();
					pprice=speaker.getPrice();flag=1;break;
				}
			}
		}
		else if(category.equals("televisions")){
			televisions=(ArrayList<Television>)products.get(category);
			for(Television television : televisions){
				if(television.getId().equals(id)){
					pname=television.getName();
					pimage=television.getImage();
					pcondition=television.getCondition();
					pdescription=television.getDescription();
					pcompany=television.getCompany();
					pcolor=television.getColor();
					pprice=television.getPrice();
					flag=1;
					break;
				}
			}
			
		}
		else if(category.equals("voiceassistants")){
			voiceassistants=(ArrayList<VoiceAssistant>)products.get(category);
			for(VoiceAssistant voiceassistant : voiceassistants){
				if(voiceassistant.getId().equals(id)){
					pname=voiceassistant.getName();
					pimage=voiceassistant.getImage();
					pcondition=voiceassistant.getCondition();
					pdescription=voiceassistant.getDescription();
					pcompany=voiceassistant.getCompany();
					pcolor=voiceassistant.getColor();
					pprice=voiceassistant.getPrice();flag=1;break;
				}
			}
		}
		else if(category.equals("headphones")){
			headphones=(ArrayList<HeadPhone>)products.get(category);
			for(HeadPhone headphone : headphones){
				if(headphone.getId().equals(id)){
					pname=headphone.getName();
					pimage=headphone.getImage();
					pcondition=headphone.getCondition();
					pdescription=headphone.getDescription();
					pcompany=headphone.getCompany();
					pcolor=headphone.getColor();
					pprice=headphone.getPrice();
					flag=1;break;
				}
			}
		}
		else if(category.equals("phones")){
			phones=(ArrayList<Phone>)products.get(category);
			for(Phone phone : phones){
				if(phone.getId().equals(id)){
					pname=phone.getName();
					pimage=phone.getImage();
					pcondition=phone.getCondition();
					pdescription=phone.getDescription();
					pcompany=phone.getCompany();
					pcolor=phone.getColor();
					pprice=phone.getPrice();
					flag=1;break;
				}
			}
		}
		if(flag==0)
		{
			String e="No Product Found!";
			request.setAttribute("checkMsg",e);
			RequestDispatcher rd = request.getRequestDispatcher("/ProductUpdatePage");	
			rd.forward(request,response);
		}
		
		
		util.parseHtml("header1.html");
		out.println("<div id='body' style=\"background-color:white;width:100%;\">");
		out.println("<section id='content' style=\"width:100%;background-color:white;\">");
		out.println("<article> <h1 align=\"center\"><span style='color:blue;'>"+"Update Product</span></h1> ");
		out.println("<form  style='border:2px #f1f1f1 solid;' action = \"ProductUpdatePage\" ><div style='padding:12px;'>");
		out.println("<label><b>Product Name</b></label><input style='width:100%' type='text' placeholder='Enter Product Name' name='pname' required value='"+pname+"' \"></br></br>");
		out.println("<label><b>Product Description</b></label></br><textarea style='width:100%;' placeholder='Enter Product Description' name='pdescription' required >"+pdescription+"</textarea></br></br>");
		out.println("<label><b>Product Color</b></label></br><input style='width:100%;' type='text' placeholder='Enter Product Color' name='pcolor' required value='"+pcolor+"' \"></br></br>");
		out.println("<label><b>Product Price</b></label><input style='width:100%' type='text' placeholder='Enter Product Price' name='pprice' required value='"+pprice+"' \"></br></br>");
		out.println("<label><b>Enter Product Image Name</b></label></br><input style='width:100%;' type='text'  placeholder='Enter Product Image Name(with Extension)' name='pimage' value='"+pimage+"' required ></br></br>");
		out.println("<label><b>Product Condition</b></label></br><input style='width:100%;' type='text' placeholder='Enter Product Condition' name='pcondition' required value='"+pcondition+"'  \"></br></br>");
		out.println("<label><b>Product Company</b></label></br><input style='width:100%;' type='text' placeholder='Enter Product Company' name='pcompany' required value='"+pcompany+"' \"></br></br>");
		out.println("<label><b>Select Category</b></label></br><select name='pcategory'><option value='HeadPhones' selected>HeadPhone</option><option value='Phones'>Phone</option><option value='Laptops'>Laptop</option><option value='VoiceAssistant'>Voice Assistant</option><option value='SmartWatches'>Smart Watch</option><option value='FitnessWatches'>Fitness Watch</option><option value='Television'>Television</option><option value='SoundSystem'>Speaker</option></select>");
		out.println("<input type='hidden' name='updateMsg' value='Product Updated!'>");
		out.println("<input type='hidden' name='pid' value='"+id+"'>");
		out.println("<input type='hidden' name='pcategory' value='"+category+"'>");
		out.println("<button type='submit' style='color:white;background-color:green;margin:6px;padding:12px 22px;width:100%;cursor:pointer;'> Update Product </button>");
		out.println("</div></form>");
		out.println("</article></section>");
		//util.parseHtml("nav1.html");
		util.parseHtml("footer.html");
		
	}
}