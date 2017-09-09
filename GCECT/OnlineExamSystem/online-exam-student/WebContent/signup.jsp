<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>REGISTRATION</title>
</head>
<body>

<table width="1280" height="787" border="0" cellspacing="2">
  <tr>
    <td background="DESIGN-INF/top.jpg" width="1280" height="150">		
	</td>
  </tr>
  <tr>
	<td align="left" height="100"><img src="DESIGN-INF/signup.jpg" width="300" height="150">
   </td>
  <tr>
    <td  align="center" width="1280" height="90%">
  	  <form action="SignUpServlet" method="post">
			<table height="50%%" width="400" border="1" bgcolor="lightgreen">
	    		<caption><b><u>REGISTRATION FORM</u></b></caption>
		    	<tr>
		      		<td width="140">USER NAME : </td>
		      		<td width="140" align="center"><input type="textbox"  name="uname"/></td>
		    	</tr>
		    	<tr>
		      		<td width="140">FIRST NAME : </td>
		      		<td width="140" align="center"><input type="text" name="fstname"/></td>
		    	</tr>
		    	<tr>
		      		<td width="140">LAST NAME : </td>
		      		<td width="140" align="center"><input type="text" name="lstname"/></td>
		      	</tr>
			    <tr>
		      		<td width="140">EMAIL ADDRESS:</td>
		      		<td width="140" align="center"> <input type="text" name="email"/></td>
		      	</tr>
		    	<tr>
		      		<td width="140">PASSWORD :</td>
		      		<td width="140" align="center"><input type="password" name="passwd"/></td>
		    	</tr>
		    	<!--<tr>  
		    		<td width="140">CONFIRM PASSWORD :</td>
		      		<td width="140" align="center"><input type="password" name="con_pass"/></td>
		    	</tr>-->
	   
 			</table><br>
    
	   <input type="SUBMIT" name="register" value="SUBMIT"/>&nbsp;
	    <input type="reset" name="reset" value="RESET"/>
	    
	</form>
	</td>
  </tr>
  <!--<tr>
      <td  width="1280" height="100"></td>
  </tr> -->
  <tr>
    <td background="DESIGN-INF/buttom.jpg" width="1280" height="87"></td>
  </tr>
</table>
</body>
</html>