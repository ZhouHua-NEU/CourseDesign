<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.util.*,datastruct.House"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset=utf-8">
<title>Insert title here</title>
</head>
<body>

<%
List<House> list = (List<House>)request.getAttribute("comlist");
%>

<table border="1">

<tr>
<td>房屋编号</td>
<td>房屋地址</td>
<td>房屋面积</td>
<td>房屋类型</td>
<td>社区名称</td>
<td>街道名称</td>

</tr>

<%
for(House com:list){
%>

<tr>
<td><%=com.getHouseid()%></td>
<td><%=com.getHouseaddress()%></td>
<td><%=com.getHousearea()%></td>
<td><%=com.getHousetype()%></td>
<td><%=com.getHousecommunity()%></td>
<td><%=com.getHousestreet()%></td>

</tr>
<%
}
%>


</table>
<!-- 插入4个超链接，内容为增删改查，其中trget="right" -->
<a href="right/house/addHouse.jsp" target="right">增加房屋</a>
<a href="right/house/deleteHouse.jsp" target="right">删除房屋</a>
<a href="right/house/updateHouse.jsp" target="right">修改房屋</a>
<a href="right/house/queryHouse.jsp" target="right">查询房屋</a>


</body>
</html>