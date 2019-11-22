package dao;

import java.text.*;
import java.util.*;

import beans.*;
import servlet.MyServlet;
import utils.*;

import java.sql.*;

public class CustomerDao {
	
    static ResultSet rs = null;   
    static PreparedStatement st = null;
    static PreparedStatement st1 = null;
    static PreparedStatement st2 = null;
    static PreparedStatement st3 = null;
    static PreparedStatement st4 = null;
    
    public static CustomerBean login(CustomerBean bean) {
    	
    	String username = bean.getUsername();    
        String password = bean.getPassword();
        
    try 
    {
       st = MyServlet.conn.prepareStatement("select * from customer where c_id=? and password=?");
       st.setString(1,username); 
       st.setString(2,password);
       
//       st1 = MyServlet.conn.prepareStatement("select stb.stb_ser_id,stb_type from retailer inner join stb on retailer.stb_ser_id=stb.stb_ser_id where c_id=?");
//       st1.setString(1,username);
       
       //Execute the query
     	rs=st.executeQuery();
//     	rs1=st1.executeQuery();
       
	   if(!rs.next()) {
		   bean.setValid(false);
	   }
	   else{
		   bean.setC_name(rs.getString("c_name"));
           bean.setValid(true);
       }
//	   List stb_ser_id = new ArrayList<>();
//	   List stb_type = new ArrayList<>();
//	   while(rs1.next()) {
//		   stb_type.add(rs1.getString("stb_type"));
//		   stb_ser_id.add(rs1.getString("stb_ser_id"));
//	   }
//	   bean.setStb_type(stb_type);
//	   bean.setStb_ser_id(stb_ser_id);
    } 
    catch (Exception ex) 
    {
    	ex.printStackTrace();
    } 
	    
    return bean;
	
    }
    
public static CustomerBean listSTB(CustomerBean bean) {
    	
    	String username = bean.getUsername();
        
    try 
    {
       st = MyServlet.conn.prepareStatement("select stb.stb_ser_id,stb_type from retailer inner join stb on retailer.stb_ser_id=stb.stb_ser_id where c_id=?");
       st.setString(1,username);
       
       //Execute the query
     	rs=st.executeQuery();
       
	   List stb_ser_id = new ArrayList<>();
	   List stb_type = new ArrayList<>();
	   while(rs.next()) {
		   stb_type.add(rs.getString("stb_type"));
		   stb_ser_id.add(rs.getString("stb_ser_id"));
	   }
	   bean.setStb_type(stb_type);
	   bean.setStb_ser_id(stb_ser_id);
    } 
    catch (Exception ex) 
    {
    	ex.printStackTrace();
    } 
	    
    return bean;
	
    }
    
public static STBBean searchSTB(STBBean bean) {
    	
    	String stbType = bean.getStbType();
    	String billType = bean.getBillType();
    try 
    {
       st = MyServlet.conn.prepareStatement("select * from stb_type where stb_type=?");
       st.setString(1,stbType); 
       
       //Execute the query
     	rs=st.executeQuery();
     	
     	while(rs.next()) {
	     	bean.setDeposit(rs.getInt("stb_deposit"));
	     	bean.setDiscount(rs.getInt("stb_discount"));
	     	bean.setInstallationCharge(rs.getInt("stb_inst_price"));
	     	bean.setPrice(rs.getInt("stb_price"));
	     	bean.setDimensions(rs.getString("stb_dimensions"));
	     	bean.setFeatures(rs.getString("stb_features"));
	     	bean.setUpgradationCharge(rs.getInt("upgradation_charges"));
     	}
    } 
    catch (Exception ex) 
    {
    	ex.printStackTrace();
    } 
	    
    return bean;
	
    }

public static STBBean buySTB(STBBean bean, String username, String c_name) {
	
	String stbType = bean.getStbType();
	String billType = bean.getBillType();
try 
{
   st = MyServlet.conn.prepareStatement("select * from stb inner join stb_type on stb.stb_type=stb_type.stb_type where stb_ser_id in(select stb_ser_id from retailer where retailer_id in (select retailer_id from customer where c_id=?)) and stb.stb_type=? and status is NULL and rownum<=1");
   st.setString(2,stbType); 
   st.setInt(1,Integer.parseInt(username)); 
   
   //Execute the query
 	rs=st.executeQuery();
 	
 	while(rs.next()) {
 	 	bean.setStbType(rs.getString("stb_type"));
 	 	bean.setSerialNumber(rs.getInt("stb_ser_id"));
 	 	System.out.println(rs.getInt("stb_ser_id"));
 	 	int stb_ser_id=rs.getInt("stb_ser_id");
 	 	st1 = MyServlet.conn.prepareStatement("update retailer set c_id=?,c_name=? where stb_ser_id=?");
 	 	st1.setInt(1,Integer.parseInt(username));
 	 	st1.setString(2,c_name); 
 	 	st1.setInt(3,stb_ser_id); 
 	 	st2 = MyServlet.conn.prepareStatement("update stb set status='Assigned to customer' where stb_ser_id=?");
 	 	st2.setInt(1,stb_ser_id);
 	 	st3=MyServlet.conn.prepareStatement("select * from stb_type inner join stb on stb.stb_type=stb_type.stb_type where stb.stb_type=?");
 	 	st3.setString(1, stbType);
 	 	int stb_mac_id = rs.getInt("stb_mac_id");
 	 	int stb_price = rs.getInt("stb_price");
 	 	int stb_inst_price = rs.getInt("stb_inst_price");
 	 	int stb_deposit = rs.getInt("stb_deposit");
 	 	int stb_discount = rs.getInt("stb_discount");
 	 	double tax =0.12*(stb_price+stb_inst_price+stb_deposit-stb_discount);
 	 	int amount_payable=stb_price+stb_inst_price+stb_deposit-stb_discount;
 	 	
 	 	System.out.println(stb_mac_id+" "+stb_price+" "+stb_inst_price+" "+stb_deposit+" "+stb_discount+" "+tax+" "+amount_payable+" ");
 	 	
 	 	st4 = MyServlet.conn.prepareStatement("insert into stb_purchased values(?,?,?,?,?,?,?,?,?,?)");
 	 	st4.setString(1, c_name);
 	 	st4.setString(2, stbType);
 	 	st4.setInt(3, stb_mac_id);
 	 	st4.setInt(4, stb_ser_id);
 	 	st4.setInt(5, stb_price);
 	 	st4.setInt(6, stb_inst_price);
 	 	st4.setInt(7, stb_deposit);
 	 	st4.setInt(8, stb_discount);
 	 	st4.setDouble(9, tax);
 	 	st4.setInt(10, amount_payable);
 	 	
 	 	st1.executeQuery();
 	 	st2.executeQuery();
 	 	st3.executeQuery();
 	 	st4.executeQuery();
 	}
 	
} 
catch (Exception ex) 
{
	ex.printStackTrace();
} 
    
return bean;

}

}