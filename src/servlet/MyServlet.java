package servlet;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.CustomerBean;
import dao.CustomerDao;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
//		response.setContentType("text/html");
//		PrintWriter out = response.getWriter();
		
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
//			          HttpSession session = request.getSession(true);	    
//			          session.setAttribute("currentSessionUser",cust);
//			          response.sendRedirect("customerHome.jsp"); 
			    	  request.setAttribute("retailer_id", cust.getRetailer_id()); 
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
