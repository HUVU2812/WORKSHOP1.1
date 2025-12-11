
<%@page import="com.spa.dto.Review, java.util.List"%>
<jsp:useBean id="reviewList" scope="request" type="java.util.List<com.spa.dto.Review>" />
<%@ include file="/header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <title>View Feedback</title>
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
		    <th>Status</th>
		    <th>Action</th>
		</tr>
		<% for (Review review : reviewList) {%>
		<tr>
		    <td>
			<span><%= review.getUserName()%></span>
			<% if (review.getIsVIP()) { %>
			<span style="color: gold; font-weight: bold; background-color: #222; padding: 5px 10px; border-radius: 5px;">VIP</span>
			<% }%>
		    </td>
		    <td><%= review.getServiceName()%></td>
		    <td>
			<% for (int i = 0; i < review.getRating(); i++) { %>
			&#9733;
			<% }%>
		    </td>

		    <td><%= review.getComments()%></td>
		    
                    <td>
                        <% if (review.isStatus()) { %>
                            <span style="color: green;">Visible</span>
                        <% } else { %>
                            <span style="color: red;">Hidden</span>
                        <% } %>
                    </td>
		    <td>
			<form action="${pageContext.request.contextPath}/MainController" method="post">
			    <input type="hidden" name="action" value="Cancel">
			    <input type="hidden" name="reviewID" value="<%= review.getReviewID()%>">
			    <input type="submit" value="Delete" class="delete-btn">
			</form>
		    </td>
		</tr>
		<% }%>
	    </table>
	</div>
    </body>
</html>
