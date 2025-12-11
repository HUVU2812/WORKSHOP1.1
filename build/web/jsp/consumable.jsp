<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
    <head>
        <title>Consumable Stock & Usage Log</title>
    </head>
    <body>

        <h2>Consumable Inventory</h2>

        <a href="${pageContext.request.contextPath}/MainController?action=AddConsumable" class="btn">Add Consumable</a>

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
                    <td>
                        <c:choose>
                            <c:when test="${roleID == 'ADM'}">
                                <a href="${pageContext.request.contextPath}/MainController?action=DeleteConsumable&consumableID=${c.consumableID}" class="btn">Delete</a>
                                <a href="${pageContext.request.contextPath}/MainController?action=UpdateConsumable&consumableID=${c.consumableID}" class="btn">Update</a>
                            </c:when>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <h2>View Usage Log by Appointment ID</h2>

        <a href="${pageContext.request.contextPath}/MainController?action=AddUsageLog" class="btn">Add Usage Log</a>
        <table border="1" cellpadding="5">
            <tr>
                <th>Appointment ID</th>
                <th>Consumable Name</th>
                <th>Quantity Used</th>
                <th>Used At</th>
                <th>Staff</th>
                

            </tr>

            <c:forEach var="u" items="${USAGE_LOGS}">
                <tr>
                    <td>${u.appointmentID}</td>
                    <td>${u.consumableName}</td>
                    <td>${u.quantityUsed}</td>
                    <td>${u.usedAt}</td>
                    <td>${u.staffName}</td>

                    <td>
                        <c:choose>
                            <c:when test="${roleID == 'ADM'}">
                                <a href="${pageContext.request.contextPath}/MainController?action=DeleteUsageLog&logID=${u.logID}" class="btn">Delete</a>
                                <a href="${pageContext.request.contextPath}/MainController?action=UpdateUsageLog&logID=${u.logID}" class="btn">Update</a>
                            </c:when>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>

        </table>

    </body>
</html>
