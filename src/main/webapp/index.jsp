<%@ page language="java" pageEncoding="UTF-8"%>
<%
    pageContext.setAttribute("path", request.getContextPath());
%>
<html>
<body>
<jsp:forward page="${path}/boss/loginIndex"></jsp:forward>
</body>
</html>
