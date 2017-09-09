<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="false" import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.ArrayList"%>
<%@page import="subject_class.*"%>
<%@page import="student_class.*"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%	
		HttpSession session=request.getSession(false); 
		if (session == null){ 
			response.sendRedirect("index.jsp");
		}else {
			StudentBean adminfo=(StudentBean)session.getAttribute("ADMINFO");
			Date logindate=(Date)session.getAttribute("SESS_LOGIN_TIME_ADM");
	%>
	<table height="250" width="1350" background="DESIGN-INF/header-bg.jpg" colspan="2">
	<tr>
		<td width="1000">
			<table width="168">
			<tr>
				<td height="216">&nbsp;</td>
			    <td width="42"><a href="index.jsp" alt="Link to Home Page"><img src="Source/m1.jpg" width="42" height="260" /></a></td>
			    <td width="43"><img src="Source/m2.jpg" width="43" height="263" /></td>
			    <td width="43"><img src="Source/m3.jpg" width="43" height="260" /></td>
			    <td width="40"><img src="Source/m4.jpg" width="40" height="261" /></td>
			</tr>
			</table>	
		</td>
		<td width="350" align="center" valign="middle" >Logged in user : <%=adminfo.getUname() %><br>Login time : <%=logindate %><br>
			<form action="index.jsp" method="post"><div align="right"><input type="submit" name="logout" value="SIGN OUT" /></div></form>
		</td>
	</tr>
	</table>
	<form action="AdminControlServlet" method="post">
	<font color="GREEN" size="6"> WELCOME. . .  LOGIN SUCCESSFUL </font>
	<table width="1350" >
	<tr>
		<td width="675">
			<h1>Administrator Details. . .</h1>	
			<br><br>
			<table width="675" align="center">
				<tr>
				<td width="675"><font color="" size="4"><b>Your UserId is	:	</b></font><%=adminfo.getEmail() %></td>
				</tr>
				<tr>
				<td width="675"><font color="" size="4"><b>First Name	:	</b></font><%=adminfo.getFstname() %></td>
				</tr>
				<tr>
				<td width="675"><font color="" size="4"><b>Last Name	:	</b></font><%=adminfo.getLstname() %></td>
				</tr>
				<tr>
				<td width="675"><font color="" size="4"><b>Email Id	:	</b></font><%=adminfo.getEmail() %></td>
				</tr>
				<tr>
					<td height="10" width="675"></td>
				</tr>
				<tr>
					<td width="675" align="left"><input type ="submit" name="addsubject" value="ADD SUBJECT"></td>
				</tr>
				<tr>
					<td height="10" width="675"></td>
				</tr>
				<tr>
					<td height="10" width="675"></td>
				</tr>
				<tr>
					<td width="675"><input type ="submit" name="examdetails" value="Details of All Examinations"></td>
				</tr>
			</table>		
		</td>
		<td width="250">
			<table width="250" >
				<tr>
					<td width="250" ><font color="" size="5"> <b>Subject List. . . </b></font></td>
				</tr>
				<tr>
					<td><font size="3" ><b> Select a subject to add questions. . . </b></font></td>
				</tr>
				<%
					ArrayList<SubjectBean> list=(ArrayList<SubjectBean>)session.getAttribute("SUBJECTADM");
					for(SubjectBean sl: list){
				%>
				<tr>
					<td width="250">
						<input type="radio" name="subject" value="<%=sl.getSubject()%> "/><b><%= sl.getSubject() %></b>
					</td>
				</tr>
				<%}
					
				%>
				<tr>
					<td width="250" align="left"><input type="submit" name="addques" value="ADD"/></td>
				</tr>
			</table>
		</td>
	</tr>
	</table>
	<table width="1350">
		<tr>
			<td>
			</td>
		</tr>
	</table>
	<table height="173" width="1350" background="DESIGN-INF/bg_rest.jpg">
	<tr>
		<td height="173" width="1350"></td>
	</tr>
	</table>
	</form>
<%} %>
</body>
</html>