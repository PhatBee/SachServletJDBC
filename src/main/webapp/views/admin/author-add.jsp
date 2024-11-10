<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 10/11/2024
  Time: 19:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/commons/taglib.jsp"%>
<link rel="stylesheet" href='<c:url value="/templates/css/bootstrap.min.css" />'>

<div class="container mt-4">
  <h2>Thêm tác giả mới</h2>
  <form action="${pageContext.request.contextPath}/admin/author/insert" method="post">
    <div class="form-group">
      <label for="authorName">Tên tác giả</label>
      <input type="text" class="form-control" id="authorName" name="authorName" placeholder="Nhập tên tác giả" required>
    </div>
    <div class="form-group">
      <label for="dateOfBirth">Ngày sinh</label>
      <input type="date" class="form-control" id="dateOfBirth" name="dateOfBirth" required>
    </div>
    <button type="submit" class="btn btn-primary">Thêm</button>
    <a href="${pageContext.request.contextPath}/admin/authors" class="btn btn-secondary">Quay lại</a>
  </form>
</div>
