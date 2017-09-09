<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="false" import="java.util.ArrayList,subject_class.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Choose Exam</title>
</head>
<body>

<table width="1280" height="787" border="0" cellspacing="2">
  <tr>
    <td background="DESIGN-INF/top.jpg" width="1280" height="150">
		<%	
			HttpSession session=request.getSession(false); 
							
			if (session == null) {			 
				response.sendRedirect("index.jsp");
			}else {						
				String sess_uname=(String)session.getAttribute("SESS_USER");
		%>
		<div align="right"><font size="3" color="red"><input type="button" value=" <%= sess_uname %>" disabled="disabled"/></font></div>
   		<form action="index.jsp" method="post"><div align="right"><input type="submit" name="logout" value="SIGN OUT" /></div></form>
	</td>
  </tr>
  <tr>
    <td width="1280" height="350">
    <form action="TakeTestServlet" method="post">
    	<table  align="center" width="500" border="1" bgcolor="lightgreen">
    	
		<% ArrayList<SubjectBean> sublist=(ArrayList<SubjectBean>)request.getAttribute("SUBJECT_LIST"); %>
		
			<tr>
				<td align="center" width="50">Choice</td>
				<td align="center" width="300">Subject Name</td>
				<td align="center" width="70">EASY</td>
				<td align="center" width="70">HARD</td>
			</tr>
		<%	for(SubjectBean sub_b : sublist){	%>
			<tr>
				<td align="center"><input type="radio" name="subject" value="<%= sub_b.getSubject()  %>" /></td>
				<td><%=sub_b.getSubject() %></td>
				<td align="center"><input type="radio" name="level" value="easy" /></td>
				<td align="center"><input type="radio" name="level" value="hard"/></td>
			</tr>
		<%		}
			}
		%>
		</table><br>
        <div align="center">
			<input type="submit" value="OK">
        </div>
		</form>	
    <div align="center"><marquee behavior="alternate"onmouseover=this.stop()
onmouseout=this.start()><font size="+2" face="Comic Sans MS, cursive"><a href="rules_regs.jsp"><u>READ RULES & REGULATIONS BEFORE BEGINNING THE TEST</u></a></font></marquee></div>
    </td>
  </tr>  
  <tr>  
      <td  width="1280" height="200"><img src="DESIGN-INF/questions.jpg" width="300" height="200" align="left"><img src="DESIGN-INF/mousecheckboxes.jpg" width="300" height="200" align="right"></td>
  </tr>
    <td background="DESIGN-INF/buttom.jpg" width="1280" height="87"></td>
  </tr>
</table>

</body>
</body>
</html>