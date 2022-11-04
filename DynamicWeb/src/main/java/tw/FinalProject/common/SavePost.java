package tw.FinalProject.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/SavePost.do")
public class SavePost extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public ArrayList<String> time=new ArrayList<>();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request,response);
	}

	
	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String newPost=request.getParameter("postbox");
		String postUser=request.getParameter("PostUser");
		
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
	    	
	    	
	    	String sqlstr="insert into ArticleData(UserName,Article) values(?,?)";
	    	PreparedStatement prestate = conn.prepareStatement(sqlstr);
	    	prestate.setString(1,postUser);
	    	prestate.setString(2, newPost);
	    	prestate.execute();
	    	prestate.close();
	    	conn.close();
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
	    
	    response.sendRedirect("http://localhost:8080/FinalProject/MainPage.jsp");
		
		
		
	}

}
