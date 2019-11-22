<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Hi ${cust_name} 
<form method="get" action="MyServlet">
<input type="hidden" name="form" value="buySTB"></input>
<div>
<p><strong>Select Billing Type</strong></p>
<input type="radio" name="billType" value="Prepaid"<c:if test="${billType == 'Prepaid'}"><c:out value = "checked=true"/></c:if>>Prepaid</input>
<input type="radio" name="billType" value="Postpaid"<c:if test="${billType == 'Postpaid'}"><c:out value = "checked=true"/></c:if>>Postpaid</input>
</div>
<div>
<p><strong>Select STB Type</strong></p>
<select name="stbType">
<option value="Select">Select</option>
<option value="Standard"<c:if test="${stbType == 'Standard'}"><c:out value = "selected=selected"/></c:if>>Standard</option>
<option value="HD"<c:if test="${stbType == 'HD'}"><c:out value = "selected=selected"/></c:if>>HD</option>
<option value="HD+"<c:if test="${stbType == 'HD+'}"><c:out value = "selected=selected"/></c:if>>HD+</option>
<option value="IPTV"<c:if test="${stbType == 'IPTV'}"><c:out value = "selected=selected"/></c:if>>IPTV</option>
</select>
</div><br>
<input name="submit" type="submit" value="Search"/>
<input name="submit" type ="submit" value="Buy"/><br><br>
${message} <br>
	<c:if test="${inputDisplay == 1}">
    	Details of your STB:<br>
		STB type: ${stbType}<br>
		Features: ${features}<br>
		Dimensions: ${dimensions}<br>
		STB price: ${price}<br>
		Installation Charge: ${installationCharge}<br>
		Upgradation Charge: ${upgradationCharge}<br>
		Discount: ${discount}<br>
		Billing Type: ${billType}<br>
	</c:if>

</body>
</html>