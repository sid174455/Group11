package servlet;

import java.io.*;
import java.sql.Connection;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.CustomerBean;
import beans.STBBean;
import dao.CustomerDao;
import utils.ConnectionManager;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Connection conn = null;
	@Override
	public void init() throws ServletException {
		conn = ConnectionManager.getConnection();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String s = request.getParameter("form").toString();
		
	    if(s.equals("login")) {
			try
			{	 
				 CustomerBean cust = new CustomerBean();
				 cust.setUsername(request.getParameter("uname"));
				 cust.setPassword(request.getParameter("pwd"));   
			     cust = CustomerDao.login(cust);
			     if (cust.isValid())
			     {
			          HttpSession session = request.getSession();	    
			          session.setAttribute("currentSessionUser",cust);
			          cust=CustomerDao.listSTB(cust);
			          request.setAttribute("c_name", cust.getC_name());
			    	  List stb_type = cust.getStb_type();
			    	  List stb_ser_id= cust.getStb_ser_id();
			    	  request.setAttribute("stb_ser_id", stb_ser_id);
			    	  request.setAttribute("stb_type", stb_type);
			    	  request.getRequestDispatcher("customerHome.jsp").forward(request, response);
			          
			     }
				        
			     else {
			    	  request.setAttribute("message", "Please input valid username and password."); 
			          request.getRequestDispatcher("/login.jsp").forward(request, response);
			          
			     }
			}
					
					
			catch (Exception ex) 	    
			{
				ex.printStackTrace(); 
			}
		}
		else if(s.equals("searchNewSTB")) {
			CustomerBean cust = (CustomerBean) request.getSession().getAttribute("currentSessionUser");
	        request.setAttribute("cust_name",cust.getC_name());
			request.getRequestDispatcher("PurchaseSetTopBox.jsp").forward(request, response);
		}
		else if(s.equals("buySTB")) {
			if(request.getParameter("submit").equals("Search")) {
				STBBean currentSTB = new STBBean();
				currentSTB.setStbType(request.getParameter("stbType"));
				currentSTB.setBillType(request.getParameter("billType"));
				currentSTB = CustomerDao.searchSTB(currentSTB);
				CustomerBean cust = (CustomerBean) request.getSession().getAttribute("currentSessionUser");
				request.setAttribute("cust_name",cust.getC_name());
				request.setAttribute("stbType", currentSTB.getStbType());
				request.setAttribute("billType", currentSTB.getBillType());
				request.setAttribute("inputDisplay", 1);
				request.setAttribute("features", currentSTB.getFeatures());
				request.setAttribute("dimensions", currentSTB.getDimensions());
				request.setAttribute("price", currentSTB.getPrice());
				request.setAttribute("installationCharge", currentSTB.getInstallationCharge());
				request.setAttribute("upgradationCharge", currentSTB.getUpgradationCharge());
				request.setAttribute("discount", currentSTB.getDiscount());
				request.setAttribute("billType", currentSTB.getBillType());
				request.getRequestDispatcher("PurchaseSetTopBox.jsp").forward(request, response);
			} 			
			else if(request.getParameter("submit").equals("Buy")){
				STBBean currentSTB = new STBBean();
				currentSTB.setStbType(request.getParameter("stbType"));
				currentSTB.setBillType(request.getParameter("billType"));
				CustomerBean cust = (CustomerBean) request.getSession().getAttribute("currentSessionUser");	    
				currentSTB = CustomerDao.buySTB(currentSTB,cust.getUsername(),cust.getC_name());
				if(currentSTB.getSerialNumber()==0)
				{	
					request.setAttribute("cust_name",cust.getC_name());
					request.setAttribute("stbType", currentSTB.getStbType());
					request.setAttribute("billType", currentSTB.getBillType());
					request.setAttribute("message", "STB of type "+currentSTB.getStbType()+" is not available in your retailer's inventory. Please select another STB.");
					request.getRequestDispatcher("PurchaseSetTopBox.jsp").forward(request, response);
				}
				else {
					request.setAttribute("stb_ser_id", currentSTB.getSerialNumber());
					request.setAttribute("stb_type", currentSTB.getStbType());
					request.getRequestDispatcher("BuySTB.jsp").forward(request, response);
				}
			}
		}
		else if(s.equals("custHomepage")) {
			CustomerBean cust = (CustomerBean) request.getSession().getAttribute("currentSessionUser");
			cust=CustomerDao.listSTB(cust);
	        request.setAttribute("c_name", cust.getC_name());
	    	List stb_type = cust.getStb_type();
	    	List stb_ser_id= cust.getStb_ser_id();
	    	request.setAttribute("stb_ser_id", stb_ser_id);
	    	request.setAttribute("stb_type", stb_type);
	    	request.getRequestDispatcher("customerHome.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
	}

}
