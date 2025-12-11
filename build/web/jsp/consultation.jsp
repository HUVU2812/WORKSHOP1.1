<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.spa.dto.Appointment" %>
<%@ include file="/header.jsp" %>
<html>
    <head>
        <title>Consultation and Service Tracking</title>
    </head>
    <body>
	<div class="service-list-container" >
	    <h2 style="color: white">Consultation and Service Tracking</h2>

	    <table border="1" class="service-table">
		<tr>
		    <th>Appointment ID</th>
		    <th>User</th>
		    <th>Service</th>
		    <th>Date</th>
		    <th>Status</th>
		    <th>Consultation Notes</th>
		    <th>Mask as completed</th>
		</tr>
		<%
		    List<Appointment> consultations = (List<Appointment>) request.getAttribute("consultations");
		    if (consultations != null) {
			for (Appointment consultation : consultations) {
		%>
		<tr>
		    <td><%= consultation.getAppointmentID()%></td>
		    <td>
			<span style="display: flex; align-items: center; gap: 8px;">
			    <span><%= consultation.getUserName()%></span>
			    <% if (consultation.getIsVIP()) { %>
			    <span style="color: gold; font-weight: bold; background-color: #222; padding: 5px 10px; border-radius: 5px;">
				VIP
			    </span>
			    <% }%>
			</span>
		    </td>
		    <td><%= consultation.getServiceName()%></td>
		    <td><%= consultation.getAppointmentDate()%></td>
		    <td><%= consultation.getStatus()%></td>
		    <td>
			<form action="MainController" method="post">
			    <input type="hidden" name="action" value="AddConsultationNotes">
			    <input type="hidden" name="appointmentID" value="<%= consultation.getAppointmentID()%>">
			    <textarea name="note" rows="3" cols="30" placeholder="Add notes..."><%= consultation.getNote() != null ? consultation.getNote() : ""%></textarea>
			    <input type="submit" value="Save" class="edit-btn">
			</form>
		    </td>
		    <td>
			<form action="${pageContext.request.contextPath}/MainController" method="post">
			    <input type="hidden" name="action" value="CompleteAppointment">
			    <input type="hidden" name="appointmentID" value="<%= consultation.getAppointmentID()%>">
			    <input type="submit" value="Complete" class="edit-btn">
			</form>
		    </td>
		</tr>
		<%
			}
		    }
		%>
	    </table>
	</div>
    </body>
</html>
