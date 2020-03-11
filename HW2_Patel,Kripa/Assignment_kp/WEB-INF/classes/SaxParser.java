import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;
import java.io.*;
import java.util.*;
import java.text.*;
import javax.xml.parsers.*;
import java.text.ParseException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


public class SaxParser extends DefaultHandler {  
	HashMap<String,Object> products;
	List<Television> televisions;
	List<Laptop> laptops;
	List<VoiceAssistant> voiceassistants;
	List<Phone> phones;
	List<SmartWatch> watches;
	List<FitnessWatch> fwatches;
	List<HeadPhone> headphones;
	List<SoundSystem> speakers;
	
	Laptop laptop;
	VoiceAssistant voiceassistant;
	SmartWatch watch;
	Phone phone;
	SoundSystem speaker;
	FitnessWatch fwatch;
	HeadPhone headphone;
	Television television;
	
	Accessory sa,ha,la,wa,fwa,pa,va,pta;
	boolean l,pt,v,fw,w,p,h,s;
	
	String xml;
    String eParse;
	int totalSize;
 
    public SaxParser(String xml) {
        this.xml = xml;
        speakers = new ArrayList<SoundSystem>();
		laptops=new ArrayList<Laptop>();
		phones = new ArrayList<Phone>();
		voiceassistants=new ArrayList<VoiceAssistant>();
        watches = new ArrayList<SmartWatch>();
		fwatches = new ArrayList<FitnessWatch>();
		headphones=new ArrayList<HeadPhone>();
		televisions=new ArrayList<Television>();
		products= new HashMap<String,Object>();
		
		parseXmlDocument();
		
		products.put("fwatches",fwatches);
		products.put("speakers",speakers);
		products.put("laptops",laptops);
		products.put("televisions",televisions);
		products.put("phones",phones);
		products.put("voiceassistants",voiceassistants);
		products.put("watches",watches);
		products.put("headphones",headphones);
		
		totalSize=speakers.size()+phones.size()+voiceassistants.size()+laptops.size()+fwatches.size()+watches.size()+headphones.size()+televisions.size();
    }
	
	private void parseXmlDocument() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(xml, this);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
			e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IO error");
        }
    }
	
	public HashMap<String,Object> getProducts()
	{
		return products;
	}
	
	public int getProductsSize()
	{
		return totalSize;
	}
	
    @Override
    public void startElement(String str1, String str2, String elementName, Attributes attributes) throws SAXException {

		if (elementName.equalsIgnoreCase("accessory")) {
			if(l)
			{
				la= new Accessory();
			}
			if(fw)
			{
				fwa= new Accessory();
			}
			if(s)
			{
				sa= new Accessory();
			}
			if(v)
			{
				va= new Accessory();
			}
			if(w)
			{
				wa= new Accessory();
			}
			if(h)
			{
				ha= new Accessory();
			}
			if(pt)
			{
				pta= new Accessory();
			}
			if(p)
			{
				pa= new Accessory();
			}
        }
	
        if (elementName.equalsIgnoreCase("speakers")) {
			speaker = new SoundSystem();
            speaker.setRetailer(attributes.getValue("retailer"));
			speaker.setId(attributes.getValue("id"));
			s=true;
        }
		
		if (elementName.equalsIgnoreCase("VoiceAssistant")) {
            voiceassistant = new VoiceAssistant();
            voiceassistant.setId(attributes.getValue("id"));
            voiceassistant.setRetailer(attributes.getValue("retailer"));
			pt=true;
        }
		
        if (elementName.equalsIgnoreCase("Laptops")) {
            laptop = new Laptop();
            laptop.setId(attributes.getValue("id"));
            laptop.setRetailer(attributes.getValue("retailer"));
			l=true;
        }
		
		if (elementName.equalsIgnoreCase("SmartWatches")) {
            watch = new SmartWatch();
            watch.setId(attributes.getValue("id"));
            watch.setRetailer(attributes.getValue("retailer"));
			w=true;
        }
		
		
		if (elementName.equalsIgnoreCase("Phones")) {
            phone = new Phone();
            phone.setId(attributes.getValue("id"));
            phone.setRetailer(attributes.getValue("retailer"));
			p=true;
        }
		
		if (elementName.equalsIgnoreCase("FitnessWatches")) {
            fwatch = new FitnessWatch();
            fwatch.setId(attributes.getValue("id"));
			fwatch.setRetailer(attributes.getValue("retailer"));
			fw=true;
        }
		if (elementName.equalsIgnoreCase("Headphones")) {
            headphone = new HeadPhone();
            headphone.setId(attributes.getValue("id"));
            headphone.setRetailer(attributes.getValue("retailer"));
			h=true;
        }
		
		if (elementName.equalsIgnoreCase("televisions")) {
			//Accessory la= new Accessory();
            television = new Television();
            television.setId(attributes.getValue("id"));
            television.setRetailer(attributes.getValue("retailer"));
			v=true;
        }
    }

    @Override
    public void endElement(String str1, String str2, String element) throws SAXException {
		
		if (element.equals("Laptops")) {
            laptops.add(laptop);
			l=false;
			return;
        }
		
		if (element.equals("FitnessWatches")) {
            fwatches.add(fwatch);
			fw=false;
			return;
        }
		
		 if (element.equals("speakers")) {
            speakers.add(speaker);
			s=false;
			return;
        }
		
		if (element.equals("SmartWatches")) {
            watches.add(watch);
			w=false;
			return;
        }
		
		if (element.equals("Headphones")) {
            headphones.add(headphone);
			h=false;
			return;
        }
		
		if (element.equals("VoiceAssistant")) {
            voiceassistants.add(voiceassistant);
			pt=false;
			return;
        }
		
		if (element.equals("televisions")) {
            televisions.add(television);
			v=false;
			return;
        }
		
		if (element.equals("Phones")) {
            phones.add(phone);
			p=false;
			return;
        }
		
		 if (element.equalsIgnoreCase("name")) {
			if(l)
			{
				laptop.setName(eParse);
			}
			if(s)
			{
				speaker.setName(eParse);
			}
			if(w)
			{
				watch.setName(eParse);
			}
			if(h)
			{
				headphone.setName(eParse);
			}
			if(p)
			{
				phone.setName(eParse);
			}
			if(v)
			{
				television.setName(eParse);
			}
			if(pt)
			{
				voiceassistant.setName(eParse);
			}
			if(fw)
			{
				fwatch.setName(eParse);
			}
	    return;
        }
		
        if (element.equalsIgnoreCase("image")) {
			
			if(fw)
			{
				fwatch.setImage(eParse);
			}
			if(l)
			{
				laptop.setImage(eParse);
			}
			if(pt)
			{
				voiceassistant.setImage(eParse);
			}
			if(s)
			{
				speaker.setImage(eParse);
			}
			if(w)
			{
				watch.setImage(eParse);
			}
			if(h)
			{
				headphone.setImage(eParse);
			}
			if(v)
			{
				television.setImage(eParse);
			}
			if(p)
			{
				phone.setImage(eParse);
			}
	    return;
        }
       
		if (element.equalsIgnoreCase("condition")) {
			if(l)
			{
				laptop.setCondition(eParse);
			}
			if(s)
			{
				speaker.setCondition(eParse);
			}
			if(v)
			{
				television.setCondition(eParse);
			}
			if(pt)
			{
				voiceassistant.setCondition(eParse);
			}
			if(fw)
			{
				fwatch.setCondition(eParse);
			}
			if(w)
			{
				watch.setCondition(eParse);
			}
			if(h)
			{
				headphone.setCondition(eParse);
			}
			if(p)
			{
				phone.setCondition(eParse);
			}
            
	    return;
        }
		
		
		
		if (element.equalsIgnoreCase("company")) {
			if(l)
			{
				laptop.setCompany(eParse);
			}
			if(s)
			{
				speaker.setCompany(eParse);
			}
			if(v)
			{
				television.setCompany(eParse);
			}
			if(pt)
			{
				voiceassistant.setCompany(eParse);
			}
			if(fw)
			{
				fwatch.setCompany(eParse);
			}
			if(w)
			{
				watch.setCompany(eParse);
			}
			if(h)
			{
				headphone.setCompany(eParse);
			}
			if(p)
			{
				phone.setCompany(eParse);
			}
            
	    return;
        }
		
		if (element.equalsIgnoreCase("description")) {
			if(l)
			{
				laptop.setDescription(eParse);
			}
			if(s)
			{
				speaker.setDescription(eParse);
			}
			if(v)
			{
				television.setDescription(eParse);
			}
			if(pt)
			{
				voiceassistant.setDescription(eParse);
			}
			if(fw)
			{
				fwatch.setDescription(eParse);
			}
			if(w)
			{
				watch.setDescription(eParse);
			}
			if(h)
			{
				headphone.setDescription(eParse);
			}
			if(p)
			{
				phone.setDescription(eParse);
			}
            
	    return;
        }
			
        if(element.equalsIgnoreCase("price")){
			if(l)
			{
				laptop.setPrice(Float.parseFloat(eParse));
			}
			if(s)
			{
				speaker.setPrice(Float.parseFloat(eParse));
			}
			if(v)
			{
				television.setPrice(Float.parseFloat(eParse));
			}
			if(pt)
			{
				voiceassistant.setPrice(Float.parseFloat(eParse));
			}
			if(fw)
			{
				fwatch.setPrice(Float.parseFloat(eParse));
			}
			if(w)
			{
				watch.setPrice(Float.parseFloat(eParse));
			}
			if(h)
			{
				headphone.setPrice(Float.parseFloat(eParse));
			}
			if(p)
			{
				phone.setPrice(Float.parseFloat(eParse));
			}
            
	    return;
        }
		
		if (element.equalsIgnoreCase("color")) {
			if(l)
			{
				laptop.setColor(eParse);
			}
			if(s)
			{
				speaker.setColor(eParse);
			}
			if(v)
			{
				television.setColor(eParse);
			}
			if(pt)
			{
				voiceassistant.setColor(eParse);
			}
			if(fw)
			{
				fwatch.setColor(eParse);
			}
			if(w)
			{
				watch.setColor(eParse);
			}
			if(h)
			{
				headphone.setColor(eParse);
			}
			if(p)
			{
				phone.setColor(eParse);
			}
            
	    return;
        }
		
			
        if(element.equalsIgnoreCase("accessory")){
			if(s)
			{
				speaker.getAccessories().add(sa);
			}
			if(l)
			{
				laptop.getAccessories().add(la);
			}
			if(v)
			{
				television.getAccessories().add(va);
			}
			if(pt)
			{
				voiceassistant.getAccessories().add(pta);
			}
			if(fw)
			{
				fwatch.getAccessories().add(fwa);
			}
			if(w)
			{
				watch.getAccessories().add(wa);
			}
			if(h)
			{
				headphone.getAccessories().add(ha);
			}
			if(p)
			{
				phone.getAccessories().add(pa);
			}	
	    return;
        }
		
		if(element.equalsIgnoreCase("accessoryCondition")){
			if(s)
			{
				sa.setCondition(eParse);
			}
			if(l)
			{
				la.setCondition(eParse);
			}
			if(v)
			{
				va.setCondition(eParse);
			}
			if(pt)
			{
				pta.setCondition(eParse);
			}
			if(fw)
			{
				fwa.setCondition(eParse);
			}
			if(w)
			{
				wa.setCondition(eParse);
			}
			if(h)
			{
				ha.setCondition(eParse);
			}
			if(p)
			{
				pa.setCondition(eParse);
			}
	    return;
        }
		
		
		if(element.equalsIgnoreCase("accessoryImage")){
			if(s)
			{
				sa.setImage(eParse);
			}
			if(l)
			{
				la.setImage(eParse);
			}
			if(v)
			{
				va.setImage(eParse);
			}
			if(pt)
			{
				pta.setImage(eParse);
			}
			if(fw)
			{
				fwa.setImage(eParse);
			}
			if(w)
			{
				wa.setImage(eParse);
			}
			if(h)
			{
				ha.setImage(eParse);
			}
			if(p)
			{
				pa.setImage(eParse);
			}
	    return;
        }
		
		if(element.equalsIgnoreCase("accessoryName")){
			if(s)
			{
				sa.setName(eParse);
			}
			if(l)
			{
				la.setName(eParse);
			}
			if(v)
			{
				va.setName(eParse);
			}
			if(pt)
			{
				pta.setName(eParse);
			}
			if(fw)
			{
				fwa.setName(eParse);
			}
			if(w)
			{
				wa.setName(eParse);
			}
			if(h)
			{
				ha.setName(eParse);
			}
			if(p)
			{
				pa.setName(eParse);
			}
	    return;
        }
		
		
		if(element.equalsIgnoreCase("accessoryPrice")){
			if(s)
			{
				sa.setPrice(Float.parseFloat(eParse));
			}
			if(l)
			{
				la.setPrice(Float.parseFloat(eParse));
			}
			if(v)
			{
				va.setPrice(Float.parseFloat(eParse));
			}
			if(pt)
			{
				pta.setPrice(Float.parseFloat(eParse));
			}
			if(fw)
			{
				fwa.setPrice(Float.parseFloat(eParse));
			}
			if(w)
			{
				wa.setPrice(Float.parseFloat(eParse));
			}
			if(h)
			{
				ha.setPrice(Float.parseFloat(eParse));
			}
			if(p)
			{
				pa.setPrice(Float.parseFloat(eParse));
			}
	    return;
        }
	

    }

    @Override
    public void characters(char[] content, int begin, int end) throws SAXException {
        eParse = new String(content, begin, end);
		eParse=eParse.trim();
    }
	
    public static void main(String[] args) {
        new SaxParser("ProductCatalog.xml");
				

    }

}
