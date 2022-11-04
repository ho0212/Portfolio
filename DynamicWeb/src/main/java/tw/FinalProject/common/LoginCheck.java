package tw.FinalProject.common;

import java.beans.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.PublicKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("unused")
@WebServlet("/LoginCheck.do")
public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ArrayList<String> errorMsg = new ArrayList<String>();
	private RequestDispatcher rd;
	public ArrayList<String> accountList = new ArrayList<>();
	public ArrayList<String> pwdList = new ArrayList<>();
	public ArrayList<String> UserList = new ArrayList<>();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String Account = request.getParameter("account");
		String Password = request.getParameter("password");
		errorMsg.clear();
		accountList.clear();
		pwdList.clear();
		UserList.clear();

		// connect to database
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String dbURL = "jdbc:sqlserver://192.168.100.200:1433;DatabaseName=Dynamic_FinalProject";
		String userName = "Ho0212";
		String userPwd = "malone0212";
		try {
			Class.forName(driverName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Connection conn = DriverManager.getConnection(dbURL, userName, userPwd);
			java.sql.Statement statement = conn.createStatement();
			String sqlAccount = "select Account from MemberData where Account='" + Account + "'";
			ResultSet rsAccount = statement.executeQuery(sqlAccount);
			if (rsAccount.next()) {
				accountList.add(rsAccount.getString("Account"));
			}
			String sqlPwd = "select Password from MemberData where Password='" + Password + "'";
			ResultSet rsPwd = statement.executeQuery(sqlPwd);
			if (rsPwd.next()) {
				pwdList.add(rsPwd.getString("Password"));
			}
			String sqlUser = "select UserName from MemberData where Account='" + Account + "'";
			ResultSet rsUser = statement.executeQuery(sqlUser);
			if (rsUser.next()) {
				UserList.add(rsUser.getString("UserName"));
			}
			rsPwd.close();
			rsAccount.close();
			statement.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// verify account and password
		if (accountList.size() != 0) {
			if (pwdList.size() != 0) {
				request.getSession().setAttribute("User", UserList.get(0));

			} else {
				errorMsg.add("Your account or password is not correct.");
			}
		} else {
			errorMsg.add("Your account or password is not correct.");
		}

		if (errorMsg.size() == 0) {
			request.getSession().setAttribute("User", UserList.get(0));
			rd = request.getRequestDispatcher("/MainPage.jsp");
		} else {
			rd = request.getRequestDispatcher("ErrorServlet.do");
		}
		rd.forward(request, response);

	}

}
