<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>10-4</title>
</head>
<body>
	<h3>10-4. 세션 데이터 확인</h3>
	<%
		String sid = session.getId();
		Object name = session.getAttribute("name");
		String uid = (String) session.getAttribute("uid");
		String hp = (String) session.getAttribute("hp");
	%>
	
	<p>
		sid : <%= sid %> <br />
		name : <%= name.toString() %> <br />
		uid : <%= uid %> <br />
		hp : <%= hp %> <br />
	</p>

</body>
</html>