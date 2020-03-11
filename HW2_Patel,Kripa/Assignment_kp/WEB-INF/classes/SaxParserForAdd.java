import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.io.*;
import org.xml.sax.helpers.AttributesImpl;
import java.util.*;
import java.text.ParseException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SaxParserForAdd extends DefaultHandler { 
	String arr[] ;
	String fname;
	static String indent;	
	static int num;
	String pCompany;
    String xmlRead;
	String productName; 	
	String pCondition;
	String pPrice; 
	String pDescription;
	String pColor;
	String pImage; 
	String pCategory;
	int totalSize;
    boolean f;
	
    public SaxParserForAdd(String fname,String productName, String pPrice, String pColor, String pCondition, String pDescription, String pCompany,String pImage, String pCategory,int totalSize) 
	{
		num= 0;
		f=false;
		this.pColor=pColor;
		this.fname = fname;
		this.pCondition=pCondition;
		this.productName=productName;
		this.pCompany=pCompany;
		this.pPrice=pPrice;
		this.pImage=pImage;
		this.pCategory=pCategory;
		this.pDescription=pDescription;
		this.totalSize=totalSize;	
		indent = "";
		arr= new String[100000];
		validateDoc();
    }

	public void processingInstruction(String target, String data) {
		  arr[num] = indent;
		  arr[num] += "<?";
		  arr[num] += target;
		  if (data != null && data.length() > 0) {
			 arr[num] += ' ';
			 arr[num] += data;
		  }
		  arr[num] += "?>";
		  num++;
	}
   
	private void validateDoc() {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser parser = factory.newSAXParser();
			File inputFile = new File(fname);
			parser.parse(fname, this);
			FileWriter filewriter = new FileWriter(fname);
		 
			for(int loopIndex = 0; loopIndex < num; loopIndex++) {
				filewriter.write(arr[loopIndex].toCharArray());
				filewriter.write('\n');
			}
			filewriter.close();
			} catch (Exception e) {
				System.out.println("ParserConfig error");
			} 
		}
		
	   public void startDocument() {
		  arr[num] = indent;
		  arr[num] += "<?xml version = \"1.0\" encoding = \""+"UTF-8" + "\"?>";
		  num++;
		}
	
   
    @Override
    public void characters(char[] characters, int start, int length) throws SAXException {
         String characterData = (new String(characters, start, length)).trim();
      
      if(characterData.indexOf("\n") < 0 && characterData.length() > 0) {
         arr[num] = indent;
         arr[num] += characterData;
		 num++;
     }
    }

	@Override
	public void endElement(String str1, String str2, String qualifiedName) throws SAXException {
	 indent = indent.substring(0, indent.length() ) ;
      arr[num] = indent;
      arr[num] += "</";
      arr[num] += qualifiedName;
      arr[num] += '>';
      num++;
    }
	
    @Override
    public void startElement(String str1, String str2, String qualifiedName, Attributes attributes) throws SAXException {
		  arr[num] = indent;
		  indent += "";
		  arr[num] += '<';
		  arr[num] += qualifiedName;
		 
		  if (attributes != null) {
			 int numberAttributes = attributes.getLength();
			 for (int loopIndex = 0; loopIndex < numberAttributes; loopIndex++) {
				arr[num] += ' ';
				arr[num] += attributes.getQName(loopIndex);
				arr[num] += "=\"";
				arr[num] += attributes.getValue(loopIndex);
				arr[num] += '"';	
			 }
			 arr[num] += '>';
			 num++;
		  }
		  else{
			  arr[num] += '>';	  
			   num++;
		  }
	
		if(qualifiedName.equalsIgnoreCase("products"))
		{
			AttributesImpl atts = new AttributesImpl();
			atts.addAttribute("","","id","CDATA",(totalSize+1)+"");
			atts.addAttribute("","","retailer","CDATA","bestdeal");
			startElement("",pCategory,pCategory,atts);
			startElement("","image","image",null);
			characters(pImage.toCharArray(), 0, pImage.length());
			endElement("", "image", "image");
			
			startElement("","name","name",null);
			characters(productName.toCharArray(), 0, productName.length());
			endElement("", "name", "name");
			
			startElement("","price","price",null);
			characters(pPrice.toCharArray(), 0, pPrice.length());
			endElement("", "price", "price");
			
			startElement("","condition","condition",null);
			characters(pCondition.toCharArray(), 0, pCondition.length());
			endElement("", "condition", "condition");
			
			startElement("","company","company",null);
			characters(pCompany.toCharArray(), 0, pCompany.length());
			endElement("", "company", "company");
			
			startElement("","color","color",null);
			characters(pColor.toCharArray(), 0, pColor.length());
			endElement("", "color", "color");
			
			startElement("","description","description",null);
			characters(pDescription.toCharArray(), 0, pDescription.length());
			endElement("", "description", "description");
			
			endElement("", pCategory, pCategory);
		}
   }

}
