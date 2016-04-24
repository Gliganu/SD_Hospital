<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>




<h2 class="error">${errorMsg}</h2>
<h2 class="valid">${successMsg}</h2>


<sec:authorize access="!isAuthenticated()">

	<h2>Welcome !</h2>

</sec:authorize>

<h2>View All Patients</h2>

<sec:authorize access="hasRole('ROLE_ADMIN')">
	<p>
			<a href=<c:url value="/newAccount"/>>Create new account</a>
	</p>
	<p>
			<a href=<c:url value="/allUsers"/>>See all accounts</a>
	</p>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_SECRETARY')">
	<p>
		<a href=<c:url value="/addPatient "/>>Add Patient </a>
	</p>
</sec:authorize>


<sec:authorize access="isAuthenticated()">



	<table class="allCenter" border=1 width=100%>
		<tr>
			<th>Name</th>
			<th>CNP</th>
			<th>Date of birth</th>
			<th>Address</th>
			<sec:authorize access="hasRole('ROLE_SECRETARY')">
				<th>Update</th>
				<th>Delete</th>
			</sec:authorize>
			<sec:authorize access="hasAnyRole('ROLE_SECRETARY','ROLE_DOCTOR')">
				<th>Consultations</th>
			</sec:authorize>
		</tr>
	
		<c:forEach var="patient" items="${patients}">
			<tr>
				<td>${patient.name}</td>
				<td>${patient.personalNumericCode}</td>
				<td>${patient.dateOfBirth}</td>
				<td>${patient.address}</td>
				<sec:authorize access="hasRole('ROLE_SECRETARY')">
					<td><a href=<c:url value="/updatePatient?id=${patient.personalNumericCode}"/>>Update</a></td>
					<td><a href=<c:url value="/deletePatient?id=${patient.personalNumericCode}"/>>Delete</a></td>
				</sec:authorize>
				<sec:authorize access="hasAnyRole('ROLE_SECRETARY','ROLE_DOCTOR')">
					<td><a href=<c:url value="/consultations?id=${patient.personalNumericCode}"/>>Consultations</a></td>
				</sec:authorize>
			</tr>
		</c:forEach>
	</table>

</sec:authorize>



