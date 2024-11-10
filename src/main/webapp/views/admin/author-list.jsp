<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 10/11/2024
  Time: 15:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ include file="/commons/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Danh sách tác giả</title>
    <link rel="stylesheet" href="<c:url value='/templates/css/bootstrap.min.css' />">
</head>
<body>
<div class="container mt-5">
    <h2 class="mb-4">Danh sách tác giả</h2>
    <a href="${pageContext.request.contextPath}/admin/author/add" class="btn btn-primary">Thêm tác giả mới</a>
    <table class="table table-bordered table-hover">
        <thead class="thead-dark">
        <tr>
            <th scope="col">STT</th>
            <th scope="col">Tên tác giả</th>
            <th scope="col">Ngày sinh</th>
            <th scope="col">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="author" items="${pagedAuthors}" varStatus="status">
            <tr>
                <!-- Cột STT -->
                <th scope="row">${status.index + 1}</th>

                <!-- Cột Tên tác giả -->
                <td>${author.author_name}</td>

                <!-- Cột Ngày sinh -->
                <td>
                    <fmt:formatDate value="${author.date_of_birth}" pattern="dd-MM-yyyy" />
                </td>

                <!-- Cột Action -->
                <td>
                    <a href="<c:url value='/admin/author/edit?id=${author.author_id}' />" class="btn btn-warning btn-sm">Edit</a>
                    <a href="<c:url value='/admin/author/delete?id=${author.author_id}' />" class="btn btn-danger btn-sm" onclick="return confirm('Bạn có chắc muốn xóa tác giả này?');">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <!-- Pagination -->
    <nav aria-label="Page navigation">
        <ul class="pagination justify-content-center">
            <c:forEach var="page" begin="1" end="${totalPages}">
                <li class="page-item ${page == currentPage ? 'active' : ''}">
                    <a class="page-link" href="${pageContext.request.contextPath}/admin/authors?page=${page}">${page}</a>
                </li>
            </c:forEach>
        </ul>
    </nav>
</div>

<script src="<c:url value='/templates/js/bootstrap.bundle.min.js' />"></script>
</body>
</html>

