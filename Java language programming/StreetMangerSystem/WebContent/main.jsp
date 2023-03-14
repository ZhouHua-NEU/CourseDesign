<!-- 生成jsp模板 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<!-- 生成frameset页面，分左中右三栏 -->
<frameset rows="10%,90%">
    <frame src="title.jsp"/>
    <frameset cols="20%,40%,40%">
        <frame src="left.jsp" name="left" scrolling="auto" noresize="noresize" />
        <frame src="CommunityPresent" name="center" scrolling="auto" noresize="noresize" />
        <frame src="right/community/community_add.jsp" name="right" scrolling="auto" noresize="noresize" />
    </frameset>
</frameset>



</html>






