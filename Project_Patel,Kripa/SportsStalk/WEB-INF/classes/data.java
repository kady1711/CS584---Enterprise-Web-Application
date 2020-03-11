import java.lang.*; 
import java.io.*; 
import java.util.*; 
import java.sql.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.JSONException;

import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URI;
import java.nio.charset.StandardCharsets;

@WebServlet("/data")
public class data  {
    

    public static void doPost(String request,String response) 
             
     {

        try
        {
      String filePath="C:\\tomcat\\tomcat-7.0.34-preconfigured\\apache-tomcat-7.0.34\\webapps\\SportsStalk\\WEB-INF\\classes\\"+request+".json";
      FileReader reader = new FileReader(filePath);
      JSONParser jsonParser = new JSONParser();
      JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

      JSONArray jsonArray = (JSONArray) jsonObject.get("events");
      
      
      Connection con = ConnectToDB();
       PreparedStatement pstmt = con.prepareStatement("INSERT INTO events values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
      for(Object object : jsonArray) {
            JSONObject record = (JSONObject) object;
            JSONObject ancestors = (JSONObject) record.get("ancestors");
            JSONArray jsonArray1 = (JSONArray) ancestors.get("categories");
            //JSONArray jsonArray2= (JSONArray) jsonArray1.get("name[1]");
            
            JSONObject cat = (JSONObject) jsonArray1.get(1);
            
            
            Long id = (Long)record.get("id");
            String status = (String) record.get("status");
            String locale = (String) record.get("locale");
            String name = (String) record.get("name");
            String date = (String) record.get("eventDateLocal");

            JSONObject ven=(JSONObject) record.get("venue");
            
            String venue=(String) ven.get("name");
            String city=(String) ven.get("city");
            String state=(String) ven.get("state");
            String postalCode=(String) ven.get("postalCode");
            String country=(String) ven.get("country");
            String catName=(String) cat.get("name");
           

            pstmt.setLong(1, id);
            pstmt.setString(2, status);
            pstmt.setString(3, locale);
            pstmt.setString(4, name);
            pstmt.setString(5, date);
            pstmt.setString(6, venue);
            pstmt.setString(7, city);
            pstmt.setString(8, state);
            pstmt.setString(9, postalCode);
            pstmt.setString(10, country);
            pstmt.setString(11, catName);
            
            pstmt.executeUpdate();
           
         
      } 
    }
    catch (FileNotFoundException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } 
       catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      

    
    
 
    } 
    public static Connection ConnectToDB() throws Exception {
      //Registering the Driver
      DriverManager.registerDriver(new com.mysql.jdbc.Driver());
      //Getting the connection
      String mysqlUrl = "jdbc:mysql://localhost:3306/SportsStalk?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
      Connection con = DriverManager.getConnection(mysqlUrl, "root", "Amb!vert6");
      System.out.println("Connection established......");
      return con;
   }
   
    public static String convertInputStreamToString(InputStream inputStream) throws IOException {

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }

        return result.toString(StandardCharsets.UTF_8.name());

    }
  }

    
