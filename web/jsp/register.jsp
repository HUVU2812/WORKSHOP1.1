<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Register</title>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/css/style.css">
    </head>
    <body>
	<div class="register-container">
	    <h2 style="color: white">Register</h2>

	    <form action="${pageContext.request.contextPath}/MainController" method="post">
		<input type="hidden" name="action" value="RegisterUser">

		<label for="userID" style="font-size: 15px; font-weight: bold">User ID</label>
		<input type="text" name="userID" required><br><br>

		<label for="fullName" style="font-size: 15px; font-weight: bold">Full Name</label>
		<input type="text" name="fullName" required><br><br>

		<label for="email" style="font-size: 15px; font-weight: bold">Email</label>
		<input type="email" name="email" required><br><br>

		<label for="phoneNumber" style="font-size: 15px; font-weight: bold">Phone Number</label>
		<input type="text" name="phoneNumber" required><br><br>

		<label for="password" style="font-size: 15px; font-weight: bold">Password</label>
		<input type="password" name="password" required><br><br>
		<select name="roleID" hidden>
		    <option value="USR"></option>
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
	<div class="login-back">
	    <a href="${pageContext.request.contextPath}/jsp/login.jsp" >Back to Login</a>
	</div>
    </body>
</html>
