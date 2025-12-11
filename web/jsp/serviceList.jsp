<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/header.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="com.spa.dto.Service" %>
<html>
    <head>
	<title>Service List</title>

    </head>
    <body>
	<div class="service-list-container">
	    <h2  style="color: white">Spa Services</h2>

	    <table border="1" class="service-table">
		<tr>
		    <th>Service ID</th>
		    <th>Service Name</th>
		    <th>Description</th>
		    <th>Price</th>
		    <th>Status</th>
		    <th>Actions</th>
		</tr>
		<%
		    List<Service> services = (List<Service>) request.getAttribute("services");
		    if (services != null) {
			for (Service service : services) {
		%>
		<tr>
		    <td><%= service.getServiceID()%></td>
		    <td><%= service.getServiceName()%></td>
		    <td><%= service.getDescription()%></td>
		    <td><%= service.getPrice()%></td>
		    <td>
			<% if (service.isStatus()) { %>
			<span style="color: green;">Service VIP</span>
                        <% } else { %>
			<span style="color: red;">Service No VIP</span>
                        <% }%>
		    </td>
		    <td>


			<form action="${pageContext.request.contextPath}/MainController" method="post" style="display:inline;">
			    <input type="hidden" name="action" value="UpdateService">
			    <input type="hidden" name="serviceID" value="<%= service.getServiceID()%>">
			    <input type="submit" value="Edit" class="edit-btn">
			</form>
			<form action="${pageContext.request.contextPath}/MainController" method="post" style="display:inline;">
			    <input type="hidden" name="action" value="DeleteService">
			    <input type="hidden" name="serviceID" value="<%= service.getServiceID()%>">
			    <input type="submit" value="Delete" class="delete-btn">

			</form>
		    </td>
		</tr>
		<%
			}
		    }
		%>
	    </table>
	</div>
    </body>
</html>
