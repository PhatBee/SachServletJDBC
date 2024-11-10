<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 08/11/2024
  Time: 23:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ include file="/commons/taglib.jsp" %>
<link rel="stylesheet" href="<c:url value='/templates/css/bootstrap.min.css' />">

<div class="container mt-4">
    <h2 class="mb-4">Thông tin sách và đánh giá</h2>

    <div class="row">
        <!-- Cột 1: Hình ảnh sách -->
        <div class="col-md-4">
            <c:choose>
                <c:when test="${book.cover_image.startsWith('http') || book.cover_image.startsWith('https')}">
                    <!-- Nếu đường dẫn là URL trực tuyến -->
                    <img src="${book.cover_image}" class="img-fluid" alt="${book.title}" style="object-fit: cover; height: 300px;">
                </c:when>
                <c:otherwise>
                    <!-- Nếu đường dẫn là ảnh local -->
                    <img src="<c:url value='/images/${book.cover_image}' />" class="img-fluid" alt="${book.title}" style="object-fit: cover; height: 300px;">
                </c:otherwise>
            </c:choose>
        </div>

        <!-- Cột 2: Thông tin sách và reviews -->
        <div class="col-md-8">
            <h3>${book.title}</h3>
            <p><strong>Mã ISBN:</strong> ${book.isbn}</p>
            <p><strong>Tác giả:</strong>
                <c:choose>
                    <c:when test="${not empty book.authors}">
                        <c:forEach var="author" items="${book.authors}">
                            <span>${author.author_name}</span><c:if test="${!authorStatus.last}">, </c:if>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        No information
                    </c:otherwise>
                </c:choose>
            </p>
            <p><strong>Publisher:</strong> ${book.publisher}</p>
            <p><strong>Ngày phát hành:</strong> <fmt:formatDate value="${book.publish_date}" pattern="dd-MM-yyyy" /></p>
            <p><strong>Số lượng:</strong> ${book.quantity}</p>

            <h4>Đánh giá:</h4>
            <c:forEach var="rating" items="${book.ratings}">
                <p><strong>${rating.user.fullname}:</strong> ${rating.review_text}</p>
            </c:forEach>

            <!-- Form thêm review mới -->
            <c:if test="${not empty sessionScope.account}">
                <h5>Thêm đánh giá mới:</h5>
                <form action="<c:url value='${pageContext.request.contextPath}/reviews/add' />" method="post">
                    <div class="form-group">
                        <label for="reviewText">Nội dung đánh giá:</label>
                        <textarea class="form-control" id="reviewText" name="reviewText" rows="4"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="rating">Đánh giá:</label>
                        <select class="form-control" id="rating" name="rating">
                            <option value="1">1 sao</option>
                            <option value="2">2 sao</option>
                            <option value="3">3 sao</option>
                            <option value="4">4 sao</option>
                            <option value="5">5 sao</option>
                        </select>
                    </div>
                    <input type="hidden" name="bookid" value="${book.bookid}" />
                    <button type="submit" class="btn btn-primary">Gửi đánh giá</button>
                </form>
            </c:if>
            <c:if test="${empty sessionScope.account}">
                <p>Vui lòng <a href="<c:url value='/login' />">đăng nhập</a> để thêm đánh giá.</p>
            </c:if>
        </div>
    </div>
</div>
