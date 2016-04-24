\<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<h2>Add consultation</h2>

<h2 class="error">${errorMsg}</h2>
<h2 class="valid">${successMsg}</h2>


<c:url var="ADD_CONSULT_URL" value="/updateConsult"></c:url> 


<form:form id="details" action="${ADD_CONSULT_URL}" method="post"
	commandName="consult">
	<table>
		
		<tr>
			<td><form:input name="patientPersonalNumericCode" path="patientPersonalNumericCode" type="hidden" value="${cnp}"/></td>
		</tr>
		
		<tr>
			<td>Date</td>
			<td><form:input name="date" path="date" type="datetime-local"/></td>
			<td><form:errors path="date" cssClass="error" /></td>
		</tr> 
		
		<tr>
			<td>Length</td>
			<td><form:input name="length" path="length" type="text"/></td>
			<td><form:errors path="length" cssClass="error" /></td>
		</tr> 
		
		<tr>
			<td>Doctor</td>

			<td><form:select name="doctorName" path="doctorName">
					<form:options items="${doctorNames}" />
				</form:select></td>
				
			<td><form:errors path="doctorName" cssClass="error" /></td>

		</tr>
		
		<tr>
			<td><input value="Update consultation info" type="submit" /></td>
		</tr>
		
	</table>
</form:form>

<br/>


<c:url var="QUERY_URL" value="/checkDoctorSchedule"></c:url> 

<form:form id="details" action="${QUERY_URL}" method="post"
	commandName="query">
	<table>
		
		<tr>
			<td><form:input name="patientPersonalNumericCode" path="patientPersonalNumericCode" type="hidden" value="${cnp}"/></td>
		</tr>
		
		<tr>
			<td>Check schedule for: </td>

			<td><form:select name="doctorName" path="doctorName">
					<form:options items="${doctorNames}" />
				</form:select></td>
				
			<td><form:errors path="doctorName" cssClass="error" /></td>

		</tr>
		
		<tr>
			<td><input value="Check" type="submit" /></td>
		</tr>
		
	</table>
</form:form>

<br/>
<br/>
<br/>

<c:if test="${not empty consults}">

	<h2>${selectedDoctor}'s schedule</h2>
	
	<table class="allCenter" border=1 width=100%>
		<tr>
			<th>Id</th>
			<th>Patient</th>
			<th>Doctor</th>
			<th>Date</th>
			<th>Length</th>
		</tr>
	
		<c:forEach var="consult" items="${consults}">
			<tr>
				<td>${consult.id}</td>
				<td>${consult.patient.name}</td>
				<td>${consult.user.name}</td>
				<td>${consult.date}</td>
				<td>${consult.length}</td>
			</tr>
		</c:forEach>
	</table>
	
</c:if>



