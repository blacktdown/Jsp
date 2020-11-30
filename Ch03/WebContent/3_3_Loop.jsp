<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>3-3</title>
</head>
<body>
	<h3>3-3.JSP 반복문</h3>
	
	<h4>for</h4>
	<%
		for(int i=1; i<=5; i++){
			out.println("<h4>for반복문 :" +i+ "</h4>");
		}
	
	%>
	<h4>while</h4>
	<%
		int i=1;
		while(i<=5){
	%>
		<span>while 반복문 : <%= i %></span>	
	<% 
		i++;
		} %>
	<h4>구구단</h4>
	<table border="1">
		<tr>
			<td>2단</td>		
			<td>3단</td>		
			<td>4단</td>		
			<td>5단</td>		
			<td>6단</td>		
			<td>7단</td>		
			<td>8단</td>		
			<td>9단</td>		
		</tr>
		
		<% for(int x=1; x<=9; x++){ %>
		<tr>
		<% for(int y=2; y<=9; y++){ %>
			<td><%= y %> x <%= x %> = <%=y*x %></td>
		<% } %>
		</tr>
		<% } %>
	</table>
	

</body>
</html>