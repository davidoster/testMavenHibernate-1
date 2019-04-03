<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>List of Products</title>

	<style>
		tr:first-child{
			font-weight: bold;
			background-color: #C6C9C4;
		}
	</style>

</head>


<body>
	<h2>List of Products</h2>	
	<table>
		<tr>
			<td>Id</td><td>Name</td><td>Price</td><td></td>
		</tr>
		<c:forEach items="${products}" var="product">
			<tr>
			<td>${product.id}</td>
			<td>${product.name}</td>
			<td>${product.price}</td>
			<td><a href="<c:url value='/edit-${product.id}-product' />">${product.id}</a></td>
			<td><a href="<c:url value='/delete-${product.id}-product' />">delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<br/>
	<a href="<c:url value='/new' />">Add New Product</a>
</body>
</html>