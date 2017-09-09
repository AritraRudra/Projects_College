<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="false" import=" java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SUCCESSFUL</title>
</head>
<body>

<table width="1280" height="887" border="0" cellspacing="0">
  <tr>
    <td background="DESIGN-INF/top.jpg" width="1280" height="150">
	<%	
		HttpSession session=request.getSession(false); 
	
		if (session == null) {		
			response.sendRedirect("index.jsp");
		}else {  
			String sess_uname=(String)session.getAttribute("SESS_USER");
			Date logindate=(Date)session.getAttribute("SESS_LOGIN_TIME");
	%>
	<div align="right"><font size="3" color="red"><input type="button" value=" <%= sess_uname %>" disabled="disabled"/></font></div>
    <form action="index.jsp" method="post"><div align="right"><input type="submit" name="logout" value="SIGN OUT" /></div></form>
    	</td>
    </tr>
    <tr>
   	  <td background="DESIGN-INF/top.jpg" width="1280" height="50">
    <div><font size="+3">
    	<table width="1200" height="50" border="0" align="center" cellspacing="0" cellpadding="0">
			<tr>
			    <td align="center" width="200"><form action="index.jsp"><input type="submit" value="HOME"/></form></td>
			    <td align="center" width="200"><form action="FAQ.jsp"><input type="submit" value="FAQ"/></form></td>
			    <td align="center" width="200"><form action="ruls_regs.jsp"><input type="submit" value="RULES & REGULATIONS"/></form></td>
			    <td align="center" width="200"><form action="aboutus.jsp"><input type="submit" width="" value="ABOUTUS"/></form></td>     	
		     </tr>
        </table>
    </font></div>
	</td>
  </tr>
  <tr>
      <td  width="1280" height="100"><font size="5" color="lightgreen" face="Comic Sans MS, cursive">WELCOME ... </font></td>
  </tr>
  <tr>
    <td align="center" width="1280" height="300">
   	<a href="SubjectSelectServlet"><img src="DESIGN-INF/takethetest.png" width="131" height="131"></td>
  </tr>
  <tr>
  	<td width="1280" height="200" align="center"><a href="StudentExamHistoryServlet"><u>
  	<font size="5" color="lightgreen" face="Comic Sans MS, cursive">VIEW YOUR EXAM HISTORY</font></u></a>
    </td>
  </tr>
  <tr>
    <td background="DESIGN-INF/buttom.jpg" width="1280" height="87"></td>
  </tr>
</table>
<% } %>

</body>
</html>