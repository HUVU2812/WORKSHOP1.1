<%-- 
    Document   : activeVIPUser
    Created on : Mar 3, 2025, 7:23:21 PM
    Author     : Admin
--%>


<%@ include file="/header.jsp" %>
<%@page import="com.spa.dto.User"%>
<jsp:useBean id="userList" scope="request" type="java.util.List<com.spa.dto.User>" />

<!DOCTYPE html>
<html>
    <head>
        <title>Active VIP</title>
    </head>
    <body>
	<div class="service-list-container">
	    <h2 style="color: white">User List</h2>

	    <table border="1" class="service-table">
		<tr>
		    <th>UserID</th>
		    <th>Full Name</th>
		    <th>Email</th>
		    <th>Phone Number</th>
		    <th>VIP</th>
		    <th>Action</th>
		</tr>
		<% for (User u : userList) {%>
		<tr>
		    <td><%= u.getUserID()%></td>
		    <td><%= u.getFullName()%></td>
		    <td><%= u.getEmail()%></td>
		    <td><%= u.getPhoneNumber()%></td>
		    <td><%= u.getIsVIP() ? "Yes" : "No"%></td>
		    <td>
			<form action="MainController" method="POST">
			    <input type="hidden" name="action" value="Active">
			    <input type="hidden" name="userID" value="<%= u.getUserID()%>">
			    <% if (u.getIsVIP()) { %>
			    <input type="hidden" name="isVIP" value="false">
			    <button type="submit" class="delete-btn">Deactivate VIP</button>
			    <% } else { %>
			    <input type="hidden" name="isVIP" value="true">
			    <button type="submit" class="edit-btn">Activate VIP</button>
			    <% }%>
			</form>
		    </td> 
		</tr>
		<% }%>
	    </table>

	</div>
    </body>
</html>
