<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="false" import="java.util.ArrayList,java.sql.Date,history.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>YOUR RESULT</title>
</head>
<body>

<table width="1280" height="787" border="0" cellspacing="2">
  <tr>
    <td background="DESIGN-INF/top.jpg" width="1280" height="150">
	<%
		HttpSession session=request.getSession(false); 
		
		if(session==null){			
			response.sendRedirect("index.jsp");			
		}else{
			String sess_uname=(String)session.getAttribute("SESS_USER");		
	%>
	<div align="right"><font size="3" color="red"><input type="button" value=" <%= sess_uname %>" disabled="disabled"/></font></div>
    <form action="index.jsp" method="post"><div align="right"><input type="submit" name="logout" value="SIGN OUT" /></div></form>
	</td>
  </tr>
  <tr>
  	<td align="center" width="1280" height="200"><img src="DESIGN-INF/yesican.jpg" width="300" height="200"></td>
  </tr>
	<%	
		try{
			int curr_result= (Integer)session.getAttribute("CURR_RESULT");
			session.removeAttribute("CURR_RESULT");			
	 %>
	
	<tr>
		<td  align="center" width="1280" height="300">
			<font color="red" size="5">
				YOUR SCORE IS :	<%= curr_result %></font>
		</td>
	</tr>
	<%		
		}catch(Exception e){		
	%>
	
	<tr>
		<td width="1280" height="300">
	<% ArrayList<ResultHistoryBean> resultlist=(ArrayList<ResultHistoryBean>)request.getAttribute("RESULT_LIST"); %>
		<table width="640" border="1" bgcolor="lightgreen" align="center">
			<tr>
				<td align="center" width="200">Subject Name</td>
				<td align="center" width="70">Level</td>
				<td align="center" width="70">Marks</td>
				<td align="center" width="100">Exam Date</td>
			</tr>
	<%	for(ResultHistoryBean result : resultlist){ %>
			
			<tr>
				<td align="center"><%=result.getSubject() %></td>	
				<td align="center"><%=result.getLevel() %></td>	
				<td align="center"><%=result.getMarks() %></td>				
				<td align="center"><%=result.getExam_date() %></td>
				
			</tr>			
	<%	}  %>
		</table>	
		</td>
	</tr>
	<%  }finally{   %>	
	<tr>
		<td width="1280" height="300" align="center">	
		<form action="success.jsp" method="post">
		<div align="center"><input type="submit" name="done" value="OK" align="middle"></div>
		</form> 
	<% 		
			}//End of finally
		//}//End of else in session valid (inner else)
	}//End of outer else
	%>
		</td>
	</tr>
	<tr>
    <td background="DESIGN-INF/buttom.jpg" width="1280" height="87"></td>
  </tr>
</table>
    
</body>
</html>