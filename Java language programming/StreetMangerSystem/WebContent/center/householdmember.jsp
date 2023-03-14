<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.util.*,datastruct.HouseHoldMember"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset=utf-8">
<title>Insert title here</title>
</head>
<body>

<%
List<HouseHoldMember> list = (List<HouseHoldMember>)request.getAttribute("comlist");
%>

<table border="1">
<tr>
<td>成员姓名</td>
<td>成员电话</td>
<td>成员id</td>
<td>成员家庭id</td>

<%
for(HouseHoldMember com:list){
%>
<tr>
<td><%=com.getMembername()%></td>
<td><%=com.getMemberphone()%></td>
<td><%=com.getMemberid()%></td>
<td><%=com.getHouseholdid()%></td>

<% } %>
</tr>
</table>
<!-- 插入4个超链接，内容是对标题增删改查，目标为right-->
<a href="right/householdmember/addHouseholdMember.jsp" target="right">增加家庭成员</a>
<a href="right/householdmember/deleteHouseholdMember.jsp" target="right">删除家庭成员</a>
<a href="right/householdmember/updateHouseholdMember.jsp" target="right">修改家庭成员</a>
<a href="right/householdmember/queryHouseholdMember.jsp" target="right">查询家庭成员</a>

</body>
