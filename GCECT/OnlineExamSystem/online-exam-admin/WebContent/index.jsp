<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
</head>
<body>
	<form action="StudentLoginServlet" method="post">
	<br><br>
	<TABLE border=0 cellSpacing=0 cellPadding=10 width="100%" bgcolor=#ffffff align=center>
	<tr>
	<td align="center" colspan="2"><h2>Administrator login :</h2> </td>
	</tr>
	<tr>
	<td align=right><b><font color=#df2332 size=4>Email Address:</font></b></td>
	<td><input type="text" name="loginid"></td>
	</tr>
	<tr>
	<td align=right><b><font color=#df2332 size=4>Password:</font></b></td>
	<td><input type="password" name="password"></td>
	</tr>
	
	</TABLE>
	<TABLE border=0 cellSpacing=0 cellPadding=10 width="100%" bgcolor=#ffffff align=center>
	<tbody>
	<tr>
	<td></td>
	<td align="center" colspan="4">
		 
		 <%
		 String errmsg1=(String)request.getAttribute("ERRORMSG1");
		 try{
			if(!(errmsg1.equalsIgnoreCase("null")))
		%>	 				
		 <label><font color="red">
		 	<%=  "INVALID COMBINATION OF USERNAME AND PASSWORD." %>
		 </font></label> <br><br>	
		<%	}catch(Exception e){
		%>
		 <label><font color="green"> <%=""  %> </font></label> <br><br>
		 <%
		 }	 
		 %>
	
		<input type="submit" value="Submit" />
		<input type="reset" value="Reset" />
		<input type="button" value="Abort" onClick="Abort()"/>
	
	</td>
	</tr>
	</TABLE>
	
	</form>
</body>
</html>