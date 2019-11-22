<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
You bought STB with MAC ID ${stb_ser_id} and type ${stb_type} successfully.<br><br>
<form method="get" action="MyServlet">
	<input type="hidden" name="form" value="custHomepage"></input>
	<input type="submit" value="Go to your homepage">
</body>
</html>