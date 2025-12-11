<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>Register</title>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/css/style.css">
    </head>
    <body>
	<div class="register-container">
	    <a href="<%= request.getContextPath()%>/jsp/dashboard.jsp" class="home-link" style="color: white">Home</a> 

	    <h2 style="color: white">Register</h2>

	    <form action="${pageContext.request.contextPath}/MainController" method="post">
		<input type="hidden" name="action" value="RegisterStaffUser">

		<label for="userID" style="color: white">User ID</label>
		<input type="text" name="userID" required><br><br>

		<label for="fullName" style="color: white">Full Name</label>
		<input type="text" name="fullName" required><br><br>

		<label for="email" style="color: white">Email</label>
		<input type="email" name="email" required><br><br>

		<label for="phoneNumber" style="color: white">Phone Number</label>
		<input type="text" name="phoneNumber" required><br><br>

		<label for="password" style="color: white">Password</label>
		<input type="password" name="password" required><br><br>
		<select name="roleID" hidden>
		    <option value="STF"></option>
		</select>
		<input type="submit" value="Register" class="register-btn">
	    </form>


	    <%
		String successMessage = (String) request.getAttribute("SUCCESS");
		if (successMessage != null) {
	    %>
	    <p style="color: green;"><%= successMessage%></p>
	    <%
		}
	    %>
	    <%
		String error = (String) request.getAttribute("ERROR");
		if (error != null) {
	    %>
	    <p style="color: red;"><%= error%></p>
	    <%
		}
	    %>
	</div>
    </body>
</html>
