package org.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.model.StudentModel;
import org.service.StudentService;


/*@WebServlet("/updateStudent")
public class UpdateStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id =
				Integer.parseInt(request.getParameter("id"));

				StudentService service =
						new StudentService();

				StudentModel s =
				service.getStudentById(id);

				response.setContentType("text/html");

				response.getWriter().println(
				getForm(s)
				);
				}

				/* SHOW FORM */
				/*private String getForm(StudentModel s) {

				return "<html><body>" +
				"<h2>Update Student</h2>" +
				"<form method='post' action='updateStudent'>" +

				"<input type='hidden' name='id' value='" + s.getStudentId() + "'/>" +

				"Name: <input name='name' value='" + s.getStudentName() + "'/><br><br>" +
				"Email: <input name='email' value='" + s.getEmail() + "'/><br><br>" +
				"Course: <input name='course' value='" + s.getCourse() + "'/><br><br>" +
				"Mobile: <input name='mobile' value='" + s.getMobile() + "'/><br><br>" +

				"<button type='submit'>Update</button>" +
				"</form></body></html>";
				}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id =
				Integer.parseInt(request.getParameter("id"));

				String name =
				request.getParameter("name");

				String email =
				request.getParameter("email");

				String course =
				request.getParameter("course");

				String mobile =
				request.getParameter("mobile");

				StudentService service =
				new StudentService();

				boolean status =
				service.updateStudent(id, name, email, course, mobile);

				if(status) {
				response.sendRedirect("viewStudents");
				} else {
				response.getWriter().println("Update Failed");
				}
				}


}*/


@WebServlet("/updateStudent")
public class UpdateStudentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	
	
	// =========================
	// SHOW UPDATE FORM
	// =========================

	protected void doGet(
	HttpServletRequest request,
	HttpServletResponse response)

	throws ServletException, IOException {

		int id =
		Integer.parseInt(
		request.getParameter("id"));

		StudentService service =
		new StudentService();

		StudentModel s =
		service.getStudentById(id);

		response.setContentType(
		"text/html");

		response.getWriter().println(
		getForm(s));
	}

	
	
	// =========================
	// UPDATE STUDENT
	// =========================

	protected void doPost(
	HttpServletRequest request,
	HttpServletResponse response)

	throws ServletException, IOException {

		int id =
		Integer.parseInt(
		request.getParameter("id"));

		String name =
		request.getParameter("name");

		String email =
		request.getParameter("email");

		String course =
		request.getParameter("course");

		String mobile =
		request.getParameter("mobile");

		StudentService service =
		new StudentService();

		boolean status =
		service.updateStudent(
		id,
		name,
		email,
		course,
		mobile);

		if(status) {

			response.sendRedirect(
			"viewStudents");

		} else {

			response.getWriter().println(
			"<h2 style='color:red;text-align:center;'>Update Failed</h2>");
		}
	}

	
	
	// =========================
	// FORM UI
	// =========================

	private String getForm(StudentModel s) {

		return

		"<!DOCTYPE html>"

		+ "<html>"

		+ "<head>"

		+ "<meta charset='UTF-8'>"

		+ "<meta name='viewport' content='width=device-width, initial-scale=1.0'>"

		+ "<title>Update Student</title>"

		+ "<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>"

		+ "<style>"

		+ "body{"

		+ "background:linear-gradient(to right,#1e3c72,#2a5298);"

		+ "height:100vh;"

		+ "display:flex;"

		+ "justify-content:center;"

		+ "align-items:center;"

		+ "font-family:Arial;"

		+ "}"

		+ ".card-box{"

		+ "width:500px;"

		+ "background:white;"

		+ "padding:35px;"

		+ "border-radius:15px;"

		+ "box-shadow:0 10px 30px rgba(0,0,0,0.3);"

		+ "}"

		+ ".title{"

		+ "text-align:center;"

		+ "margin-bottom:25px;"

		+ "font-weight:bold;"

		+ "color:#1e3c72;"

		+ "}"

		+ ".btn-update{"

		+ "background:#1e3c72;"

		+ "color:white;"

		+ "font-weight:bold;"

		+ "}"

		+ ".btn-update:hover{"

		+ "background:#16325c;"

		+ "}"

		+ "</style>"

		+ "</head>"

		+ "<body>"

		+ "<div class='card-box'>"

		+ "<h2 class='title'>Update Student</h2>"

		+ "<form method='post' action='updateStudent'>"

		
		
		// Hidden ID

		+ "<input type='hidden' name='id' value='" + s.getStudentId() + "'/>"

		
		
		// Name

		+ "<div class='mb-3'>"

		+ "<label class='form-label'>Student Name</label>"

		+ "<input type='text' class='form-control' "

		+ "name='name' value='" + s.getStudentName() + "' required>"

		+ "</div>"

		
		
		// Email

		+ "<div class='mb-3'>"

		+ "<label class='form-label'>Email</label>"

		+ "<input type='email' class='form-control' "

		+ "name='email' value='" + s.getEmail() + "' required>"

		+ "</div>"

		
		
		// Course

		+ "<div class='mb-3'>"

		+ "<label class='form-label'>Course</label>"

		+ "<input type='text' class='form-control' "

		+ "name='course' value='" + s.getCourse() + "' required>"

		+ "</div>"

		
		
		// Mobile

		+ "<div class='mb-3'>"

		+ "<label class='form-label'>Mobile</label>"

		+ "<input type='text' class='form-control' "

		+ "name='mobile' value='" + s.getMobile() + "' required>"

		+ "</div>"

		
		
		// Buttons

		+ "<div class='d-grid gap-2'>"

		+ "<button type='submit' class='btn btn-update'>"

		+ "Update Student"

		+ "</button>"

		
		
		+ "<a href='viewStudents' class='btn btn-secondary'>"

		+ "Back"

		+ "</a>"

		+ "</div>"

		
		
		+ "</form>"

		+ "</div>"

		+ "</body>"

		+ "</html>";
	}
}


