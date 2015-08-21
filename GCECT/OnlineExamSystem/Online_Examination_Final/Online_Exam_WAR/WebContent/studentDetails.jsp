<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="false" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.ArrayList"%>
<%@page import="history.*"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Test Details</title>
</head>
<body>
	<%	
		HttpSession session=request.getSession(false); 
		if (session == null){  
			response.sendRedirect("index.jsp");
		}else{		
			String sess_uname=(String)session.getAttribute("SESS_USER");
			
	%>
	<table height="250" width="1350" background="DESIGN-INF/header-bg.jpg">
		<tr>
			<td width="1000">
			<table width="168">
			<tr>
				<td height="216">&nbsp;</td>
			    <td width="42"><a href="" alt="Link to Home Page"><img src="Source/m1.jpg" width="42" height="260" /></a></td>
			    <td width="43"><img src="Source/m2.jpg" width="43" height="263" /></td>
			    <td width="43"><img src="Source/m3.jpg" width="43" height="260" /></td>
			    <td width="40"><img src="Source/m4.jpg" width="40" height="261" /></td>
			</tr>
			</table>	
		</td>
			<td width="350" align="center" valign="middle" >Logged in user : <%=sess_uname %><br>
				<form action="index.jsp" method="post"><div align="right"><input type="submit" name="logout" value="SIGN OUT" /></div></form>
			</td>
		</tr>
		</table>
	<%
		ArrayList<ResultHistoryBean> resultlist=(ArrayList<ResultHistoryBean>)request.getAttribute("ALL_RESULTS");		
	%>
	<table bgcolor="lightyellow" border="2" align="center">
		<tr>
			<th>Examination Id:</th>
			<th>Student Id:</th>			
			<th>Subject:</th>
			<th>Level:</th>
			<th>Marks:</th>
			<th>Date of Examination:</th>
		</tr>
		<% 
			for(ResultHistoryBean rb: resultlist)
			{
		%>
				<tr>
					<td><%=rb.getExamid() %></td>
					<td><%=rb.getSid() %></td>
					<td><%=rb.getSubject() %></td>
					<td><%=rb.getLevel() %></td>
					<td><%=rb.getMarks() %></td>
					<td><%=rb.getExam_date() %></td>
				</tr>
		<%} %>
	</table>		
	<table height="173" width="1350" background="DESIGN-INF/bg_rest.jpg">
		<tr>
			<td height="173" width="1350"></td>
		</tr>
		</table>
		<br><br>
		<form action="adminloginsuccess.jsp" method="post"><input type="submit" name="back" value="DONE" align="middle"/></form>
<%} %>
</body>
</html>