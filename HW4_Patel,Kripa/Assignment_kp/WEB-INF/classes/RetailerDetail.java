
public class RetailerDetail implements java.io.Serializable {
	private String retailerId;
	private String retailerName;
	private String retailerCity;
	private String retailerState;
	private String retailerZip;
	
	public RetailerDetail(String retailerZip, String retailerCity,String retailerState, String retailerName, String retailerId) {
		super();
		this.retailerName = retailerName;
		this.retailerState = retailerState;
		this.retailerCity = retailerCity;
		this.retailerId = retailerId;
		this.retailerZip = retailerZip;
	}
	public String getRetailerId() {
		return retailerId;
	}
	public void setRetailerId(String retailerId) {
		this.retailerId = retailerId;
	}
	public String getRetailerCity() {
		return retailerCity;
	}
	public void setRetailerCity(String retailerCity) {
		this.retailerCity = retailerCity;
	}
	public String getRetailerZip() {
		return retailerZip;
	}
	public void setRetailerZip(String retailerZip) {
		this.retailerZip = retailerZip;
	}
	public String getRetailerName() {
		return retailerName;
	}
	public void setRetailerName(String retailerName) {
		this.retailerName = retailerName;
	}
	
	public String getRetailerState() {
		return retailerState;
	}
	public void setRetailerState(String retailerState) {
		this.retailerState = retailerState;
	}
	
	
}
