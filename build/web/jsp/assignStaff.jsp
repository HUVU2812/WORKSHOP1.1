<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@page import="java.util.List" %>
<%@page import="com.spa.dto.Appointment" %>
<%@page import="com.spa.dto.User" %>
<jsp:useBean id="appointments" scope="request" type="java.util.List<com.spa.dto.Appointment>" />
<jsp:useBean id="staffList" scope="request" type="java.util.List<com.spa.dto.User>" />
<%@ include file="/header.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Assign Staff</title>
    </head>
    <body>
	<div class="assign-container">
	    <h2  style="color: white">Appointment List</h2>
	    <form action="MainController" method="POST">
		<table border="1" class="appointment-table">
		    <tr>
			<th>Appointment ID</th>
			<th>User</th>
			<th>Service</th>
			<th>Date</th>
			<th>Status</th>
			<th>Current Staff</th>
			<th>Choose Staff</th>
			<th>Action</th>
		    </tr>
		    <% for (Appointment apt : appointments) {%>
		    <tr>
			<td><%= apt.getAppointmentID()%></td>
			<td>
			<span style="display: flex; align-items: center; gap: 8px;">
			    <span><%= apt.getUserName()%></span>
			    <% if (apt.getIsVIP()) { %>
			    <span style="color: gold; font-weight: bold; background-color: #222; padding: 5px 10px; border-radius: 5px;">
				VIP
			    </span>
			    <% }%>
			</span>
		    </td>
			<td><%= apt.getServiceName()%></td>
			<td><%= apt.getAppointmentDate()%></td>
			<td><%=apt.getStatus()%></td>
			<td>
			    <% if (apt.getStaffName() == null) { %>
			    <span class="no-staff">No staff yet</span>
			    <% } else {%>
			    <%= apt.getStaffName()%>
			    <% }%>
			</td>
			<td>
			    <select name="staffID_<%= apt.getAppointmentID()%>">
				<option value="">Choose Staff</option>
				<% for (User staff : staffList) {%>
				<option value="<%= staff.getUserID()%>"><%= staff.getFullName()%></option>
				<% }%>
			    </select>
			</td>
			<td>
			    <button type="submit" class="assign-btn" onclick="document.getElementById('appointmentID').value = '<%= apt.getAppointmentID()%>';">
				Assign Staff
			    </button>
			</td>
		    </tr>
		    <% }%>
		</table>
		<input type="hidden" name="appointmentID" id="appointmentID">
		<input type="hidden" name="action" value="UpdateStaff">
	    </form>
	</div>
    </body>
</html>
