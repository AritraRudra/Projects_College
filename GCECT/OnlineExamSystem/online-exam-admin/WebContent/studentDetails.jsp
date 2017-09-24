<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="false" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.ArrayList"%>
<%@page import="allhistory.ResultBean"%>
<%@page import="allhistory.ExamQuestion"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%	
		HttpSession session=request.getSession(false); 
		Date logindate=(Date)session.getAttribute("SESS_LOGIN_TIME");
		String sess_uname=(String)session.getAttribute("SESS_USER");
		String sess_subj=(String) session.getAttribute("SESS_SUBJ");
		try{
			if(sess_uname.equalsIgnoreCase("null"))
				response.sendRedirect("index.jsp");
		}catch(Exception e){
			response.sendRedirect("index.jsp");
		}
	%>
	<table height="250" width="1350" background="D:\project\header-bg.jpg">
		<tr>
			<td width="1000">	</td>
			<td width="350" align="center" valign="middle" >Logged in user : <%=sess_uname %><br>Login time : <%=logindate %><br></td>
		</tr>
		</table>
	<%
		ArrayList<ResultBean> list=(ArrayList<ResultBean>)request.getAttribute("RESULT");		
	%>
	<table bgcolor="lightyellow" border="2">
		<tr>
			<th>Examination d:</th>
			<th>Student Id:</th>
			<th>Student User-name:</th>
			<th>Subject:</th>
			<th>Level:</th>
			<th>Marks:</th>
			<th>Date of Examination:</th>
		</tr>
		<% 
			for(ResultBean rb:list)
			{
		%>
				<tr>
					<td><%=rb.getExamId() %></td>
					<td><%=rb.getStudentId() %></td>
					<td><%=rb.getUserName() %></td>
					<td><%=rb.getSubject() %></td>
					<td><%=rb.getLevel() %></td>
					<td><%=rb.getMarks() %></td>
					<td><%=rb.getDate_time() %></td>
				</tr>
		<%} %>
	</table>		
	<table height="173" width="1350" background="D:\project\bg_rest.jpg">
		<tr>
			<td height="173" width="1350"></td>
		</tr>
		</table>
</body>
</html>