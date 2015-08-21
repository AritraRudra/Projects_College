<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>

<body background="DESIGN-INF\bg_home1.PNG" onload="MM_preloadImages('Source/m4.jpg')">
<p>&nbsp;</p>
<form action="StudentLoginServlet" method="post" >
<table width="1139" height="915" border="0">
  <tr>
    <td width="11" height="23">&nbsp;</td>
    <td colspan="2">&nbsp;</td>
    <td colspan="2">&nbsp;</td>
    <td width="8">&nbsp;</td>
    <td width="21">&nbsp;</td>
    <td width="93">&nbsp;</td>
    <td width="208">&nbsp;</td>
    <td width="16">&nbsp;</td>
    <td width="62">&nbsp;</td>
    <td width="101">&nbsp;</td>
    <td width="118">&nbsp;</td>
    <td width="210">&nbsp;</td>
  </tr>
  <tr>
    <td height="216">&nbsp;</td>
    <td width="45"><a href="home.html" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('Image12','','DESIGN-INF/m1 edited.jpg',0)"><img src="DESIGN-INF/m1 edited.jpg" name="Image12" width="45" height="262" border="0" id="Image12" /></a></td>
    <td width="45"><a href="#" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('Image11','','DESIGN-INF/m2 edited.jpg',0)"><img src="DESIGN-INF/m2 edited.jpg" name="Image11" width="45" height="262" border="0" id="Image11" /></a></td>
    <td width="45"><a href="FAQ.jsp" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('Image10','','DESIGN-INF/m3 edited.jpg',0)"><img src="DESIGN-INF/m3 edited.jpg" name="Image10" width="45" height="262" border="0" id="Image10" /></a></td>
    <td width="98"><a href="AboutUs.jsp" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('Image9','','DESIGN-INF/m4 edited.jpg',0)"><img src="DESIGN-INF/m4 edited.jpg" name="Image9" width="45" height="262" border="0" id="Image9" /></a><a href="AboutUs.jsp" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('Image9','','DESIGN-INF/m4.jpg',1)"></a></td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <% 
    	HttpSession session=request.getSession(false); 
		if (session== null) {
	%>
    <td>&nbsp;</td>
    <% }else{
			String sess_user=(String)session.getAttribute("SESS_USER");
	%>
    <td align="right"><font size="3" color="red"><input type="button" value="USER: <%= sess_user %>" disabled="disabled"/></font></td>
    <% } %>
  </tr>
  <tr>
    <td height="23">&nbsp;</td>
    <td colspan="2">&nbsp;</td>
    <td colspan="2">&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td height="21">&nbsp;</td>
    <td colspan="2">&nbsp;</td>
    <td colspan="2">&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td height="168">&nbsp;</td>
    <td colspan="2"><img src="DESIGN-INF/icon2.png" width="85" height="100" /></td>
    <td colspan="2"><p class="green"><a href="FAQ.jsp"><strong>F.A.Q.</strong></a></p>
    <p class="grey">Find Out the Rules &amp; Regulations</p></td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td><img src="DESIGN-INF/icon3.png" width="85" height="100" /></td>
    <td><p class="green"><strong>STUDENT's<br />INFORMATION</strong></p>
    <p class="grey">Rules & Regulations before Taking your Self-Test</p></td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td><img src="DESIGN-INF/icon1.png" width="85" height="100" /></td>
    <td><p class="green"><strong>REGISTER</strong></p>
    <p class="green"><span class="grey">Be a part of the e-Ex@m World Community</span></p></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td height="32">&nbsp;</td>
    <td colspan="2">&nbsp;</td>
    <td colspan="2">&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td height="110">&nbsp;</td>
    <td colspan="2">&nbsp;</td>
    <td colspan="2">&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td><img src="DESIGN-INF/img1.jpg" width="88" height="88" /></td>
    <%    	
		if (session == null) {  
			try{
	%>
    <td><strong><u>STUDENT LOGIN :</u></strong><br>
      <b>Email :</b><br><input type="text" name="email"><br><b>Password :</b><br><input type="password" name="passwd"><br>
    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="2">&nbsp;</td>
    <td colspan="2">&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>
	<%	String errmsg=(String)request.getAttribute("ERRORMSG");
			if(!(errmsg.equalsIgnoreCase("null")))					
	%>	 				
	 <label><font size="2" color="#99FF00"><%= errmsg %></font></label>
	 <% }catch(Exception e1){} %>
	<input type="submit" value="Submit" name="email"/>
	<input type="reset" value="Reset" name="passwd" />
    <br><font size="2" color="#99FF00">NEW USER ? <a href="signup.jsp">SignUp</a></font></td>
  </tr>
  <tr>
    <td height="112">&nbsp;</td>
    <td colspan="2">&nbsp;</td>
    <td colspan="2">&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td><img src="DESIGN-INF/img2.jpg" width="88" height="88" /></td>
    <td><strong><u>ADMINISTRATOR LOGIN :</u></strong><br>
    <b>Email :</b><br><input type="text" name="admemail"></br>
  <b>Password :</b><br><input type="password" name="admpasswd">
   </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="2">&nbsp;</td>
    <td colspan="2">&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td> 
    <%
    	try{
			String errmsgadm=(String)request.getAttribute("ERRORMSGADM");
		 		if(!(errmsgadm.equalsIgnoreCase("null")))
	%>	 				
	 <label><font size="2" color="#99FF00">	<%= errmsgadm %> </font></label>
	<%	 
	 		}catch(Exception e2){}
			
			
	%>
		<input type="submit" value="Submit" />
	<input type="reset" value="Reset" /></td>
  </tr>
  <%
  		}else{
			try{
				String logout=request.getParameter("logout");
				if(logout!=null){
					session.invalidate();					
					response.sendRedirect("index.jsp");
				}else{
					String sess_uname=(String)session.getAttribute("SESS_USER");
				}
			}catch(Exception e){
				session.invalidate();
				response.sendRedirect("index.jsp");
			}
		}
	%>
  <tr>
    <td>&nbsp;</td>
    <td colspan="2">&nbsp;</td>
    <td colspan="2">&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table> 
</form>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
</form>
</body>
</html>
