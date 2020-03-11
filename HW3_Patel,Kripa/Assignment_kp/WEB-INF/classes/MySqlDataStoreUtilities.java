import java.sql.*;
import java.util.*;
import java.io.*;
//webappreviews reviews
public class MySqlDataStoreUtilities
{
	Connection con = null;
	public boolean connect()
	{
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/webapp?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","root");
			if(con.isClosed() || con==null){
				return false;
			}
			else
			{
				return true;
			}
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
			return false;
		}
	}
	
	public void addNewOrderProduct(int on,String productname,String productprice,int productqty,String productimage)
	{
		try
		{	
			PreparedStatement ps1 =  con.prepareStatement("insert into orderproductdetails(orderno,pname,pprice,pqty,pimage) values(?,?,?,?,?);");
			ps1.setInt(1,on);
			ps1.setString(2,productname);
			ps1.setString(3,productprice);
			ps1.setInt(4,productqty);
			ps1.setString(5,productimage);
			ps1.execute();
			
			PreparedStatement pss =  con.prepareStatement("select * from products where pname=?");
			pss.setString(1,productname);
			ResultSet rss = pss.executeQuery();
			int q=0;
			if(rss.next())
			{	
				q = rss.getInt("quantity")- productqty;
			}	

			PreparedStatement ps = con.prepareStatement("update products set quantity=? where pname = ?");
			ps.setInt(1,q);
			ps.setString(2,productname);
			ps.execute();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void modifyOrderAmount(float a,String oid)
	{
		String a1= a+"";
		try
		{
			String q = "update orderdetails set amount="+a1+"  where orderid=?";
			
			PreparedStatement ps =  con.prepareStatement(q);
			ps.setString(1,oid);
			ps.execute();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void removeWholeOrder(String oid)
	{
		try
		{	
			PreparedStatement ps1 =  con.prepareStatement("Select * from OrderDetails where orderid=?");
			ps1.setString(1,oid);
			ResultSet rs1=ps1.executeQuery();
			rs1.next();
			int ono=rs1.getInt("orderno");	
			PreparedStatement ps =  con.prepareStatement("Delete From OrderDetails where orderid = ?" );
			ps.setString(1,oid);
			ps.execute();
			PreparedStatement ps2 =  con.prepareStatement("Delete From OrderProductDetails where orderno = ?");
			ps2.setInt(1,ono);
			ps2.execute();		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
	}
	
	public RetailerDetail getRetailerByName(String rna)
	{
		RetailerDetail retailerDetail=null;
	
		try
		{	
			PreparedStatement ps =  con.prepareStatement("select * from retailer where rname=?");
			ps.setString(1,rna);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				retailerDetail =new RetailerDetail(rs.getString("rzip"),rs.getString("rcity"),rs.getString("rstate"),rs.getString("rname"),rs.getString("rid"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return retailerDetail;
	}
	
	public int addNewOrder(String oid,String pemail,String pamount,String paddress,String pdate)
	{
		int newOid = 0;
		try
		{
			PreparedStatement ps1 =  con.prepareStatement("insert into orderdetails(orderid,email,amount,address,orderdate) values(?,?,?,?,?);" ,Statement.RETURN_GENERATED_KEYS);
			ps1.setString(1,oid);
			ps1.setString(2,pemail);
			ps1.setString(3,pamount);
			ps1.setString(4,paddress);
			ps1.setString(5,pdate);
			ps1.execute();
			ResultSet newJKeys = ps1.getGeneratedKeys();
			if (newJKeys.next()) {
				newOid = (int)newJKeys.getLong(1);
            }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return newOid;
	}
	
	public void removeProductsForOrder(int orderproductid)
	{
		try
		{
			PreparedStatement ps1 =  con.prepareStatement("select * from orderproductdetails where opid = ?" );
			ps1.setInt(1,orderproductid);
			ResultSet rs1=ps1.executeQuery();
			
			while(rs1.next())
			{
				String productname= rs1.getString("pname");
				PreparedStatement ps2 =  con.prepareStatement("select * from products where pname=?");
				ps2.setString(1,productname);
				ResultSet rs2 = ps2.executeQuery();
				int q1=0;
				if(rs2.next())
				{	
					q1 = rs2.getInt("quantity") + rs1.getInt("pqty") ;
				}	
				
				PreparedStatement ps11 =  con.prepareStatement("update products set quantity=? where pname = ?");
				ps11.setInt(1,q1);
				ps11.setString(2,productname);
				ps11.execute();
			}
			String query = "Delete from OrderProductDetails where opid="+orderproductid;
			PreparedStatement ps =  con.prepareStatement(query);
			ps.execute();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	public List<String> getTopZipCodes()
	{
		
		List<String> r = new ArrayList<String>();
		try
		{	
			PreparedStatement ps =  con.prepareStatement("select *,count(*) as count_products from orderproductdetails group by pname order by count_products DESC");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				PreparedStatement ps1 =  con.prepareStatement("select * from products where pname=?" );
				ps1.setString(1,rs.getString("pname"));
				ResultSet rs1 = ps1.executeQuery();

				while(rs1.next())
				{
					if(!r.contains(rs1.getString("rname")))
					{	
						r.add(rs1.getString("rname"));
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return r;
	}
	
	public ArrayList getTopSoldProducts()
	{
		ArrayList<String> pNames = new ArrayList<String>();
		try
		{	
			PreparedStatement ps =  con.prepareStatement("select *,count(*) as count_products from orderproductdetails group by pname order by count_products DESC");
			ResultSet rs = ps.executeQuery();
			int i=5;
			while(rs.next())
			{
				if(i>0)
				{
					pNames.add(rs.getString("pname"));
				}
				i--;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return pNames;
	}
	
	public RetailerDetail getRetailerDetailByName(String r)
	{
		RetailerDetail retailerDetail=null;
		try
		{	
			PreparedStatement ps =  con.prepareStatement("select * from retailer where rname=?");
			ps.setString(1,r);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				retailerDetail = new RetailerDetail(rs.getString("rzip"),rs.getString("rcity"),rs.getString("rstate"),rs.getString("rname"),rs.getString("rid"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return retailerDetail;
	}
	
	
	public Item getProduct(String pName)
	{
		Item item = null;
		try
		{	
			PreparedStatement ps =  con.prepareStatement("select * from products where pname=?");
			ps.setString(1,pName);
			ResultSet rs = ps.executeQuery();

			while(rs.next())
			{
				item = new Item();
				item.setPrice((float)rs.getDouble("pprice"));
				item.setName(rs.getString("pname"));
				item.setRetailer(rs.getString("rname"));
				item.setCompany(rs.getString("pcompany"));
				item.setColor(rs.getString("pcolor"));
				item.setCondition(rs.getString("pcondition"));
				item.setId(rs.getInt("pid")+"");
				item.setDescription(rs.getString("pdescription"));
				item.setImage(rs.getString("pimage"));
				item.setCategory(rs.getString("pcategory"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return item;
	}
	
	public void modifyProductsForOrder(int orderproductid, int opqty)
	{
		try
		{
			PreparedStatement ps1 =  con.prepareStatement("select * from orderproductdetails where opid = ?" );
			ps1.setInt(1,orderproductid);
			ResultSet rs1=ps1.executeQuery();
			
			while(rs1.next())
			{
				String productname= rs1.getString("pname");
				PreparedStatement ps2 =  con.prepareStatement("select * from products where pname=?");
				ps2.setString(1,productname);
				ResultSet rs2 = ps2.executeQuery();
				int q1=0;
				if(rs2.next())
				{	
					q1 = rs2.getInt("quantity") + rs1.getInt("pqty") - opqty;
				}	
				
				PreparedStatement ps11 =  con.prepareStatement("update products set quantity=? where pname = ?");
				ps11.setInt(1,q1);
				ps11.setString(2,productname);
				ps11.execute();
			}
			
			String q = "update orderproductdetails set pqty="+opqty+" where opid="+orderproductid;
			PreparedStatement ps =  con.prepareStatement(q);
			ps.execute();	
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public HashMap getProductsForOrder(String oid)
	{
		HashMap<String,List<Object>> op = new HashMap<String, List<Object>>();

		try
		{	
			PreparedStatement psp =  con.prepareStatement("select * from orderdetails where orderid=?");
			psp.setString(1,oid);
			ResultSet rsp=psp.executeQuery();
			rsp.next();
			int ono=rsp.getInt("orderno");
			String q = "select * from orderproductdetails where orderno="+ono ;
			PreparedStatement ps =  con.prepareStatement(q);
			ResultSet rs = ps.executeQuery();
			
			
			while(rs.next())
			{
				List<Object> list = new ArrayList<Object>();
				list.add(rs.getString("pname"));
				list.add(rs.getString("pprice"));
				list.add(rs.getInt("pqty"));
				list.add(rs.getString("pimage"));
				list.add(rs.getInt("opid"));
				op.put(rs.getString("pname"), list);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return op;
	}
	
	public HashMap checkOidExists()
	{
		HashMap<String,Order> dbOrderDetails = new HashMap<String,Order>();
		try
		{	
			PreparedStatement ps1 =  con.prepareStatement("select * from orderdetails");
			ResultSet rs = ps1.executeQuery();

			while(rs.next())
			{
				if(!(dbOrderDetails.containsKey(rs.getString("orderid"))))
				{
					Order o = new Order(rs.getString("orderid"),rs.getString("email"),rs.getString("amount"),rs.getString("address"),rs.getString("orderdate"));
					dbOrderDetails.put(rs.getString("orderid"),o);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return dbOrderDetails;
	}
	
	public HashMap<String,UserDetail> checkMail()
	{
		HashMap<String,UserDetail> ud = new HashMap<>();
		String q = "select * from Registration";
		try
		{	
			PreparedStatement ps =  con.prepareStatement(q);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				String username =rs.getString("username");
				String firstname = rs.getString("firstname");
				String lastname = rs.getString("lastname");
				String password = rs.getString("password");
				String usertype = rs.getString("usertype");
				if(!(ud.containsKey(username)))
				{
					UserDetail userdetail = new UserDetail(firstname,lastname,username,password,usertype);
					userdetail.setGender(rs.getString("gender"));
					userdetail.setAge(rs.getString("age"));
					
					ud.put(username,userdetail);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ud;
	}
	
	public void modifyProduct(String prid, String primage, String prname, double prprice, String prcondition, String prcompany, String prcolor, String prdescription, String prrname, String prcategory)
	{
		int pid= Integer.parseInt(prid);
		try
		{	
			String query = "update products set pimage=?,pname=?,pprice=?,pcondition=?,pcompany=?,pcolor=?,pdescription=?,rname=?,pcategory=? where pid=? " ;
			PreparedStatement ps =  con.prepareStatement(query);
			
			ps.setInt(10,pid);
			ps.setString(1,primage);
			ps.setString(2,prname);
			ps.setDouble(3,prprice);
			ps.setString(4,prcondition);
			ps.setString(5,prcompany);
			ps.setString(6,prcolor);
			ps.setString(7,prdescription);
			ps.setString(8,prrname);
			ps.setString(9,prcategory);
			
			ps.execute();
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public void removeProduct(String id)
	{
		int pid= Integer.parseInt(id);
		try
		{	
			PreparedStatement ps =  con.prepareStatement("delete from products where pid=?");
			ps.setInt(1,pid);
			ps.execute();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void addNewUser(String firstname,String lastname,String username,String password, String usertype,String gender,String age)
	{
		try
		{	
			String q = "insert into Registration(firstname,lastname,username,password,usertype,age,gender) values(?,?,?,?,?,?,?);" ;
			PreparedStatement ps =  con.prepareStatement(q);
			ps.setString(1,firstname);
			ps.setString(2,lastname);
			ps.setString(3,username);
			ps.setString(4,password);
			ps.setString(5,usertype);
			ps.setString(6,age);
			ps.setString(7,gender);
			ps.execute();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void addNewProduct1(String prid, String primage, String prname, double prprice, String prcondition, String prcompany, String prcolor, String prdescription, String prrname, String prcategory)
	{
		int pid = Integer.parseInt(prid);
		try
		{	
			String q = "insert into Products(pid,pimage,pname,pprice,pcondition,pcompany,pcolor,"+"pdescription,rname,pcategory) values(?,?,?,?,?,?,?,?,?,?);" ;
			PreparedStatement ps =  con.prepareStatement(q);
			
			ps.setInt(1,pid);
			ps.setString(2,primage);
			ps.setString(3,prname);
			ps.setDouble(4,prprice);
			ps.setString(5,prcondition);
			ps.setString(6,prcompany);
			ps.setString(7,prcolor);
			ps.setString(8,prdescription);
			ps.setString(9,prrname);
			ps.setString(10,prcategory);
			ps.execute();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public ArrayList getProductList()
	{
		ArrayList<Item> items = new ArrayList<Item>();	
		try
		{	
			PreparedStatement ps =  con.prepareStatement("select * from products");
			ResultSet rs = ps.executeQuery();

			while(rs.next())
			{
				Item item = new Item();
				item.setPrice((float)rs.getDouble("pprice"));
				item.setName(rs.getString("pname"));
				item.setQuantity(rs.getInt("quantity"));
				items.add(item);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return items;
	}
	
	public ArrayList getProductWithRebate()
	{
		ArrayList<Item> items = new ArrayList<Item>();	
		try
		{	
			PreparedStatement ps =  con.prepareStatement("select * from products where rebate = ?");
			ps.setString(1,"yes");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				Item item = new Item();
				item.setPrice((float)rs.getDouble("pprice"));
				item.setName(rs.getString("pname"));
				item.setQuantity(rs.getInt("quantity"));
				item.setRebate(rs.getString("rebate"));
				items.add(item);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return items;
	}
	
	public ArrayList getDailySales()
	{
		ArrayList<Order> oo = new ArrayList<Order>();
		try
		{	
			PreparedStatement ps =  con.prepareStatement("select *,sum(amount) as sum_sales from orderdetails group by orderdate order by sum_sales desc;");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{		
				Order o = new Order();
				o.setOrderDate(rs.getString("orderdate"));
				o.setAmount(rs.getInt("sum_sales")+"");
				oo.add(o);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return oo;
	}
	
	
	public ArrayList getTotalSale()
	{
		ArrayList<Item> items = new ArrayList<Item>();	
		try
		{	
			PreparedStatement ps =  con.prepareStatement("select *,sum(pqty) as total from orderproductdetails group by pname order by total desc ");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				Item item = new Item();
				item.setName(rs.getString("pname"));
				item.setPrice(Float.parseFloat(rs.getDouble("pprice")+""));
				item.setQuantity(rs.getInt("total"));
				items.add(item);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return items;
	}
	public ArrayList getProductWithSale()
	{
		ArrayList<Item> items = new ArrayList<Item>();	
		try
		{	
			PreparedStatement ps =  con.prepareStatement("select * from products where onsale = ?");
			ps.setString(1,"yes");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				Item item = new Item();
				item.setPrice((float)rs.getDouble("pprice"));
				item.setName(rs.getString("pname"));
				item.setQuantity(rs.getInt("quantity"));
				item.setOnSale(rs.getString("onsale"));
				items.add(item);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return items;
	}
	
	
	public void addNewProduct(String prid, String primage, String prname, double prprice, String prcondition, String prcompany, String prcolor, String prdescription, String prrname, String prcategory, ArrayList<Accessory> accessories,int prquantity,String prrebate,String pronsale)
	{
		int pid = Integer.parseInt(prid);
		try
		{	
			String q = "insert into Products(pid,pimage,pname,pprice,pcondition,pcompany,pcolor,"+"pdescription,rname,pcategory,quantity,rebate,onsale) values(?,?,?,?,?,?,?,?,?,?,?,?,?);" ;
			PreparedStatement ps =  con.prepareStatement(q);
			
			ps.setInt(1,pid);
			ps.setString(2,primage);
			ps.setString(3,prname);
			ps.setDouble(4,prprice);
			ps.setString(5,prcondition);
			ps.setString(6,prcompany);
			ps.setString(7,prcolor);
			ps.setString(8,prdescription);
			ps.setString(9,prrname);
			ps.setString(10,prcategory);
			ps.setInt(11,prquantity);
			ps.setString(12,prrebate);
			ps.setString(13,pronsale);
			ps.execute();
			
			for(Accessory accessory : accessories)
			{
				String q1 = "insert into Accessories(aimage,aname,aprice,acondition,pid) values(?,?,?,?,?) ";
				
				PreparedStatement psa =  con.prepareStatement(q1);
				
				psa.setString(1,accessory.getImage());
				psa.setString(2,accessory.getName());
				psa.setDouble(3,accessory.getPrice());
				psa.setString(4,accessory.getCondition());
				psa.setInt(5,pid);
				psa.execute();
			}	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void removeProducts()
	{
		try{
			String query = "Delete from Products" ;
			PreparedStatement ps =  con.prepareStatement(query);
			ps.execute();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public HashMap getProducts()
	{
		
		HashMap<String,Object> products= new HashMap<String,Object>();
		
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
		try{
			String q = "select * from Products" ;
			PreparedStatement ps =  con.prepareStatement(q);
			ResultSet rs = ps.executeQuery();	
			String q1 = "Select * from Accessories where pid=?";
			while(rs.next())
			{
				String category = rs.getString("pcategory");
			
				if(category.equals("watches"))
				{
					PreparedStatement ps1 =  con.prepareStatement(q1);
					ps1.setInt(1,rs.getInt("pid"));
					ResultSet rs1 = ps1.executeQuery();
					ArrayList<Accessory> accessories = new ArrayList<Accessory>();
					while(rs1.next())
					{
						Accessory accessory = new Accessory();
						accessory.setName(rs1.getString("aname"));
						accessory.setCondition(rs1.getString("acondition"));
						accessory.setImage(rs1.getString("aimage"));
						accessory.setPrice((float)rs1.getDouble("aprice"));
						accessories.add(accessory);
					}
					
					SmartWatch watch = new SmartWatch();
					watch.setAccessories(accessories);
					watch.setImage(rs.getString("pimage"));
					watch.setName(rs.getString("pname"));
					watch.setId(rs.getInt("pid")+"");
					watch.setPrice((float)rs.getDouble("pprice"));
					watch.setColor(rs.getString("pcolor"));
					watch.setCompany(rs.getString("pcompany"));
					watch.setDescription(rs.getString("pdescription"));
					watch.setCondition(rs.getString("pcondition"));
					watch.setRetailer(rs.getString("rname"));
					
					watches.add(watch);
				}
				else if(category.equals("fwatches"))
				{
					PreparedStatement ps1 =  con.prepareStatement(q1);
					ps1.setInt(1,rs.getInt("pid"));
					ResultSet rs1 = ps1.executeQuery();
					ArrayList<Accessory> accessories = new ArrayList<Accessory>();
					while(rs1.next())
					{
						Accessory accessory = new Accessory();
						accessory.setName(rs1.getString("aname"));
						accessory.setCondition(rs1.getString("acondition"));
						accessory.setImage(rs1.getString("aimage"));
						accessory.setPrice((float)rs1.getDouble("aprice"));
						accessories.add(accessory);
					}
					
					FitnessWatch fwatch = new FitnessWatch();
					fwatch.setAccessories(accessories);
					fwatch.setImage(rs.getString("pimage"));
					fwatch.setName(rs.getString("pname"));
					fwatch.setId(rs.getInt("pid")+"");
					fwatch.setPrice((float)rs.getDouble("pprice"));
					fwatch.setColor(rs.getString("pcolor"));
					fwatch.setCompany(rs.getString("pcompany"));
					fwatch.setDescription(rs.getString("pdescription"));
					fwatch.setCondition(rs.getString("pcondition"));
					fwatch.setRetailer(rs.getString("rname"));
					
					fwatches.add(fwatch);
				}
				else if(category.equals("speakers"))
				{
					PreparedStatement ps1 =  con.prepareStatement(q1);
					ps1.setInt(1,rs.getInt("pid"));
					ResultSet rs1 = ps1.executeQuery();
					ArrayList<Accessory> accessories = new ArrayList<Accessory>();
					while(rs1.next())
					{
						Accessory accessory = new Accessory();
						accessory.setName(rs1.getString("aname"));
						accessory.setCondition(rs1.getString("acondition"));
						accessory.setImage(rs1.getString("aimage"));
						accessory.setPrice((float)rs1.getDouble("aprice"));
						accessories.add(accessory);
					}
					
					SoundSystem speaker = new SoundSystem();
					speaker.setAccessories(accessories);
					speaker.setImage(rs.getString("pimage"));
					speaker.setName(rs.getString("pname"));
					speaker.setId(rs.getInt("pid")+"");
					speaker.setPrice((float)rs.getDouble("pprice"));
					speaker.setColor(rs.getString("pcolor"));
					speaker.setCompany(rs.getString("pcompany"));
					speaker.setDescription(rs.getString("pdescription"));
					speaker.setCondition(rs.getString("pcondition"));
					speaker.setRetailer(rs.getString("rname"));
					
					speakers.add(speaker);
				}
				else if(category.equals("televisions"))
				{
					PreparedStatement ps1 =  con.prepareStatement(q1);
					ps1.setInt(1,rs.getInt("pid"));
					ResultSet rs1 = ps1.executeQuery();
					ArrayList<Accessory> accessories = new ArrayList<Accessory>();
					while(rs1.next())
					{
						Accessory accessory = new Accessory();
						accessory.setName(rs1.getString("aname"));
						accessory.setCondition(rs1.getString("acondition"));
						accessory.setImage(rs1.getString("aimage"));
						accessory.setPrice((float)rs1.getDouble("aprice"));
						accessories.add(accessory);
					}
					
					Television television = new Television();
					television.setAccessories(accessories);
					television.setImage(rs.getString("pimage"));
					television.setName(rs.getString("pname"));
					television.setId(rs.getInt("pid")+"");
					television.setPrice((float)rs.getDouble("pprice"));
					television.setColor(rs.getString("pcolor"));
					television.setCompany(rs.getString("pcompany"));
					television.setDescription(rs.getString("pdescription"));
					television.setCondition(rs.getString("pcondition"));
					television.setRetailer(rs.getString("rname"));
					
					televisions.add(television);
				}
				else if(category.equals("headphones"))
				{
					PreparedStatement ps1 =  con.prepareStatement(q1);
					ps1.setInt(1,rs.getInt("pid"));
					ResultSet rs1 = ps1.executeQuery();
					ArrayList<Accessory> accessories = new ArrayList<Accessory>();
					while(rs1.next())
					{
						Accessory accessory = new Accessory();
						accessory.setName(rs1.getString("aname"));
						accessory.setCondition(rs1.getString("acondition"));
						accessory.setImage(rs1.getString("aimage"));
						accessory.setPrice((float)rs1.getDouble("aprice"));
						accessories.add(accessory);
					}
					
					HeadPhone headphone = new HeadPhone();
					headphone.setAccessories(accessories);
					headphone.setImage(rs.getString("pimage"));
					headphone.setName(rs.getString("pname"));
					headphone.setId(rs.getInt("pid")+"");
					headphone.setPrice((float)rs.getDouble("pprice"));
					headphone.setColor(rs.getString("pcolor"));
					headphone.setCompany(rs.getString("pcompany"));
					headphone.setDescription(rs.getString("pdescription"));
					headphone.setCondition(rs.getString("pcondition"));
					headphone.setRetailer(rs.getString("rname"));
					
					headphones.add(headphone);
				}
				else if(category.equals("phones"))
				{
					PreparedStatement ps1 =  con.prepareStatement(q1);
					ps1.setInt(1,rs.getInt("pid"));
					ResultSet rs1 = ps1.executeQuery();
					ArrayList<Accessory> accessories = new ArrayList<Accessory>();
					while(rs1.next())
					{
						Accessory accessory = new Accessory();
						accessory.setName(rs1.getString("aname"));
						accessory.setCondition(rs1.getString("acondition"));
						accessory.setImage(rs1.getString("aimage"));
						accessory.setPrice((float)rs1.getDouble("aprice"));
						accessories.add(accessory);
					}
					
					Phone phone = new Phone();
					phone.setAccessories(accessories);
					phone.setImage(rs.getString("pimage"));
					phone.setName(rs.getString("pname"));
					phone.setId(rs.getInt("pid")+"");
					phone.setPrice((float)rs.getDouble("pprice"));
					phone.setColor(rs.getString("pcolor"));
					phone.setCompany(rs.getString("pcompany"));
					phone.setDescription(rs.getString("pdescription"));
					phone.setCondition(rs.getString("pcondition"));
					phone.setRetailer(rs.getString("rname"));
					
					phones.add(phone);
				}
				else if(category.equals("voiceassistants"))
				{
					PreparedStatement ps1 =  con.prepareStatement(q1);
					ps1.setInt(1,rs.getInt("pid"));
					ResultSet rs1 = ps1.executeQuery();
					ArrayList<Accessory> accessories = new ArrayList<Accessory>();
					while(rs1.next())
					{
						Accessory accessory = new Accessory();
						accessory.setName(rs1.getString("aname"));
						accessory.setCondition(rs1.getString("acondition"));
						accessory.setImage(rs1.getString("aimage"));
						accessory.setPrice((float)rs1.getDouble("aprice"));
						accessories.add(accessory);
					}
					
					VoiceAssistant voiceassistant = new VoiceAssistant();
					voiceassistant.setAccessories(accessories);
					voiceassistant.setImage(rs.getString("pimage"));
					voiceassistant.setName(rs.getString("pname"));
					voiceassistant.setId(rs.getInt("pid")+"");
					voiceassistant.setPrice((float)rs.getDouble("pprice"));
					voiceassistant.setColor(rs.getString("pcolor"));
					voiceassistant.setCompany(rs.getString("pcompany"));
					voiceassistant.setDescription(rs.getString("pdescription"));
					voiceassistant.setCondition(rs.getString("pcondition"));
					voiceassistant.setRetailer(rs.getString("rname"));
					
					voiceassistants.add(voiceassistant);
				}
				else if(category.equals("laptops"))
				{
					PreparedStatement ps1 =  con.prepareStatement(q1);
					ps1.setInt(1,rs.getInt("pid"));
					ResultSet rs1 = ps1.executeQuery();
					ArrayList<Accessory> accessories = new ArrayList<Accessory>();
					while(rs1.next())
					{
						Accessory accessory = new Accessory();
						accessory.setName(rs1.getString("aname"));
						accessory.setCondition(rs1.getString("acondition"));
						accessory.setImage(rs1.getString("aimage"));
						accessory.setPrice((float)rs1.getDouble("aprice"));
						accessories.add(accessory);
					}
					
					Laptop laptop = new Laptop();
					laptop.setAccessories(accessories);
					laptop.setImage(rs.getString("pimage"));
					laptop.setName(rs.getString("pname"));
					laptop.setId(rs.getInt("pid")+"");
					laptop.setPrice((float)rs.getDouble("pprice"));
					laptop.setColor(rs.getString("pcolor"));
					laptop.setCompany(rs.getString("pcompany"));
					laptop.setDescription(rs.getString("pdescription"));
					laptop.setCondition(rs.getString("pcondition"));
					laptop.setRetailer(rs.getString("rname"));
					
					laptops.add(laptop);
				}
				products.put("fwatches",fwatches);
				products.put("watches",watches);
				products.put("voiceassistants",voiceassistants);
				products.put("speakers",speakers);
				products.put("televisions",televisions);
				products.put("laptops",laptops);
				products.put("headphones",headphones);
				products.put("phones",phones);	
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return products;
	}
}