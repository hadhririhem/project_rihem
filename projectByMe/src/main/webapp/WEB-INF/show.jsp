<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title> Book Details</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
</head>
<body> 
	<div class="row gx-5">
	<h1> Welcome <c:out value="${user.userName}" />  </h1>
	<div class="col">
		<div class="p-3 border bg-light">
		<c:if test="${book.creator.id == user.id }" > 
		<form:form action="/edit/${book.id}" method="put" modelAttribute="book" >
			<div class="mb-3">
				<form:label path="title" class="form-label">Title :</form:label>
				<form:errors class="text-danger" path="title" /> 
				<form:input type="text" class="form-control" path="title" />
			</div>
			<p> Added by : <c:out value="${book.creator.userName}" /> </p>
			<p> Added on :   <c:out value="${book.createdAt}" /> </p>
			<p> Last Updated on :  <c:out value="${book.updatedAt}" /> </p>
			<div class="mb-3">
				<form:label path="description" class="form-label"> Description :</form:label>
				<form:errors class="text-danger" path="description" /> 
				<form:textarea class="form-control" path="description" rows="5"></form:textarea>
			</div>
			<div class="mb-3">
				  <form:errors class="text-danger" path="creator" /> 
				  <form:input type="hidden" class="form-control" path="creator" value="${book.creator.id}" />
			</div>
			<div class="mb-3">
				  <form:errors class="text-danger" path="favouredBy" /> 
				  <form:input type="hidden" class="form-control" path="favouredBy" value="${book.creator.id}" />
			</div>
			<input type="submit" class="btn btn-primary" value="update"/>
		</form:form>
		<form action="/delete/${book.id}" method="post">
			<input type="hidden" name="_method" value="delete">
			<input type="submit" value="Delete" class="btn btn-outline-danger">
		</form>
		</c:if>
		<c:if test="${book.creator.id != user.id }" > 
		<p> <c:out value="${book.title}" /> </p>
		<p> Added by : <c:out value="${book.creator.userName}" /> </p>
		<p> Added on :   <c:out value="${book.createdAt}" /> </p>
		<p> Last Updated on :  <c:out value="${book.updatedAt}" /> </p>
		<p> Description : <c:out value="${book.description}" /> </p>
		</c:if>
		</div>
	</div>
		<div class="col">
			<div class="p-3 border bg-light">
			<h5>People who liked this book : </h5>
			<ul> 
				<c:forEach var="user" items="${book.favouredBy}">
				 <li> <c:out value="${user.userName}" /> </li> 
				</c:forEach>
				<c:if test="${book.favouredBy.contains(user)}" > 
					<a href="/unfavourite/${book.id}"> Un-favourite </a>
				</c:if>
				<c:if test="${!book.favouredBy.contains(user)}" > 
					<a href="/favour/${book.id}"> Add to favourites </a>
				</c:if>
			</ul>
			</div>
		</div>
	</div>
	<a class="btn btn-primary" href="/logout" role="button">Logout</a>
</body>
</html>