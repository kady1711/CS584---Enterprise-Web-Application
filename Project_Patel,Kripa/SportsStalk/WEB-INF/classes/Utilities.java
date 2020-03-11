import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.lang.*;
import java.net.*;

public class Utilities {
	String url;
	HttpServletRequest request;
	HttpSession session;
	PrintWriter out;
	public Utilities(HttpServletRequest request, PrintWriter out)
	{
		this.request=request;
		this.out=out;
		this.url=this.getUrl();
	}	
	
	public String convertHtml(String htmlFile)
	{
		String fileNew=url+htmlFile;
		String s=null;
		try
		{
			URL url = new URL(fileNew);
			URLConnection connection = url.openConnection();
			InputStream in = connection.getInputStream();
			InputStreamReader inr= new InputStreamReader(in);
			int readChars;
			char[] array=new char[1024];
			StringBuilder sbuild=new StringBuilder();
			while((readChars=inr.read(array)) > 0)
			{
				sbuild.append(array,0,readChars);
			}
			s=sbuild.toString();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return s;
	}
	
	public String getUrl()
	{
		int port=request.getServerPort();
		String server=request.getServerName();
		String scheme=request.getScheme();
		String path=request.getContextPath();
		StringBuilder sbuild=new StringBuilder();
		sbuild.append(scheme).append("://").append(server);
		if((port!=80) && (port!=443))
		{
			sbuild.append(":").append(port);
		}
		sbuild.append(path).append("/");
		return sbuild.toString();
	}
	
	public void parseHtml(String htmlFile)
	{	
		int cartItems=0;
		String s=convertHtml(htmlFile);
		StringBuilder sbuild = new StringBuilder();
		sbuild.append("");
		HttpSession session = request.getSession();
		
		if(htmlFile.equals("header.html"))
		{
			
			if(session.getAttribute("email") != null)
			{
				String email=(session.getAttribute("email")).toString();
				//UserDetail userDetail=(UserDetail)session.getAttribute("UserDetail");
				sbuild.append(" <li style='float:right;' class='end'><a href='SignoutPage'>Sign Out</a></li><li style='float:right;'><a href='Profile'>Welcome <b>"+email+"</b></a></li>    </ul></nav>" );
			}
		
			else{
				sbuild.append(" <li><a style='float:right;margin-left:170px;' href='LoginPage'>Login</a></li> <li style='float:right;'><a href='RegisterPage'>Register</a></li> </ul></nav>");
			}
		}
		out.println(s);
		out.println(sbuild.toString());
	}
	
}