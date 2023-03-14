<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.util.*,datastruct.HouseHold"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset=utf-8">
<title>Insert title here</title>
</head>
<body>

<%
List<HouseHold> list = (List<HouseHold>)request.getAttribute("comlist");
%>

<table border="1">
<tr>
<td>户主姓名</td>
<td>户主电话</td>
<td>户主身份证</td>
<td>户主房屋</td>
</tr>

<%
for(HouseHold com:list){
%>
<tr>
<td><%=com.getHouseholdname()%></td>
<td><%=com.getHouseholdphone()%></td>
<td><%=com.getHouseholdid()%></td>
<td><%=com.getHouseholdhousenumber()%></td>
</tr>
<%
}
%>
</table>
    <!-- 插入4个超链接，内容为增删改查，目标为right-->
    <a href="right/household/addHousehold.jsp" target="right">增加户主</a>
    <a href="right/household/deleteHousehold.jsp" target="right">删除户主</a>
    <a href="right/household/updateHousehold.jsp" target="right">修改户主</a>
    <a href="right/household/queryHousehold.jsp" target="right">查询户主</a>


</body>
</html>