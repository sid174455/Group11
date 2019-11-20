package dao;

import java.text.*;
import java.util.*;

import beans.*;
import utils.*;

import java.sql.*;



public class CustomerDao {
	
	static Connection conn = null;
    static ResultSet rs = null;  
    static PreparedStatement st = null;
	
    public static CustomerBean login(CustomerBean bean) {
    	
    	String username = bean.getUsername();    
        String password = bean.getPassword();
        
    try 
    {
       //connect to DB 
       conn = ConnectionManager.getConnection();
       st = conn.prepareStatement("select * from customer where username=? and password=?");
       st.setString(1,username); 
       st.setString(2,password);
       
       //Execute the query
     	ResultSet rs=st.executeQuery();
       
	   if(!rs.next()) {
		   bean.setValid(false);
	   }
	   else{
		   bean.setRetailer_id(rs.getString("retailer_id"));
           bean.setValid(true);
       }
    } 

    catch (Exception ex) 
    {
    	ex.printStackTrace();
    } 
	    
    return bean;
	
    }

}