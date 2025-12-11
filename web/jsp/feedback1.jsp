<%-- 
    Document   : feedback
    Created on : Mar 4, 2025, 12:51:14 AM
    Author     : Admin
--%>

<%@page import="com.spa.dto.Review"%>
<jsp:useBean id="reviewLists1" scope="request" type="java.util.List<com.spa.dto.Review>" />
<%@ include file="/header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="service-list-container">
	    <h2 style="color: white">Feedback List</h2>

	    <table border="1" class="service-table">
		<tr>
		    <th>User Name</th>
		    <th>Service Name</th>
		    <th>Rating</th>
		    <th>Comments</th>
		</tr>
		<% for (Review review1 : reviewLists1) {%>
		<tr>
		    <td><span><%= review1.getUserName()%></span>
			<% if (review1.getIsVIP()) { %>
			<span style="color: gold; font-weight: bold; background-color: #222; padding: 5px 10px; border-radius: 5px;">VIP</span>
			<% }%>
		    </td>
		    <td><%= review1.getServiceName()%></td>
		    <td>
			<% for (int i = 0; i < review1.getRating(); i++) { %>
			&#9733;
			<% }%>
		    </td>

		    <td><%= review1.getComments()%></td>
		    
		</tr>
		<% }%>
	    </table>
	</div>
    </body>
</html>
