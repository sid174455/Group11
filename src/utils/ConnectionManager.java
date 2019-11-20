package utils;

import java.sql.*;


public class ConnectionManager {
	 
	 public static Connection conn=null;
           
     public static Connection getConnection()
     {
        	String driverInfo = "oracle.jdbc.OracleDriver";
        	String url = "jdbc:oracle:thin:@localhost:1521:xe";
        	String name = "system";
    		String pwd = "tcs12345";
    		
        	//Register the driver
    		try {
				Class.forName(driverInfo);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
           
           try
           {            	
        	   conn = DriverManager.getConnection(url,name,pwd); 

           }
           
           catch (SQLException ex)
           {
              ex.printStackTrace();
           }

     return conn;
     }
}
