<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>

<head>
<meta charset="ISO-8859-1">
<title>Contacts</title>
</head>

<body>
	<h1 align="center">Phone Book</h1>
	<hr />
	<div id="container" style="width: 100%;">
		<div id="left" style="float: left; width: 50%;">

			<a href="/test/addContact/">Add New Contact</a>

			<table border="2" width="70%" cellpadding="2">
				<tr>
					<th>Name</th>
					<th>Phone</th>
					<th>Email</th>
					<th>Edit</th>
					<th>Delete</th>
				</tr>
				<c:forEach var="c" items="${list}">
					<tr>
						<td>${c.name}</td>
						<td>${c.phoneNumber}</td>
						<td>${c.email}</td>
						<td><a href="/test/editContact/${c.id}">Edit</a></td>
						<td><a href="/test/deleteContact/${c.id}">Delete</a></td>
					</tr>
				</c:forEach>
			</table>

		</div>
	</div>
</body>

</html>