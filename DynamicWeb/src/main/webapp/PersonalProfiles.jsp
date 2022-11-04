<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Profiles</title>
</head>
<link rel="stylesheet" href="FinalProject.css">
<style>
legend{
  font-weight:bolder;
  font-family:Times New Roman;
  font-size:16pt;
  text-decoration:underline;
}
fieldset{
  font-weight:bold;
  font-family:Times New Roman;
  padding-left: 9rem;
  margin: 1rem 0;
  position: relative;
}
fieldset::before{
  position: absolute;
  content: '';
  width: 0; height: 0; top: 0; left: 0;
  border-width: 1rem;
  border-color: currentColor;
  border-style: solid;
  border-bottom-color: transparent;
  border-right-color: transparent;
}
fieldset > legend{
  margin-left: -7rem;
  color: currentColor;
  padding: 0 1rem;
  line-height: 1;
}
.personal{
  border-color: #FFBB00;
  color: #BBBB00;
}
.contact{
 border-color: #8B0000 ;
  color: #DC143C;
}
.like{
border-color:#0000CD;
  color: #191970;
}
.bio{
border-color:#2E8B57;
  color: #008080;
}
</style>
<body>
<%
	String User=(String)request.getAttribute("User");
%>
  <div id="banner">Say Something...</div>
  <p id="mainpagecenter">Edit Personal Profiles</p>
  <form action="PersonalProfilesServlet.do" method="get">
	<fieldset class='personal'>
		<legend>Personal</legend>
		Name：<input type="text" name="userName" value="${User}" maxlength="12" size="14" readonly='readonly'/><br/><br/>
		Gender：<input type="radio" name="gender" value="male"/>Male
		       <input type="radio" name="gender" value="female" checked="checked"/>Female<br/><br/>
		Birthday：<input type="date" name="mydate"/><br/><br/>
		Blood type：<input type="radio" name="blood" value="A" checked="checked"/>A
		       <input type="radio" name="blood" value="B"/>B
		       <input type="radio" name="blood" value="O"/>O
		       <input type="radio" name="blood" value="AB"/>AB<br/><br/>
		Sign:<input type='text' name='sign'><br><br>
		Interest：<input type="text" name="interest"/><br/><br/>
			
		Nation：<input type="text" name="nation"/>
		City：<input type="text" name="city"/><br/><br/>
		Relationship status：<input type="radio" name="status" value="single" checked="checked"/>Single
		       <input type="radio" name="status" value="inlove"/>In Love
		       <input type="radio" name="status" value="married"/>Married<br/><br/>
		Education：<input type="text" name="education"/><br/><br/>
	</fieldset>
	<fieldset class='contact'>
	  <legend>Contact</legend>
	  Email：<input type="email" name="email"/><br/><br/>
		IG：<input type="text" name="ig"/><br/><br/>
	</fieldset>
		<fieldset class='like'>
			<legend>Favorite</legend>
			Favorite Book：<input type="text" name="book" /><br/><br/>
			Favorite Movie：<input type="text" name="movie" /><br/><br/>
			Favorite Star：<input type="text" name="star" /><br/><br/>
		</fieldset>
		<fieldset class='bio'>
		<legend>Autobiography</legend>
		Comments：<textarea name="message" rows="10" cols="50">Introduce yourself</textarea><br/>
	</fieldset>
	<br/>
	<input class='btn_log' type="submit" value="Send"/>
	<input class='btn_log' type="reset" value="Reset"/>	  
  </form>
</body>
</html>