<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="beans.CustomerBean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Homepage</title>
</head>
<body>
<center>
            <%--
            <% CustomerBean currentUser = (CustomerBean)(session.getAttribute("currentSessionUser"));%>
            Welcome 
            <%= currentUser.getRetailer_id() %>
            --%>
            Welcome ${retailer_id}
            
</center>
<form method="get" action="MyServlet">
<div>
<p><strong>Select Billing Type</strong></p>
<input type="radio" name="pre">Prepaid</input>
<input type="radio" name="post">Postpaid</input>
</div>
<div>
<p><strong>Select STB Type</strong></p>
<select>
<option value="Select">Select</option>
<option value="type1">Standard</option>
<option value="type2">HD</option>
<option value="type3">HD+</option>
<option value="type4">IPTV</option>
</select>
</div>
<input type ="submit" value="Submit"/>
</form>

</body>
</html>