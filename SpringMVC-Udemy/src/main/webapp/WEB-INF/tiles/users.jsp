



<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	
<h2>All users</h2>

<h2 class="error">${errorMsg}</h2>
<h2 class="valid">${successMsg}</h2>

<p>
	<a href=<c:url value="/newAccount"/>>Create new account</a>
</p>
	
<table class="allCenter" border=1 width=100%>
	<tr>
		<th>Username</th>
		<th>Name</th>
		<th>Personal Code</th>
		<th>Address</th>
		<th>Authority</th>
		<th>Edit</th>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
				<th>Delete</th>
		</sec:authorize>
	</tr>

	<c:forEach var="user" items="${allUsers}">
		<tr>
			<td>${user.username}</td>
			<td>${user.name}</td>
			<td>${user.personalCode}</td>
			<td>${user.address}</td>
			<td>${user.authority}</td>
			<td><a href= <c:url value="/updateUserInfo?id=${user.username}"/>>Edit</a></td>
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<td><a href=<c:url value="/deleteUser?id=${user.username}"/>>Delete</a></td>
			</sec:authorize>
		</tr>
	</c:forEach>
</table>




