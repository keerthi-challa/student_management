package com.Student.jdbc;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

/**
 * Servlet implementation class TestServlet
 */
public class TestServle extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @Resource(name="jdbc/StudentDB")
    private DataSource ds;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out =response.getWriter();
		response.setContentType("text/plain");
		Connection myConn=null;
		Statement myStmt=null;
		ResultSet myRs=null;
		//localhost:8080/Student/TestServlet
		try {
			myConn=ds.getConnection();
			String S="Select * from Tb";
			myStmt=myConn.createStatement();
			myRs=myStmt.executeQuery(S);
			
			while(myRs.next())
			{
				String E=myRs.getString("Student_Email");
				out.println(E);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
