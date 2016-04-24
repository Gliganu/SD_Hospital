<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<h2>Update patient info</h2>


<c:url var="ADD_PATIENT_URL" value="/updatePatient"></c:url>


<form:form id="details" action="${ADD_PATIENT_URL}" method="post"
	commandName="patient">
	<table>

		<tr>
			<td>Name:</td>
			<td><form:input name="name" path="name" type="text"/></td>
			<td><form:errors path="name" cssClass="error" /></td>
		</tr>
		
		<tr>
			<td>Personal Numeric Code:</td>
			<td><form:input name="personalNumericCode" path="personalNumericCode" type="text"/></td>
			<td><form:errors path="personalNumericCode" cssClass="error" /></td>
		</tr>
		
		<tr>
			<td>Date of birth:</td>
			<td><form:input name="dateOfBirth" path="dateOfBirth" type="date"/></td>
			<td><form:errors path="dateOfBirth" cssClass="error" /></td>
		</tr> 
		
		<tr>
			<td>Address:</td>
			<td><form:input name="address" path="address" type="text"/></td>
			<td><form:errors path="address" cssClass="error" /></td>
		</tr>
		
		<tr>
			<td><input value="Update Patient Info" type="submit" /></td>
		</tr>
		
	</table>
</form:form>

 <script type="text/javascript">
      $(function() {
          $('#dateOfBirth').datepicker();
      });
  </script>


