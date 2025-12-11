<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ include file="/header.jsp" %>
<html>
    <head>
	<title>Create Service</title>
    </head>
    <body>
	<div class="create-service-container">
	    <%
		if (session == null || session.getAttribute("userID") == null || !"ADM".equals(session.getAttribute("roleID"))) {
		    response.sendRedirect("login.jsp");
		}
	    %>

	    <h2  style="color: white">Create a New Spa Service</h2>
	    <form action="${pageContext.request.contextPath}/MainController" method="post">
		<input type="hidden" name="action" value="CreateService">

		<label for="serviceName" style="color: white">Service Name:</label>
		<input type="text" name="serviceName" required><br><br>

		<label for="description" style="color: white">Description:</label>
		<textarea name="description" rows="5" cols="50" required></textarea><br><br>

		<label for="price" style="color: white">Price:</label>
		<input type="number" step="0.01" name="price" required><br><br>
		<label for="status" style="color: white">Status:</label>
		<select name="status" required>
		    <option value="true">Active</option>
		    <option value="false">Inactive</option>
		</select><br><br>

		<input type="submit" value="Create Service">
	    </form>

	    <%
		String error = (String) request.getAttribute("ERROR");
		if (error != null) {
	    %>
	    <p style="color:red;"><%= error%></p>
	    <%
		}
	    %>
	</div>
    </body>
</html>
