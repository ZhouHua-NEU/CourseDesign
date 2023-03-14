<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.util.*,datastruct.Community"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset=utf-8">
<title>Insert title here</title>
</head>
<body>

<%
List<Community> list = (List<Community>)request.getAttribute("comlist");
%>

<table border="1">
<tr>
<td>社区名称</td>
<td>社区地址</td>
<td>社区管理员电话</td>
<td>社区管理员</td>
</tr>
<%
for(Community com:list){
%>
<tr>
<td><%=com.getCommunityname()%></td>
<td><%=com.getCommunityaddress()%></td>
<td><%=com.getCommunityphone()%></td>
<td><%=com.getCommunitymanager()%></td>
<% } %>
</tr>
</table>


<!-- 插入4个超链接，内容为增删改查，目标为right-->
<center>
<a href="right/community/community_add.jsp" target="right">增加社区</a>

<a href="right/community/community_delete.jsp" target="right">删除社区</a>

<a href="right/community/community_update.jsp" target="right">修改社区</a>

<a href="right/community/community_query.jsp" target="right">查询社区</a>
</center>
</body>
</html>