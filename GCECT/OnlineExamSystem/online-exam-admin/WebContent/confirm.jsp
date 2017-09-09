<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="false" import="java.util.Date"%>
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
		String sess_q=(String)session.getAttribute("SESS_QUEST");
		String sess_subject=(String)session.getAttribute("SESS_SUBJ");
		String sess_l=(String)session.getAttribute("SESS_LEVEL");
		String sess_opt1=(String)session.getAttribute("SESS_OPT1");
		String sess_opt2=(String)session.getAttribute("SESS_OPT2");
		String sess_opt3=(String)session.getAttribute("SESS_OPT3");
		String sess_opt4=(String)session.getAttribute("SESS_OPT4");
		String sess_c=(String)session.getAttribute("SESS_CORRECT");
		try{
			if(sess_uname.equalsIgnoreCase("null"))
				response.sendRedirect("index.jsp");
		}catch(Exception e){
			response.sendRedirect("index.jsp");
		}
	%>
	<form action="ConfirmPageServlet" method="post">
		<table height="250" width="1350" background="D:\project\header-bg.jpg">
		<tr>
			<td width="1000">	</td>
			<td width="350" align="center" valign="middle" >Logged in user : <%=sess_uname %><br>Login time : <%=logindate %><br><input type="button" value="Sign out"></td>
		</tr>
		</table>
		<font size="6" color="green">Details of Question to be Added</font>
		<table width="1350" height="320">
			<tr>
				<td width="1350" height="40"><h3>Question:</h3><%=sess_q %></td>
			</tr>
			<tr>
				<td width="1350" height="40"><h3>Subject:</h3><%=sess_subject %></td>
			</tr>
			<tr>
				<td width="1350" height="40"><h3>Level:</h3><%=sess_l %></td>
			</tr>
			<tr>
				<td width="1350" height="40"><h3>Option A):</h3><%=sess_opt1 %></td>
			</tr>
			<tr>
				<td width="1350" height="40"><h3>Option B):</h3><%=sess_opt2 %></td>
			</tr>
			<tr>
				<td width="1350" height="40"><h3>Option C):</h3><%=sess_opt3 %></td>
			</tr>
			<tr>
				<td width="1350" height="40"><h3>Option D):</h3><%=sess_opt4 %></td>
			</tr>
			<tr>
				<td width="1350" height="40"><h3>Correct Option:</h3><%=sess_c %></td>
			</tr>
		</table>
		
		<br><br>
			<input type="submit" name="submit" value="OK! Submit"/><br><br>
			<a href="StudentLoginServlet">Back to home</a>
		<table height="173" width="1350" background="D:\project\bg_rest.jpg">
		<tr>
			<td height="173" width="1350"></td>
		</tr>
		</table>
	</form>	
</body>
</html>