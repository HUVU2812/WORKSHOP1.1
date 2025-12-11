<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" %>


<html>
    <head>
        <title>Add Consumable</title>
    </head>
    <body>

        <h2>Consumable Inventory</h2>

        <table border="1" cellpadding="5">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Unit</th>
                <th>Stock</th>
                <th>Status</th>
            </tr>

            <c:forEach var="c" items="${ListConsumable}">
                <tr>
                    <td>${c.consumableID}</td>
                    <td>${c.name}</td>
                    <td>${c.unit}</td>
                    <td>${c.stock}</td>
                    <td>
                        <c:choose>
                            <c:when test="${c.status}">Active</c:when>
                            <c:otherwise>Inactive</c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </table>
        
        <h2>Add New Consumable</h2>

        <form action="MainController" method="post">
            

            <label>Consumable ID:</label>
            <input type="text" name="consumableID" value="${oldID}" required/><br/><br/>

            <label>Name:</label>
            <input type="text" name="name" value="${oldName}" required/><br/><br/>

            <label>Unit:</label>
            <input type="text" name="unit" value="${oldUnit}" required/><br/><br/>

            <label>Stock:</label>
            <input type="number" name="stock"required/><br/><br/>

            <p>
                <input type="submit" value="Add" name="action"/>
            </p>
           <a href="${pageContext.request.contextPath}/MainController?action=ViewConsumable" class="btn">cancel</a>
           
           <input type="hidden" name="typeaction" value="NewConsumable"/>
        </form>

        <c:if test="${not empty ERROR}">
            <p style="color:red">${ERROR}</p>
        </c:if>

        <c:if test="${not empty SUCCESS}">
            <p style="color:green">${SUCCESS}</p>
        </c:if>

    </body>
</html>
