import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class CartPage extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("text/html");
		PrintWriter out =response.getWriter();
		HttpSession session= request.getSession();
		int j=1;
		String firstname=null,usertype=null,username=null;
		if(session.getAttribute("UserDetail") != null)
		{
			firstname=((UserDetail)(session.getAttribute("UserDetail"))).getFirstName();
			username=((UserDetail)(session.getAttribute("UserDetail"))).getUserName();
			usertype=((UserDetail)(session.getAttribute("UserDetail"))).getUserType();
			
		} 
		
		Utilities util=new Utilities(request,out);
		util.parseHtml("header.html");
		if(firstname!=null && !(firstname.isEmpty()))
		{
			CartDetail cartdetail=(CartDetail)session.getAttribute("CartDetail");
			if(cartdetail == null)
			{
				out.println("<div id='body'><section id='content'><article><table><h2><span style='green;'>Empty Cart</span></h2>");
				out.println("</table></article></section>");
			}
			else
			{
				HashMap<String, List<Object>> objs = cartdetail.getAllItems();
				if(objs.size() == 0)
				{
					out.println("<div id='body'>");
					out.println("<section id='content'>");
					out.println("<article>");
					out.println("<table>");
					out.println("<h2><span style='red;'>No Items in Cart</span></h2>");
					out.println("</table>");
					out.println("</article>");
					out.println("</section>");
				}
				else{
					out.println("<div id='body'><section id='content'><article><table>");
					out.println("<tr><td>Index</td><td>Product Image</td><td>Product Name</td><td>Product Price</td><td>Quantity</td><td></td></tr>");
					for(Map.Entry<String, List<Object>> e : objs.entrySet()){
						String obj = e.getKey();
						List<Object> objects = e.getValue();
						out.println("<form method='get' action='FinalCheckoutPage'><input type='hidden' name='pname' value='"+obj+"'>");
						out.println("<tr><td>"+j+"</td><td><img src ='images//"+objects.get(3)+"' width = '100' height = '200'></td><td>"+objects.get(0)+" </td><td>"+"$"+objects.get(1)+"</td>");
						out.println("<td><select name='pqty'><option value='1' selected>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option></select></td>");
						out.println("<td><a href='DeleteCartPage?name="+obj+"'><button type='button' style='padding:15px 21px;cursor:pointer;background-color:red;color:white;margin:7px;width:100%;}'>Remove Item</button></a></td></tr>");
						j++;
					}
					out.println("<td colspan='6' align='center'><input type='submit' name='action' value='Checkout Time' style='padding:14px 20px;cursor:pointer;background-color:green;color:white;margin:8px;width:100%;}'></td></tr></form></table></article>");
					
					
					ProductRecommenderUtility prodRecUtil = new ProductRecommenderUtility();
				prodRecUtil.connect();
				HashMap<String,String> itemRecMap = new HashMap<String,String>();
				itemRecMap = prodRecUtil.readCSVFile();
				for(Map.Entry<String, String> m : itemRecMap.entrySet()){
					String fileUserName = m.getKey();
					if(username.equals(fileUserName))
					{
						String itemsRecList = m.getValue();
						itemsRecList = itemsRecList.replace("[","");
						itemsRecList = itemsRecList.replace("]","");
						itemsRecList = itemsRecList.replace("\""," ");
						ArrayList<String> itemsList = new ArrayList<String>(Arrays.asList(itemsRecList.split(",")));
						out.println("<h1 align=\"center\"><span style='color:green;'>"+"Recommended Products</span></h1><div id=\"myCarousel\" class=\"carousel slide\" data-ride=\"carousel\" style=\"margin-left:11%;width:81%;\"><div class=\"carousel-inner\" align='center' role=\"listbox\" >");
						int i=1;
						for(String product : itemsList)
						{
							product = product.replace("'","");
							Item item = prodRecUtil.getProductByName(product.trim().replaceAll("\'",""));
							System.out.println(product.trim());
							System.out.println(product.trim().length());
							if(i!=1)
							{
								out.println("<div class=\"item\">");
							}
							else{
								out.println("<div class=\"item active\">");
							}
							System.out.println(product);
							out.println("<table style='color:black;'>");
							out.println("<tr><td align='center'><img class=\"img-responsive\" style='width:200px;height:300px;' src=\"images\\"+item.getImage()+"\"></td></tr>");
							out.println("<tr><td align='center'>Name : "+item.getName()+"</td></tr>");
							out.println("<tr><td align='center'>Condition : "+item.getCondition()+"</td></tr>");
							out.println("<tr><td align='center'>Price : $"+item.getPrice()+"</td></tr>");
							if(firstname != null && !(firstname.isEmpty()))
							{	
								out.println("<form method = 'get' action = 'AddCartPage'>");
								out.println("<input type='hidden' name='pId' value='"+item.getId()+"'>");
								out.println("<input type='hidden' name='pCategory' value='"+item.getCategory()+"'>");
								//out.println("<input type='hidden' name='aName' value='"+accessory.getName()+"'>");
								out.println("<tr><td width='70%' align='center'><input class=\"button\" type=\"submit\" style=' width:100%;background-color:green;padding:16px 21px;margin:8px;color: white;cursor: pointer;' name=\"action\" value=\"Add To Cart\"></td></tr></form>");
							}
							i++;
							out.println("</table></div>");
							
						}
						out.println("</div><a class=\"left carousel-control\" href=\"#myCarousel\" role=\"button\" data-slide=\"prev\"><span class=\"glyphicon glyphicon-chevron-left\" aria-hidden=\"true\"></span><span class=\"sr-only\">Previous</span></a>");	
						out.println("<a class=\"right carousel-control\" href=\"#myCarousel\" role=\"button\" data-slide=\"next\"><span class=\"glyphicon glyphicon-chevron-right\" aria-hidden=\"true\"></span><span class=\"sr-only\">Next</span></a></div>");
						
					}
					
				}
				out.println("</section>");
				}
			}
		}
		else{
				out.println("<div id='body'><section id='content'><article><table>");
				out.println("<h2><span style='green;'>Login First to View Cart</span></h2>");
				out.println("</table></article></section>");
		}
		if(usertype!= null && usertype.equals("customer"))
		{
			util.parseHtml("nav.html");
		}
		else if(usertype!= null && usertype.equals("salesmanager")){
			util.parseHtml("nav1.html");
		}
		else{
			util.parseHtml("nav.html");
		}	
		util.parseHtml("footer.html");
	}
}