package org.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.model.StudentModel;
import org.service.StudentService;


@WebServlet("/addStudent")
public class AddStudentServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name =
				request.getParameter("studentName");

				String email =
				request.getParameter("email");

				String password =
				request.getParameter("password");

				String course =
				request.getParameter("course");

				String mobile =
				request.getParameter("mobile");

				StudentModel model =
				new StudentModel();

				model.setStudentName(name);

				model.setEmail(email);

				model.setPassword(password);
				
				model.setCourse(course);

				model.setMobile(mobile);

				StudentService service =
				new StudentService();

				boolean status =
				service.addStudent(model);

				if(status) {

					response.sendRedirect(
					"AddStudent.html?success=Student Added");
				}
				else {

					response.sendRedirect(
					"AddStudent.html?error=Student Not Added");
				}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
