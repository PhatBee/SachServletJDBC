<%@ page import="jakarta.servlet.http.Cookie" %><%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 05/11/2024
  Time: 18:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/commons/taglib.jsp" %>
<%
    String email = "";
    String password = "";
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if ("email".equals(cookie.getName())) {
                email = cookie.getValue();
            }
            if ("password".equals(cookie.getName())) {
                password = cookie.getValue();
            }
        }
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Đăng Nhập</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href='<c:url value="/templates/css/bootstrap.min.css" />'>
</head>
<body>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <!-- Login Form Card -->
            <div class="card">
                <div class="card-header text-center bg-primary text-white">
                    <h3>Đăng Nhập</h3>
                </div>
                <div class="card-body">
                    <c:if test="${alert !=null}">
                        <h3 class="alert alert danger">${alert}</h3>
                    </c:if>
                    <!-- Form for login -->
                    <form action="/login" method="post">
                        <!-- Email Field -->
                        <div class="form-group">
                            <label for="email">Email:</label>
                            <input type="email" class="form-control" id="email" name="email" placeholder="Nhập email của bạn"
                                   value="<%= email %>" required>
                        </div>
                        <!-- Password Field -->
                        <div class="form-group">
                            <label for="password">Mật khẩu:</label>
                            <input type="password" class="form-control" id="password" name="password" placeholder="Nhập mật khẩu"
                                   value="<%= password %>" required>
                        </div>
                        <div class="mb-3 form-check">
                            <input type="checkbox" class="form-check-input" id="remember" name="remember" <% if (!email.isEmpty()) { %>checked<% } %>>
                            <label class="form-check-label" for="remember">Remember me</label>
                        </div>
                        <!-- Login Button -->
                        <button type="submit" class="btn btn-primary btn-block">Đăng Nhập</button>
                    </form>
                </div>
                <div class="card-footer text-center">
                    <small>Chưa có tài khoản? <a href="${pageContext.request.contextPath}/register">Nhấn vào đây</a></small>
                </div>

            </div>
        </div>
    </div>
</div>

</body>
</html>

