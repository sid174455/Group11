package beans;

import java.util.*;

public class CustomerBean {
	
	private String username;
    private String password;
    public boolean valid;
    private String c_name;
    private List stb_type;
    private List stb_ser_id;
    
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
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	public List getStb_type() {
		return stb_type;
	}
	public void setStb_type(List stb_type) {
		this.stb_type = stb_type;
	}
	public List getStb_ser_id() {
		return stb_ser_id;
	}
	public void setStb_ser_id(List stb_ser_id) {
		this.stb_ser_id = stb_ser_id;
	}
	
}
