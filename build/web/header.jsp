<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/css/style.css">
<%
    HttpSession userSession = request.getSession(false);
    String fullName = (userSession != null) ? (String) userSession.getAttribute("fullName") : null;
    String roleID = (userSession != null) ? (String) userSession.getAttribute("roleID") : null;
    Boolean isVIP = (userSession != null) ? (Boolean) userSession.getAttribute("isVIP") : null;
%>
<div class="header-container">
    <div class="header-content">
	<img src="<%= request.getContextPath()%>/logo/logo.png" alt="Spa Logo" class="logo">
	<h2>Serenity Spa</h2>
    </div>

    <div class="navbar">
	<div class="left-menu">
	    <a href="<%= request.getContextPath()%>/jsp/dashboard.jsp" class="nav-link">Home</a> 
	</div>
	<div class="center-menu">
    <% if (fullName != null && "ADM".equals(roleID)) { %>
        <a href="<%= request.getContextPath()%>/MainController?action=RegisterStaff" class="nav-link">Register Staff</a>
    <% } %>

    <% if (fullName != null && ("ADM".equals(roleID) || "STF".equals(roleID))) { %>
        <a href="<%= request.getContextPath()%>/MainController?action=ViewWaitlist" class="nav-link">Waitlist</a>
    <% } %>
</div>

        <div class="right-menu">
            <% if (fullName != null) {%>
	    <a href="<%= request.getContextPath()%>/MainController?action=Logout" class="nav-link">Logout</a>
            <% }%>
        </div>
    </div>
</div>
