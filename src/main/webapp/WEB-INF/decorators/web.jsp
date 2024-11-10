<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 05/11/2024
  Time: 1:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/commons/taglib.jsp" %>
<c:url value="/admin/" var="URL"></c:url>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<%@include file="/commons/web/header.jsp"%>

<sitemesh:write property="body"/>

<%@include file="/commons/web/footer.jsp" %>

</body>
</html>

