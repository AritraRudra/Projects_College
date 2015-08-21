<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="false" import="java.util.ArrayList,java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%	
		HttpSession session=request.getSession(false); 
		Date logindate=(Date)session.getAttribute("SESS_LOGIN_TIME");
		String sess_uname=(String)session.getAttribute("SESS_USER");
		
		try{
			if(sess_uname.equalsIgnoreCase("null"))
				response.sendRedirect("index.jsp");
		}catch(Exception e){
			response.sendRedirect("index.jsp");
		}
	%>
	<form action="AddSubjectServlet" method="post">
		<table height="250" width="1350" background="D:\project\header-bg.jpg">
		<tr>
			<td width="1000">	</td>
			<td width="350" align="center" valign="middle" >Logged in user : <%=sess_uname %><br>Login time : <%=logindate %><br></td>
		</tr>
		</table>
		<table width="1350" height="320">
			<tr>
				<td width="1350" height="80"><h2>Add new Subject</h2></td>
			</tr>
			<tr>
				<td width="1350" height="80">
				New Subject Name	:	<input type="text" name="new_subject"/>
				</td>
			</tr>
			<tr>
				<td width="1350" height="80">
					<br><br><input type="submit" value="Add Question"></input>
				</td>
			</tr>
		</table>
		<table height="173" width="1350" background="D:\project\bg_rest.jpg">
		<tr>
			<td height="173" width="1350"></td>
		</tr>
		</table>
	</form>
</body>
</html>