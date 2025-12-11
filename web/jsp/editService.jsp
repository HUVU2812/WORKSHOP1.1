<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/header.jsp" %>

<%@page import="com.spa.dto.Service"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Service</title>
    </head>
    <body>
	<div class="update-service-page">
	    <div class="container">
		<h1>Update Service</h1>

		<%
		    Service service = (Service) request.getAttribute("service");
		    String errorMessage = (String) request.getAttribute("ERROR");
		    if (service == null) {

		    } else {
		%>

		<% if (errorMessage != null && !"All fields are required.".equals(errorMessage)) {%>
		<div class="error-message"><%= errorMessage%></div>
		<% }%>
		<div class="form-container">
		    <h2 class="subtitle">Update Service: <%=service.getServiceName()%></h2>

		    <form action="${pageContext.request.contextPath}/MainController" method="post">
			<input type="hidden" name="action" value="UpdateService">
			<input type="hidden" name="serviceID" value="<%= service.getServiceID()%>">
			<div class="form-group">
			    <label for="serviceName">Service Name:</label>
			    <input type="text" name="serviceName" value="<%= service.getServiceName()%>" required><br><br>
			</div>
			<div class="form-group">
			    <label for="description">Description:</label>
			    <textarea name="description" rows="5" cols="50" required><%= service.getDescription() != null ? service.getDescription() : ""%></textarea>
			</div>
			<div class="form-group">
			    <label for="price">Price:</label>
			    <input type="number" step="0.01" name="price" value="<%= service.getPrice()%>" required><br><br>
			</div>
			<div class="form-group">
			<label for="status">Status:</label>
			<select name="status" required>
			    <option value="true" <%= service.isStatus() ? "selected" : ""%>>Active</option>
			    <option value="false" <%= !service.isStatus() ? "selected" : ""%>>Inactive</option>
			</select><br><br>
			</div>
			<div class="button-container">
			    <button type="submit" class="btn">Update Service</button>
			</div>
		    </form>
		</div>
		<% }%>
	    </div>
	</div>
    </body>
</html>
