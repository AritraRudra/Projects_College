<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="false" %>
<%@page import="java.util.ArrayList,java.util.Iterator,taking_test.QuestionBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Examination In Progress ... </title>
</head>
<body>

<table width="1280" height="797" border="0" cellspacing="2">
  <tr>
    <td background="DESIGN-INF/top.jpg" width="1280" height="150">
	<%	
		HttpSession session=request.getSession(false); 
		if (session == null) {   
			response.sendRedirect("index.jsp");
		}else {			 
			String sess_uname=(String)session.getAttribute("SESS_USER");
			int i=0,last_question;
			int count=(Integer)session.getAttribute("COUNT");
	%>
		<div align="right"><font size="3" color="red"><input type="button" value=" <%= sess_uname %>" disabled="disabled"/></font></div>
   	 	<form action="index.jsp" method="post"><div align="right"><input type="submit" name="logout" value="SIGN OUT" /></div></form>
	</td>
  </tr>
  <%
  	/*ArrayList<QuestionBean> question=(ArrayList<QuestionBean>)request.getAttribute("QUESTION_SET"); 
  	Iterator<QuestionBean> itr=question.iterator();
  	int i=1;
	if(itr.hasNext()){*/
	QuestionBean question=(QuestionBean)request.getAttribute("THIS_QUESTION");	
	String level=question.getLevel();
	if(level.equalsIgnoreCase("EASY"))	{last_question=9;}
	else last_question=19;
  %>
  <tr>
  	<td align="center" height="200"><img src="DESIGN-INF/examinprogress.jpg" width="360" height="200" align="absmiddle">
  </tr>
  <tr>
  <td width="1280" height="400">
   <form method="post" action="TakeTestServlet">
	  <table align="center" border="2" width="600" cellspacing="2" cellpadding="5" bordercolor="#00FF00">
		<tr><td width="600"colspan=2 align="center"><h3><%= question.getSubject() %></h3></td></tr>
		<tr><td align="center"><%=(count+1)%></td><td width="575"><h1><%= question.getQuestion() %></h1></td></tr>
		<tr>			
			<td>a. <input type="radio" name="answer" value="<%= question.getOpt1() %>"/></td>
			<td width="575"><%= question.getOpt1() %></td>
		</tr>
		<tr>			
			<td>b. <input type="radio" name="answer" value="<%= question.getOpt2() %>"/></td>
			<td width="575"><%= question.getOpt2() %></td>
		</tr>
		<tr>			
			<td>c. <input type="radio" name="answer" value="<%= question.getOpt3() %>"/></td>
			<td width="575"><%= question.getOpt3() %></td>
		</tr>
		<tr>
			<td>d. <input type="radio" name="answer" value="<%= question.getOpt4() %>"/></td>
			<td width="575"><%= question.getOpt4() %></td>
		</tr>
             
        <tr>
        <% if(count==last_question) {  %>
        	<td><input type="submit" value="SUBMIT"></td>
			
        <%  }else{  %>
        	<td><input type="submit" value="NEXT"></td>
		
        </tr>
        <% }
		
        	count++; 
        	session.setAttribute("COUNT",count);
        	session.setAttribute("CORR_ANS",question.getCorr_opt());
		}//End of session checking
		%>
       </table>	
       </form>
	</td>
  </tr>
  
  <tr>
    <td background="DESIGN-INF/buttom.jpg" width="1280" height="87"></td>
  </tr>
</table>

</body>
</html>