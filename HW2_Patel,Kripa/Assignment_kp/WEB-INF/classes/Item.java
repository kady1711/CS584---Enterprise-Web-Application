import java.util.*;

public class Item extends Object implements java.io.Serializable  {
	ArrayList<Accessory> accessories;
	String id;
	String retailer;
    String image;
    String name;
    float price;
	String description;
    String condition;
    String color;
	String company;
	String category;
	
    public Item(){
		accessories=new ArrayList<Accessory>();
    }
	void setAccessories(ArrayList<Accessory> accessories) {
			this.accessories = accessories;
		}

	public ArrayList<Accessory> getAccessories() {
			return accessories;
		}


	void setId(String id) {
		this.id = id;
	}

	public String getId() {
			return id;
	}
	
	void setCategory(String category) {
		this.category = category;
	}

	public String getCategory() {
			return category;
	}

	void setRetailer(String retailer) {
		this.retailer = retailer;
	}

	public String getRetailer() {
			return retailer;
		}

	void setImage(String image) {
		this.image = image;
	}

	public String getImage() {
			return image;
		}

	void setName(String name) {
		this.name = name;
	}

	public String getName() {
			return name;
		}

	void setCompany(String company) {
		this.company = company;
	}

	public String getCompany() {
			return company;
		}

	void setCondition(String condition) {
		this.condition = condition;
	}

	public String getCondition() {
			return condition;
		}

	void setColor(String color) {
		this.color = color;
	}

	public String getColor() {
			return color;
		}

	void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
			return description;
		}

	void setPrice(float price) {
		this.price = price;
	}

	public float getPrice() {
			return price;
		}

	
}
