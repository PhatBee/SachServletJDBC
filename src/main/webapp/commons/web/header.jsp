<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 05/11/2024
  Time: 1:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/commons/taglib.jsp"%>
<link rel="stylesheet" href='<c:url value="/templates/css/bootstrap.min.css" />'>

<!-- Header Navigation Bar -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <!-- Logo or Brand Name -->
        <a class="navbar-brand" href="<c:choose>
            <c:when test='${sessionScope.account != null && sessionScope.account.is_admin == true}'>
                <c:url value='/admin/home'/>
            </c:when>
            <c:when test='${sessionScope.account != null && sessionScope.account.is_admin == false}'>
                <c:url value='/user/home'/>
            </c:when>
            <c:otherwise>
                <c:url value='/home'/>
            </c:otherwise>
        </c:choose>">BrandName</a>

        <!-- Toggle button for mobile view -->
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <!-- Navbar Links -->
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <!-- Home Link -->
                <li class="nav-item">
                    <a class="nav-link" href="<c:choose>
                        <c:when test='${sessionScope.account != null && sessionScope.account.is_admin == true}'>
                            <c:url value='/admin/home'/>
                        </c:when>
                        <c:when test='${sessionScope.account != null && sessionScope.account.is_admin == false}'>
                            <c:url value='/user/home'/>
                        </c:when>
                        <c:otherwise>
                            <c:url value='/'/>
                        </c:otherwise>
                    </c:choose>">Trang Chủ</a>
                </li>
                <!-- Products Link -->
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/products">Sản phẩm</a>
                </li>
                <!-- Admin Management Link - Only visible for admin -->
                <c:if test="${sessionScope.account != null && sessionScope.account.is_admin == true}">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/admin/authors">Trang quản trị</a>
                    </li>
                </c:if>
                <!-- Conditional Login/Logout Link -->
                <li class="nav-item">
                    <c:choose>
                        <c:when test="${not empty sessionScope.account}">
                            <a class="nav-link" href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
                        </c:when>
                        <c:otherwise>
                            <a class="nav-link" href="${pageContext.request.contextPath}/login">Đăng nhập</a>
                        </c:otherwise>
                    </c:choose>
                </li>
            </ul>
        </div>
    </div>
</nav>

