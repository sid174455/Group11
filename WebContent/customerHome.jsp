<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="beans.CustomerBean" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" type="text/css" href="css/style.css"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
td
{
    padding:0 20px 0 0;
}
</style>
<title>Customer Homepage</title>
</head>
<body>
<center>
            <%--
            <% CustomerBean currentUser = (CustomerBean)(session.getAttribute("currentSessionUser"));%>
            Welcome 
            <%= currentUser.getRetailer_id() %>
            --%>
            Welcome ${c_name}
</center>
STB you own:<br><br>
<table>
	<tr>
		<td> STB Serial Number</td>
		<td> STB Type</td>
		<td>Packages</td>
	</tr>
	<c:forEach items="${stb_ser_id}" varStatus="item" var="stb">
   <tr>
           <td>
           	<c:out value="${stb}" />
           </td>
           <td>
           	<c:out value="${stb_type[item.index]}" />
           </td>
           <td>
           <input type="hidden" name="form" value="addNewPackage">
           <input type="button" value="Add new Package"> 
           </td>                   
  </tr>
  </c:forEach>
</table><br><br><br>
Click below to buy new STB:<br>
<form method="get" action="MyServlet"> 
<input type="hidden" name="form" value="searchNewSTB">
<input type="submit" value="Buy new STB"> 
</form>
</body>
</html>