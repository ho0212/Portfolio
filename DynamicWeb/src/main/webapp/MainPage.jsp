<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<style>
#hello_user {
	border: none;
	outline: none;
	background: none;
	cursor: pointer;
	color: #0000EE;
	padding: 0;
	text-decoration: underline;
	font-family: inherit;
	font-size: inherit;
}


td:nth-child(even) {
	background-color: #886600;
	color: #FFFFE0;
}

td:nth-child(odd) {
	background-color: #FFFFE0;
	color: #886600;
}

td:hover {
	color: #483D8B;
	background-color: #F0FFFF;
	cursor: default;
}

table {
	text-align: left;
	font-size: 14pt;
	width:100%;
	height:100px;
}



#input_box {
	font-size: 12pt;
	position: absolute;
	top: 100;
	bottom: 0;
	left: 0;
	right: 0;
	margin: auto;
	height: 120px;
}

.div1 {
	text_align:left;
	width: 10%;
}

.div2 {
	text_align:center;
	width:80%;
}

.div3 {
	width: 10%;
	text_align:right;
}
</style>
<head>
<meta charset="UTF-8">
<title>Say Something...</title>
</head>
<link rel="stylesheet" href="FinalProject.css">
<body>
	<%
	ArrayList<String> UserList = new ArrayList<String>();
	ArrayList<String> ArticleList = new ArrayList<>();
	ArrayList<String> TimeList = new ArrayList<>();

	UserList.clear();
	ArticleList.clear();
	TimeList.clear();

	String User = (String) request.getAttribute("User");

	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	String urlstr = "jdbc:sqlserver://192.168.100.200:1433;databaseName=Dynamic_FinalProject;user=Ho0212;password=malone0212";
	Connection conn = DriverManager.getConnection(urlstr);

	Statement state = conn.createStatement();
	String sqlstr = "select * from ArticleData";
	ResultSet rs = state.executeQuery(sqlstr);
	while (rs.next()) {
		UserList.add(rs.getString("UserName"));
		ArticleList.add(rs.getString("Article"));
		TimeList.add(rs.getString("PostTime"));
	}
	rs.close();
	state.close();
	conn.close();
	%>
	<div id="banner">Say Something...</div>
	<br />
	<form action="SendUserName.do" method='post'>
		<div id="mainpagecenter" style="float: left">
			<b>你好，</b>
			<button type='submit' id='hello_user' name='hello_user'
				value='${User}'>${User}</button>
		</div>
		

	</form>
	<form action='LogOutServlet.do' method='post'>
		<div id="mainpageright">
			<input class='btn_out' type='submit' value='Log Out' style="float: right;">
		</div>
	</form>
	<h3 id="mainpageleft">留言區</h3>
	<hr>

	<%
	for (int i = 0; i <= UserList.size() - 1; i++) {
	%><table>
		<tr>
			<td class="div1"><%=UserList.get(i)%>
			</td>

			<td class="div2"><%=ArticleList.get(i)%></td>

			<td class="div3"><%=TimeList.get(i)%></td>
		</tr>
	</table>


	<%
	}
	%>
	<form action="SavePost.do" method='post'>
		<br />
		<div id="mainpageright">
			<input name='PostUser' style="visibility: hidden" value='${User}'>
			<input class="btn_log" type='submit' value='post'>
			<textarea id='input_box' name='postbox'>輸入內容...</textarea>
		</div>
	</form>

</body>
</html>