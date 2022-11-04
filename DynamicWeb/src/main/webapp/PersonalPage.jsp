<%@page import="java.io.ObjectInputFilter.Status"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Personal Page</title>
<style>
legend {

 font-size:20pt;
	animation: borderMove 5s infinite alternate;
}

@keyframes borderMove {
	    from {
    margin-left:-40%;
  }

  to {
    margin-left: 40%;
  }
}

#intro {
	text-align: center;
	margin-left: auto;
	margin-right: auto;
	cursor: default;
}

table {
	margin-left: auto;
	margin-right: auto;
	text-allign: center;
}

td:hover {
	color:	#0000CD;
	background-color: #F0FFFF;
	cursor: default;
}

</style>
</head>
<link rel="stylesheet" href="FinalProject.css">
<%
	ArrayList<String> GenderList=new ArrayList<String>();
	ArrayList<String> BirthdayList=new ArrayList<>();
	ArrayList<String> BloodList=new ArrayList<>();
	ArrayList<String> SignList=new ArrayList<>();
	ArrayList<String> InterestList=new ArrayList<String>();
	ArrayList<String> BookList=new ArrayList<>();
	ArrayList<String> MovieList=new ArrayList<>();
	ArrayList<String> StarList=new ArrayList<String>();
	ArrayList<String> NationList=new ArrayList<>();
	ArrayList<String> CityList=new ArrayList<>();
	ArrayList<String> StatusList=new ArrayList<String>();
	ArrayList<String> EducationList=new ArrayList<>();
	ArrayList<String> EmailList=new ArrayList<>();
	ArrayList<String> IGList=new ArrayList<String>();
	ArrayList<String> AutoList=new ArrayList<>();
	GenderList.clear();
	BirthdayList.clear();
	BloodList.clear();
	SignList.clear();
	InterestList.clear();
	BookList.clear();
	MovieList.clear();
	StarList.clear();
	NationList.clear();
	CityList.clear();
	StatusList.clear();
	EducationList.clear();
	EmailList.clear();
	IGList.clear();
	AutoList.clear();
	
	String User=(String)request.getAttribute("User");
	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	String urlstr="jdbc:sqlserver://192.168.100.200:1433;databaseName=Dynamic_FinalProject;user=Ho0212;password=malone0212";
	Connection conn=DriverManager.getConnection(urlstr);
	Statement state=conn.createStatement();
	String sqlstr="select * from PersonalFile where UserName='"+User+"'";
	ResultSet rs=state.executeQuery(sqlstr);
	
	while(rs.next()){
		GenderList.add(rs.getString("Gender"));
		BirthdayList.add(rs.getString("Birthday"));
		BloodList.add(rs.getString("Blood"));
		SignList.add(rs.getString("Sign"));
		InterestList.add(rs.getString("Interest"));
		BookList.add(rs.getString("Book"));
		MovieList.add(rs.getString("Movie"));
		StarList.add(rs.getString("Star"));
		NationList.add(rs.getString("Nation"));
		CityList.add(rs.getString("City"));
		StatusList.add(rs.getString("Status"));
		EducationList.add(rs.getString("Education"));
		EmailList.add(rs.getString("Email"));
		IGList.add(rs.getString("IG"));
		AutoList.add(rs.getString("Autobiography"));
	}
	rs.close();
	state.close();
	conn.close();
	
	if(GenderList.size()==0){
		GenderList.add("");
		BirthdayList.add("");
		BloodList.add("");
		SignList.add("");
		InterestList.add("");
		BookList.add("");
		MovieList.add("");
		StarList.add("");
		NationList.add("");
		CityList.add("");
		StatusList.add("");
		EducationList.add("");
		EmailList.add("");
		IGList.add("");
		AutoList.add("");
	}
	
	
	String Gender=GenderList.get(0);
	String Birthday=BirthdayList.get(0);
	String Blood=BloodList.get(0);
	String Sign=SignList.get(0);
	String Interest=InterestList.get(0);
	String Book=BookList.get(0);
	String Movie=MovieList.get(0);
	String Star=StarList.get(0);
	String Nation=NationList.get(0);
	String City=CityList.get(0);
	String Status=StatusList.get(0);
	String Education=EducationList.get(0);
	String Email=EmailList.get(0);
	String IG=IGList.get(0);
	String auto=AutoList.get(0);
	
	
	request.setAttribute("Gender", Gender);
	request.setAttribute("Birthday", Birthday);
	request.setAttribute("Blood", Blood);
	request.setAttribute("Sign", Sign);
	request.setAttribute("Interest", Interest);
	request.setAttribute("Book", Book);
	request.setAttribute("Movie", Movie);
	request.setAttribute("Star", Star);
	request.setAttribute("Nation", Nation);
	request.setAttribute("City", City);
	request.setAttribute("Status", Status);
	request.setAttribute("Education", Education);
	request.setAttribute("Email", Email);
	request.setAttribute("IG", IG);
	request.setAttribute("auto", auto);
	
%>
	
<body>
<form action="SendProfileUserName.do" method='post'>
	<div id="banner">Say Something...</div>
	<p id="mainpagecenter">Personal Profiles</p>
	<div id="intro">
		<fieldset class="personal">
			<legend>個人資料</legend>
			
			<table>
				<tr>
					<td>使用者名稱：${User}</td>
				<tr>
					<td>性別：${Gender}</td>
				</tr>
				<tr>
					<td>生日：${Birthday}</td>
				</tr>
				<tr>
					<td>血型：${Blood}</td>
				</tr>
				<tr>
					<td>星座：${Sign}</td>
				</tr>
				<tr>
					<td>興趣：${Interest}</td>
				</tr>
				<tr>
					<td>國家：${Nation}</td>
				</tr>
				<tr>
					<td>城市：${City}</td>
				</tr>
				<tr>
					<td>感情狀況：${Status}</td>
				</tr>
				<tr>
					<td>學歷：${Education}</td>
				</tr>
			</table>
		</fieldset>
		<fieldset class="like">
			<legend>喜歡的東西</legend>
			<table>
				<tr>
					<td>喜歡的書籍：${Book}</td>
				</tr>
				<tr>
					<td>喜歡的電影：${Movie}</td>
				</tr>
				<tr>
					<td>喜歡的明星：${Star}</td>
				</tr>
			</table>
		</fieldset>
		<fieldset class="contact">
			<legend>聯絡方法</legend>
			<table>
				<tr>
					<td>Email：${Email}</td>
				</tr>
				<tr>
					<td>IG：${IG}</td>
				</tr>
			</table>
		</fieldset>
		<fieldset class="bio">
			<legend>自我介紹</legend>
			<table>
				<tr>
					<td>補充說明：${auto}</td>
				</tr>
			</table>
		</fieldset>
		<br /> <input class="btn_log" type="submit" value="Setting" /><br />
	</div>
</form>
<br>
<div style='text-align:center'>
<input type='button' class='btn_log' onclick="window.location.href='http://localhost:8080/FinalProject/MainPage.jsp'" value='回首頁'/>
</div>
</body>
</html>