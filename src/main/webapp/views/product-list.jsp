<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 08/11/2024
  Time: 21:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/commons/taglib.jsp" %>
<link rel="stylesheet" href='<c:url value="/templates/css/bootstrap.min.css" />'>

<div class="container mt-4">
    <h2 class="mb-4">Danh sách sản phẩm</h2>
    <!-- Hiển thị liên kết thêm sách nếu là admin -->
    <c:if test="${sessionScope.account != null && sessionScope.account.is_admin == true}">
        <div class="mb-3">
            <a href="<c:url value='/admin/product/add' />" class="btn btn-success">Thêm sách mới</a>
        </div>
    </c:if>

    <div class="row">
        <c:forEach var="book" items="${bookList}">
            <div class="col-md-4 mb-4">
                <div class="card">
                    <div class="row no-gutters">
                        <!-- Cột hình ảnh -->
                        <div class="col-12">
                            <c:choose>
                                <c:when test="${book.cover_image.startsWith('http') || book.cover_image.startsWith('https')}">
                                    <!-- Nếu đường dẫn là URL trực tuyến -->
                                    <img src="${book.cover_image}" class="card-img" alt="${book.title}" style="height: 200px; object-fit: cover;">
                                </c:when>
                                <c:otherwise>
                                    <!-- Nếu đường dẫn là ảnh local -->
                                    <img src="<c:url value='/images/${book.cover_image}' />" class="card-img" alt="${book.title}" style="height: 200px; object-fit: cover;">
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <!-- Cột thông tin -->
                        <div class="col-12">
                            <div class="card-body">
                                <h5 class="card-title">${book.title}</h5>
                                <p class="card-text"><strong>Mã ISBN:</strong> ${book.isbn}</p>
                                <p class="card-text">
                                    <strong>Tác giả:</strong>
                                    <c:choose>
                                        <c:when test="${not empty book.authors}">
                                            <c:forEach var="author" items="${book.authors}">
                                                <span>${author.author_name}</span><c:if test="${!status.last}">, </c:if>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            No information
                                        </c:otherwise>
                                    </c:choose>
                                </p>
                                <p class="card-text"><strong>Publisher:</strong> ${book.publisher}</p>
                                <p class="card-text"><strong>Publisher Date:</strong> <fmt:formatDate value="${book.publish_date}" pattern="dd-MM-yyyy" /></p>
                                <p class="card-text"><strong>Số lượng:</strong> ${book.quantity}</p>
                                <p class="card-text"><strong>Review:</strong> <a href="<c:url value='/reviews?id=${book.bookid}' />">Xem đánh giá</a></p>

                                <!-- Hiển thị nút Edit và Delete nếu là admin -->
                                <c:if test="${sessionScope.account != null && sessionScope.account.is_admin == true}">
                                    <div class="mt-3">
                                        <a href="<c:url value='/admin/product/edit?id=${book.bookid}' />" class="btn btn-warning btn-sm">Edit</a>
                                        <a href="<c:url value='/admin/product/delete?id=${book.bookid}' />" class="btn btn-danger btn-sm" onclick="return confirm('Bạn có chắc muốn xóa sách này?');">Delete</a>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

    <!-- Phân trang theo số thứ tự -->
    <div class="row">
        <div class="col-md-12 text-center">
            <!-- Hiển thị các số trang -->
            <c:forEach var="i" begin="1" end="${totalPages}">
                <c:choose>
                    <c:when test="${i == pageNumber}">
                        <span class="btn btn-secondary">${i}</span> <!-- Trang hiện tại -->
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/products?page=${i}' />" class="btn btn-primary">${i}</a> <!-- Liên kết tới các trang khác -->
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
    </div>
</div>
