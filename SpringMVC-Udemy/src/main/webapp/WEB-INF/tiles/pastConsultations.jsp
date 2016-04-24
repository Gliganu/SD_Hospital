<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>




<h2>Past Consultations</h2>

<h2 class="error">${errorMsg}</h2>
<h2 class="valid">${successMsg}</h2>


<table class="allCenter" border=1 width=100%>
	<tr>
		<th>Id</th>
		<th>Patient</th>
		<th>Doctor</th>
		<th>Date</th>
		<th>Length</th>
		<sec:authorize access="hasRole('ROLE_DOCTOR')">
			<th>Doctor Notes</th>
		</sec:authorize>
	</tr>

	<c:forEach var="consult" items="${pastConsults}">
		<tr>
			<td>${consult.id}</td>
			<td>${consult.patient.name}</td>
			<td>${consult.user.name}</td>
			<td>${consult.date}</td>
			<td>${consult.length}</td>
			<sec:authorize access="hasRole('ROLE_DOCTOR')">
				<td><a href=<c:url value="/consultDoctorNotes?id=${consult.id}"/>>Note</a></td>
			</sec:authorize>
		</tr>
	</c:forEach>
</table>




