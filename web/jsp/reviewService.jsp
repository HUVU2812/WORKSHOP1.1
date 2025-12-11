<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/header.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="com.spa.dto.Service" %>
<html>
    <head>
	<title>Review a Service</title>
	<style>
	    html, body {
		overflow: hidden; /* Chặn cuộn trang */
		height: 100vh; /* Chiều cao cố định bằng 100% màn hình */
	    }

            .star-rating {
                display: flex;
                flex-direction: row-reverse; /* Đảo ngược thứ tự sao */
                justify-content: center;
                font-size: 70px; /* Kích thước sao lớn hơn */
                color: gray; /* Mặc định là màu xám */
                cursor: pointer;
            }
            .star-rating input {
                display: none; /* Ẩn input */
            }
            .star-rating label {
                transition: color 0.2s ease-in-out; /* Hiệu ứng chuyển màu */
            }
            /* Khi hover vào sao, tất cả các sao bên trái cũng sáng */
            .star-rating label:hover,
            .star-rating label:hover ~ label {
                color: gold;
            }
            /* Khi chọn, các sao bên trái cũng sáng */
            .star-rating input:checked ~ label {
                color: gold;
            }
	    .rating {
		pointer-events: none; /* Chặn toàn bộ sự kiện chuột trên vùng rating */
	    }

	    .rating label {
		pointer-events: auto; /* Chỉ bật sự kiện chuột khi chạm đúng sao */
		cursor: pointer; /* Biến thành bàn tay khi hover vào sao */
	    }
        </style>
    </head>
    <body>
	<div class="review-service-container">
	    <h2 style="color: white">Review a Service</h2>

	    <form action="MainController" method="post">
		<input type="hidden" name="action" value="ReviewService">
		<div class="form-group1">
		    <label for="serviceID" style="color: white; font-size: 20px">Select Service:</label>
		    <select name="serviceID" required>
			<%
			    List<Service> services = (List<Service>) request.getAttribute("services");
			    if (services != null) {
				for (Service service : services) {
			%>
			<option value="<%= service.getServiceID()%>"><%= service.getServiceName()%></option>
			<%
				}
			    }
			%>
		    </select><br><br>
		</div>
		<div class="form-group1">
                    <label for="rating" style="color: white; font-size: 20px">Rating:</label>
                    <div class="star-rating">
                        <input type="radio" name="rating" value="5" id="star5"><label for="star5">&#9733;</label>
                        <input type="radio" name="rating" value="4" id="star4"><label for="star4">&#9733;</label>
                        <input type="radio" name="rating" value="3" id="star3"><label for="star3">&#9733;</label>
                        <input type="radio" name="rating" value="2" id="star2"><label for="star2">&#9733;</label>
                        <input type="radio" name="rating" value="1" id="star1"><label for="star1">&#9733;</label>
                    </div><br><br>
                </div>
		<div class="form-group1" >
		    <label for="comments" style="color: white; font-size: 20px">Comments:</label>
		    <textarea name="comments" rows="4" cols="50"></textarea><br><br>
		</div>
		<input type="submit" value="Submit Review">
	    </form>

	    <%
		String error = (String) request.getAttribute("ERROR");
		if (error != null) {
	    %>
	    <p style="color:red;"><%= error%></p>
	    <%
		}
	    %>
	</div>
    </body>
</html>
