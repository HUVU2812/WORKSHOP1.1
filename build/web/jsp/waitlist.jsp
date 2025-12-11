<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.spa.dto.WaitlistEntry" %>
<%@ page import="com.spa.dto.Service" %>
<%@ include file="/header.jsp" %>
<html>
<head>
  <title>Waitlist</title>
  <style>
    /* Layout gọn, không bị tràn nền */
    form{margin-top: 360px}
    body { margin-top: 120px; }
    body { margin: 0; }
    .wl-wrapper{ max-width: 1200px; margin: 24px auto; padding: 16px; background: #fff; border-radius: 10px; box-shadow: 0 6px 18px rgba(0,0,0,.15); }
    .wl-row{ display:flex; align-items:center; gap:12px; flex-wrap:wrap; }
    .wl-row label{ font-weight:600; }
    .wl-right{ margin-left:auto; opacity:.8 }
    .wl-table-wrap{ overflow-x:auto; border-radius: 8px; border:1px solid #e5e5e5; }
    table.wl{ width:100%; border-collapse:collapse; min-width:800px; }
    table.wl th, table.wl td{ padding:10px 12px; border-bottom:1px solid #eee; text-align:left; white-space:nowrap; }
    table.wl thead th{ position:sticky; top:0; background:#fafafa; z-index:1; }
    .wl-btn{ padding:8px 12px; border:1px solid #ccc; background:#f6f6f6; border-radius:6px; cursor:pointer; }
    .wl-btn:hover{ background:#ececec; }
    .wl-error{ background:#ffe3e3; color:#b30000; padding:10px 12px; border-radius:6px; margin-bottom:12px; }
    .wl-empty{ color:#666; text-align:center; padding:16px; }
  </style>
</head>
<body>
  <div class="wl-wrapper">
    <h2 style="margin:0 0 12px;">Waitlist</h2>

    <%
       String error = (String) request.getAttribute("ERROR");
       Integer count = (Integer) request.getAttribute("count");
       if (error != null) {
    %>
      <div class="wl-error"><%= error %></div>
    <%
       }
    %>

    <!-- Bộ lọc -->
    <form method="get" action="<%= request.getContextPath() %>/MainController" class="wl-row" style="margin-bottom:12px;">
      <input type="hidden" name="action" value="ViewWaitlist"/>
      <label>Date:</label>
      <input type="date" name="date" value="<%= (request.getAttribute("selectedDate")!=null)?request.getAttribute("selectedDate"):"" %>" />
      <label>Service:</label>
      <select name="serviceID">
        <option value="all">All</option>
        <%
          List<Service> services = (List<Service>) request.getAttribute("services");
          String selService = (String) request.getAttribute("selectedServiceID");
          if (services != null) {
            for (Service s : services) {
              String selected = (selService != null && selService.equals(s.getServiceID())) ? "selected" : "";
        %>
          <option value="<%= s.getServiceID() %>" <%= selected %>><%= s.getServiceName() %></option>
        <%
            }
          }
        %>
      </select>
      <button class="wl-btn" type="submit">Filter</button>
      <div class="wl-right">Total: <%= (count!=null?count:0) %></div>
    </form>

    <div class="wl-table-wrap">
      <table class="wl">
        <thead>
          <tr>
            <th>Wait ID</th><th>User</th><th>Service</th><th>Appointment Date</th><th>Status</th><th>Action</th>
          </tr>
        </thead>
        <tbody>
        <%
          List<WaitlistEntry> list = (List<WaitlistEntry>) request.getAttribute("waitlist");
          if (list != null && !list.isEmpty()) {
            for (WaitlistEntry e : list) {
        %>
          <tr>
            <td><%= e.getWaitID() %></td>
            <td><%= e.getUserID() %></td>
            <td><%= e.getServiceID() %></td>
            <td><%= e.getAppointmentDate() %></td>
            <td><%= e.getStatus() %></td>
            <td>
              <form action="<%= request.getContextPath() %>/MainController" method="post" style="margin:0">
                <input type="hidden" name="action" value="ApproveWaitlist" />
                <input type="hidden" name="waitID" value="<%= e.getWaitID() %>" />
                <button class="wl-btn" type="submit">Approve</button>
              </form>
            </td>
          </tr>
        <%
            }
          } else {
        %>
          <tr><td colspan="6" class="wl-empty">No items.</td></tr>
        <%
          }
        %>
        </tbody>
      </table>
    </div>
  </div>
</body>
</html>
