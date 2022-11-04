package tw.FinalProject.common;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





@WebServlet("/RegisterCheck.do")
public class RegisterCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Map<String,String> MemberData=new HashMap<String,String>();
	public ArrayList<String> accountList=new ArrayList<>();
	public ArrayList<String> UserNameList=new ArrayList<>();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request,response);
	}
	
	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		String UserName=request.getParameter("username");
		String newAccount=request.getParameter("newaccount");
		String newPwd1=request.getParameter("newpassword1");
		String newPwd2=request.getParameter("newpassword2");
		accountList.clear();
		UserNameList.clear();
		
		//connect to database
		String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String dbURL="jdbc:sqlserver://192.168.100.200:1433;DatabaseName=Dynamic_FinalProject";
		String userName="Ho0212";
		String userPwd="malone0212";
		try {
	    	Class.forName(driverName);
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
	    try {
	    	Connection conn=DriverManager.getConnection(dbURL,userName,userPwd);
	    	java.sql.Statement statement=conn.createStatement();
	    	String sqlAccount="select Account from MemberData where Account='"+newAccount+"'";
	    	ResultSet rsAccount=statement.executeQuery(sqlAccount);
	    	if(rsAccount.next()) {
	    		accountList.add(rsAccount.getString("Account"));
	    	}
	    	String sqlUserName="select Password from MemberData where UserName='"+UserName+"'";
	    	ResultSet rsUserName=statement.executeQuery(sqlUserName);
	    	if(rsUserName.next()) {
	    		UserNameList.add(rsUserName.getString("Password"));
	    	}
	    	rsUserName.close();
	    	rsAccount.close();
	    	statement.close();
	    	conn.close();
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
	    
	    //check new userName and new Account whether exist in current database
	    if(UserNameList.size()==0) {
	    	if(accountList.size()==0) {
	    		// distinguish all information are correct
				if(UserName.length()>0 && newAccount.length()>0) {
					if(newPwd1.equals(newPwd2)) {
						// connect to database and add data into database
					    try {
					    	Class.forName(driverName);
					    }catch(Exception e) {
					    	e.printStackTrace();
					    }
					    try {
					    	Connection conn=DriverManager.getConnection(dbURL,userName,userPwd);
					    	PreparedStatement statement=conn.prepareStatement("insert into MemberData(UserName,Account,Password) values(?,?,?)");
					    	statement.setString(1,UserName);
					    	statement.setString(2,newAccount);
					    	statement.setString(3,newPwd1);
					    	statement.execute();
					    	statement.close();
					    	conn.close();
					    	
					    }catch(Exception e) {
					    	e.printStackTrace();
					    }
						response.sendRedirect("http://localhost:8080/FinalProject/RegisterSuccess.html");
					}else {
						response.sendRedirect("http://localhost:8080/FinalProject/ErrorRegisterPage.html");
					}
				}else {
					response.sendRedirect("http://localhost:8080/FinalProject/ErrorRegisterPage.html");
				}
	    	}else {
	    		response.sendRedirect("http://localhost:8080/FinalProject/AccountDuplicate.html");
	    	}
	    	
	    }else {
	    	response.sendRedirect("http://localhost:8080/FinalProject/UserNameDuplicate.html");
	    }
		
		
		
		
		
		
		
		
		
		
	}

}
