<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

<!-- 生成表单给servlet，表单字段包括-->
<form action="deleteHouse" method="post">
    <table>
        <tr>
            <td>房屋编号：</td>
            <td><input type="text" name="houseId" /></td>
        </tr>
        <tr>
            <td><input type="submit" value="提交" /></td>
            <td><input type="reset" value="重置" /></td>
        </tr>
    </table>
</form>

</body>
</html>