<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.spa.dto.Appointment" %>
<%@ include file="/header.jsp" %>
<html>
    <head>
        <title>Appointment List</title>
    </head>
    <body>
	<div class="service-list-container">
	    <h2 style="color: white">Your Appointments</h2>
	    <%
		List<Appointment> appointments = (List<Appointment>) request.getAttribute("appointments");
		if (appointments == null || appointments.isEmpty()) {
	    %>
	    <p>No pending appointments.</p>
	    <% } else { %>
	    <table border="1" class="service-table">
		<tr>
		    <th>Appointment ID</th>
		    <th>Service</th>
		    <th>Date</th>
		    <th>Status</th>
		    <th>Note</th>
		    <th>Actions</th>
		</tr>
		<% for (Appointment appointment : appointments) {%>
		<tr>
		    <td><%= appointment.getAppointmentID()%></td>
		    <td><%= appointment.getServiceName()%></td>
		    <td><%= appointment.getAppointmentDate()%></td>
		    <td><%= appointment.getStatus()%></td>
		    <td><%= appointment.getNote() != null ? appointment.getNote() : ""%></td>
		    <td>
			<form action="${pageContext.request.contextPath}/MainController" method="post">
			    <input type="hidden" name="action" value="CancelAppointment">
			    <input type="hidden" name="appointmentID" value="<%= appointment.getAppointmentID()%>">
			    <input type="submit" value="Cancel" class="delete-btn">
			</form>
		    </td>
		</tr>
		<% } %>
	    </table>
	    <% }%>
	</div>
    </body>
</html>
