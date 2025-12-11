<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <title>Add Usage Log</title>
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

        <h2>Add New Usage Log</h2>

        <form action="MainController" method="post">
            <label>Consumable ID:</label>
            <select name="consumableID" required>
                <option value="">-- Select Consumable --</option>
                <c:forEach var="consumable" items="${ListConsumable}">
                    <option value="${consumable.consumableID}" >
                        ${consumable.consumableID} - ${consumable.name}
                    </option>
                </c:forEach>
            </select><br/><br/>

            <label>Appointment ID:</label>
            <select name="appointmentID" required>
                <option value="">-- Select Appointment --</option>

                <c:choose>
                    
                    <c:when test="${roleID == 'ADM'}">
                        <c:forEach var="appointment" items="${consultationsForAd}">
                            <option value="${appointment.appointmentID}">
                                ${appointment.appointmentID} - ${appointment.staffName}
                            </option>
                        </c:forEach>
                    </c:when>

                    
                    <c:otherwise>
                        <c:forEach var="appointment" items="${consultations}">
                            <option value="${appointment.appointmentID}">
                                ${appointment.appointmentID}
                            </option>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>

            </select>
            <br/><br/>


            <label>Quantity Used:</label>
            <input type="number" name="quantityUsed" value="${oldQuantityUsed}" required/><br/><br/>

            <label>Used At (YYYY-MM-DD):</label>
            <input type="date" name="usedAt" value="${oldUsedAt}" required/><br/><br/>

            <p>
                <input type="submit" value="Add" name="action"/>
            </p>
            <a href="${pageContext.request.contextPath}/MainController?action=ViewConsumable" class="btn">Cancel</a>

            <input type="hidden" name="typeaction" value="NewUsageLog"/>
        </form>

        <c:if test="${not empty ERROR}">
            <p style="color:red">${ERROR}</p>
        </c:if>

        <c:if test="${not empty SUCCESS}">
            <p style="color:green">${SUCCESS}</p>
        </c:if>
    </body>
</html>