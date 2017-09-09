<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="false"%>
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
			String sess_subj=(String) session.getAttribute("SESS_SUBJ");
	%>			
		<table height="250" width="1350" background="DESIGN-INF/header-bg.jpg" colspan="2">
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
			<td width="350" align="center" valign="middle" >Logged in user : <%=sess_uname %><br>
				<form action="index.jsp" method="post">
					<div align="right"><input type="submit" name="logout" value="SIGN OUT" /></div>
				</form>
			</td>					
		</tr>
		</table>
		<form action="AddQuestionServlet" method="post">
		<table width="1350" bgcolor="lightyellow" >
		<tr>
			<td width="350" colspan="3"><font size="7" color="green"><b><u>Subject is <%=sess_subj %></u></b></font><br><br><b><u>Enter the Question details</u></b><hr></hr></td>
			<td width="1000" align="left"></td>
		</tr>
		<tr>
			<td width="80" height="80"><h3>Question	:</h3></td>
			<td width="820" height="80"><textarea name="question" rows="6" cols="80"></textarea></td>
			<td width="450" height="80"><h2>Select Level</h2><br><input type="radio" name="level"  value="EASY"><b>Easy</b><br>
			<input type="radio" name="level" value="HARD"><b>Hard</b></td>
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
			<td  width="820" height="80"><textarea name="opt1" rows="4" cols="50"></textarea></td>
			</tr>
			<tr>
				<td width="80" height="80"><h3>Option B)	:</h3></td>
			<td  width="820" height="80"><textarea name="opt2" rows="4" cols="50"></textarea></td>
			</tr>
			<tr>
				<td width="80" height="80"><h3>Option C)	:</h3></td>
			<td  width="820" height="80"><textarea name="opt3" rows="4" cols="50"></textarea></td>
			</tr>
			<tr>
				<td width="80" height="80"><h3>Option D)	:</h3></td>
			<td  width="820" height="80"><textarea name="opt4" rows="4" cols="50"></textarea></td>
			</tr>
			</table>
			</td>
			<td width="450"height="320" colspan="2">
			</td>
		</tr>
		<tr>
			<td width="80" height="80"><h3>Correct Option	:</h3></td>
			<td width="820" height="80"><textarea name="corr_opt" rows="4" cols="50"></textarea></td>
			<td width="450" height="80"></td>
		</tr>
		<tr>
			<td width="80" height="80"></td>
			<td width="820" height="80"></td>
			<td width="450" height="80" align="left"><input type="submit" value="Confirm"></input></td>
		</tr>
		</table>
		</form>
		<table height="173" width="1350" background="DESIGN-INF/bg_rest.jpg">
		<tr>
			<td></td>
		</tr>
		</table>
		
<%} %>
</body>
</html>