import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;
import java.net.*;
import java.util.Map.Entry;
public class TopLikedProductsPage extends HttpServlet
{	
	private static Map sortByAvg(Map<String, Float> temp,final boolean o)
    {

        LinkedList<Entry<String, Float>> ll = new LinkedList<Entry<String, Float>>(temp.entrySet());

        Collections.sort(ll, new Comparator<Entry<String, Float>>()
        {
            public int compare(Entry<String, Float> o1,Entry<String, Float> o2)
            {
                if (o)
                {
                    return o1.getValue().compareTo(o2.getValue());
                }
                else
                {
                    return o2.getValue().compareTo(o1.getValue());

                }
            }
        });

        Map<String, Float> mainMap = new LinkedHashMap<String, Float>();
        for (Entry<String, Float> eee : ll)
        {
            mainMap.put(eee.getKey(), eee.getValue());
        }

        return mainMap;
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("text/html");
		PrintWriter out =response.getWriter();
		HttpSession session = request.getSession();
		Utilities util=new Utilities(request,out);
		if(request.getAttribute("checkMsg") != null)
		{
			out.println("<h3 style='color:black;background-color:blue;'>"+(String)(request.getAttribute("checkMsg"))+"</h3>");
			request.setAttribute("checkMsg","");
		}	
		MySqlDataStoreUtilities dbInstance = new MySqlDataStoreUtilities();
		dbInstance.connect();
		List<String> r = dbInstance.getTopZipCodes();
		String firstname=null,usertype=null;
		if(session.getAttribute("UserDetail") != null)
		{
			firstname=((UserDetail)(session.getAttribute("UserDetail"))).getFirstName();
			usertype=((UserDetail)(session.getAttribute("UserDetail"))).getUserType();
		} 
		MongoDbDataStoreUtilities mdb = new MongoDbDataStoreUtilities();
		mdb.connect();
		HashMap<String,ArrayList<ReviewDetail>> rs = mdb.getAllReviews();
		
		util.parseHtml("header.html");
		out.println("<div id='body'>");
		out.println("<section id='content'>");
		out.println(" <h1 align=\"center\"><span style='color:red;'>Top 5 Liked Products</span></h1> ");
		out.println("<article>");
		HashMap<String,Float> avgMap = new HashMap<String,Float>();
		for(Map.Entry<String, ArrayList<ReviewDetail>> e : rs.entrySet())
		{
			ArrayList<ReviewDetail> rsList = e.getValue();			
			String pName = e.getKey();
			if(!rsList.isEmpty())
			{	
				int jj=0,sum=0;;
				int[] avgRating = new int[rsList.size()];
				
				for(ReviewDetail reviewDetail : rsList)
				{
						avgRating[jj]= Integer.parseInt(reviewDetail.getReviewRating());
						jj++;
				}
				
				for(int j=0; j<jj; j++)
				{
					sum+= avgRating[j];
				}
				float a1=sum/jj;
				avgMap.put(pName,a1);
			}	
		}
		int m=5;
		out.println("<table>");	
		Map<String, Float> sortAvg = sortByAvg(avgMap,false);
		for(Map.Entry<String, Float> ee : sortAvg.entrySet())
		{
			if(m > 0)
			{	
					String pName = ee.getKey();
					Item item= (Item) dbInstance.getProduct(pName);
					
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
			m--;
		}
		
		out.println("</table>");
		
		out.println("</article>");
		out.println("</section>");		
		util.parseHtml("nav.html");
		util.parseHtml("footer.html");
	}
}