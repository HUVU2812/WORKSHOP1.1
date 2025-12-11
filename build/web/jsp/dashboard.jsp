<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ include file="/header.jsp" %>

<html>
    <head>
        <title>Dashboard</title>
    </head>
    <body>
        <% String info = (String) session.getAttribute("INFO");
            if (info != null) {%><p class="info-message"><%= info%></p><% session.removeAttribute("INFO");
                } %>
        <div class="dashboard-container">
            <%
                if (session == null || session.getAttribute("email") == null) {
                    response.sendRedirect("jsp/login.jsp");
                }


            %>

            <h2>
                <span class="welcome-text">Welcome, <%= fullName%>!</span>
                <% if (Boolean.TRUE.equals(isVIP)) { %>
                <span style="color: gold; font-weight: bold; background-color: #222; padding: 5px 10px; border-radius: 5px;">
                    VIP
                </span>
                <% } %>
            </h2>

            <div class="dashboard-menu">
                <% if ("ADM".equals(roleID)) { %>
                <a href="${pageContext.request.contextPath}/MainController?action=CreateService" class="btn">Create Service</a>
                <a href="${pageContext.request.contextPath}/MainController?action=ViewServices" class="btn">Manage Services</a>
                <a href="${pageContext.request.contextPath}/MainController?action=AssignStaff" class="btn">Assign Staff</a>
                <a href="${pageContext.request.contextPath}/MainController?action=ActiveVIP" class="btn">Active VIP</a>
                <a href="${pageContext.request.contextPath}/MainController?action=Feedback" class="btn">View Feedback</a> 
                <a href="${pageContext.request.contextPath}/MainController?action=ViewConsumable" class="btn">View Consumables</a>
                
                <% } else if ("STF".equals(roleID)) { %>
                <a href="${pageContext.request.contextPath}/MainController?action=ViewConsultation" class="btn">View Consultations</a>
                <a href="${pageContext.request.contextPath}/MainController?action=ViewConsumable" class="btn">View Consumables</a>
                <% } else if ("USR".equals(roleID)) { %>
                <% if (isVIP != null && isVIP) { %>
                <a href="${pageContext.request.contextPath}/MainController?action=BookAppointment" class="btn">Book an Appointment</a>
                <a href="${pageContext.request.contextPath}/MainController?action=ViewAppointments" class="btn">View Appointments</a>
                <a href="${pageContext.request.contextPath}/MainController?action=ReviewService" class="btn">Review a Service</a>
                <a href="${pageContext.request.contextPath}/MainController?action=FeedBackUser" class="btn">View Feedback</a>
                <% } else { %>
                <a href="${pageContext.request.contextPath}/MainController?action=BookAppointment1" class="btn">Book an Appointment</a>
                <a href="${pageContext.request.contextPath}/MainController?action=ViewAppointments" class="btn">View Appointments</a>
                <a href="${pageContext.request.contextPath}/MainController?action=ReviewService1" class="btn">Review a Service</a>
                <a href="${pageContext.request.contextPath}/MainController?action=FeedBackUser1" class="btn">View Feedback</a>
                <% } %>
                <% }%>
            </div>
        </div>

        <a href="<%= request.getContextPath()%>/MainController?action=ViewWaitlist" class="nav-link">Waitlist</a>
    </body>
</html>
