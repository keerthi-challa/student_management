package com.Student.jdbc;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.List;

import javax.sql.DataSource;

/**
 * Servlet implementation class StudentServlet
 */
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name = "jdbc/StudentDB")
	private DataSource dataSource;

	private DBConnection dbConnection;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		dbConnection = new DBConnection(dataSource);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String receivedCommand = request.getParameter("command");
		
		if(receivedCommand == null) {
			receivedCommand = "LIST";
		}
		
		switch(receivedCommand) {
		case "LIST":{
			listStudent(request, response);
			break;
		}
		
		case "ADD":{
			AddStudent(request, response);
			break;
		}
		
		default:{
			listStudent(request, response);
			break;
		}
		}
		
		
	}

	private void AddStudent(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String ID = request.getParameter("id");
		String Name = request.getParameter("name");
		String Gender = request.getParameter("gender");
		String Email = request.getParameter("email");
		String Phone = request.getParameter("phone");
		int Id = Integer.parseInt(ID);
		
		SettersGetters newStudent = new SettersGetters(Id, Name, Gender, Email, Phone);
		
		dbConnection.addStudent(newStudent);
		
		
	}

	private void listStudent(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// TODO Auto-generated method stub

		List<SettersGetters> students = dbConnection.getStudents();

		request.setAttribute("Student_list", students);

		RequestDispatcher dispacher = request.getRequestDispatcher("/index.jsp");
		dispacher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
