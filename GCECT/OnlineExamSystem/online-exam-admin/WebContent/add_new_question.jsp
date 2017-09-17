<%@page import="enums.QuestionEnums"%>
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
		String sess_subj=(String) session.getAttribute("SESS_SUBJ");
		try{
			if(sess_uname.equalsIgnoreCase("null"))
				response.sendRedirect("index.jsp");
		}catch(Exception e){
			response.sendRedirect("index.jsp");
		}
	%>
	<form action="AddQuestionServlet" method="post">
		<table height="250" width="1350" background="D:\project\header-bg.jpg" colspan="2">
		<tr>
			<td width="1000">	</td>
			<td width="350" align="center" valign="middle" >Logged in user : <%=sess_uname %><br>Login time : <%=logindate %></td>
		</tr>
		</table>
		<table width="1350" bgcolor="lightyellow" >
		<tr>
			<td width="1350" colspan="3"><font size="6" color=""><br><font size="4" color="green"><b><u>Subject is <%=sess_subj %></u></b></font><br><b><u>Enter the Question details</u></b></font><hr></td>
		</tr>
		<tr>
			<td width="80" height="80"><h3>Question	:</h3></td>
			<td width="820" height="80"><textarea name="<%=QuestionEnums.QUESTION.toString()%>" rows="6" cols="80"></textarea></td>
			<td width="450" height="80"><h2>Select Level</h2><br><input type="radio" name="level"  value="Easy"><b>Easy</b><br><input type="radio" name="level" value="Hard"><b>Hard</b></td>
		</tr>
		<tr>
			<td width="80" height="80"></td>
			<td  width="820" height="80"></td>
			<td width="450" height="80"><font size="4" color=""></font></td>
		</tr>
		<tr>
			<td width="900" height="320" colspan="2">
			<table width="900" height="320">
			<tr>
				<td width="80" height="80"><h3>Option A)	:</h3></td>
			<td  width="820" height="80"><textarea name="<%=QuestionEnums.OPTION_1.toString()%>" rows="4" cols="50"></textarea></td>
			</tr>
			<tr>
				<td width="80" height="80"><h3>Option B)	:</h3></td>
			<td  width="820" height="80"><textarea name="<%=QuestionEnums.OPTION_2.toString()%>" rows="4" cols="50"></textarea></td>
			</tr>
			<tr>
				<td width="80" height="80"><h3>Option C)	:</h3></td>
			<td  width="820" height="80"><textarea name="<%=QuestionEnums.OPTION_3.toString()%>" rows="4" cols="50"></textarea></td>
			</tr>
			<tr>
				<td width="80" height="80"><h3>Option D)	:</h3></td>
			<td  width="820" height="80"><textarea name="<%=QuestionEnums.OPTION_4.toString()%>" rows="4" cols="50"></textarea></td>
			</tr>
			</table>
			</td>
			<td width="450"height="320" colspan="2">
			</td>
		</tr>
		<tr>
			<td width="80" height="80"><h3>Correct Option	:</h3></td>
			<td width="820" height="80"><textarea name="<%=QuestionEnums.CORRECT_OPTION.toString()%>" rows="4" cols="50"></textarea></td>
			<td width="450" height="80"></td>
		</tr>
		<tr>
			<td width="80" height="80"></td>
			<td width="820" height="80"></td>
			<td width="450" height="80" align="left"><input type="submit" value="Confirm"></input></td>
		</tr>
		</table>
		<table height="173" width="1350" background="D:\project\bg_rest.jpg">
		<tr>
			<td></td>
		</tr>
		</table>
		</form>
</body>
</html>