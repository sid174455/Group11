package beans;

public class CustomerBean {
	
	private String username;
    private String password;
    private String retailer_id;
    public boolean valid;
    
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public String getRetailer_id() {
		return retailer_id;
	}
	public void setRetailer_id(String retailer_id) {
		this.retailer_id = retailer_id;
	}

}
