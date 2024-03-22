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
<title>books</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
</head>
<body>
	<h1> Welcome <c:out value="${user.userName}" />  </h1>	
	<div class="row gx-5">
	  <div class="col">
	    <div class="p-3 border bg-light">
	    	<h1>Add a book : </h1>
		<form:form action="/add" method="post" modelAttribute="book" >
			<div class="mb-3">
			  <form:label path="title" class="form-label">Title :</form:label>
			  <form:errors class="text-danger" path="title" /> 
			  <form:input type="text" class="form-control" path="title" />
			</div>
			<div class="mb-3">
			  <form:label path="description" class="form-label"> Description :</form:label>
			  <form:errors class="text-danger" path="description" /> 
			  <form:textarea class="form-control" path="description" rows="5"></form:textarea>
			</div>		
			<div class="mb-3">
			  <form:errors class="text-danger" path="creator" /> 
			  <form:input type="hidden" class="form-control" path="creator" value="${user.id}" />
			</div>
			<div class="mb-3">
			  <form:errors class="text-danger" path="favouredBy" /> 
			  <form:input type="hidden" class="form-control" path="favouredBy" value="${user.id}" />
			</div>
			<input type="submit" class="btn btn-dark" value="add new book"/>
		</form:form>
	    </div>
	  </div>
	  <div class="col">
	    <div class="p-3 border bg-light">
	    	<h1> All Books :</h1>
	    <div class="d-flex flex-column bd-highlight" > 
		<c:forEach var="book" items="${books}" > 
		<div class="p-2 bd-highlight" >
			<a href="/books/${book.id}"> <c:out value="${book.title}" />  </a>
			<p> Added by <c:out value="${book.creator.userName }" /> </p>
			<c:if test="${book.favouredBy.contains(user)}">
			<p>You have favoured this book </p>
			</c:if>
			<c:if test="${!book.favouredBy.contains(user)}">
			<a href="/favour/${book.id}"> Add to favorites  </a>
			</c:if>
		</div>
		</c:forEach>
		</div>
	  </div>
	</div>
	</div>
	
	<a class="btn btn-primary" href="/logout" role="button">Logout</a>
</body>
</html>