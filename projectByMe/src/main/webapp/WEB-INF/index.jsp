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
<title>Login And Register</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
	<div class="row"> 
		<div class="col" >
		<h1 class="display-6">Register</h1>
		<form:form action="/register" method="post" modelAttribute="newUser">
		
			<div class="g-col-3 g-start-2"> 
			<form:label path="userName" class="col-sm-2 col-form-label">User Name :</form:label>
		    <div class="col-sm-10">
		      <form:input type="text" class="form-control" path="userName" />
		    </div>
		    <form:errors class="text-danger" path="userName" /> 
		    
		    <form:label path="email" class="col-sm-2 col-form-label">Email :</form:label>
		    <div class="col-sm-10">
		      <form:input type="email" class="form-control" path="email" />
		    </div>
		     <form:errors class="text-danger" path="email" /> 
		    
		    <form:label path="password" class="col-sm-2 col-form-label">Password :</form:label>
		    <form:errors class="text-danger" path="password" /> 
		    <div class="col-sm-10">
		      <form:input type="password" class="form-control" path="password" />
		    </div>
		    
		    <form:label path="confirm" class="col-sm-2 col-form-label">Confirm password: </form:label>
		    <form:errors class="text-danger" path="confirm" /> 
		    <div class="col-sm-10">
		      <form:input type="password" class="form-control" path="confirm" />
		    </div>
		    <input type="submit" class="btn btn-primary" value="sign in" />
		    </div>
		</form:form>
		</div>
		
		<div class="col" >
		<h1 class="display-6">Login</h1>
		<form:form action ="/login" method="post" modelAttribute="newLogin"> 
			<form:label path="email" class="col-sm-2 col-form-label">Email</form:label>
			<form:errors class="text-danger" path="email" /> 
		    <div class="col-sm-10">
		      <form:input type="email" class="form-control" path="email" />
		    </div>
		    <form:label path="password" class="col-sm-2 col-form-label">Password</form:label>
		    <div class="col-sm-10">
		      <form:input type="password" class="form-control" path="password" />
		    </div>
		    <form:errors class="text-danger" path="password" /> 
		     <input type="submit" class="btn btn-primary"  value="login"/>
		</form:form >
		</div>
	</div>
	</div>
</body>
</html>