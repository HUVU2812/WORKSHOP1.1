<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" %>


<html>
    <head>
        <title>Update Consumable</title>
    </head>
    <body>
    
        <h2>Update Consumable</h2>

        <form action="MainController" method="post">
            

            <label>Consumable ID:</label>
            <input type="text" name="consumableID" value="${CONSUMABLE.consumableID}" disabled="true"/><br/><br/>

            <label>Name:</label>
            <input type="text" name="name" value="${CONSUMABLE.name}" required/><br/><br/>

            <label>Unit:</label>
            <input type="text" name="unit" value="${CONSUMABLE.unit}" required/><br/><br/>

            <label>Stock:</label>
            <input type="number" name="stock" value="${CONSUMABLE.stock}" required/><br/><br/>

            <p>
                <input type="submit" value="Save" name="action"/>
            </p>
           <a href="${pageContext.request.contextPath}/MainController?action=ViewConsumable" class="btn">cancel</a>
           
           <input type="hidden" name="typeaction" value="UpdateConsumableController"/>
        </form>

        <c:if test="${not empty ERROR}">
            <p style="color:red">${ERROR}</p>
        </c:if>

        <c:if test="${not empty SUCCESS}">
            <p style="color:green">${SUCCESS}</p>
        </c:if>

    </body>
</html>
