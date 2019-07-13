<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CRM</title>
<link type="css/text" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css ">
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>CRM - Customer Relationship Manager</h2>
		</div>
	</div>
	
	<div id="container">
		<div id="content">
		<!--put new button - for add new customer  -->
		
		<input type="button" value="Add Customer" onclick="window.location.href='showFromForAdd'; return false;"
			   class="add-button"/>
			   
		<!--  add a search box -->
			<form:form action="search" method="GET">
				Search customer: <input type="text" name="theSearchName" />
				
				<input type="submit" value="Search" class="add-button" />
			</form:form>
			
			<!--  add our html table here -->
		
		<!--  -->
			<table>
				<tr>
					<th>Index</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>
				<c:forEach var="customer" items="${customers}" varStatus="status">
				
				<!--Construct the update link with the customer id  -->
				
				<c:url var="updateLink" value="/customer/showFormForUpdate">
				
					<c:param name="customerId" value="${customer.id}"/>
					
				</c:url>
				
				<c:url var="deleteLink" value="/customer/showFormForDelete">
				
					<c:param name="customerId" value="${customer.id}"/>
					
				</c:url>
				
				
					<tr>
						<td>${status.index + 1 }</td>
						<td>${customer.firstName}</td>
						<td>${customer.lastName}</td>
						<td>${customer.email}</td>
						<td>
							<a href="${updateLink}">Update</a>
							|
							<a href="${deleteLink}"
							onclick="if(!(confirm('Are you sure you want to delete this Customer')))return false">Delete</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>