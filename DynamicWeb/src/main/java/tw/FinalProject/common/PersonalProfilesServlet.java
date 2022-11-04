package tw.FinalProject.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PersonalProfilesServlet.do")
public class PersonalProfilesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public ArrayList<String> UserNameList=new ArrayList<>();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request,response);
	}
	
	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html;charset=UTF-8");
		String UserName=request.getParameter("userName");
		String gender=request.getParameter("gender");
		String mydate=request.getParameter("mydate");
		String blood=request.getParameter("blood");
		String sign=request.getParameter("sign");
		String interest=request.getParameter("interest");
		String book=request.getParameter("book");
		String movie=request.getParameter("movie");
		String star=request.getParameter("star");
		String nation=request.getParameter("nation");
		String city=request.getParameter("city");
		String status=request.getParameter("status");
		String education=request.getParameter("education");
		String email=request.getParameter("email");
		String ig=request.getParameter("ig");
		String message=request.getParameter("message");
		
		
		
	
		
		
	
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
	    	String sqlUserName="select UserName from PersonalFile where UserName='"+UserName+"'";
	    	ResultSet rsUserName=statement.executeQuery(sqlUserName);
	    	if(rsUserName.next()) {
	    		UserNameList.add(rsUserName.getString("UserName"));
	    	}
	    	rsUserName.close();
	    	statement.close();
	    	
	    	if(UserNameList.size()==0) {
	    		PreparedStatement statement1 = conn.prepareStatement("insert into PersonalFile(UserName,Gender,Birthday,Blood,Sign,Interest,Book,Movie,Star,Nation,City,Status,Education,Email,IG,Autobiography) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
	    		statement1.setString(1, UserName);
	    		statement1.setString(2, gender);
	    		statement1.setString(3, mydate);
	    		statement1.setString(4, blood);
	    		statement1.setString(5, sign);
	    		statement1.setString(6, interest);
	    		statement1.setString(7, book);
	    		statement1.setString(8, movie);
	    		statement1.setString(9, star);
	    		statement1.setString(10, nation);
	    		statement1.setString(11, city);
	    		statement1.setString(12, status);
	    		statement1.setString(13, education);
	    		statement1.setString(14, email);
	    		statement1.setString(15, ig);
	    		statement1.setString(16, message);
	    		statement1.executeUpdate();
	    		statement1.close();
	    	}else {
	    		PreparedStatement statement2=conn.prepareStatement("update PersonalFile set Gender=?,Birthday=?,Blood=?,Sign=?,Interest=?,Book=?,Movie=?,Star=?,Nation=?,City=?,Status=?,Education=?,Email=?,IG=?,Autobiography=? where UserName=?");
    		statement2.setString(1, gender);
    		statement2.setString(2, mydate);
    		statement2.setString(3,blood);
    		statement2.setString(4,sign);
    		statement2.setString(5,interest);
    		statement2.setString(6,book);
    		statement2.setString(7,movie);
    		statement2.setString(8,star);
    		statement2.setString(9,nation);
    		statement2.setString(10,city);
    		statement2.setString(11,status);
    		statement2.setString(12,education);
    		statement2.setString(13,email);
    		statement2.setString(14,ig);
    		statement2.setString(15,message);
    		statement2.setString(16, UserName);
	    	statement2.executeUpdate();
	    	statement2.close();
	    	}
	    	
	    	
	    	conn.close();
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
		
		request.setAttribute("User", UserName);
		request.getRequestDispatcher("/PersonalPage.jsp").forward(request, response);
	}

}
