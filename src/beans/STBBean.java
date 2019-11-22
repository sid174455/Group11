package beans;

public class STBBean {
	
	private String billType;
	private String stbType;
	private int macID;
	private int serialNumber;
	private int price;
	private int installationCharge;
	private int deposit;
	private int discount;
	private String features;
	private String dimensions;
	private int upgradationCharge;
	
	
	
	public String getBillType() {
		return billType;
	}
	public void setBillType(String billType) {
		this.billType = billType;
	}
	public String getStbType() {
		return stbType;
	}
	public void setStbType(String stbType) {
		this.stbType = stbType;
	}
	public int getMacID() {
		return macID;
	}
	public void setMacID(int macID) {
		this.macID = macID;
	}
	public int getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	public String getFeatures() {
		return features;
	}
	public void setFeatures(String features) {
		this.features = features;
	}
	public String getDimensions() {
		return dimensions;
	}
	public void setDimensions(String dimensions) {
		this.dimensions = dimensions;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getInstallationCharge() {
		return installationCharge;
	}
	public void setInstallationCharge(int installationCharge) {
		this.installationCharge = installationCharge;
	}
	public int getDeposit() {
		return deposit;
	}
	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public int getUpgradationCharge() {
		return upgradationCharge;
	}
	public void setUpgradationCharge(int upgradationCharge) {
		this.upgradationCharge = upgradationCharge;
	}
	
	
	
	
}
