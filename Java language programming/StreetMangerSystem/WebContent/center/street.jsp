<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.util.*,datastruct.Street"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset=utf-8">
<title>Insert title here</title>
</head>
<body>

<%
List<Street> list = (List<Street>)request.getAttribute("comlist");
%>

<table border="1">
<tr>
<td>街道名称</td>
<td>街道管理员</td>
<td>街道管理员电话</td>
</tr>

<%
for(Street com:list){
%>

<tr>
<td><%=com.getStreetname()%></td>
<td><%=com.getStreetmanager()%></td>
<td><%=com.getStreetphone()%></td>
</tr>

<%
}
%>
</table>
<!-- 插入4个超链接，内容为增删改查，目标为right-->
<a href="right/street/addStreet.jsp" target="right">增加街道</a>
<a href="right/street/deleteStreet.jsp" target="right">删除街道</a>
<a href="right/street/updateStreet.jsp" target="right">修改街道</a>
<a href="right/street/queryStreet.jsp" target="right">查询街道</a>

</body>
</html>