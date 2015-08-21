<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="false"%>
<%@page import="taking_test.QuestionBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%	
		HttpSession session=request.getSession(false); 
		if (session == null){			 
			response.sendRedirect("index.jsp");
		}else{
		
		String sess_uname=(String)session.getAttribute("SESS_USER");
		QuestionBean qb=(QuestionBean)session.getAttribute("QUES");				
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
			<td width="350" align="center" valign="middle" >Logged in user : <%=sess_uname %><br><form action="index.jsp" method="post"><div align="right"><input type="submit" name="logout" value="SIGN OUT" /></div></form></td>
		</tr>
		</table>
		<form action="ConfirmPageServlet" method="post">
		<font size="6" color="green">Details of Question to be Added</font>
		<table width="1350" height="320">
			<tr>
				<td width="1350" height="40"><h3>Question:</h3><%=qb.getQuestion() %></td>
			</tr>
			<tr>
				<td width="1350" height="40"><h3>Subject:</h3><%=qb.getSubject() %></td>
			</tr>
			<tr>
				<td width="1350" height="40"><h3>Level:</h3><%=qb.getLevel() %></td>
			</tr>
			<tr>
				<td width="1350" height="40"><h3>Option A):</h3><%=qb.getOpt1() %></td>
			</tr>
			<tr>
				<td width="1350" height="40"><h3>Option B):</h3><%=qb.getOpt2() %></td>
			</tr>
			<tr>
				<td width="1350" height="40"><h3>Option C):</h3><%=qb.getOpt3() %></td>
			</tr>
			<tr>
				<td width="1350" height="40"><h3>Option D):</h3><%=qb.getOpt4() %></td>
			</tr>
			<tr>
				<td width="1350" height="40"><h3>Correct Option:</h3><%=qb.getCorr_opt() %></td>
			</tr>
		</table>
		<br>
		<%if(qb.getQuestion().equals("")||qb.getLevel().equals("null")||qb.getOpt1().equals("")||qb.getOpt2().equals("")||qb.getOpt3().equals("")||qb.getOpt4().equals("")||qb.getCorr_opt().equals("")){%>
			<font size="5" color="red">The Above Field(s) Cannot Be Left Empty! Please Go Back And Try Again.</font>
		<%} else{%>
		<br><br>
			<input type="submit" name="submit" value="OK ! Submit"/><br><br><%} %>
			</form>	
			<br><br><form action="adminloginsuccess.jsp" method="post"><input type="submit" name="back" value="Back to home" /></form>
		<table height="173" width="1350" background="DESIGN-INF/bg_rest.jpg">
		<tr>
			<td height="173" width="1350"></td>
		</tr>
		</table>
	
	<%} %>
</body>
</html>