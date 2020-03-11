import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.*; 
import java.io.*; 
import java.util.*; 
import java.sql.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.JSONException;
import javax.servlet.annotation.WebServlet;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URI;
import java.nio.charset.StandardCharsets;
@WebServlet("/BaseballList")

public class BaseballList extends HttpServlet {



	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String name = null;
	/*	String command =
  "curl,-X,GET,--header,Authorization: Bearer EYA2GiHdnsAqQRFk49hpzBVhdA0g,--header,Accept: application/json,https://api.stubhub.com/sellers/search/events/v3?q=Baseball&rows=500,";
ProcessBuilder processBuilder = new ProcessBuilder(command.split(","));


processBuilder.directory(new File("C:\\apache-tomcat-7.0.34\\webapps\\Project"));
Process process = processBuilder.start();
try
{
InputStream inputStream = process.getInputStream();
         
          
        // checking the command in list 
        String result = data.convertInputStreamToString(inputStream);
            
            PrintWriter out = new PrintWriter(new FileWriter("C:\\apache-tomcat-7.0.34\\webapps\\SportsStalk\\WEB-INF\\classes\\Baseball.json"));
            out.write(result);
}
catch (FileNotFoundException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } 
       catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }  */
      String Baseball="Baseball";
      String baseball="baseball";
      data.doPost(Baseball,baseball);
		HashMap<String,Baseball> allbaseballs = new HashMap<String,Baseball> ();

		try{
		     allbaseballs = MySqlDataStoreUtilities.getBaseballs();
		}
		catch(Exception e)
		{

		}


		HashMap<String, Baseball> hm = new HashMap<String, Baseball>();

			hm.putAll(allbaseballs);
			name = "";



		Utilities utility = new Utilities(request,pw);
		utility.parseHtml("header.html");

		pw.print("<br><div ><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 30px;padding: 0px 630px;color:#DE2D3A;text-decoration:none;'>"+name+" BASEBALL</a>");
		pw.print("</h2><br><div class='entry'><table id='bestseller'>");
		int i = 1; int size= hm.size();
		for(Map.Entry<String, Baseball> entry : hm.entrySet())
		{
			Baseball base = entry.getValue();
			if(i%1==0) pw.print("<tr class='card'>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>"+base.getName()+"</h3></td>");
			pw.print("<td>"+base.getEventDate()+"</td>");
			pw.print("<td>"+base.getVenue()+"</td>");
			pw.print("<td>"+base.getCity()+"</td>");
			pw.print("<td>"+base.getState()+"</td>");
			pw.print("<td>"+base.getPostal()+"</td>");
			pw.print("<form class = 'submit-button' method = 'get' action = ''>"+
					"<td><input class = 'submit-button' type = 'submit' value='Interested Event'></td>" +
					"</form>");
					
			/* if(i%3==1) pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>"+base.getName()+"</h3>");
			pw.print("<strong>"+base.getEventDate()+"</strong><ul>");
			pw.print("<br/>");
			pw.print(base.getVenue());
			pw.print("<br/>");
			pw.print(base.getCity());
			pw.print("<br/>");
			pw.print(base.getState()); */

			/*pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='TVs'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
			pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='TVs'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='price' value='"+TV.getPrice()+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
			pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='TVs'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='ViewReview' class='btnreview'></form></li>");*/
			pw.print("</div>");
			if(i%1==0 || i == size) pw.print("</tr>");
			i++;
		}
		pw.print("</table></div></div></div>");

		utility.parseHtml("footer.html");


}
}