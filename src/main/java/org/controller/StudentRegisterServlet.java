package org.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.model.StudentModel;
import org.service.StudentService;

@WebServlet("/register")
public class StudentRegisterServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("studentId");

		String name = request.getParameter("studentName");

		String email = request.getParameter("email");

		String mobile = request.getParameter("mobile");

		String course = request.getParameter("course");

		String password = request.getParameter("password");

		StudentModel model = new StudentModel();

		model.setStudentName(name);
		model.setEmail(email);
		model.setMobile(mobile);
		model.setCourse(course);
		model.setPassword(password);

		StudentService service = new StudentService();

		boolean status = service.addStudent(model);

		if (status) {

			response.sendRedirect("Registration.html?msg=Success");

		} else {

			response.sendRedirect("Registration.html?msg=Failed");

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
