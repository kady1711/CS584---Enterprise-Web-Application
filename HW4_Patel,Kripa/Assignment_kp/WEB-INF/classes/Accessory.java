import java.io.*;
public class Accessory implements Serializable{
	String image;
	String condition;
	String name;
	float price;
	
	public Accessory()
	{
		
	}
	
	public Accessory(String name, float price, String condition,String image){
		this.name=name;
		this.condition=condition;
		this.image=image;
		this.price=price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}


}
