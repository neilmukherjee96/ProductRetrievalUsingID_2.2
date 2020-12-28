<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import ="com.service.Record" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Find Product</title>
</head>
<body>
	<div>
	<%
	Record r=new Record();
	int total=r.getRecords();
	
	%>
	
	<div>
		<h1>Search Product</h1>
	</div>
	<div>
	<p1>Please use the form below to search our catalog.<br> Our list of item ranges from number  1 to <%=total %>	 </p1>
	</div>

		<form action="Retrieval" method="post">
			Enter a product Id<input type="text" name="product_id"><br>
			<input type="submit">
		</form>
	</div>
</body>
</html>