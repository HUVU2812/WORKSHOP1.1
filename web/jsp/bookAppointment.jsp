<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/header.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="com.spa.dto.Service" %>

<html>
    <head>
	<title>Book an Appointment</title>
    </head>
    <body>
	<div class="book-appointment-container">
	    <h2 style="color: white">Book an Appointment</h2>

	    <form action="${pageContext.request.contextPath}/MainController" method="post">
		<input type="hidden" name="action" value="BookAppointment">
		<div class="form-group">
		    <label for="serviceID" style="color: white">Select Service</label>
		    <select name="serviceID" required>
			<%
			    List<Service> services = (List<Service>) request.getAttribute("services");
			    if (services != null) {
				for (Service service : services) {
			%>
			<option value="<%= service.getServiceID()%>"><%= service.getServiceName()%> - $<%= service.getPrice()%></option>
			<%
				}
			    }
			%>
		    </select><br><br>
		</div>
		<div class="form-group">
		    <label for="appointmentDate" style="color: white">Appointment Date</label>
		    <input type="datetime-local" name="appointmentDate" required><br><br>
		</div>
		<input type="submit" value="Book Appointment">
	    </form>

	    <%
		String error = (String) session.getAttribute("ERROR");
		if (error != null && !error.isEmpty()) {
	    %>
	    <p class="error-message"><%= error%></p>
	    <%
		session.removeAttribute("ERROR");
		}
	    %>
	</div> 
    </body>
</html>
