<%-- 
    Document   : login
    Created on : Feb 20, 2025, 1:56:46 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/css/style.css">
    </head>
    <body>
        <div class="login-form">
            <%
		String errorMessage = (String) request.getAttribute("errorMessage");
		if (errorMessage != null) {
            %>
            <p style="color: red;"><%= errorMessage%></p>
            <%
		}
            %>
            <form action="${pageContext.request.contextPath}/LoginController" method="post">
                <div class="input-field">
                    <label for="email">Email</label>
                    <input type="email" name="email" required><br><br>
                </div>
                <div class="input-field">
                    <label for="password">Password</label>
                    <input type="password" name="password" required><br><br>
                </div>
                <input type="submit" value="Login">
            </form>
	    <a href="${pageContext.request.contextPath}/MainController?action=Register" style="font-weight: bold; font-size: 30px; color: blue">Register User</a>
        </div>
        
    </body>
</html>
