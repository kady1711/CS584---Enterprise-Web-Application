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
@WebServlet("/GolfList")

public class GolfList extends HttpServlet {



	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String name = null;
	/*	String command =
  "curl,-X,GET,--header,Authorization: Bearer EYA2GiHdnsAqQRFk49hpzBVhdA0g,--header,Accept: application/json,https://api.stubhub.com/sellers/search/events/v3?q=golf&rows=500,";
ProcessBuilder processBuilder = new ProcessBuilder(command.split(","));


processBuilder.directory(new File("C:\\apache-tomcat-7.0.34\\webapps\\Project"));
Process process = processBuilder.start();
try
{
InputStream inputStream = process.getInputStream();
         
          
        // checking the command in list 
        String result = data.convertInputStreamToString(inputStream);
            
            PrintWriter out = new PrintWriter(new FileWriter("C:\\apache-tomcat-7.0.34\\webapps\\SportsStalk\\WEB-INF\\classes\\Golf.json"));
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
      String Golf="Golf";
      String golf="golf";
      data.doPost(Golf,golf);
		HashMap<String,Golf> allgolfs = new HashMap<String,Golf> ();

		try{
		     allgolfs = MySqlDataStoreUtilities.getGolfs();
		}
		catch(Exception e)
		{

		}


		HashMap<String, Golf> hm = new HashMap<String, Golf>();

			hm.putAll(allgolfs);
			name = "";



		Utilities utility = new Utilities(request,pw);
		utility.parseHtml("header.html");

		pw.print("<br><div><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 30px;padding: 0px 630px;color:#DE2D3A;text-decoration:none;'>"+name+" GOLF</a>");
		pw.print("</h2><br><div class='entry'><table id='bestseller'>");
		int i = 1; int size= hm.size();
		for(Map.Entry<String, Golf> entry : hm.entrySet())
		{
			Golf golf1 = entry.getValue();
			if(i%1==0) pw.print("<tr class='card'>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>"+golf1.getName()+"</h3></td>");
			pw.print("<td>"+golf1.getEventDate()+"</td>");
			pw.print("<td>"+golf1.getVenue()+"</td>");
			pw.print("<td>"+golf1.getCity()+"</td>");
			pw.print("<td>"+golf1.getState()+"</td>");
			pw.print("<td>"+golf1.getPostal()+"</td>");
			pw.print("<form class = 'submit-button' method = 'get' action = ''>"+
					"<td><input class = 'submit-button' type = 'submit' value='Interested Event'></td>" +
					"</form>");
			
			/* if(i%3==1) pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>"+golf1.getName()+"</h3>");
			pw.print("<strong>"+golf1.getEventDate()+"</strong><ul>");
			pw.print("<br/>");
			pw.print(golf1.getVenue());
			pw.print("<br/>");
			pw.print(golf1.getCity());
			pw.print("<br/>");
			pw.print(golf1.getState()); */

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