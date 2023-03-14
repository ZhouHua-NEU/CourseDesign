<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

<!-- 生成表单给servlet，表单字段包括户主姓名，户主电话，户主身份证号，户主所属房屋编号-->
<form action="updateHousehold" method="post">
    户主姓名：<input type="text" name="householdName" value="${household.householdName}"/><br/>
    户主电话：<input type="text" name="householdPhone" value="${household.householdPhone}"/><br/>
    户主身份证号：<input type="text" name="householdId" value="${household.householdId}"/><br/>
    户主所属房屋编号：<input type="text" name="householdHouseId" value="${household.householdHouseId}"/><br/>
    <input type="hidden" name="householdId" value="${household.householdId}"/>
    <input type="submit" value="修改"/>
</form>




</body>
</html>