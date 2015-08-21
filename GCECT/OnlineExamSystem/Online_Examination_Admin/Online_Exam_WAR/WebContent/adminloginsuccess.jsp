<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="false" import="java.util.ArrayList,java.util.Date" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="login.AdminBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="login.SubjectList"%><html>
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
<form action="AdminControlServlet" method="post">
	
	<table height="250" width="1350" background="D:\project\header-bg.jpg" colspan="2">
	<tr>
		<td width="1000">	</td>
		<td width="350" align="center" valign="middle" >Logged in user : <%=sess_uname %><br>Login time : <%=logindate %><br><input type="button" value="Sign out"></td>
	</tr>
	</table>
	<font color="GREEN" size="6"> WELCOME. . .  LOGIN SUCCESSFULL </font>
	<table width="1350" >
	<tr>
		<td width="675">
			<%AdminBean ab=(AdminBean)request.getAttribute("ADMINISTRATOR");%>
			<h1>Administrator Details. . .</h1>	
			<br><br>
			<table width="675" align="center">
				<tr>
				<td width="675"><font color="" size="4"><b>Your UserId is	:	</b></font><%=ab.getAdminid() %></td>
				</tr>
				<tr>
				<td width="675"><font color="" size="4"><b>First Name	:	</b></font><%=ab.getFstname() %></td>
				</tr>
				<tr>
				<td width="675"><font color="" size="4"><b>Last Name	:	</b></font><%=ab.getLstname() %></td>
				</tr>
				<tr>
				<td width="675"><font color="" size="4"><b>Email Id	:	</b></font><%=ab.getLoginid() %></td>
				</tr>
				<tr>
					<td height="10" width="675"></td>
				</tr>
				<tr>
					<td width="675" align="left"><a href="addsubject.jsp">Add New Subject</a></td>
				</tr>
				<tr>
					<td height="10" width="675"></td>
				</tr>
				<tr>
					<td height="10" width="675"></td>
				</tr>
				<tr>
					<td width="675"><a href="AllExamHistoryServlet">Details of All Examinations</a></td>
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
					ArrayList<SubjectList> list=(ArrayList<SubjectList>)request.getAttribute("SUBJECT");
					for(SubjectList sl: list){
				%>
				<tr>
					<td width="250">
						<input type="radio" name="subject" value="<%=sl.getSubject()%> "/><b><%= sl.getSubject() %></b>
					</td>
				</tr>
				<%}
					
				%>
				<tr>
					<td width="250" align="left"><input type="submit" name="Submit" value="Add Question to the Selected Subject"></input></td>
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
	<table height="173" width="1350" background="D:\project\bg_rest.jpg">
	<tr>
		<td height="173" width="1350"></td>
	</tr>
	</table>
</form>
</body>
</html>